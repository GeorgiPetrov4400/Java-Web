package com.resellerapp.service.impl;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ConditionService conditionService;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, ConditionService conditionService, CurrentUser currentUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.conditionService = conditionService;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(offerServiceModel, Offer.class);
        offer.setUser(userService.findById(currentUser.getId()));
        offer.setCondition(conditionService.findByConditionNameEnum(offerServiceModel.getCondition()));

        offerRepository.save(offer);
    }

    @Override
    public List<OfferDTO> getOfferOwnedBy(long currentUserId) {
        return offerRepository.findByUserId(currentUserId)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOfferNotOwnedBy(long currentUserId) {
        return offerRepository.findByUserIdNot(currentUserId)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeOfferById(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferDTO> getBoughtItemsBy(long currentUserId) {
        return offerRepository.findByUserId(currentUserId)
                .stream()
                .map((boughtItem -> modelMapper.map(boughtItem, OfferDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> getOfferWithoutBuyer(Long id) {
        return offerRepository.findAllByBuyer_IdIsNullAndUserIdNot(id);
    }

    @Override
    public void buyOffer(Long offerId, Long currentUserId) {
        Offer offer = offerRepository.findById(offerId).orElse(null);

        User user = userRepository.findById(currentUserId).orElse(null);

        user.getBoughtOffers().add(offer);
        offer.setUser(user);
        this.offerRepository.saveAndFlush(offer);
        this.userRepository.save(user);
    }

}

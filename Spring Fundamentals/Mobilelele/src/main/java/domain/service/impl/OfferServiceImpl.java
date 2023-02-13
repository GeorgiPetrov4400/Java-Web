package domain.service.impl;

import domain.models.dtos.AddOfferDTO;
import domain.models.entities.Model;
import domain.models.entities.Offer;
import domain.models.entities.User;
import domain.models.mapper.OfferMapper;
import domain.repository.ModelRepository;
import domain.repository.OfferRepository;
import domain.repository.UserRepository;
import domain.service.OfferService;
import domain.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;
    private final OfferMapper offerMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository, CurrentUser currentUser, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
        this.offerMapper = offerMapper;
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer newOffer = offerMapper.addOfferDtoToOffer(addOfferDTO);

        //TODO - current user should be logged in

        User seller = userRepository.findByUsername(currentUser.getName()).orElseThrow();

        Model model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }
}

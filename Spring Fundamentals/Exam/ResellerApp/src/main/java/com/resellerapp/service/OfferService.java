package com.resellerapp.service;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    void addOffer(OfferServiceModel offerServiceModel);

    List<OfferDTO> getOfferOwnedBy(long currentUserId);

    List<OfferDTO> getOfferNotOwnedBy(long currentUserId);

    void removeOfferById(Long id);

    List<OfferDTO> getBoughtItemsBy(long currentUserId);

    List<Offer> getOfferWithoutBuyer(Long id);

    void buyOffer(Long offerId, Long currentUserId);
}

package com.resellerapp.controller;

import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OfferService offerService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OfferService offerService, UserService userService) {
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "redirect:/";
        }

        model.addAttribute("currentUser", currentUser);
        List<Offer> currentUserOffers = this.userService.getCurrentUserOffers();
        model.addAttribute("currentUserOffers", currentUserOffers);

//        List<Offer> currentUserBoughtOffers = this.userService.getCurrentUserBoughtOffers();
//        model.addAttribute("currentUserBoughtOffers", currentUserBoughtOffers);

        List<Offer> offerWithOutBuyer = this.offerService.getOfferWithoutBuyer(currentUser.getId());
        model.addAttribute("othersUsersOffers", offerWithOutBuyer);


//        long currentUserId = currentUser.getId();
//
//        List<OfferDTO> ownOffers = this.offerService.getOfferOwnedBy(currentUserId);
//        List<OfferDTO> otherOffers = this.offerService.getOfferNotOwnedBy(currentUserId);
//
//        model.addAttribute("ownOffers", ownOffers);
//        model.addAttribute("otherOffers", otherOffers);
//
        List<OfferDTO> boughtItem = this.offerService.getBoughtItemsBy(currentUser.getId());
        model.addAttribute("boughtItem", boughtItem);

        return "home";
    }

    @GetMapping("/buy/{id}")
    public String buyOfferFromUser(@PathVariable Long id) {

        this.offerService.buyOffer(id, currentUser.getId());

        return "redirect:/home";
    }
}

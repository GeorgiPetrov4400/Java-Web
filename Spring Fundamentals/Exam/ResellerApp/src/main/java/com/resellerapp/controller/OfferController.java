package com.resellerapp.controller;

import com.resellerapp.model.dto.OfferAddDTO;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public OfferController(OfferService offerService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public OfferAddDTO offerAddDTO() {
        return new OfferAddDTO();
    }

    @GetMapping("/add")
    public String add() {
        if (currentUser.getId() == null) {
            return "redirect:/users/login";
        }

        return "offer-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid OfferAddDTO offerAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (currentUser.getId() == null) {
            return "redirect:/";

        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDTO", offerAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO",
                            bindingResult);

            return "redirect:add";
        }

        offerService.addOffer(modelMapper.map(offerAddDTO, OfferServiceModel.class));

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable Long id) {
        offerService.removeOfferById(id);

        return "redirect:/home";
    }

}

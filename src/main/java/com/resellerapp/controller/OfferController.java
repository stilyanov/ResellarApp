package com.resellerapp.controller;

import com.resellerapp.config.UserSession;
import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final UserSession userSession;
    private final OfferService offerService;

    public OfferController(UserSession userSession, OfferService offerService) {
        this.userSession = userSession;
        this.offerService = offerService;
    }

    @ModelAttribute("offerData")
    public AddOfferDTO offerDTO() {
        return new AddOfferDTO();
    }

    @ModelAttribute("conditions")
    public ConditionEnum[] conditions() {
        return ConditionEnum.values();
    }

    @GetMapping("/add")
    public String addOffer(){
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "offer-add";
    }

    @PostMapping("/add")
    public String doAddOffer(@Valid AddOfferDTO offerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerData", offerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerData", bindingResult);

            return "redirect:/offers/add";
        }

        boolean success = offerService.addOffer(offerDTO);

        if (!success) {
            redirectAttributes.addFlashAttribute("offerData", offerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerData", bindingResult);
            return "redirect:/offers/add";
        }

        return "redirect:/home";
    }

}

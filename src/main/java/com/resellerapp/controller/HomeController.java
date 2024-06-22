package com.resellerapp.controller;

import com.resellerapp.config.UserSession;
import com.resellerapp.model.dto.UserOfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final OfferService offerService;

    public HomeController(UserSession userSession, OfferService offerService) {
        this.userSession = userSession;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedIndex(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        List<Offer> allOffersWithSeller = this.offerService.getOffersFromCurrentUser();

        model.addAttribute("allOffers", allOffersWithSeller);

        return "home";
    }

}

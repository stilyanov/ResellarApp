package com.resellerapp.service;

import com.resellerapp.config.UserSession;
import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;
    private final UserSession userSession;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, ConditionRepository conditionRepository, UserSession userSession) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.userSession = userSession;
    }

    public boolean addOffer(AddOfferDTO offerDTO) {
        if (!userSession.isLoggedIn()) {
            return false;
        }

        Optional<Condition> condition = this.conditionRepository.findByName(offerDTO.getCondition());

        if (condition.isEmpty()) {
            return false;
        }

        Optional<User> optionalUser = this.userRepository.findByUsername(this.userSession.username());

        if (optionalUser.isEmpty()) {
            return false;
        }

        Offer offer = new Offer();
        offer.setDescription(offerDTO.getDescription());
        offer.setPrice(offerDTO.getPrice());
        offer.setCondition(condition.get());
        offer.setSeller(optionalUser.get());

        this.offerRepository.save(offer);

        return true;

    }
}

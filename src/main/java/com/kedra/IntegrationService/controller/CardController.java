package com.kedra.IntegrationService.controller;

import com.kedra.IntegrationService.model.CardDTO;
import com.kedra.IntegrationService.model.CardModel;
import com.kedra.IntegrationService.service.CardService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("user")
    public String validateUser() {
        return "HELLO USER";
    }
    @GetMapping("search")
    public ResponseEntity<String> searchByName(@RequestParam("name") String name) throws IOException {
        try {
            return cardService.searchCardByName(name);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("collection/add")
    public ResponseEntity<String> addCardToCollection(@RequestBody CardDTO cardDTO) {
        return cardService.addCardToCollection(cardDTO.getCard_name(), cardDTO.getImg_url(), cardDTO.getScryfall_id(), cardDTO.getUsername());
    }

    @GetMapping("collection/get/{username}")
    public ResponseEntity<List<CardModel>> getCardsFromUserCollection(@PathVariable String username) {
        return cardService.getCardCollectionBYUsername(username);
    }
}

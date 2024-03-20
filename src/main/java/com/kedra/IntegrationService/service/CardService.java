package com.kedra.IntegrationService.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedra.AuthorizationService.models.UserEntity;
import com.kedra.AuthorizationService.repository.UserRepository;
import com.kedra.IntegrationService.model.CardModel;
import com.kedra.IntegrationService.repository.CardRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CardService {

    private CardRepository cardRepository;
    private UserRepository userRepository;
    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }
    public ResponseEntity<String> searchCardByName(String name) throws IOException, JSONException {
        URL urlObj = new URL("https://api.scryfall.com/cards/search?q=" + name);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    public ResponseEntity<String> addCardToCollection(String cardName, URL imgUrl, int scryfall_id, String username) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("user doesnt exits"));
        CardModel cardFromRepo = cardRepository.findByScryfallId(scryfall_id).orElse(null);

        if(cardFromRepo == null) {
            cardFromRepo = CardModel.builder()
                    .cardName(cardName)
                    .img_url(imgUrl)
                    .scryfallId(scryfall_id)
                    .build();
            cardRepository.save(cardFromRepo);
        }

        if(user.getCards().contains(cardFromRepo)) {
            return new ResponseEntity<>("You already have this card in your collection!", HttpStatus.BAD_REQUEST);
        }

        user.getCards().add(cardFromRepo);
        userRepository.save(user);
        return new ResponseEntity<>("Card has been added successfully to your collection!", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<CardModel>> getCardCollectionBYUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("user doesnt exits"));
        return new ResponseEntity<>(user.getCards(), HttpStatus.FOUND);
    }
}

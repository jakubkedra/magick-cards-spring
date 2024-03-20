package com.kedra.IntegrationService.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedra.IntegrationService.model.CardModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class CardService {
    public ResponseEntity<List<CardModel>> searchCardByName(String name) throws IOException {
        URL urlObj = new URL("https://restcountries.com/v3.1/name/France");
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        //System.out.println("Response CODE: " + responseCode);

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            //System.out.println(sb);
            ObjectMapper objectMapper = new ObjectMapper();
            List<CardModel> cards = objectMapper.readValue(String.valueOf(sb), new TypeReference<List<CardModel>>() {});
            return new ResponseEntity<>(cards, HttpStatus.FOUND);

        } else {
            return new ResponseEntity<>(null, HttpStatus.REQUEST_TIMEOUT);
        }
    }
}

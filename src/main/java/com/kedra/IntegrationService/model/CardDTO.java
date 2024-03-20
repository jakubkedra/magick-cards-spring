package com.kedra.IntegrationService.model;

import lombok.Data;

import java.net.URL;

@Data
public class CardDTO {
    private String scryfall_id;
    private String card_name;
    private URL img_url;
    private String username;
}

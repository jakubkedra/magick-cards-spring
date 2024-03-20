package com.kedra.IntegrationService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Table(name = "cards")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int db_id;
    private int scryfall_id;

    private String card_name;
    private URL img_url;
}

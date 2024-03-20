package com.kedra.IntegrationService.model;

import com.kedra.AuthorizationService.models.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String scryfallId;

    private String cardName;
    private URL img_url;

    @ManyToMany(mappedBy = "cards")
    private List<UserEntity> users = new ArrayList<>();
}

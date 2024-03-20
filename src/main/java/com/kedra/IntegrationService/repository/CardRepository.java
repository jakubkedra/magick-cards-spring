package com.kedra.IntegrationService.repository;

import com.kedra.AuthorizationService.models.Role;
import com.kedra.IntegrationService.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardModel, Integer> {
    Boolean existsByCardName(String cardName);
    Optional<CardModel> findByScryfallId(int scryfallId);
    Optional<CardModel> findByUsersId(int userId);
    Optional<CardModel> findByUsersUsername(String username);
}

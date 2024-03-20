package com.kedra.AuthorizationService.repository;

import com.kedra.AuthorizationService.models.UserEntity;
import com.kedra.IntegrationService.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}

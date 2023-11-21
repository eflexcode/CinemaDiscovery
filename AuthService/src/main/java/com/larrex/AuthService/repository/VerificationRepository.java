package com.larrex.AuthService.repository;

import com.larrex.AuthService.entity.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends MongoRepository<VerificationToken, String> {

    Optional<VerificationToken> findByToken(String token);

}

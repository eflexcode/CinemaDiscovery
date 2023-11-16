package com.larrex.AuthService.repository;

import com.larrex.AuthService.entity.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema,String> {
}

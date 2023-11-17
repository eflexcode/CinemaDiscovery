package com.larrex.AccountServer.repository;

import com.larrex.AccountServer.entity.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema,String> {
}

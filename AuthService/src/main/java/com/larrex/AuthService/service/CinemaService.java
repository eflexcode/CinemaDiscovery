package com.larrex.AuthService.service;

import com.larrex.AuthService.entity.Cinema;
import com.larrex.AuthService.model.CinemaModel;

public interface CinemaService {

    Cinema createCinema(CinemaModel cinemaModel);
    Cinema updateCinema(CinemaModel cinemaModel);
    Cinema getCinemaById(CinemaModel cinemaModel,String organisationId);
    void deleteCinema(String organisationId);

}

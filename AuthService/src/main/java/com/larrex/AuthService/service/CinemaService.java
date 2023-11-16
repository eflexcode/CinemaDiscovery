package com.larrex.AuthService.service;

import com.larrex.AuthService.entity.Address;
import com.larrex.AuthService.entity.Cinema;
import com.larrex.AuthService.model.CinemaModel;

public interface CinemaService {

    Cinema createCinema(CinemaModel cinemaModel);
    Cinema updateCinema(CinemaModel cinemaModel,String organisationId);
    Cinema updateLocation(Address address,String organisationId);
    Cinema getCinemaById(String organisationId);
    void deleteCinema(String organisationId);

}

package com.larrex.AccountServer.service;

import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.entity.Cinema;
import com.larrex.AccountServer.model.CinemaModel;

public interface CinemaService {

    Cinema createCinema(CinemaModel cinemaModel);
    Cinema updateCinema(CinemaModel cinemaModel,String organisationId);
    Cinema updateLocation(Address address, String organisationId);
    Cinema getCinemaById(String organisationId);
    void deleteCinema(String organisationId);

}

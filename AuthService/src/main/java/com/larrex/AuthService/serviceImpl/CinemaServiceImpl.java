package com.larrex.AuthService.serviceImpl;

import com.larrex.AuthService.entity.Cinema;
import com.larrex.AuthService.model.CinemaModel;
import com.larrex.AuthService.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    @Override
    public Cinema createCinema(CinemaModel cinemaModel) {
        return null;
    }

    @Override
    public Cinema updateCinema(CinemaModel cinemaModel) {
        return null;
    }

    @Override
    public Cinema getCinemaById(CinemaModel cinemaModel, String organisationId) {
        return null;
    }

    @Override
    public void deleteCinema(String organisationId) {

    }
}

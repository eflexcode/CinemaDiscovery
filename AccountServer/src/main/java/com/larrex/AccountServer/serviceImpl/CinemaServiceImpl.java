package com.larrex.AccountServer.serviceImpl;

import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.entity.Cinema;
import com.larrex.AccountServer.model.CinemaModel;
import com.larrex.AccountServer.service.CinemaService;
import com.larrex.AccountServer.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public Cinema createCinema(CinemaModel cinemaModel) {
        Date date = new Date();
        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(cinemaModel, cinema);
        cinema.setCreatedAt(date);
        cinema.setUpdateAt(date);
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateLocation(Address address, String organisationId) {

        Cinema cinema = getCinemaById(organisationId);
        cinema.setAddress(address);
        cinema.setUpdateAt(new Date());

        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(CinemaModel cinemaModel, String organisationId) {

        Cinema cinema = getCinemaById(organisationId);
        cinema.setFullAddress(cinemaModel.getFullAddress() != null ? cinemaModel.getFullAddress() : cinema.getFullAddress());
        cinema.setImageUrl(cinemaModel.getImageUrl() != null ? cinemaModel.getImageUrl() : cinema.getImageUrl());
        cinema.setOpenAt(cinemaModel.getOpenAt() != null ? cinemaModel.getOpenAt() : cinema.getOpenAt());
        cinema.setEmail(cinemaModel.getEmail() != null ? cinemaModel.getEmail() : cinema.getEmail());
        cinema.setOrganisationDescription(cinemaModel.getOrganisationDescription() != null ? cinemaModel.getOrganisationDescription() : cinema.getOrganisationDescription());
        cinema.setOrganisationName(cinemaModel.getOrganisationName() != null ? cinemaModel.getOrganisationName() : cinema.getOrganisationName());
        cinema.setUpdateAt(new Date());

        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema getCinemaById(String organisationId) {
        return cinemaRepository.findById(organisationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Cinema with id"));
    }

    @Override
    public void deleteCinema(String organisationId) {
        cinemaRepository.deleteById(organisationId);
    }
}

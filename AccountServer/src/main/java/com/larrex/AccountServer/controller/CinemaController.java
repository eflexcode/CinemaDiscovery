package com.larrex.AccountServer.controller;

import com.larrex.AccountServer.entity.Cinema;
import com.larrex.AccountServer.entity.Address;
import com.larrex.AccountServer.model.CinemaModel;
import com.larrex.AccountServer.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema/")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema createCinema(@RequestBody CinemaModel cinemaModel) {
        return cinemaService.createCinema(cinemaModel);
    }

    @PutMapping("{organization_id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateCinema(@PathVariable(name = "organization_id") String organization_id, @RequestBody CinemaModel cinemaModel) {
        return cinemaService.updateCinema(cinemaModel, organization_id);
    }

    @PutMapping("location/{organization_id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema updateLocation(@PathVariable(name = "organization_id") String organization_id, @RequestBody Address address) {
        return cinemaService.updateLocation(address, organization_id);
    }

    @GetMapping("{organization_id}")
    @ResponseStatus(HttpStatus.OK)
    public Cinema getCinemaById(@PathVariable(name = "organization_id") String organization_id) {
        return cinemaService.getCinemaById(organization_id);
    }

    @DeleteMapping("{organization_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCinemaById(@PathVariable(name = "organization_id") String organization_id) {
       cinemaService.deleteCinema(organization_id);
    }

}

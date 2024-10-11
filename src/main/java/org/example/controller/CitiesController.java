package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.service.CityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CitiesController {
    private final CityService cityService;

    @PostMapping("/save")
    public CityResponse save(@RequestBody CityRequest cityRequest) {
        return cityService.saveCity(cityRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        cityService.deleteCity(id);
    }
}

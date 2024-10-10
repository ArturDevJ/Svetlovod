package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.service.CityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CitiesController {
    private final CityService cityService;

    @PostMapping("/save")
    public CityResponse save(@RequestBody CityRequest cityRequest) {
        return cityService.saveCity(cityRequest);
    }
}

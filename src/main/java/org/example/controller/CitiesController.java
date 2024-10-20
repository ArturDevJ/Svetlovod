package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.repository.CityRepository;
import org.example.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CitiesController {
    private final CityService cityService;
    private final CityRepository cityRepository;

    @PostMapping
    public CityResponse save(@RequestBody CityRequest cityRequest) {
        return cityService.saveCity(cityRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        cityService.deleteCity(id);
    }

    @GetMapping
    public List<CityResponse> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public CityResponse get(@PathVariable(value = "id") Long id) {
        return cityService.getCity(id);
    }

    @PutMapping("/{id}")
    public CityResponse update(@RequestBody CityRequest cityRequest,@PathVariable(value = "id") Long id) {
        return cityService.updateCity(cityRequest, id);
    }
}

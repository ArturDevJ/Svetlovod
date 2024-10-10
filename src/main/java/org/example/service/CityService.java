package org.example.service;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.model.CityEntity;
import org.example.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public CityResponse saveCity(CityRequest cityRequest) {
        CityEntity city = new CityEntity();
        city.setName(cityRequest.getName());
        city = cityRepository.save(city);
        CityResponse cityResponse = new CityResponse();
        cityResponse.setId(city.getId());
        cityResponse.setName(city.getName());
        return cityResponse;
    }
}

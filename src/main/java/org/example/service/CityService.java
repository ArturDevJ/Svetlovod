package org.example.service;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.exception.SvetlovodException;
import org.example.model.CityEntity;
import org.example.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void deleteCity(Long id) {
        cityRepository.findById(id).orElseThrow(() ->
                new SvetlovodException("EMPTY_CITY", "НЕТ ГОРОДА", HttpStatus.NOT_FOUND));
        CityEntity city = cityRepository.getReferenceById(id);
        cityRepository.delete(city);
    }

    public CityResponse getCity(Long id) {
        Optional<CityEntity> optionalCityEntity = cityRepository.findById(id);
        if (optionalCityEntity.isEmpty()) {
            throw new SvetlovodException("EMPTY_CITY", "НЕТ ГОРОДА", HttpStatus.NOT_FOUND);
        }
        CityEntity city = optionalCityEntity.get();
        CityResponse cityResponse = new CityResponse();
        cityResponse.setId(city.getId());
        cityResponse.setName(city.getName());
        return cityResponse;
    }

    public List<CityResponse> getAll() {
        List<CityResponse> response = new ArrayList<>();
        List<CityEntity> list = cityRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            CityResponse cityResponse = new CityResponse();
            response.add(cityResponse);
        }
        return response;
    }

    public CityResponse updateCity(CityRequest cityRequest, Long id) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(id);
        cityEntity.setName(cityRequest.getName());
        cityEntity = cityRepository.save(cityEntity);
        CityResponse cityResponse = new CityResponse();
        cityResponse.setId(cityEntity.getId());
        cityResponse.setName(cityEntity.getName());
        return cityResponse;
    }
}

package org.example.service;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.CityRequest;
import org.example.dto.response.CityResponse;
import org.example.exception.SvetlovodException;
import org.example.model.CityEntity;
import org.example.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}

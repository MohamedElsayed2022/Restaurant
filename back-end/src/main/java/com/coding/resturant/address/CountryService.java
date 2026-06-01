package com.coding.resturant.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    public List<Country> getAllCountries() {
        return  countryRepository.findAll();
    }

}

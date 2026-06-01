package com.coding.resturant.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StateService {
    private final StateRepository stateRepository;
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }
    public List<State> getStatesByCountryCode (String code) {
        return stateRepository.findByCountryCode(code);
    }
}

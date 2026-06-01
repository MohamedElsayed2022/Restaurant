package com.coding.resturant.address;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class StateController {
    private final StateService stateService;

    @GetMapping("allStates")
    public List<State> getAllStates(){
        return stateService.getAllStates();
    }
    @GetMapping("statescode")
    public List<State> getStatesByCode(@RequestParam String code) {
        return stateService.getStatesByCountryCode(code);
    }
}

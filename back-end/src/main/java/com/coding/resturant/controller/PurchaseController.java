package com.coding.resturant.controller;
import com.coding.resturant.dto.PurchaseRequest;
import com.coding.resturant.dto.PurchaseResponse;
import com.coding.resturant.model.Client;
import com.coding.resturant.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/buy/")
public class PurchaseController {
    private  PurchaseService purchaseService;

    @PostMapping("purchase")
    public PurchaseResponse addRequestOrder(@RequestBody PurchaseRequest purchaseRequest) {
       return purchaseService.addRequestOrder(purchaseRequest);

    }
}

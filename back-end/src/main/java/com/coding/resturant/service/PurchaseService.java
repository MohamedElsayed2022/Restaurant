package com.coding.resturant.service;

import com.coding.resturant.dto.PurchaseRequest;
import com.coding.resturant.dto.PurchaseResponse;
import com.coding.resturant.model.Client;
import com.coding.resturant.model.RequestOrder;

import java.util.List;

public interface PurchaseService {
    public PurchaseResponse addRequestOrder(PurchaseRequest purchase);
}

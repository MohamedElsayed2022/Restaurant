package com.coding.resturant.purchase;

import com.coding.resturant.purchase.dto.PurchaseRequest;
import com.coding.resturant.purchase.dto.PurchaseResponse;

public interface PurchaseService {
    public PurchaseResponse addRequestOrder(PurchaseRequest purchase);
}

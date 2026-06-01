package com.coding.resturant.purchase.dto;


import com.coding.resturant.address.Address;
import com.coding.resturant.user.Client;
import com.coding.resturant.purchase.Item;
import com.coding.resturant.purchase.RequestOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Client client;
    private RequestOrder requestOrder;
    private List<Item> items = new ArrayList<>() ;
    private Address fromAddress;
    private Address toAddress;
}

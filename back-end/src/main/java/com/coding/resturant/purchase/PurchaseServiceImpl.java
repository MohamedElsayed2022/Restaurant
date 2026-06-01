package com.coding.resturant.purchase;

import com.coding.resturant.purchase.dto.PurchaseRequest;
import com.coding.resturant.purchase.dto.PurchaseResponse;
import com.coding.resturant.user.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private ClientRepository clientRepository;
    @Override
    @Transactional
    public PurchaseResponse addRequestOrder(PurchaseRequest purchase) {
        //[1]
        RequestOrder requestOrder = purchase.getRequestOrder();
        String myCode = getCode();
        requestOrder.setCode(myCode);
        //[2]

        List<Item> items = purchase.getItems();
        items.forEach(requestOrder::addItem);
        //[3]
        requestOrder.setFromAddress(purchase.getFromAddress());
        requestOrder.setToAddress(purchase.getToAddress());
        //[4]
        purchase.getClient().addRequestOrder(requestOrder);
        clientRepository.save(purchase.getClient());

        return new PurchaseResponse(purchase.getClient().getName() , myCode);
    }

    private String getCode() {
        return UUID.randomUUID().toString();
    }
}

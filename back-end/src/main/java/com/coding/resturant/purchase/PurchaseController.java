package com.coding.resturant.purchase;
import com.coding.resturant.purchase.dto.PurchaseRequest;
import com.coding.resturant.purchase.dto.PurchaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

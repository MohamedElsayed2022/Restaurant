package com.coding.resturant.purchase;

import com.coding.resturant.address.Address;
import com.coding.resturant.user.Client;
import com.coding.resturant.product.CategoryOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request_order")
public class RequestOrder extends CategoryOrder {
    @Column(name = "code")
    private String code;
    @Column(name = "note")
    @Lob
    private String note ;
    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "total_quantity")
    private int totalQuantity;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "requestOrder")
    private List<Item> items = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_address_id" , referencedColumnName = "id")
    private Address toAddress ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_address_id" , referencedColumnName = "id")
    private Address fromAddress;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client = new Client();

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        item.setRequestOrder(this);
    }


}

package com.coding.resturant.purchase;

import com.coding.resturant.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends BaseEntity {
    @Column(name = "image")
    private String img;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "request_order_id")
    private RequestOrder requestOrder;

}

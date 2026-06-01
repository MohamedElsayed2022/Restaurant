package com.coding.resturant.base;

import com.coding.resturant.product.CategoryOrder;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class PublicData extends CategoryOrder {

    @Column(name = "name")
    private String name;


}

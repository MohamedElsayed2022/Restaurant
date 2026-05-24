package com.coding.resturant.model;

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

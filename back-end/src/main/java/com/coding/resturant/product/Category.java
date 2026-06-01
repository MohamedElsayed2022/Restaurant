package com.coding.resturant.product;

import com.coding.resturant.base.PublicData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category extends PublicData {

    @Column(name = "category_logo")
    private String logo;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}

package com.coding.resturant.product;

import com.coding.resturant.base.PublicData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends PublicData {

    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id")
    )
    private List<String>  img;
    @Column(name = "description")
   // @Lob
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



}

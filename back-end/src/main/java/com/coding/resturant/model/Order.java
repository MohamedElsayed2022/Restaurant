package com.coding.resturant.model;

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
@Table(name = "orders")
public class Order extends PublicData {

    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_images",
            joinColumns = @JoinColumn(name = "order_id")
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

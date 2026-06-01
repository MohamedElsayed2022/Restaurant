package com.coding.resturant.auth;

import com.coding.resturant.product.CategoryOrder;
import com.coding.resturant.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token extends CategoryOrder {

    private String token;


    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

}

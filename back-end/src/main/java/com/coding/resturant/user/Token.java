package com.coding.resturant.user;

import com.coding.resturant.model.CategoryOrder;
import com.coding.resturant.model.User;
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

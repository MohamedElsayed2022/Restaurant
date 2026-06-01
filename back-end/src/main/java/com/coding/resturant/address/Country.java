package com.coding.resturant.address;

import com.coding.resturant.base.PublicData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "country")
public class Country extends PublicData {

    @Column(name = "code")
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<State> states;
}

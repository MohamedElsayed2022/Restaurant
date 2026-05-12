package com.coding.resturant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "authorities")
public class Authorities extends BaseEntity{
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users = new ArrayList<>();
}

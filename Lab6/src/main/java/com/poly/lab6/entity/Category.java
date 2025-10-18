package com.poly.lab6.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    String id;
    String name;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
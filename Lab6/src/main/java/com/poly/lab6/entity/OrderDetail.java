package com.poly.lab6.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "Orderdetails")public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    Integer quantity; 
    
    @ManyToOne 
    @JoinColumn(name = "Productid") 
    Product product; 
    
    @ManyToOne 
    @JoinColumn(name = "Orderid")
    Order order; 
}
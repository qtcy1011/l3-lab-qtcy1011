package com.poly.lab6.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity @Table(name = "Accounts")
public class Account implements Serializable{
    @Id
    String username;
    String password;
    String fullname;
    String email;
    String photo;
    boolean activated;
    boolean admin;
    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
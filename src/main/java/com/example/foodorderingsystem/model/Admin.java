package com.example.foodorderingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="email_id")
    private String emailid;

    @Column(name="password")
    private String password;

    @Column(name="mobile_no")
    private String mobileno;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="pincode")
    private String pincode;
}

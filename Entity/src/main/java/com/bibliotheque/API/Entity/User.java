package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String name ;
    public String email;
    public String password;
    private String token;
    private boolean admin;

}


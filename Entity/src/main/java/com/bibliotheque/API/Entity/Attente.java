package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attente")
@Data
public class Attente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public Date dateMail;
    public int numberCustomer;
    public String edition;

    @ManyToOne
    private Book book;



}

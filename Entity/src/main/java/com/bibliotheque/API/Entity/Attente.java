package com.bibliotheque.API.Entity;

import lombok.*;

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
    public Boolean mail;
    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne
    private Edition edition;


}

package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")
@Data
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public boolean available;

    @ManyToOne
    private Edition edition;


}

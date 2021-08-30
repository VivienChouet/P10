package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "edition")
@Data
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;

    @ManyToOne
    public Book book;

}

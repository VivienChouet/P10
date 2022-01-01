package com.bibliotheque.API.Entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "book")
@Data
public class Book {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String title;
    public String author;
    public Date publication;
    public String resume;


 }




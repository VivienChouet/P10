package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

import java.awt.print.Book;

@Data
public class EditionDTO {

    private int id;
    private String name;
    private BookDTO book;
}

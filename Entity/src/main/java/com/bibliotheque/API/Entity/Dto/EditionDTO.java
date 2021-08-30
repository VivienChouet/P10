package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Book;
import lombok.Data;


@Data
public class EditionDTO {

    private int id;
    public String name;
    private BookDTO book;

}

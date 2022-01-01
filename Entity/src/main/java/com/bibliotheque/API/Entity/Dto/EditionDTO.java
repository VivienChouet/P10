package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Book;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class EditionDTO {

    private int id;
    public String name;
    private BookDTO book;

}

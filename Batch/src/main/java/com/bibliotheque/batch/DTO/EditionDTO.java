package com.bibliotheque.batch.DTO;

import lombok.Data;

@Data
public class EditionDTO {

    private int id;
    public String name;
    private BookDTO book;

}
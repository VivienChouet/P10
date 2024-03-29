package com.bibliotheque.API.Entity.Dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class BookDTO {

    private int id;
    private String title;
    private String author;
    private Date publication;
    private String resume;
}

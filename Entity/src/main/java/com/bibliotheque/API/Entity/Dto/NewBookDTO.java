package com.bibliotheque.API.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewBookDTO {

    private String title;
    private String author;
    private Date publication;
    private String resume;
}

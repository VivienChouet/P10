package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

@Data
public class NewBookDTO {

    private String title;
    private String author;
    private String publication;
    private String resume;
}

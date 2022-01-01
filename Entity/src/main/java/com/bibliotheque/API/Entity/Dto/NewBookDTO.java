package com.bibliotheque.API.Entity.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewBookDTO {

    private String title;
    private String author;
    private Date publication;
    private String resume;
}

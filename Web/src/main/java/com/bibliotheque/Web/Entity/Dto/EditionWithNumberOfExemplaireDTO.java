package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

@Data
public class EditionWithNumberOfExemplaireDTO {

    private String editionName;
    private int book_id;
    private int edition_id;
    private int numberOfEditionAvailable;
    private int numberOfEditionTotal;
    private int attenteAvailable;
}

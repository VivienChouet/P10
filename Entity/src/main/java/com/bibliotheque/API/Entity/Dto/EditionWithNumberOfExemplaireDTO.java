package com.bibliotheque.API.Entity.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EditionWithNumberOfExemplaireDTO {

    private String editionName;
    private int book_id;
    private int edition_id;
    private int numberOfEditionAvailable;
    private int numberOfEditionTotal;
    private int attenteAvailable;

}


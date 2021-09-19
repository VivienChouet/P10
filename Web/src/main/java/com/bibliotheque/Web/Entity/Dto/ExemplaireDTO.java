package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;


@Data
public class ExemplaireDTO {

    private int id;
    public boolean available;
    private EditionDTO edition;

}

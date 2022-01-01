package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;


@Data
public class ExemplaireDTO {
    public int id;
    public boolean available;
    private EditionDTO edition;
}

package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Edition;
import lombok.Data;

@Data
public class ExemplaireDTO {

    public int id;
    public boolean available;
    private Edition edition;

}

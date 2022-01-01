package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Edition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExemplaireDTO {

    public int id;
    public boolean available;
    private Edition edition;

}

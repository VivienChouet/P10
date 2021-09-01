package com.bibliotheque.API.Entity.Dto;

import lombok.Data;


import java.util.Date;

@Data
public class AttenteDTO {
    public int id;
    public Date dateMail;
    public EditionDTO edition;
    private UserDTO user;


}

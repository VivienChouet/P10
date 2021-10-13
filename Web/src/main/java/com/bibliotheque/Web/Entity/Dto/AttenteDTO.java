package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttenteDTO {
    public int id;
    public Date dateMail;
    public Boolean mail;
    public EditionDTO edition;
    private UserDTO user;
}
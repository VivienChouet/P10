package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ListReservationDTO {
    private UserDTO user;
    private ExemplaireDTO exemplaire;
    private Date date_debut;
    private Date date_fin;
    private Boolean extension;
    public Boolean ended;
    public Boolean recuperer;
    private int id;
}

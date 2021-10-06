package com.bibliotheque.API.Entity.Dto;

import lombok.Data;

@Data
public class NewReservationDTO {

    public int edition;
    public int user;
    private boolean attente;


}


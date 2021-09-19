package com.bibliotheque.API.Entity.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class MyReservationDTO {

    private String book;
    private String edition;
    private Date date_debut;
    private Date date_fin;
    private Boolean extension;
    private int id;
}

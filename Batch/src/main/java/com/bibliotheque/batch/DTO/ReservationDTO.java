package com.bibliotheque.batch.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;
    private boolean ended;
    private boolean extension;
    private boolean batch;
    private AttenteDTO attente;
    private boolean recuperer;
    private UserDTO user;
    private ExemplaireDTO exemplaire;
}


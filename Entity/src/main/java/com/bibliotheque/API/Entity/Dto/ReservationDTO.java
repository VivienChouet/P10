package com.bibliotheque.API.Entity.Dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ReservationDTO {
    public int id;
    public Date date_debut;
    public Date date_fin;
    private boolean ended;
    private boolean extension;
    private boolean batch;
    private boolean recuperer;
    private AttenteDTO attente;
    private UserDTO user;
    private ExemplaireDTO exemplaire;

}


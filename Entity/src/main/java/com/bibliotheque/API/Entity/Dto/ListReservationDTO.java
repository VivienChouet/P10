package com.bibliotheque.API.Entity.Dto;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ListReservationDTO {

    private User user;
    private Exemplaire exemplaire;
    private Date date_debut;
    private Date date_fin;
    private Boolean extension;
    private Boolean ended;
    private Boolean recuperer;
    private int id;
}

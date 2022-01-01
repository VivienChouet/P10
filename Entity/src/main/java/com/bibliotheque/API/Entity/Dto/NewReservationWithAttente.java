package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Attente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class NewReservationWithAttente {
    public int edition;
    public int user;
    private Attente attente;
    private boolean recuperer;
}

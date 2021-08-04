package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import lombok.Data;
import java.util.Date;

@Data
public class AttenteDTO {

    public int id;
    public Date dateMail;
    public int numberCustomer;
    private Exemplaire exemplaire;
    private Reservation reservation;
}

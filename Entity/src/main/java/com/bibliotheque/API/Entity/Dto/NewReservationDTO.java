package com.bibliotheque.API.Entity.Dto;

import java.util.Date;


public class NewReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;

    public int iduser;
    public int idexemplaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(int idexemplaire) {
        this.idexemplaire = idexemplaire;
    }
}


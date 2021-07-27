package com.bibliotheque.API.Entity.Dto;

import java.util.Date;


public class ReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;
    private boolean ended;
    private boolean extension;
    private boolean batch;
    private UserDTO user;
    private ExemplaireDTO exemplaire;

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

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isExtension() {
        return extension;
    }

    public void setExtension(boolean extension) {
        this.extension = extension;
    }

    public boolean isBatch() {
        return batch;
    }

    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ExemplaireDTO getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(ExemplaireDTO exemplaire) {
        this.exemplaire = exemplaire;
    }
}


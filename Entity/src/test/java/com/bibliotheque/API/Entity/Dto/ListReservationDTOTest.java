package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ListReservationDTOTest {

    @Test
public void listReservationTest () {
    ListReservationDTO listReservationDTO = new ListReservationDTO();
    User user =  new User();
    Exemplaire exemplaire = new Exemplaire();
    Date date = new Date();
    ListReservationDTO listReservationDTO1 = new ListReservationDTO();

    listReservationDTO.setRecuperer(true);
    listReservationDTO.setEnded(true);
    listReservationDTO.setUser(user);
    listReservationDTO.setExemplaire(exemplaire);
    listReservationDTO.setDate_debut(date);
    listReservationDTO.setDate_fin(date);
    listReservationDTO.setExtension(true);
    listReservationDTO.setId(1);

        listReservationDTO1.setRecuperer(true);
        listReservationDTO1.setEnded(true);
        listReservationDTO1.setUser(user);
        listReservationDTO1.setExemplaire(exemplaire);
        listReservationDTO1.setDate_debut(date);
        listReservationDTO1.setDate_fin(date);
        listReservationDTO1.setExtension(true);
        listReservationDTO1.setId(1);


        assertEquals(listReservationDTO.getDate_debut(),date);
    assertEquals(listReservationDTO.getDate_fin(),date);
    assertEquals(listReservationDTO.getEnded(),true);
    assertEquals(listReservationDTO.getExemplaire(),exemplaire);
    assertEquals(listReservationDTO.getRecuperer(),true);
    assertEquals(listReservationDTO.getExtension(),true);
    assertEquals(listReservationDTO.getId(),1);
    assertEquals(listReservationDTO.getUser(),user);
    assertEquals(new ListReservationDTO().hashCode() == new ListReservationDTO().hashCode(), true);
assertEquals(listReservationDTO.equals(listReservationDTO), true);
    }
}
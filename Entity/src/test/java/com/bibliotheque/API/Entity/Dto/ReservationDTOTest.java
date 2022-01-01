package com.bibliotheque.API.Entity.Dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDTOTest {


    @Test
    public void reservationDTOTest(){
        ReservationDTO reservationDTO = new ReservationDTO();
        Date date = new Date();
        AttenteDTO attenteDTO = new AttenteDTO();
        UserDTO userDTO = new UserDTO();
        ExemplaireDTO exemplaireDTO = new ExemplaireDTO();
        int id = 1;
        boolean bool = true;

        reservationDTO.setId(id);
        reservationDTO.setDate_debut(date);
        reservationDTO.setDate_fin(date);
        reservationDTO.setAttente(attenteDTO);
        reservationDTO.setBatch(bool);
        reservationDTO.setEnded(bool);
        reservationDTO.setExemplaire(exemplaireDTO);
        reservationDTO.setRecuperer(bool);
        reservationDTO.setExtension(bool);
        reservationDTO.setUser(userDTO);

        assertEquals(reservationDTO.getAttente(),attenteDTO);
        assertEquals(reservationDTO.getDate_debut(),date);
        assertEquals(reservationDTO.getDate_fin(),date);
        assertEquals(reservationDTO.getId(),id);
        assertEquals(reservationDTO.getExemplaire(),exemplaireDTO);
        assertEquals(reservationDTO.getUser(),userDTO);
        assertEquals(reservationDTO.isBatch(),bool);
        assertEquals(reservationDTO.isEnded(),bool);
        assertEquals(reservationDTO.isExtension(),bool);
        assertEquals(reservationDTO.isRecuperer(),bool);
        assertEquals(new ReservationDTO().hashCode() == new ReservationDTO().hashCode(), true);
        assertEquals(reservationDTO.equals(reservationDTO),true);
    }
}
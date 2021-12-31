package com.bibliotheque.API.Entity.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteReservationDTOTest {

    @Test
    void DTOTest() {
        DeleteReservationDTO deleteReservationDTO = new DeleteReservationDTO();
        int id = 1;

        deleteReservationDTO.setId(id);

        assertEquals(deleteReservationDTO.getId(),id);
        assertEquals(new DeleteReservationDTO().hashCode() == new DeleteReservationDTO().hashCode(), true);
        assertEquals(deleteReservationDTO.equals(deleteReservationDTO), true);
    }
}
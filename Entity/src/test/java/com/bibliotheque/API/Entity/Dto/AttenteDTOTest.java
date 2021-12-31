package com.bibliotheque.API.Entity.Dto;


import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AttenteDTOTest {


    @Test
    void setDTO() {
        //Arrange
        int id = 1;
        String test = "test";
        Date date = new Date();
        UserDTO user = new UserDTO();
        EditionDTO edition = new EditionDTO();
        boolean mail = true;

        AttenteDTO attenteDTO =new AttenteDTO();
        attenteDTO.setId(1);
        attenteDTO.setDateMail(date);
        attenteDTO.setUser(user);
        attenteDTO.setEdition(edition);
        attenteDTO.setMail(mail);
        //Act


        //Assert
        assertEquals(attenteDTO.getDateMail(),date);
        assertEquals(attenteDTO.getId(),id);
        assertEquals(attenteDTO.getUser(), user);
        assertEquals(attenteDTO.getEdition(),edition);
        assertEquals(attenteDTO.getMail(),mail);
        assertEquals(new AttenteDTO().hashCode() == new AttenteDTO().hashCode(), true);
        assertEquals(attenteDTO.equals(attenteDTO),true);
    }

    @Test
    void GetDTO() {
    }


}
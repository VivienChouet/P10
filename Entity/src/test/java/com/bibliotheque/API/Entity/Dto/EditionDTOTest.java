package com.bibliotheque.API.Entity.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditionDTOTest {

    @Test
    void DTOTest(){
        EditionDTO editionDTO = new EditionDTO();
        BookDTO bookDTO = new BookDTO();
        int id = 1;
        String test = "test";

        editionDTO.setId(id);
        editionDTO.setBook(bookDTO);
        editionDTO.setName(test);

        assertEquals(editionDTO.getId(),id);
        assertEquals(editionDTO.getBook(),bookDTO);
        assertEquals(editionDTO.getName(),test);
        assertEquals(new EditionDTO().hashCode() == new EditionDTO().hashCode() , true);
        assertEquals(editionDTO.equals(editionDTO), true);
    }

}
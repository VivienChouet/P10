package com.bibliotheque.API.Entity.Dto;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void testDTO(){
        //Arrange
        int id = 1;
        String test ="test";
        Date date = new Date();

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(id);
        bookDTO.setAuthor(test);
        bookDTO.setTitle(test);
        bookDTO.setResume(test);
        bookDTO.setPublication(date);

        //Assert
        assertEquals(bookDTO.getTitle(),test);
        assertEquals(bookDTO.getAuthor(),test);
        assertEquals(bookDTO.getTitle(),test);
        assertEquals(bookDTO.getResume(),test);
        assertEquals(bookDTO.getPublication(),date);
        assertEquals(bookDTO.getId(),id);
        assertEquals(new BookDTO().hashCode() == new BookDTO().hashCode(), true);
        assertEquals(bookDTO.equals(bookDTO), true);
    }

}
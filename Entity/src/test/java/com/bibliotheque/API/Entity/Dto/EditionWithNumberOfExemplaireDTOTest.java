package com.bibliotheque.API.Entity.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditionWithNumberOfExemplaireDTOTest {

    @Test
    void DTOTest (){
        EditionWithNumberOfExemplaireDTO editionWithNumberOfExemplaireDTO = new EditionWithNumberOfExemplaireDTO();
        String test ="test";
        int id = 1;

        editionWithNumberOfExemplaireDTO.setEditionName(test);
        editionWithNumberOfExemplaireDTO.setBook_id(id);
        editionWithNumberOfExemplaireDTO.setEdition_id(id);
        editionWithNumberOfExemplaireDTO.setNumberOfEditionAvailable(id);
        editionWithNumberOfExemplaireDTO.setNumberOfEditionTotal(id);
        editionWithNumberOfExemplaireDTO.setAttenteAvailable(id);

        assertEquals(editionWithNumberOfExemplaireDTO.getEditionName(),test);
        assertEquals(editionWithNumberOfExemplaireDTO.getBook_id(),id);
        assertEquals(editionWithNumberOfExemplaireDTO.getEdition_id(),id);
        assertEquals(editionWithNumberOfExemplaireDTO.getNumberOfEditionTotal(),id);
        assertEquals(editionWithNumberOfExemplaireDTO.getNumberOfEditionAvailable(),id);
        assertEquals(editionWithNumberOfExemplaireDTO.getAttenteAvailable(),id);;
        assertEquals(new EditionWithNumberOfExemplaireDTO().hashCode() == new EditionWithNumberOfExemplaireDTO().hashCode() , true);
        assertEquals(editionWithNumberOfExemplaireDTO.equals(editionWithNumberOfExemplaireDTO),true);
    }


}
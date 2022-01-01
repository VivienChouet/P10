package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.NewEditionDTO;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditionService {

    @Autowired
    OperateurDiamant operateurDiamant;


    public void save(NewEditionDTO newEditionDTO) throws JsonProcessingException {
        String json = (String) operateurDiamant.jsonConvert(newEditionDTO);
        operateurDiamant.post("http://localhost:8080/edition/new" , json);

    }
}

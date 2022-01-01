package com.bibliotheque.Web.service;


import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bibliotheque.Web.Entity.Dto.*;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class ExemplaireService {

    @Autowired
    OperateurDiamant operateurDiamant;




    /**
     * @param
     * @throws JsonProcessingException
     */
    public void save(int id) throws JsonProcessingException {
            operateurDiamant.post("http://localhost:8080/exemplaire/" + id, "vide");
    }


    public List<EditionWithNumberOfExemplaireDTO> listExemplaireByEditionId(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/exemplaire/book/available/" + id);
        List<EditionWithNumberOfExemplaireDTO> editions = operateurDiamant.listObject(response, EditionWithNumberOfExemplaireDTO.class);
        return editions;
    }
}

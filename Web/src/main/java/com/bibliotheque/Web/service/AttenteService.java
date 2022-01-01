package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.AttenteDTO;
import com.bibliotheque.Web.utility.LoggingController;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AttenteService {

    @Autowired
    OperateurDiamant operateurDiamant;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);


    public List<AttenteDTO> listAttente() throws JsonProcessingException {
       HttpResponse response =  operateurDiamant.Request("http://localhost:8080/attente/");
       List<AttenteDTO> attenteDTOS = operateurDiamant.listObject(response, AttenteDTO.class);
       return attenteDTOS;
    }

    public void attenteRecuperer(int id) {
        operateurDiamant.post("http://localhost:8080/attente/" + id,"vide");

    }
}

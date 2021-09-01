package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.*;
import com.bibliotheque.API.Entity.Dto.*;
import com.bibliotheque.API.Repository.*;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttenteService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ExemplaireRepository exemplaireRepository;
    @Autowired
    AttenteRepository attenteRepository;
    @Autowired
    ExemplaireService exemplaireService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EditionRepository editionRepository;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);


    //TODO: methode permettant d'inscrire un user dans la queu

public void newAttente (NewAttenteDTO newAttenteDTO){
    if (attentePossible(newAttenteDTO.edition)){
        User user = this.userRepository.findById(newAttenteDTO.user).get();
        Edition edition = this.editionRepository.findById(newAttenteDTO.edition).get();
        Attente attente = new Attente();
        attente.setUser(user);
        attente.setEdition(edition);
        attenteRepository.save(attente);
        logger.info("new attente créer");
    }
    logger.info("new attente impossible");
}

 public Integer attenteNumberMax (int edition_id){
       Integer attenteNumberMax = null;
       List<Exemplaire> exemplaires = this.exemplaireService.findByEdition_id(edition_id);
       attenteNumberMax = 2*exemplaires.size();
     logger.info("attente max pour l'edition id : " + edition_id + " est de = " + attenteNumberMax);
       return attenteNumberMax;
    }

    public Integer attenteNumberOnEdition (int edition_id){
    Integer attenteNumber = null;
    List<Attente> attentes = attenteRepository.findByEditionId(edition_id);
    attenteNumber = attentes.size();
    logger.info("attente en cours pour l'edition id : " + edition_id + " est de = " + attenteNumber);
    return attenteNumber;
    }

    public Integer numberAttenteAvailable (int edition_id){
    int attenteMax = attenteNumberMax(edition_id);
    int attente = attenteNumberOnEdition(edition_id);
    int attenteAvailable = attenteMax-attente;
    logger.info("nombre d'attente disponible pour edition id : " + edition_id + " est de = " + attenteAvailable);
    return attenteAvailable;
    }

    public boolean attentePossible (int edition_id){
    boolean attente = false;
    if (numberAttenteAvailable(edition_id) != 0 && numberAttenteAvailable(edition_id) > 0 )
    attente = true;
    logger.info("new attente possible : " + attente);
    return attente;
    }

    //TODO: methode permettant d'envoyer un mail


}

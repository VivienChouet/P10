package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Dto.*;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Repository.AttenteRepository;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Repository.ReservationRepository;
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



   public Date dateReturnExemplaire (int book_id, String edition){
       List<Exemplaire> exemplaires = this.exemplaireRepository.findByBook_idAndEdition(book_id,edition);
       List<Reservation> reservations = new ArrayList<>();
       System.out.println("Taille list exemplaire trier par book id et reservation : " + exemplaires.size());
       for(int i = 0 ; i<exemplaires.size();i++){
           if(reservationRepository.findByExemplaireAndEnded(exemplaires.get(i),false) != null){
           reservations.add(i,reservationRepository.findByExemplaireAndEnded(exemplaires.get(i),false));
           System.out.println("ajout de l'entrée : " + reservationRepository.findByExemplaireAndEnded(exemplaires.get(i),false));
       }}
       System.out.println("Taille liste de reservation : " + reservations.size());
        Date date = reservations.stream().map(Reservation::getDate_fin).max(Date::compareTo).get();
       return date;
   }

    //TODO: methode permettant d'inscrire un user dans la queu

    public void newAttenteReservation (BookDTO book, String edition, UserDTO user){
        AttenteDTO attenteDTO = new AttenteDTO();
        attenteDTO.setBook(book);
        attenteDTO.setEdition(edition);
        attenteDTO.setUser(user);
        attenteDTO.setNumberCustomer(attenteNumber(book.getId(), edition) + 1 );
    }

    public Integer attenteNumber (int book_id, String edition){
       Integer attente = null;
       attente = this.attenteRepository.findByBookAndEdition(book_id,edition).size();
       return attente;
    }

    //TODO: methode permettant d'avoir le nombre de queu max

  /*  public Integer attenteNumberMax (int book_id, String edition){
       Integer attenteNumberMax = null;
       TreeMap<String, Integer> exemplaires = this.exemplaireService.countExemplaire(book_id);

       return attenteNumberMax;
    }*/

    //TODO: methode permettant d'envoyer un mail


}

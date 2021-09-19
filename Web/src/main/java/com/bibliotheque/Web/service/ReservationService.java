package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.*;
import com.bibliotheque.Web.utility.LoggingController;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    UserService userService;

    @Autowired
    OperateurDiamant operateurDiamant;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    /**
     *
     * @param edition
     * @throws JsonProcessingException
     */
    public void newReservation(int  edition) throws JsonProcessingException {
        UserDTO userDTO = userService.connectedUser();
        NewReservationDTO newReservationDTO = new NewReservationDTO();
        newReservationDTO.setEdition(edition);
        newReservationDTO.setUser(userDTO.getId());
        String json = (String) operateurDiamant.jsonConvert(newReservationDTO);
        operateurDiamant.post("http://localhost:8080/reservation/", json);
        logger.info("New Reservation :  " + newReservationDTO);

    }
    /**
     * List Reservation By User
     * @return List<ReservationDTO>
     * @throws JsonProcessingException
     */
    public List<MyReservationDTO> reservationByUser() throws JsonProcessingException {
        System.out.println("TOKEN = " + userService.token);
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/reservation/myreservation", userService.token);
        List<MyReservationDTO> myReservationDTOS = operateurDiamant.listObject(response, MyReservationDTO.class);
        return myReservationDTOS;
    }

    /**
     *Find Reservation by Id
     * @param id
     * @return ReservationDTO
     * @throws JsonProcessingException
     */
    public ReservationDTO findById(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/reservation/" + id);
        ReservationDTO reservation = (ReservationDTO) operateurDiamant.singleObject(response, ReservationDTO.class);
        return reservation;
    }

    /**
     * Extension
     * @param id
     */
    public void extension(int id){

        logger.info("extension reservation id : " + id);
    operateurDiamant.post("http://localhost:8080/reservation/extension/" + id,"vide");
    }

    /**
     * End Reservation
     * @param id
     */
    public void endedReservation(int id){
       operateurDiamant.post("http://localhost:8080/reservation/return/" + id, "vide");
    }

    /**
     *
     * @return List<ReservationDTO>
     * @throws JsonProcessingException
     */
    public List<ReservationDTO> listReservation() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/reservation/");
        List<ReservationDTO> reservationDTOS = operateurDiamant.listObject(response, ReservationDTO.class);
        return reservationDTOS;
    }

    public void newAttente(int edition) throws JsonProcessingException {
        UserDTO userDTO = userService.connectedUser();
        NewAttenteDTO newAttenteDTO = new NewAttenteDTO();
        newAttenteDTO.setEdition(edition);
        newAttenteDTO.setUser(userDTO.getId());
        String json = (String) operateurDiamant.jsonConvert(newAttenteDTO);
        operateurDiamant.post("http://localhost:8080/attente/",json);
    }
}

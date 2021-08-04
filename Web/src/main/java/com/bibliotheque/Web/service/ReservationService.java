package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.Entity.Dto.ReservationResearchDTO;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
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
     * @param bookId
     * @throws JsonProcessingException
     */
    public void newReservation(String edition, Integer bookId) throws JsonProcessingException {
        UserDTO userDTO = userService.connectedUser();
        ReservationResearchDTO reservationResearchDTO = new ReservationResearchDTO();
        reservationResearchDTO.setEdition(edition);
        reservationResearchDTO.setId(bookId);
        reservationResearchDTO.setUser(userDTO.getId());
        String json = (String) operateurDiamant.jsonConvert(reservationResearchDTO);
        operateurDiamant.post("http://localhost:8080/reservation/", json);
        logger.info("New Reservation :  " + reservationResearchDTO);

    }
    /**
     * List Reservation By User
     * @return List<ReservationDTO>
     * @throws JsonProcessingException
     */
    public List<ReservationDTO> reservationByUser() throws JsonProcessingException {
        System.out.println("TOKEN = " + userService.token);
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/reservation/myreservation", userService.token);
        List<ReservationDTO> reservations = operateurDiamant.listObject(response, ReservationDTO.class);
        return reservations;
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

}

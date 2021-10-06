package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Dto.MyReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Repository.ReservationRepository;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExemplaireRepository exemplaireRepository;
    @Autowired
    UserService userService;
    @Autowired
    ExemplaireService exemplaireService;
    @Autowired
    AttenteService attenteService;

    /**
     * find All
     *
     * @return List<Reservation>
     */
    public List<Reservation> findAll() {
        List<Reservation> reservations = this.reservationRepository.findByAttente(false);
        return reservations;
    }

    /**
     * Find Reservation By Id
     *
     * @param id
     * @return Reservation
     */
    public Reservation findById(int id) {
        logger.info("Reservation Id = " + id);
        Reservation reservation = this.reservationRepository.findById(id).get();
        return reservation;
    }

    /**
     * Delete
     *
     * @param id
     */
    public void delete(int id) {
        logger.info("Delete reservation = " + id);
        reservationRepository.delete(findById(id));
    }

    /**
     * Save
     *
     * @param newReservationDTO
     */
    public void save(NewReservationDTO newReservationDTO) {
        logger.info("new reservation = " + newReservationDTO);
        Reservation reservation = new Reservation();
        reservation.setDate_debut(new Date());
        reservation.setDate_fin(endReservationDate(new Date()));
        reservation.setUser(userRepository.findById(newReservationDTO.user).get());
        reservation.setEnded(false);
        reservation.setExtension(false);
        reservation.setExemplaire(exemplaireRepository.findByEdition_IdAndAvailable(newReservationDTO.edition, true).get(0));
        reservation.setAttente(newReservationDTO.isAttente());
        reservationRepository.save(reservation);
        exemplaireService.reservation(exemplaireRepository.findByEdition_IdAndAvailable(newReservationDTO.edition, true).get(0));

    }


    /**
     * Reservation End Date
     *
     * @param date
     * @return
     */
    public Date endReservationDate(Date date) {
        DateTime dn = new DateTime(date);
        DateTime date_fin = dn.plusWeeks(4);
        Date dateFin = date_fin.toDate();
        logger.info("Date de début = " + date);
        logger.info("Date de fin = " + dateFin);
        return dateFin;
    }

    /**
     * Extension
     *
     * @param id
     */
    public void extension(int id) {
        logger.info("Update Started");
        Reservation reservation = reservationRepository.findById(id).get();
        Date dateDay = new Date();
        if(reservation.date_fin.after(dateDay)){
            reservation.setExtension(true);
            reservationRepository.save(reservation);
        }
        Date date = reservation.getDate_fin();
        reservation.setDate_fin(endReservationDate(date));
        reservation.setExtension(true);
        reservationRepository.save(reservation);
    }

    /**
     * Find Reservation By User Connected
     *
     * @param token
     * @return List<Reservation>
     */
    public List<Reservation> findByUser(String token) {
        String username = userService.findUsernameByToken(token);
        User user = userService.findByUsername(username);
        logger.info("find reservation by user = " + user.name);
        List<Reservation> reservations = reservationRepository.findByUser_IdAndEnded(user.getId(), false);
        return reservations;
    }

    /**
     * End Reservation
     *
     * @param id
     */
    public void endReservation(int id) throws Exception {
        logger.info("ended reservation " + id);
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setEnded(true);
        reservationRepository.save(reservation);
        Exemplaire exemplaire = reservation.getExemplaire();
        exemplaire.setAvailable(true);
        exemplaireRepository.save(exemplaire);
        List<Attente> attentes = attenteService.findByEdition_Id(reservation.getExemplaire().getEdition().id);
        if (attentes.size() != 0) {
            attenteService.attenteToReservation(reservation.getExemplaire().getEdition().getId());
        }
    }


    public List<MyReservationDTO> myReservation(String token) {
        List<Reservation> reservations = this.findByUser(token);
List<MyReservationDTO> myReservationDTOS = new ArrayList<>();
        for (Reservation reservation: reservations) {
            MyReservationDTO myReservationDTO = new MyReservationDTO();
            myReservationDTO.setBook(reservation.getExemplaire().getEdition().getBook().title);
            myReservationDTO.setEdition(reservation.getExemplaire().getEdition().name);
            myReservationDTO.setDate_debut(reservation.date_debut);
            myReservationDTO.setExtension(reservation.extension);
            myReservationDTO.setDate_fin(reservation.date_fin);
            myReservationDTO.setId(reservation.id);
            myReservationDTOS.add(myReservationDTO);
        }
        return myReservationDTOS;
    }


    public void attenteRecuperer(int id) {
        Reservation reservation = new Reservation();
        Attente attente = new Attente();
        attente = this.attenteService.findById(id);
        reservation = this.reservationRepository.findByUser_IdAndExemplaire_Edition_Id(attente.getUser().getId(), attente.id).get(0);

    }
}

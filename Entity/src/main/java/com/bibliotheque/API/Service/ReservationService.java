package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Dto.ListReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationWithAttente;
import com.bibliotheque.API.Entity.Dto.ReservationDTO;
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
    public List<ListReservationDTO> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        List<ListReservationDTO> listReservationDTOS = créationDTO(reservations);
        return listReservationDTOS;
    }

    public List<ListReservationDTO> myReservation(String token) {
        List<Reservation> reservations = this.findByUser(token);
        List<ListReservationDTO> listReservationDTOS = créationDTO(reservations);
        return listReservationDTOS;
    }

    public List<ListReservationDTO> créationDTO(List<Reservation> reservations){
        List<ListReservationDTO> listReservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ListReservationDTO listReservationDTO = new ListReservationDTO();
            listReservationDTO.setExemplaire(reservation.getExemplaire());
            listReservationDTO.setUser(reservation.getUser());
            listReservationDTO.setDate_debut(reservation.date_debut);
            listReservationDTO.setExtension(reservation.extension);
            listReservationDTO.setDate_fin(reservation.date_fin);
            listReservationDTO.setId(reservation.id);
            listReservationDTO.setEnded(reservation.ended);
            listReservationDTO.setRecuperer(reservation.recuperer);
            listReservationDTOS.add(listReservationDTO);
        }
        return listReservationDTOS;
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
    public Reservation save(NewReservationDTO newReservationDTO) {
        logger.info("new reservation = " + newReservationDTO);
        Reservation reservation = new Reservation();
        reservation.setDate_debut(new Date());
        reservation.setDate_fin(endReservationDate(new Date()));
        reservation.setUser(userRepository.findById(newReservationDTO.user));
        reservation.setEnded(false);
        reservation.setExtension(false);
        reservation.setRecuperer(true);
        reservation.setExemplaire(exemplaireRepository.findByEdition_IdAndAvailable(newReservationDTO.edition, true).get(0));
        reservationRepository.save(reservation);
        exemplaireService.reservation(exemplaireRepository.findByEdition_IdAndAvailable(newReservationDTO.edition, true).get(0));
        return reservation;
    }

    public Reservation saveWithAttente (NewReservationWithAttente newReservationWithAttente) {
        logger.info("new reservation issue d'attente = " + newReservationWithAttente);
        Reservation reservation = new Reservation();
        reservation.setDate_debut(new Date());
        reservation.setDate_fin(endReservationDate(new Date()));
        reservation.setUser(userRepository.findById(newReservationWithAttente.user));
        reservation.setEnded(false);
        reservation.setExtension(false);
        reservation.setExemplaire(exemplaireRepository.findByEdition_IdAndAvailable(newReservationWithAttente.edition, true).get(0));
        reservation.setAttente(newReservationWithAttente.getAttente());
        reservation.setRecuperer(newReservationWithAttente.isRecuperer());
        reservationRepository.save(reservation);
        exemplaireService.reservation(exemplaireRepository.findByEdition_IdAndAvailable(newReservationWithAttente.edition, true).get(0));
        return reservation;
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
    public boolean extension(int id) {
        logger.info("Update Started");
        Reservation reservation = reservationRepository.findById(id).get();
        Date dateDay = new Date();
        if(reservation.date_fin.after(dateDay)){
            reservation.setExtension(true);
            reservationRepository.save(reservation);
            return true;
        }
        Date date = reservation.getDate_fin();
        reservation.setDate_fin(endReservationDate(date));
        reservation.setExtension(true);
        reservationRepository.save(reservation);
        return false;
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

    public void attenteRecuperer(int id) {
        Reservation reservation = this.reservationRepository.findByAttenteId(id);
        reservation.setRecuperer(true);
        reservation.setAttente(null);
        reservationRepository.save(reservation);
        attenteService.deleteAttente(id);

    }
}

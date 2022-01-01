package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Dto.ListReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationWithAttente;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Repository.ReservationRepository;
import com.bibliotheque.API.Repository.UserRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;
    @Mock
    ExemplaireRepository exemplaireRepositoryMock;
    @Mock
    ReservationRepository reservationRepositoryMock;
    @Mock
    UserRepository userRepositoryMock;
    @Mock
    ExemplaireService exemplaireServiceMock;


    @Test
    void créationDTO() {
        //Arrange
        List<ListReservationDTO> listReservationDTOList = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        Exemplaire exemplaire = new Exemplaire();
        User user = new User();
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        Date date = new Date();

        reservation1.setId(1);
        reservation1.setDate_fin(date);
        reservation1.setDate_debut(date);
        reservation1.setExtension(true);
        reservation1.setEnded(true);
        reservation1.setRecuperer(true);
        reservation1.setUser(user);
        reservation1.setExemplaire(exemplaire);

        reservation2.setId(2);
        reservation2.setDate_fin(date);
        reservation2.setDate_debut(date);
        reservation2.setExtension(true);
        reservation2.setEnded(true);
        reservation2.setRecuperer(true);
        reservation2.setUser(user);
        reservation2.setExemplaire(exemplaire);

        reservations.add(reservation1);
        reservations.add(reservation2);


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
            listReservationDTOList.add(listReservationDTO);
        }

        //Act
        System.out.println("Reservation list test = " + reservations);
        List<ListReservationDTO> listReservationDTOS = reservationService.créationDTO(reservations);
        //Assert
        assertEquals(listReservationDTOS, listReservationDTOList);
    }

 @Test
     void save() {
         //Arrange
         NewReservationDTO newReservationDTO = new NewReservationDTO();
         int edition = 1;
         int user = 1;
         newReservationDTO.setEdition(edition);
         newReservationDTO.setUser(user);
         User user1 = new User();
         user1.setId(1);
         List<Exemplaire> exemplaires = new ArrayList<>();
         Exemplaire exemplaire = new Exemplaire();
         exemplaire.setId(1);
         exemplaires.add(exemplaire);

         Date date = new Date();
         DateTime dn = new DateTime(date);
         DateTime date_fin = dn.plusWeeks(4);
         Date dateFin = date_fin.toDate();

         Reservation reservation = new Reservation();
         reservation.setDate_debut(new Date());
         reservation.setDate_fin(dateFin);
         reservation.setUser(user1);
         reservation.setEnded(false);
         reservation.setExtension(false);
         reservation.setRecuperer(true);
         reservation.setExemplaire(exemplaire);
         //Mock
         System.out.println("UserId = " + user + "User = " + user1);
         when(userRepositoryMock.findById(user)).thenReturn(user1);
         when(exemplaireRepositoryMock.findByEdition_IdAndAvailable(edition, true)).thenReturn(exemplaires);
         when(exemplaireServiceMock.reservation(exemplaire)).thenReturn(exemplaire);
         //Act
         Reservation reservationTest = reservationService.save(newReservationDTO);
         //Assert
         assertEquals(reservationTest.getId(),reservation.getId());
}


    @Test
    void saveWithAttente() {
        //Arrange
        NewReservationWithAttente newReservationWithAttente = new NewReservationWithAttente();
        int edition = 1;
        int user = 1;
        newReservationWithAttente.setEdition(edition);
        newReservationWithAttente.setUser(user);
        newReservationWithAttente.setRecuperer(true);
        User user1 = new User();
        user1.setId(1);
        List<Exemplaire> exemplaires = new ArrayList<>();
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setId(1);
        exemplaires.add(exemplaire);

        Date date = new Date();
        DateTime dn = new DateTime(date);
        DateTime date_fin = dn.plusWeeks(4);
        Date dateFin = date_fin.toDate();

        Reservation reservation = new Reservation();
        reservation.setDate_debut(new Date());
        reservation.setDate_fin(dateFin);
        reservation.setUser(user1);
        reservation.setEnded(false);
        reservation.setExtension(false);
        reservation.setRecuperer(true);
        reservation.setExemplaire(exemplaire);
        //Mock
        System.out.println("UserId = " + user + "User = " + user1);
        when(userRepositoryMock.findById(user)).thenReturn(user1);
        when(exemplaireRepositoryMock.findByEdition_IdAndAvailable(edition, true)).thenReturn(exemplaires);
        when(exemplaireServiceMock.reservation(exemplaire)).thenReturn(exemplaire);
        //Act
        Reservation reservationTest = reservationService.saveWithAttente(newReservationWithAttente);
        //Assert
        assertEquals(reservation.getId(),reservationTest.getId());
    }

    @Test
    void endReservationDate() {
        Date date = new Date();
        DateTime dn = new DateTime(date);
        DateTime date_fin = dn.plusWeeks(4);
        Date dateFin = date_fin.toDate();

        Date date1 = reservationService.endReservationDate(date);

        Assertions.assertEquals(dateFin, date1);
    }

 /*   @Test
    void extension() {

        Reservation reservationExtensionPossible = new Reservation();
        Reservation reservationExtensionImpossible = new Reservation();
        Date dateImpossible = new Date();
        DateTime dn = new DateTime();
        DateTime date_fin = dn.plusWeeks(1);
        Date datePossible = date_fin.toDate();

        reservationExtensionImpossible.setDate_fin(dateImpossible);
        reservationExtensionPossible.setDate_fin(datePossible);

        when(reservationRepositoryMock.findById(1).get()).thenReturn(reservationExtensionPossible);
        when(reservationRepositoryMock.findById(2).get()).thenReturn(reservationExtensionImpossible);


    }
    */

    @Test
    void findByUser() {
    }

    @Test
    void endReservation() {
    }
}
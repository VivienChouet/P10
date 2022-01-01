package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.*;
import com.bibliotheque.API.Entity.Dto.NewAttenteDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationWithAttente;
import com.bibliotheque.API.Repository.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttenteServiceTest {

    static
    Instant startedAt;

    @InjectMocks
    AttenteService attenteService;

    @Mock
    ExemplaireService exemplaireServiceMock;

    @Mock
    ReservationService reservationServiceMock;

    @Mock
    AttenteRepository attenteMock;

    @Mock
    EditionRepository editionMock;

    @Mock
    ReservationRepository reservationMock;

    @Mock
    ExemplaireRepository exemplaireMock;

    @Mock
    UserRepository userMock;



    @BeforeAll
    static void setUp() {startedAt = Instant.now();
    }

    @AfterAll
    static void tearDown() {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms" , duration));
    }

    @DisplayName("New Attente")
    @Test
    void newAttente() {
        //Arrange
       NewAttenteDTO newAttenteDTO = new NewAttenteDTO();
        newAttenteDTO.setUser(1);
        newAttenteDTO.setEdition(1);

       User user = new User();
       user.setName("test");

        Edition edition = new Edition();
        edition.setName("test");

        Attente attente = new Attente();
        attente.setEdition(edition);
        attente.setUser(user);
        attente.setMail(false);

        List exemplaires = new LinkedList();
        Exemplaire exemplaire1 =new Exemplaire();
        Exemplaire exemplaire2 = new Exemplaire();
        exemplaires.add(exemplaire1);
        exemplaires.add(exemplaire2);

        //Mock
        when(reservationMock.findByUser_IdAndExemplaire_Edition_IdAndEnded(1,1,false)).thenReturn(null);
        when(attenteMock.findByEdition_IdAndUser_Id(1,1)).thenReturn(null);
        when(userMock.findById(1)).thenReturn(user);
        when(editionMock.findById(1)).thenReturn(edition);
        when(exemplaireServiceMock.findByEdition_id(1)).thenReturn(exemplaires);

        //Act
        Attente attente1 = attenteService.newAttente(newAttenteDTO);
        System.out.println("Attente = " + attente1);
        System.out.println("Service = " + attenteService.newAttente(newAttenteDTO));
        //Asset
        Assertions.assertEquals(attente1,attente);
    
    }

    @Test
    void attenteNumberMax() {

        //Arrange
        List exemplaires = new LinkedList();
        Exemplaire exemplaire1 = new Exemplaire();
        Exemplaire exemplaire2 = new Exemplaire();
        exemplaire1.setId(1);
        exemplaire2.setId(2);
        exemplaires.add(exemplaire1);
        exemplaires.add(exemplaire2);
        System.out.println("Exemplaire list = " + exemplaires);
        //Mock
        when(exemplaireServiceMock.findByEdition_id(1)).thenReturn(exemplaires);
        //Act
        int nbrAttenteMax = attenteService.attenteNumberMax(1);
        //Asset
        Assertions.assertEquals(nbrAttenteMax,4);
    }

    @Test
    void attenteNumberOnEdition() {
        //Arrange
        int id = 1;
        List edition = new LinkedList();
        Edition edition1 =new Edition();
        Edition edition2 = new Edition();
        edition.add(edition1);
        edition.add(edition2);
        //Mock
        when(attenteMock.findByEditionId(1)).thenReturn(edition);
        //Act
        int attenteMax = attenteService.attenteNumberOnEdition(id);
        //Asset
        Assertions.assertEquals(attenteMax,2);
    }

    @Test
    void attenteToReservation() {
    }

    @Test
    void attenteBatch() {
        //Arrange
        List<Attente> attentes = new ArrayList();
        Date date = new Date();
        Attente attente1 = new Attente();
        Attente attente2 = new Attente();
        attente1.setDateMail(date);
        attente2.setDateMail(date);
        //Mock
        when(attenteMock.findAll()).thenReturn(attentes);
        //Act
        List<Attente> attenteList = attenteService.attenteBatch();
        //Assert
        Assertions.assertEquals(attenteList,attentes);

    }

    @Test
    void attenteNonRecuperer() {
    }

    @Test
    void sendmail() {
    }

    @Test
    void updateReservation() {

        //Arrange
        NewAttenteDTO newAttenteDTO = new NewAttenteDTO();
        NewReservationWithAttente newReservationWithAttente = new NewReservationWithAttente();
        newAttenteDTO.setUser(1);
        newAttenteDTO.setEdition(1);

        User user = new User();
        user.setName("test");
        user.setId(1);

        Edition edition = new Edition();
        edition.setName("test");
        edition.setId(1);

        Attente attente = new Attente();
        attente.setEdition(edition);
        attente.setUser(user);

        //Act
        Attente attente1 = attenteService.updateReservation(attente);
        //Assert
        Assertions.assertEquals(attente1,attente);

    }
}
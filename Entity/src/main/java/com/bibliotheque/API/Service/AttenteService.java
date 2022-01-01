package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.*;
import com.bibliotheque.API.Entity.Dto.NewAttenteDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationWithAttente;
import com.bibliotheque.API.Repository.*;
import com.bibliotheque.API.Utility.LoggingController;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    ReservationService reservationService;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);


    public Attente newAttente(NewAttenteDTO newAttenteDTO) {
        List<Reservation> reservations = this.reservationRepository.findByUser_IdAndExemplaire_Edition_IdAndEnded(newAttenteDTO.user, newAttenteDTO.edition, false);
        List<Attente> attentes = this.attenteRepository.findByEdition_IdAndUser_Id(newAttenteDTO.edition, newAttenteDTO.user);
        if (attentePossible(newAttenteDTO.edition)) {
            User user = this.userRepository.findById(newAttenteDTO.user);
            Edition edition = this.editionRepository.findById(newAttenteDTO.edition);
            Attente attente = new Attente();
            attente.setUser(user);
            attente.setEdition(edition);
            attente.setMail(false);
            attenteRepository.save(attente);
            logger.info("new attente créer");
            return attente;
        }
        logger.info("new attente impossible");
        return null;
    }

    public Integer attenteNumberMax(int edition_id) {
        Integer attenteNumberMax = null;
        List<Exemplaire> exemplaires = this.exemplaireService.findByEdition_id(edition_id);
        attenteNumberMax = 2 * exemplaires.size();
        logger.info("attente max pour l'edition id : " + edition_id + " est de = " + attenteNumberMax);
        return attenteNumberMax;
    }

    public Integer attenteNumberOnEdition(int edition_id) {
        Integer attenteNumber = null;
        List<Attente> attentes = attenteRepository.findByEditionId(edition_id);
        attenteNumber = attentes.size();
        logger.info("attente en cours pour l'edition id : " + edition_id + " est de = " + attenteNumber);
        return attenteNumber;
    }

    public Integer numberAttenteAvailable(int edition_id) {
        int attenteMax = attenteNumberMax(edition_id);
        int attente = attenteNumberOnEdition(edition_id);
        int attenteAvailable = attenteMax - attente;
        logger.info("nombre d'attente disponible pour edition id : " + edition_id + " est de = " + attenteAvailable);
        return attenteAvailable;
    }

    public boolean attentePossible(int edition_id) {
        boolean attente = numberAttenteAvailable(edition_id) != 0 && numberAttenteAvailable(edition_id) > 0;
        logger.info("new attente possible : " + attente);
        return attente;
    }

    public void deleteAttente(int id) {
        logger.info("suppression attente id = " + id);
        Attente attente = this.attenteRepository.findById(id).get();
        attenteRepository.delete(attente);
    }

    /*
    Logique de la fonction General Attente
     */

    /**
     *
     */
    public void attenteToReservation(int edition_id) throws Exception {
        List<Attente> attentes = this.attenteRepository.findByEdition_IdAndMail(edition_id, false);
        if (attentes.size()!=0){
            Attente attente = attentes.get(0);
            sendmail(attente);
            updateReservation(attente);
        }
    }

/*
Si Le livre est récupérer
 */

    public void AttenteAccepter(int id) {
        reservationService.attenteRecuperer(id);
        deleteAttente(id);
    }


/*
Si le livre n'est pas récuperer Fonction BATCH
*/

    public List<Attente> attenteBatch() {
        List<Attente> attentes = new ArrayList<>();
        Date date = new Date();
        DateTime dn = new DateTime(date);
        DateTime date_fin = dn.minusHours(48);
        Date dateFin = date_fin.toDate();
        List<Attente> attenteListAll = this.attenteRepository.findAll();
        for (Attente attente: attenteListAll) {
            System.out.println("Attente avant le if : " + attente) ;
            if (attente.dateMail != null) {

                if (attente.dateMail.before(dateFin)) {
                    System.out.println("attente : " + attente);
                    attentes.add(attente);
                }
            }
        }

        System.out.println("Liste des attentes en retard =  " + attentes);
        return attentes;
    }

    public void attenteNonRecuperer(int id) throws Exception {
        Attente attente = attenteRepository.findById(id).get();
        int id_Reservation = this.reservationRepository.findByUser_IdAndExemplaire_Edition_Id(attente.getUser().getId(), attente.getEdition().getId()).get(0).getId();
        reservationService.endReservation(id_Reservation);
        deleteAttente(id);
    }

/*
Methode Logique Attente
 */

    public void sendmail(Attente attente) throws Exception {
        Email from = new Email("slaschh@gmail.com");
        String subject = "Votre livre :  " + attente.getEdition().getBook().getTitle() + " vous attend à la bibliotheque";
        Email to = new Email(attente.getUser().email);
        Content content = new Content("text/plain", "Bonjour Mr/Mme " + attente.getUser().getName() + ' ' + ",\n" +
                "Vous vous étiez inscrit a une liste d'attente pour le livre : " + attente.getEdition().getBook().getTitle() + " ayant pour édition  : " + attente.getEdition().getName() + ".\n" +
                "Vous pouvez dès a présent venir le récuperer. Passer un délai de 48h votre reservation sera annulée ");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info("mail send : " + response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Attente updateReservation(Attente attente) {
        Date date = new Date();
        attente.setDateMail(date);
        attente.setMail(true);
        attenteRepository.save(attente);
        NewReservationWithAttente newReservationWithAttente = new NewReservationWithAttente();
        newReservationWithAttente.setEdition(attente.getEdition().id);
        newReservationWithAttente.setUser(attente.getUser().getId());
        newReservationWithAttente.setAttente(attente);
        newReservationWithAttente.setRecuperer(false);
        reservationService.saveWithAttente(newReservationWithAttente);
        logger.info("update reservation suite attente id = " + attente.id);
        return attente;
    }


    public List<Attente> findByEdition_Id(int id) {
        List<Attente> attentes = this.attenteRepository.findByEditionId(id);

        return attentes;
    }

    public List<Attente> findByDate_mail() {

        List<Attente> attentes = this.attenteRepository.findAttenteByDateMailNotNull();
        logger.info("list de toutes les attentes a récuperer : " + attentes.size());
        return attentes;
    }

    public Attente findById(int id) {
           return attenteRepository.findById(id).get();
    }

    public Attente findByUserId(int id) {
        return attenteRepository.findByUserId(id).get(0);
    }
}

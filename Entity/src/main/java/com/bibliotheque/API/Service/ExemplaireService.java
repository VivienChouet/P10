package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Dto.EditionWithNumberOfExemplaireDTO;
import com.bibliotheque.API.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.API.Entity.Edition;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Repository.EditionRepository;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaireService {

    @Autowired
    ExemplaireRepository exemplaireRepository;
    @Autowired
    BookService bookService;
    @Autowired
    EditionService editionService;
    @Autowired
    EditionRepository editionRepository;
    @Autowired
            AttenteService attenteService;


    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    /**
     * find All
     *
     * @return List<Exemplaire>
     */
    public List<Exemplaire> findAll() {
        logger.info("List Exemplaire");
        List<Exemplaire> exemplaires = this.exemplaireRepository.findAll();
        return exemplaires;
    }

    /**
     * findById
     * @param id
     * @return Exemplaire
     */
    public Exemplaire findById(int id) {
        logger.info("Exemplaire id " + id);
        Exemplaire exemplaire = this.exemplaireRepository.findById(id);
        return exemplaire;
    }

    /**
     * Save
     *
     * @param newExemplaireDTO
     */


 public void save(NewExemplaireDTO newExemplaireDTO) {
        logger.info("save new exemplaire = ");
        Exemplaire exemplaire = new Exemplaire();
        Edition edition = this.editionRepository.findById(newExemplaireDTO.getEdition()).get();
        exemplaire.setAvailable(true);
        exemplaire.setEdition(edition);
        exemplaireRepository.save(exemplaire);
    }

    /**
     * Delete
     * @param id
     */
    public void delete(int id) {
        logger.info("delete = " + id);
        Exemplaire exemplaire = this.exemplaireRepository.findById(id);
        exemplaireRepository.delete(exemplaire);
    }

    /**
     * find Exemplaire By Book_id
     * @param id
     * @return List<Exemplaire>
     */
    public List<Exemplaire> findByBook_idAndAvailable(int id) {
        logger.info("find Exemplaire by Book Id = " + id);
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByEdition_IdAndAvailable(id, true);
        return exemplaires;
    }



    public List<Exemplaire> findbyAvailable() {
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByAvailable(true);
        return exemplaires;
    }

    public List<Exemplaire> findByEdition_id(int id) {
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByEdition_Id(id);
        return exemplaires;
    }

    public void reservation(Exemplaire exemplaire) {
        logger.info("exemplaire id " + exemplaire.id + " reserved");
        exemplaire.setAvailable(false);
        exemplaireRepository.save(exemplaire);
    }

    public List<EditionWithNumberOfExemplaireDTO> editionWithAllNumber(int id) {
        logger.info("Search all edition with some number ");
        List<EditionWithNumberOfExemplaireDTO> editionWithNumberOfExemplaireDTOS = new ArrayList<>();
        List<Edition> editions = this.editionRepository.findByBook_Id(id);
        for (Edition edition: editions) {
            List<Exemplaire> exemplaires = this.findByEdition_id(edition.id);
            int exemplaireTotal = exemplaires.size();
            List<Exemplaire> exemplaires1 = this.findByBook_idAndAvailable(id);
            int reservationAvailable = exemplaires1.size();
            int attenteAvailable = attenteService.numberAttenteAvailable(edition.id);
           EditionWithNumberOfExemplaireDTO numberOfExemplaireDTO = new EditionWithNumberOfExemplaireDTO();
           numberOfExemplaireDTO.setEditionName(edition.name);
           numberOfExemplaireDTO.setBook_id(edition.getBook().id);
           numberOfExemplaireDTO.setNumberOfEditionAvailable(reservationAvailable);
           numberOfExemplaireDTO.setNumberOfEditionTotal(exemplaireTotal);
           numberOfExemplaireDTO.setAttenteAvailable(attenteAvailable);
           numberOfExemplaireDTO.setEdition_id(edition.id);
            editionWithNumberOfExemplaireDTOS.add(numberOfExemplaireDTO);
        }
        logger.info("Book id " + id + " = " + editionWithNumberOfExemplaireDTOS );
        return editionWithNumberOfExemplaireDTOS;
    }

}


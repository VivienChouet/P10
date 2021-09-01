package com.bibliotheque.API.Service;

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

/*
    public TreeMap countExemplaire(int bookid) {
        List<Exemplaire> exemplaireList = findByBook_id(bookid);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < exemplaireList.size(); i++) {
            list.add(exemplaireList.get(i).edition);
        }
        TreeMap<String, Integer> exemplaires = new TreeMap<>();
        for (String i : list) {
            Integer j = exemplaires.get(i);
            exemplaires.put(i, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> val : exemplaires.entrySet()) {
            System.out.println("Element " + val.getKey() + " " + "occurs : " + val.getValue() + " times");
        }
        return exemplaires;
    }
*/
/*  public void updateBookIdDataBaseNumberOfExemplaire(int book_id) {
        TreeMap<String, Integer> exemplaires = countExemplaire(book_id);
        for (Map.Entry<String, Integer> val : exemplaires.entrySet())
        {
            String edition = val.getKey();
            Integer numberExemplaire = val.getValue();
            List<Exemplaire> exemplaireList = this.exemplaireRepository.findByBook_id(book_id);
            logger.info("Edition mise a jour : " + edition + " nombre d'exemplaire : " + numberExemplaire);
            for(int i = 0 ; i<exemplaireList.size() ; i++){
                System.out.println("Taille de la liste : " + exemplaireList.size() + " id = " + exemplaireList.get(i).id);
                if (edition == exemplaireList.get(i).getEdition() ){
                    exemplaireList.get(i).setNumber(numberExemplaire);
                    logger.info("save de l'exmplaire : " + exemplaireList.get(i).id + " mise a jour du nombre d'exemplaire : " + exemplaireList.get(i).number);
                    exemplaireRepository.save(exemplaireList.get(i));
                }
            }
        }
    }*/
}


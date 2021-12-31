package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Entity.Dto.NewEditionDTO;
import com.bibliotheque.API.Entity.Edition;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Repository.BookRepository;
import com.bibliotheque.API.Repository.EditionRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    EditionRepository editionRepository;
    @Autowired
    ExemplaireService exemplaireService;
    @Autowired
    BookRepository bookRepository;



    public void save (NewEditionDTO newEditionDTO){
        logger.info("save new edition : " + newEditionDTO.name);
        Book book =  this.bookRepository.findById(newEditionDTO.book).get();
        Edition edition = new Edition();
        edition.setBook(book);
        edition.setName(newEditionDTO.name);
        editionRepository.save(edition);
    }

    public void delete (int id){
        logger.info("delete edition id : " + id);
        Edition edition = this.editionRepository.findById(id);
        editionRepository.delete(edition);
    }

    public void countNumberOfExemplaire(int id){
    logger.info("count number of exemplaire on edition id : " + id);
    List<Exemplaire> exemplaires =  exemplaireService.findByEdition_id(id);
    Edition edition = editionRepository.findById(id);
    int number = exemplaires.size();
    }

    public List<Edition> findByBookId(int id) {
        logger.info("find list edition with book id : " + id);
        List<Edition> editions = this.editionRepository.findByBook_Id(id);
        return editions;
    }

}

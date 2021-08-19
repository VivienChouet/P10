package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttenteRepository extends JpaRepository<Attente, Integer> {

    List<Attente> findByBookAndEdition(int book_id, String edition);

}
package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Dto.AttenteDTO;
import com.bibliotheque.API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttenteRepository extends JpaRepository<Attente, Integer> {


    List<Attente> findByEditionId(int edition_id);

    List<Attente> findByEdition_IdAndUser_Id(int id, int userId);

    List<Attente> findAttenteByDateMailNotNull();

    List<Attente> findByEdition_IdAndMail(int edition_id, boolean mail);


}
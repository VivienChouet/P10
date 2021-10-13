package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUser_IdAndEnded(int id, boolean b);

    List<Reservation> findByEndedAndBatch(boolean ended,boolean batch);

    List<Reservation> findByAttente(boolean attente);

    List<Reservation> findByUser_IdAndExemplaire_Edition_IdAndEnded(int user, int edition, boolean ended);

    List<Reservation> findByUser_IdAndExemplaire_Edition_Id(int user, int edition);

    List<Reservation> findByRecuperer(boolean b);
}

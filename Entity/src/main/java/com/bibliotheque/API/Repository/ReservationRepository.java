package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.API.Entity.Dto.ReservationDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUser_IdAndEnded(int id, boolean b);

    List<Reservation> findByEndedAndBatch(boolean ended,boolean batch);

    Reservation findByExemplaireAndEnded(Exemplaire exemplaire, boolean ended);
}

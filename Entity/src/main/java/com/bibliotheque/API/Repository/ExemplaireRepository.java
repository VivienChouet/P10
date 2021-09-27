package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {


    Exemplaire findById(int id);

    List<Exemplaire> findByAvailable(boolean b);

    List<Exemplaire> findByEdition_IdAndAvailable(int id, boolean b);

    List<Exemplaire> findByEdition_Id(int id);


}

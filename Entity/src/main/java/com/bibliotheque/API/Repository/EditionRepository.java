package com.bibliotheque.API.Repository;


import com.bibliotheque.API.Entity.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {


    @Query("select e from Edition e where e.book.id = ?1")
    List<Edition> findByBook_Id(int id);

    Edition findById(int id);
}

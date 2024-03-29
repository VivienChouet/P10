package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Dto.AttenteDTO;
import com.bibliotheque.API.Entity.Dto.ReservationDTO;
import com.bibliotheque.API.Entity.Mapper.AttenteMapper;
import com.bibliotheque.API.Entity.Mapper.ReservationMapper;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Service.AttenteService;
import com.bibliotheque.API.Service.BatchService;
import com.bibliotheque.API.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    BatchService batchService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    AttenteService attenteService;
    @Autowired
    AttenteMapper attenteMapper;

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> Batch() {
        List<Reservation> reservations = batchService.batch();
        if(reservations != null){
        return new ResponseEntity<>(reservationMapper.toDto(reservations), HttpStatus.OK);}
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<ReservationDTO>> BatchUpdate(@PathVariable int id) {
        Reservation reservation = this.reservationService.findById(id);
        batchService.update(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/attente")
    public ResponseEntity<List<AttenteDTO>> BatchAttente (){
        List<Attente> attentes = attenteService.attenteBatch();
        return new ResponseEntity<>(attenteMapper.toDto(attentes), HttpStatus.OK);
    }

    @PostMapping("/attente/{id}")
    public ResponseEntity<List<AttenteDTO>> BatchAttenteUpdate (@PathVariable int id) throws Exception {
        attenteService.attenteNonRecuperer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Attente;
import com.bibliotheque.API.Entity.Dto.AttenteDTO;
import com.bibliotheque.API.Entity.Dto.NewAttenteDTO;
import com.bibliotheque.API.Entity.Mapper.AttenteMapper;
import com.bibliotheque.API.Service.AttenteService;
import com.bibliotheque.API.Service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attente")
public class AttenteController {

    @Autowired
    AttenteMapper attenteMapper;
    @Autowired
    AttenteService attenteService;
    @Autowired
    ExemplaireService exemplaireService;

    @RequestMapping("attentedate")
    public ResponseEntity DateReturnMax (){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity NewAttente (@RequestBody NewAttenteDTO newAttenteDTO){
        if (attenteService.attentePossible(newAttenteDTO.edition)) {
        attenteService.newAttente(newAttenteDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
    }

    @PostMapping("/{id}")
            public ResponseEntity ValidationAttente(@PathVariable int id)
    {
        attenteService.AttenteAccepter(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity ListAttente (){
        List<Attente> attente =  this.attenteService.findByDate_mail();
        return new ResponseEntity(attenteMapper.toDto(attente), HttpStatus.OK);
    }
}

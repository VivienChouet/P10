package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Mapper.AttenteMapper;
import com.bibliotheque.API.Service.AttenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/attente")
public class AttenteController {

    @Autowired
    AttenteMapper attenteMapper;
    @Autowired
    AttenteService attenteService;

    @RequestMapping("attentedate")
    public ResponseEntity DateReturnMax (){
        Date date = attenteService.dateReturnExemplaire(14," Gallimard jeunesse");
        System.out.println(date);
        return new ResponseEntity(HttpStatus.OK);
    }
}

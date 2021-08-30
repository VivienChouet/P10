package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Dto.EditionDTO;
import com.bibliotheque.API.Entity.Edition;
import com.bibliotheque.API.Entity.Mapper.EditionMapper;
import com.bibliotheque.API.Service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edition")
public class EditionController {

    @Autowired
    EditionService editionService;

    @Autowired
    EditionMapper editionMapper;

    @PostMapping("/new")
    public ResponseEntity <EditionDTO> newEdition (@RequestBody EditionDTO editionDTO){
        editionService.save(editionMapper.toEntity(editionDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EditionDTO>> listEditionByBookId (@PathVariable int id){
        List<Edition> editions = this.editionService.findByBookId(id);
        return new ResponseEntity<>(editionMapper.toDto(editions),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EditionDTO> deleteEditionId (@PathVariable int id){
        editionService.delete(id);
        return new ResponseEntity <>(HttpStatus.OK);
    }

}

package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.NewEditionDTO;
import com.bibliotheque.Web.service.EditionService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/edition")
public class EditionController {

@Autowired
    UserService userService;
@Autowired
    EditionService editionService;

    @GetMapping("/new/{id}")
    public String newExemplaire(@PathVariable int id,  Model model) throws JsonProcessingException {
        NewEditionDTO newEditionDTO = new NewEditionDTO();
        newEditionDTO.setBook(id);
        model.addAttribute("edition", newEditionDTO);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return "edition/new";
    }

    @PostMapping("/new/{id}")
    public ModelAndView newExemplaire (@ModelAttribute NewEditionDTO newEditionDTO, @PathVariable int id, Model model) throws JsonProcessingException {
        model.addAttribute("edition", newEditionDTO);
        newEditionDTO.setBook(id);
        editionService.save(newEditionDTO);
        return new ModelAndView("redirect:/");
    }



}

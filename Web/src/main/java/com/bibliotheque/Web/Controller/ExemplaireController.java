package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.service.ExemplaireService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;
    @Autowired
    UserService userService;

 @RequestMapping("/new/{id}")
    public ModelAndView newExemplaire ( @PathVariable int id, Model model) throws JsonProcessingException {

        exemplaireService.save(id);

     return new ModelAndView("redirect:/");
    }
}

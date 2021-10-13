package com.bibliotheque.Web.Controller;


import com.bibliotheque.Web.Entity.Dto.AttenteDTO;
import com.bibliotheque.Web.Entity.Dto.ListReservationDTO;
import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
import com.bibliotheque.Web.service.AttenteService;
import com.bibliotheque.Web.service.ReservationService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    AttenteService attenteService;

    @GetMapping("/")
    public String gestionAdmin (Model model) throws JsonProcessingException {
        List<UserDTO> userDTOS = this.userService.listUser();
        model.addAttribute("users", userDTOS);

        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

        return "admin/gestion-admin";
    }

    @PostMapping("/{id}")
    public String gestionAdmin (@PathVariable int id){
        return "home";
    }

    @GetMapping("/reservation")
    public String reservationAdmin(Model model) throws JsonProcessingException {
    List<ListReservationDTO> listReservationDTOS = this.reservationService.listReservation();
    model.addAttribute("reservations", listReservationDTOS);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

    return "admin/gestion-reservation";
    }

    @PostMapping("/reservation/{id}")
    public ModelAndView reservationEnded (@PathVariable int id){
        reservationService.endedReservation(id);
        return new ModelAndView("redirect:/admin/reservation" );
    }

    @GetMapping("/attente")
    public String attenteAdmin(Model model)throws JsonProcessingException{
        List<AttenteDTO> attenteDTOS = this.attenteService.listAttente();
        model.addAttribute("attentes",attenteDTOS);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

        return "admin/gestion-attente";
    }

    @PostMapping("/attente/{id}")
    public ModelAndView attenteRecupere (@PathVariable int id){
       attenteService.attenteRecuperer(id);
        return new ModelAndView("redirect:/admin/reservation" );
    }

}

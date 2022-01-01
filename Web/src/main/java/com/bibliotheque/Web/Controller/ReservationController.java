package com.bibliotheque.Web.Controller;


import com.bibliotheque.Web.Entity.Dto.ListReservationDTO;
import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.service.ReservationService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @RequestMapping("/edition")
    public ModelAndView newReservation(@RequestParam (name ="edition") int edition, Model model) throws JsonProcessingException {

        reservationService.newReservation(edition);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/myreservation")
    public String myReservation(Model model) throws JsonProcessingException {
        List<ListReservationDTO> myReservationDTOS = this.reservationService.reservationByUser();
        model.addAttribute("reservations", myReservationDTOS);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return "reservation/myReservation";
    }

    @PostMapping("/extension/{id}")
    public ModelAndView extension (@PathVariable int id, Model model){
        reservationService.extension(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/end/{id}")
    public ModelAndView endedReservation (@PathVariable int id) throws JsonProcessingException {
    reservationService.endedReservation(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/admin")
    public Object adminReservation(Model model) throws JsonProcessingException {
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        if (userService.admin()){
        List<ListReservationDTO> reservationDTOS = this.reservationService.listReservation();
        model.addAttribute("reservations", reservationDTOS);
                return "reservation/admin";}
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/attente")
    public ModelAndView newAttente(@RequestParam (name ="edition") int edition, Model model) throws JsonProcessingException {

        reservationService.newAttente(edition);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return new ModelAndView("redirect:/");
    }

}

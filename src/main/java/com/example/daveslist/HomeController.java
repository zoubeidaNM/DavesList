package com.example.daveslist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping("/")
    public String index(){
        return "listrooms";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/listrooms")
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "listrooms";
    }

    @GetMapping("/addroom")
    public String addRoom(Model model){

        model.addAttribute("room", new Room());

        return "roomform";

    }

    @PostMapping("/process")
    public String processForm(@Valid Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "roomform";
        }

        roomRepository.save(room);
        return "redirect:/listrooms";
    }

    @RequestMapping("/detail/{id}")
    public String showPet(@PathVariable("id") long id, Model model) {
        model.addAttribute("room", roomRepository.findOne(id));
        return "showroom";
    }

    @RequestMapping("/update/{id}")
    public String updatePet(@PathVariable("id") long id, Model model) {
        model.addAttribute("room", roomRepository.findOne(id));
        return "roomform";
    }
}

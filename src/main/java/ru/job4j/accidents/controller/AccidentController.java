package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.TypeService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private AccidentService accidentService;
    private TypeService typeService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("types", typeService.findAll());
        return "index";
    }

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("types", typeService.findAll());
        return "createAccident";
    }

    @PostMapping("/createAccident")
    public String addAccident(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        accident.setType(typeService.findById(typeId));
        accidentService.addAccident(accident);
        return "redirect:/index";
    }

    @GetMapping("/formEditAccident/{accidentId}")
    public String formEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", accidentService.findById(id));
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String editAccident(@ModelAttribute Accident accident) {
        accidentService.editAccident(accident);
        return "redirect:/index";
    }
}

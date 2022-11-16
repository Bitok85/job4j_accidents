package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }

    @GetMapping("/createAccident")
    public String createAccident(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "createAccident";
    }

    @PostMapping("/createAccident")
    public String addAccident(@ModelAttribute Accident accident) {
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

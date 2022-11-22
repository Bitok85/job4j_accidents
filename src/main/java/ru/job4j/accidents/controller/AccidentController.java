package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.RuleService;
import ru.job4j.accidents.service.TypeService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AccidentController {
    private AccidentService accidentService;
    private TypeService typeService;
    private RuleService ruleService;

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
        model.addAttribute("rules", ruleService.findAll());
        return "createAccident";
    }

    @PostMapping("/createAccident")
    public String addAccident(@ModelAttribute Accident accident,
                              @RequestParam("type.id") int typeId,
                              @RequestParam(value = "ruleIds", required = false) List<Integer> ruleIds) {
        accident.setType(typeService.findById(typeId));
        if (ruleIds != null) {
            accident.setRules(
                    ruleIds.stream()
                            .map(ruleId -> ruleService.findById(ruleId))
                            .collect(Collectors.toSet()));
        }

        accidentService.addAccident(accident);
        return "redirect:/index";
    }

    @GetMapping("/formEditAccident/{accidentId}")
    public String formEditAccident(Model model, @PathVariable("accidentId") int id) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String editAccident(@ModelAttribute Accident accident,
                               @RequestParam("type.id") int id,
                               @RequestParam("ruleIds") List<Integer> ruleIds) {
        accident.setType(typeService.findById(id));
        if (ruleIds != null) {
            accident.setRules(
                    ruleIds.stream()
                            .map(ruleId -> ruleService.findById(ruleId))
                            .collect(Collectors.toSet()));
        }
        accidentService.editAccident(accident);
        return "redirect:/index";
    }
}

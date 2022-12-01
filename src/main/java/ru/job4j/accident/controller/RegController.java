package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

@Controller
@AllArgsConstructor
public class RegController {
    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthorityService authorities;
    private static final Logger LOG = Logger.getLogger(RegController.class.getName());

    @GetMapping("reg")
    public String regPage(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "User already exists";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        try {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            LOG.error("User already exists");
            return "redirect:/reg?error=true";
        }
    }
}

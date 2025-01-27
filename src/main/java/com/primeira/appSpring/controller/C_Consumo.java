package com.primeira.appSpring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class C_Consumo {

    @GetMapping("/consumo")
    public String getConsumo(HttpSession session, Model model) {
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario"));
        }
        return "consumo/consumo";
    }
}

package cl.tingeso.mueblesstgo.controllers;

import cl.tingeso.mueblesstgo.services.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wage-view")
public class WageController {

    @Autowired
    WageService wageService;

    @GetMapping("{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("wage", wageService.getById(id, Boolean.TRUE));
        return "wage";
    }
}

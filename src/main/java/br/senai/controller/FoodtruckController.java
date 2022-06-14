package br.senai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodtruckController {

    @GetMapping("/foodtruck/list")
    public String foodTruckList(Model model){
        return "/foodtruck/list";
    }

}

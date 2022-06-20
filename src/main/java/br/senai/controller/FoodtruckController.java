package br.senai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodTruckController {

    @GetMapping("/foodtruck/list")
    public String barList(){
        return "/foodtruck/list";
    }

}

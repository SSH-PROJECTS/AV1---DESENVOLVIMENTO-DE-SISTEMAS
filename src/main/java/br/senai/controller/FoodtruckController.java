package br.senai.controller;

import br.senai.model.FoodTruck;
import br.senai.service.FoodTruckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodtruckController {

    @Autowired
    FoodTruckServiceImpl foodTruckService;

    @GetMapping("/foodtruck/list")
    public String foodTruckList(Model model){
        model.addAttribute("produtos", foodTruckService.findAll());
        return "/foodtruck/list";
    }

    /*
    @GetMapping("/foodTruck/add")
    public String foodtruckList(Model model){
        model.addAttribute("produto", new FoodTruck());
        return "/foodTruck/add";
    }*/

    @GetMapping("/foodtruck/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        model.addAttribute("produto", foodTruckService.findById(id));
        return "/foodtruck/edit";
    }

    @PostMapping("/foodtruck/save")
    public String save(FoodTruck foodTruck,Model model){
        try{
            foodTruckService.save(foodTruck);
            return "redirect:/foodtruck/list";
        }catch (Exception e){
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("produto", foodTruckService.findById(foodTruck.getId()));

            return "foodtruck/edit";
        }
    }

    @GetMapping("/foodtruck/delete/{id}")
    public String delete(@PathVariable long id){
        try{
            foodTruckService.deleteById(id);
        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }

        return "redirect:/foodtruck/list";
    }

}

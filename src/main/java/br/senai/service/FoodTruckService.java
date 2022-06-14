package br.senai.service;

import br.senai.model.FoodTruck;

import java.util.List;

public interface FoodTruckService {

    public List<FoodTruck> findAll();
    public List<FoodTruck> findById();
    public FoodTruck findByProduto(String produto);

}

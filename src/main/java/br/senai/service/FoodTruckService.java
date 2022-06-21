package br.senai.service;


import br.senai.model.FoodTruck;

import java.util.List;

public interface FoodTruckService {

    public List<FoodTruck> findAll();
    public FoodTruck findById(Long id);
    public FoodTruck findByNome(String nome);
    public FoodTruck findByTipo(String tipo);
    public FoodTruck findByPreco(String preco);
    public FoodTruck findByAlcoolico(Boolean alcoolico);
    public FoodTruck save(FoodTruck foodTruck);
    public void deleteById(Long id);


}

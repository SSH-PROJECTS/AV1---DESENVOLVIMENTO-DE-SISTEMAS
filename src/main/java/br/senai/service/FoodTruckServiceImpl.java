package br.senai.service;

import br.senai.model.FoodTruck;
import br.senai.repository.FoodTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodTruckServiceImpl implements FoodTruckService{

    @Autowired
    FoodTruckRepository foodTruckRepository;

    @Override
    public List<FoodTruck> findAll() {
        return foodTruckRepository.findAll(Sort.by("id"));
    }

    @Override
    public FoodTruck findById(Long id) {
        Optional listProdutos = foodTruckRepository.findById(id);
        if (!listProdutos.isEmpty()){
            return (FoodTruck) listProdutos.get();
        } else{
            return new FoodTruck();
        }
    }

    @Override
    public FoodTruck findByNome(String nome) {
        return foodTruckRepository.findByNome(nome);
    }

    @Override
    public FoodTruck findByTipo(String tipo) {
        return foodTruckRepository.findByTipo(tipo);
    }

    @Override
    public FoodTruck findByPreco(String preco) {
        return foodTruckRepository.findByPreco(preco);
    }

    @Override
    public FoodTruck findByAlcoolico(Boolean alcoolico) {
        return foodTruckRepository.findByAlcoolico(alcoolico);
    }

    @Override
    public FoodTruck save(FoodTruck foodTruck) {
        try{
            return foodTruckRepository.save(foodTruck);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            foodTruckRepository.deleteById(id);
        }catch (Exception e){
            throw e;
        }
    }
}

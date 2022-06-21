package br.senai.repository;

import br.senai.model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {
    public List<FoodTruck> findAll();
    public FoodTruck findByNome(String nome);
    public FoodTruck findByTipo(String tipo);
    public FoodTruck findByPreco(String preco);
    public FoodTruck findByAlcoolico(Boolean alcoolico);
}

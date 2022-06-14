package br.senai.repository;

import br.senai.model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {

    public FoodTruck findByProduto(String produto);
    public FoodTruck findByPreco(Double preco);
    public FoodTruck findByQuantidade(int quantidade);

}

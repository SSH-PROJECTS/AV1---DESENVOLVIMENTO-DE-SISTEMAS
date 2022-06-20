package br.senai.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="foodTruck")
public class FoodTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String nome;

    @Size(max = 100)
    private String tipo;

    @NotNull
    @Size(max = 10)
    private Double preco;

    private Boolean alcoolico;

    /*  GETTERS AND SETTERS  */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Boolean getAlcoolico() {
        return alcoolico;
    }
    public void setAlcoolico(Boolean alcoolico) {
        this.alcoolico = alcoolico;
    }

    @Override
    public String toString() {
        return "FoodTruck{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", preco=" + preco +
                ", alcolico=" + alcoolico +
                '}';
    }
}

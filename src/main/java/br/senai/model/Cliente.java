package br.senai.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    @Size(max = 100)
    private String nome;

    @Size(max = 100)
    private String email;

    @Size(max=25)
    private String cpf;

    @ManyToMany(mappedBy = "clientes")
    private List<Evento> eventos;

    @ManyToMany
    @JoinTable(
            name="cliente_permissao",
            joinColumns=@JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name="permissao_id")
    )
    private List<Permissao> permissoes;


    public List<Permissao> getPermissaos() {
        return permissoes;
    }

    public void setPermissaos(List<Permissao> permissaos) {
        this.permissoes = permissaos;
    }

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

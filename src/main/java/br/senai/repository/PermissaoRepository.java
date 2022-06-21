package br.senai.repository;

import br.senai.model.Permissao;
import br.senai.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    public List<Permissao> findByUsuariosIn(Cliente ... usuarios);
}

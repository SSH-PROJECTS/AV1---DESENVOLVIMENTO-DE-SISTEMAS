package br.senai.repository;

import br.senai.model.Usuario;
import br.senai.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    public List<Permissao> findByUsuariosIn(Usuario ... usuarios);
}

package br.senai.service;

import br.senai.model.Cliente;
import br.senai.model.Permissao;
import br.senai.repository.PermissaoRepository;
import br.senai.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService, UserDetailsService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll(Sort.by("nome"));
    }

    @Override
    public Cliente findById(Long id){
        Optional listCliente = clienteRepository.findById(id);
        if (!listCliente.isEmpty()){
            return (Cliente) listCliente.get();
        } else {
            return  new Cliente();
        }

    }

    @Override
    public Cliente findByNome
            (String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Cliente save(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteById(Long id){
        try {
            clienteRepository.deleteById(id);
        }catch (Exception e){
            throw e;
        }
    }

    public Collection<GrantedAuthority> authorities(Cliente usuario){

        Collection<GrantedAuthority> autorizacoes = new ArrayDeque<>();
        List<Permissao> permissoes = permissaoRepository.findByUsuariosIn(usuario);
        for(Permissao permissao: permissoes){
            autorizacoes.add(new SimpleGrantedAuthority(("ROLE_" + permissao.getNome())));
        }
        System.out.println("Permissões: " + autorizacoes);
        return autorizacoes;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente usuario = clienteRepository.findByNome(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        UserDetails user = User.withUsername(usuario.getNome())
                .password(usuario.getNome())
                .authorities(authorities(usuario)).build();

        return user;
    }
}

package br.senai.service;

import br.senai.model.Permissao;
import br.senai.model.Usuario;
import br.senai.service.UsuarioService;
import br.senai.repository.PermissaoRepository;
import br.senai.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> updateUsuario = usuarioRepository.findById(id);
        if (!updateUsuario.isEmpty()){
            updateUsuario.get().setNome(usuario.getNome());
            updateUsuario.get().setUsername(usuario.getUsername());
            updateUsuario.get().setPassword(usuario.getPassword());
            return usuarioRepository.save(updateUsuario.get());
        } else {
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        try {
            String newPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
            usuario.setPassword(newPassword);
            return usuarioRepository.save(usuario);
        }
        catch (Exception e){ throw e; }
    }

    @Override
    public Optional<Usuario> read(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return usuarioRepository.findById(id).isEmpty();
        } catch (Exception e){
            return false;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario n??o encontrado");
        }

        UserDetails user = User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities(usuario)).build();

        return user;
    }

    public Collection<GrantedAuthority> authorities(Usuario usuario){

        Collection<GrantedAuthority> autorizacoes = new ArrayDeque<>();
        List<Permissao> permissoes = permissaoRepository.findByUsuariosIn(usuario);
        for(Permissao permissao: permissoes){
            autorizacoes.add(new SimpleGrantedAuthority(("ROLE_" + permissao.getNome())));
        }
        return autorizacoes;
    }
}
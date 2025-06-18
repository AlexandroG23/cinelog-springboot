package com.aasencios.springbootcinelogtmdb.service;

import com.aasencios.springbootcinelogtmdb.model.Usuario;
import com.aasencios.springbootcinelogtmdb.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(String nombre, String email, String password){
        Usuario usuario = new Usuario(nombre, email, passwordEncoder.encode(password));
        return usuarioRepository.save(usuario);
    }
}

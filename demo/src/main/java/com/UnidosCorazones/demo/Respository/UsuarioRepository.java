package com.UnidosCorazones.demo.Respository;

import com.UnidosCorazones.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Spring Security buscará por 'username'. Asumimos que el 'username'
    // que el usuario introduce es su correo electrónico.
    Optional<Usuario> findByCorreo(String correo);
}

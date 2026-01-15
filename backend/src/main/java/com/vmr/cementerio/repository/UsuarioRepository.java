package com.vmr.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Usuario;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

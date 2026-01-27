package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Ciudadano;
import java.util.Optional;
import java.util.List;

@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {
    Optional<Ciudadano> findByEmail(String email);
    Optional<Ciudadano> findByDni(String dni);
    List<Ciudadano> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrEmailContainingIgnoreCase(String nombre, String apellidos, String email);
}

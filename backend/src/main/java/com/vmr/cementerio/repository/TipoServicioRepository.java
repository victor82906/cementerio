package com.vmr.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vmr.cementerio.model.TipoServicio;
import java.util.Optional;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {
    
    Optional<TipoServicio> findByNombre(String nombre);

}

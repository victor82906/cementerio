package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
}

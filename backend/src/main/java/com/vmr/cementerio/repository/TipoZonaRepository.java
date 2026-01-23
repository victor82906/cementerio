package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.TipoZona;

@Repository
public interface TipoZonaRepository extends JpaRepository<TipoZona, Long> {
    
}

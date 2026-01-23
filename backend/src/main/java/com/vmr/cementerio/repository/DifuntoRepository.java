package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Difunto;

@Repository
public interface DifuntoRepository extends JpaRepository<Difunto, Long> {
    
}

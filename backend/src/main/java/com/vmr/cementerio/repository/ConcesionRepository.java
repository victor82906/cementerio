package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Concesion;

@Repository
public interface ConcesionRepository extends JpaRepository<Concesion, Long>{
    
}

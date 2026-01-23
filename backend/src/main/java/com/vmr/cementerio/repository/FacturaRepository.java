package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
}

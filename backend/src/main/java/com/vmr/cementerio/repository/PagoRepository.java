package com.vmr.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vmr.cementerio.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    
}

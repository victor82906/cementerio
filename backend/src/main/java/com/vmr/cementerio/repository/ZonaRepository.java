package com.vmr.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Zona;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {
    
    List<Zona> findByCementerioId(Long id);

    boolean existsByIdAndCementerioAyuntamientoId(Long id, Long ayuntamientoId);


}

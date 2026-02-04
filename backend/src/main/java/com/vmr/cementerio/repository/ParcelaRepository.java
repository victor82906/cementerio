package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Parcela;
import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
    
    List<Parcela> findByZonaId(Long id);

    boolean existsByIdAndZonaCementerioAyuntamientoId(Long id, Long ayuntamientoId);

}

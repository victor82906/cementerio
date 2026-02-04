package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Cementerio;
import java.util.List;

@Repository
public interface CementerioRepository extends JpaRepository<Cementerio, Long>{
    // para consultas que vayan a devolver varias tuplas no se debe utilizar Optional
    List<Cementerio> findByNombreContainingIgnoreCaseOrAyuntamientoNombreContainingIgnoreCase(String nombre, String nombreAyuntamiento);

    List<Cementerio> findByAyuntamientoId(Long id);

    boolean existsByIdAndAyuntamientoId(Long id, Long ayuntamientoId);

}

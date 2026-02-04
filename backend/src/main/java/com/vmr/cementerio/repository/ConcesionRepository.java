package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Concesion;
import java.util.Optional;
import java.util.List;

@Repository
public interface ConcesionRepository extends JpaRepository<Concesion, Long>{
    
    Optional<Concesion> findByParcelaId(Long id);

    List<Concesion> findByCiudadanoId(Long id);

    boolean existsByParcelaIdAndCiudadanoId(Long parcelaId, Long ciudadanoId);

    boolean existsByIdAndCiudadanoId(Long id, Long ciudadanoId);

}

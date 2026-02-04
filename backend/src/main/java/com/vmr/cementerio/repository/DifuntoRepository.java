package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Difunto;
import java.util.List;

@Repository
public interface DifuntoRepository extends JpaRepository<Difunto, Long> {
    
    List<Difunto> findByParcelaId(Long id);

    List<Difunto> findByParcela_Concesion_Ciudadano_Id(Long id);

    boolean existsByIdAndParcelaConcesionCiudadanoId(Long id, Long ciudadanoId);

}

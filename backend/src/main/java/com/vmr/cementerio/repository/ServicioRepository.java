package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Servicio;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
    List<Servicio> findByParcelaId(Long id);

    List<Servicio> findByParcela_Zona_Cementerio_Ayuntamiento_Id(Long id);

    boolean existsByIdAndParcelaConcesionCiudadanoId(Long id, Long ciudadanoId);

    boolean existsByIdAndParcelaZonaCementerioAyuntamientoId(Long id, Long ayuntamientoId);

}

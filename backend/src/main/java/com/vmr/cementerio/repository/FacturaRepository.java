package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Factura;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
    List<Factura> findDistinctByConcesion_Ciudadano_IdOrServicio_Parcela_Concesion_Ciudadano_Id(Long ciudadanoId1, Long ciudadanoId2);

    List<Factura> findDistinctByConcesion_Parcela_Zona_Cementerio_Ayuntamiento_IdOrServicio_Parcela_Zona_Cementerio_Ayuntamiento_Id(Long aytoId1, Long aytoId2);

    boolean existsByIdAndConcesion_Ciudadano_IdOrIdAndServicio_Parcela_Concesion_Ciudadano_Id(
        Long facturaId1, Long ciudadanoId1, 
        Long facturaId2, Long ciudadanoId2
    );

    boolean existsByIdAndConcesion_Parcela_Zona_Cementerio_Ayuntamiento_IdOrIdAndServicio_Parcela_Zona_Cementerio_Ayuntamiento_Id(
        Long facturaId1, Long ayuntamientoId1, 
        Long facturaId2, Long ayuntamientoId2
    );

}

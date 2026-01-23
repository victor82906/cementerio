package com.vmr.cementerio.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vmr.cementerio.model.Ayuntamiento;

@Repository
public interface AyuntamientoRepository extends JpaRepository<Ayuntamiento, Long> {

    Optional<Ayuntamiento> findByEmail(String email);

    List<Ayuntamiento> findByNombreContainingIgnoreCase(String nombre);

}

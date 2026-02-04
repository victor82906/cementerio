package com.vmr.cementerio.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.vmr.cementerio.repository.AyuntamientoRepository;
import com.vmr.cementerio.repository.CementerioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.vmr.cementerio.mapper.CementerioMapper;
import com.vmr.cementerio.model.Cementerio;
import com.vmr.cementerio.dto.response.CementerioDTO;
import com.vmr.cementerio.model.Ayuntamiento;
import java.util.List;
import com.vmr.cementerio.service.CementerioService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class CementerioServiceImpl implements CementerioService{
    
    private final CementerioRepository cementerioRepository;
    private final CementerioMapper cementerioMapper;
    private final AyuntamientoRepository ayuntamientoRepository;

    @Value("${ruta.fotos}")
    private String rutaFotos;

    public List<CementerioDTO> getAll(){
        return cementerioRepository.findAll()
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }

    public List<CementerioDTO> buscaPorNombre(String nombre){
        return cementerioRepository.findByNombreContainingIgnoreCaseOrAyuntamientoNombreContainingIgnoreCase(nombre, nombre)
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }

    public CementerioDTO findById(Long id){
        return cementerioMapper.toDTO(cementerioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado")));
    }

    @Transactional
    public CementerioDTO save(Long ayuntamientoId, CementerioDTO cementerioDTO, MultipartFile foto) throws IOException {
        Ayuntamiento ayuntamiento = ayuntamientoRepository.findById(ayuntamientoId)
                .orElseThrow(() -> new EntityNotFoundException("Ayuntamiento no encontrado"));

        Cementerio cementerio = cementerioMapper.toEntity(cementerioDTO);
        cementerio.setAyuntamiento(ayuntamiento);
        cementerio = cementerioRepository.save(cementerio);
        guardarFoto(cementerio.getId(), foto);
        cementerio = cementerioRepository.findById(cementerio.getId()).get();
        return cementerioMapper.toDTO(cementerio);
    }

    @Transactional
    public CementerioDTO update(Long id, CementerioDTO cementerioDTO){
        Cementerio cementerio = cementerioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio no encontrado"));

        cementerio = cementerioMapper.toEntity(cementerioDTO);
        return cementerioMapper.toDTO(cementerioRepository.save(cementerio));
    }

    @Transactional
    public void delete(Long id){
        if(cementerioRepository.existsById(id)){
            cementerioRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Cementerio no encontrado");
        }
    }

    public List<CementerioDTO> findByAyuntamientoId(Long id){
        return cementerioRepository.findByAyuntamientoId(id)
                .stream()
                .map(cementerioMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public String guardarFoto(Long id, MultipartFile archivo) throws IOException {
        // 1. Buscamos al usuario o lanzamos excepción si no existe
        Cementerio cementerio = cementerioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cementerio con ID " + id + " no encontrado"));

        // 2. Validación manual: solo imágenes
        if (archivo.getContentType() == null || !archivo.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen válida (JPG/PNG)");
        }

        // 3. Gestión de archivos (Crear directorio si no existe)
        Path directorioPath = Paths.get(rutaFotos);
        if (!Files.exists(directorioPath)) {
            Files.createDirectories(directorioPath);
        }

        // 4. Borrar foto anterior para ahorrar espacio
        if (cementerio.getMapa() != null) {
            Files.deleteIfExists(directorioPath.resolve(cementerio.getMapa()));
        }

        // 5. Generar nombre único y guardar
        String extension = archivo.getOriginalFilename().substring(archivo.getOriginalFilename().lastIndexOf("."));
        String nombreArchivo = "cementerio_" + id + "_" + System.currentTimeMillis() + extension;
        Files.copy(archivo.getInputStream(), directorioPath.resolve(nombreArchivo));

        // 6. Actualizar BD
        cementerio.setMapa(nombreArchivo);
        cementerioRepository.save(cementerio);

        return nombreArchivo;
    }


}

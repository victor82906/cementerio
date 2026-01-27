package com.vmr.cementerio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.vmr.cementerio.dto.request.LoginRequest;
import com.vmr.cementerio.model.Usuario;
import com.vmr.cementerio.dto.response.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    LoginRequest toLoginRequest(Usuario usuario);

    Usuario toUsuario(LoginRequest loginRequest);

    @Mapping(target = "rol", ignore = true)
    Usuario toEntity(UsuarioDTO usaurioDTO);

    @Mapping(target = "contrasena", ignore = true)   
    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioDTO toDTO(Usuario usuario);

}

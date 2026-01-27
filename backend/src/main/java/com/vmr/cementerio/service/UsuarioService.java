package com.vmr.cementerio.service;

import java.util.List;
import com.vmr.cementerio.dto.response.UsuarioDTO;

public interface UsuarioService {

    public List<UsuarioDTO> getAll();

    public UsuarioDTO findByEmail(String email);

}

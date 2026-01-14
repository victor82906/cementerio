package com.vmr.cementerio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String correo;

    private String contrasena;

    private String nombre;

    private String telefono;

    private String direccion;

    private String foto;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

}

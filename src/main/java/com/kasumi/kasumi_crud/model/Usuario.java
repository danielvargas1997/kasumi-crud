package com.kasumi.kasumi_crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")  // tu tabla es “usuario”
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")     // coincide con tu columna real
    private Integer idUsuario;

    @Column(name = "nombreUsuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRol", nullable = false)   // aquí era idRol, no id_rol
    private Rol rol;

    public Usuario() {}

    // getters y setters
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer id) { this.idUsuario = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}

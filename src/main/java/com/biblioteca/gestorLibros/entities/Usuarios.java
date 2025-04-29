package com.biblioteca.gestorLibros.entities;

import java.util.Date;

public class Usuarios {

    private Long id;
    private String nombre;
    private String correo;
    private String fechaMiembro;

    public Usuarios() {

    }

    public Usuarios(Long id, String nombre, String correo, String fechaMiembro) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.fechaMiembro = fechaMiembro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaMiembro() {
        return fechaMiembro;
    }

    public void setFechaMiembro(String fechaMiembro) {
        this.fechaMiembro = fechaMiembro;
    }
}

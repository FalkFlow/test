package com.biblioteca.gestorLibros.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Libros {

    private Long id;
    @NotBlank(message = "El titulo no puede estar en blanco")
    private String titulo;
    @NotBlank(message = "El autor no puede estar en blanco")
    private String autor;
    @NotBlank(message = "El ISBN no puede estar en blanco")
    private String ISBN;
    @NotNull(message = "Tiene que haber copias disponibles para ingresar")
    @Min(value = 1, message = "Debe haber almenos una copia disponible")
    private Integer copiarDisponibles;

    public Libros() {
    }

    public Libros(Long id, String titulo, String autor, String ISBN, Integer copiarDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.copiarDisponibles = copiarDisponibles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getCopiarDisponibles() {
        return copiarDisponibles;
    }

    public void setCopiarDisponibles(Integer copiarDisponibles) {
        this.copiarDisponibles = copiarDisponibles;
    }
}

package com.biblioteca.gestorLibros.entities;

public class Libros {

    private Long id;
    private String titulo;
    private String autor;
    private String ISBN;
    private int copiarDisponibles;

    public Libros() {
    }

    public Libros(Long id, String titulo, String autor, String ISBN, int copiarDisponibles) {
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

    public int getCopiarDisponibles() {
        return copiarDisponibles;
    }

    public void setCopiarDisponibles(int copiarDisponibles) {
        this.copiarDisponibles = copiarDisponibles;
    }
}

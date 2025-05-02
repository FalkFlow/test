package com.biblioteca.gestorLibros.Data;

import com.biblioteca.gestorLibros.entities.Libros;
import com.biblioteca.gestorLibros.entities.Loan;
import com.biblioteca.gestorLibros.entities.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class DatStore {
    public static final List<Usuarios> usuarios = new ArrayList<>();
    public static final List<Libros> libros = new ArrayList<>();
    public static final List<Loan> loans = new ArrayList<>();

    static
    {
        usuarios.add(new Usuarios(1L, "Juan", "Juanito@gmail.com", "12/4/2024"));
        usuarios.add(new Usuarios(2L, "Pedro", "Pedro@gmail.com", "22/05/2020"));
        usuarios.add(new Usuarios(3L, "Judas", "Elvendio@gamil.com", "31/12/2019"));
        libros.add(new Libros(1L, "Juan y la ballena", "Pablo Coelho", "La paquita", 2));
        libros.add(new Libros(2L, "Juan y la ballena parte 2", "Pablo Coelho", "La paquita", 1));
    }
}


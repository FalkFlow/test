package com.biblioteca.gestorLibros.controllers;


import com.biblioteca.gestorLibros.entities.Usuarios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestUsuariosController {

    private static final List<Usuarios> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuarios(1L,"Juan","Juanito@gmail.com","12/4/2024"));
        usuarios.add(new Usuarios(2L,"Pedro","Pedro@gmail.com","22/05/2020"));
        usuarios.add(new Usuarios(3L,"Judas","Elvendio@gamil.com","31/12/2019"));
    }

    @GetMapping("/")
    public String home() {
        return "Biblioteca";
    }

    @GetMapping("/usuarios")
    public List<Usuarios> usuarios() {
        return usuarios;
    }
}

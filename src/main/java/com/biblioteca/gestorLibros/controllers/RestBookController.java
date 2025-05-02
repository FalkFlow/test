package com.biblioteca.gestorLibros.controllers;

import com.biblioteca.gestorLibros.entities.Libros;
import com.biblioteca.gestorLibros.entities.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestBookController {

    private List<Libros> libros = new ArrayList<>();

    {
        libros.add(new Libros(1L, "Juan y la ballena", "Pablo Coelho", "La paquita", 2));
        libros.add(new Libros(2L, "Juan y la ballena parte 2", "Pablo Coelho", "La paquita", 1));
    }

    @GetMapping("/libros")
    public ResponseEntity<Map<String, Object>> libros() {
        Map<String, Object> data = new HashMap<>();
        data.put("Mensaje", "Usuarios encontrados");
        data.put("Usuarios", libros);
        data.put("estatus",200);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/libros/{id}")
    public ResponseEntity<Map<String, Object>> libros(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();

        Libros libroEncontrado = null;
        for(Libros libro : libros){
            if(libro.getId().equals(id)){
                libroEncontrado = libro;
                break;
            }
        }

        if(libroEncontrado != null){
            data.put("Mensaje", "Usuario encontrado");
            data.put("Usuario", libroEncontrado);
            data.put("estatus",200);
            return ResponseEntity.ok(data);
        }else{
            data.put("Mensaje", "Usuario no encontrado");
            data.put("Usuario", libroEncontrado);
            data.put("estatus",404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<Map<String, Object>> addLibros(@Valid @RequestBody Libros libro, BindingResult result) {
        Map<String, Object> data = new HashMap<>();

        if (result.hasErrors()) {
            data.put("Mensaje", "Error de validacion");
            data.put("Errores", result.getFieldErrors().stream().map(e -> {
                Map<String, String> error = new HashMap<>();
                error.put("Campo", e.getField());
                error.put("error", e.getDefaultMessage());
                return error;
            }));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
        libros.add(libro);
        data.put("Mensaje", "Usuario agregado");
        data.put("Usuarios", libro);
        data.put("estatus", 201);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Map<String, Object>> actualizarLibros(@PathVariable Long id, @RequestBody Libros datosActualizados) {
        Map<String, Object> data = new HashMap<>();

        Libros libroEncontrado = null;
        for (Libros libro : libros) {
            if (libro.getId().equals(id)) {
                libroEncontrado = libro;
                break;
            }
        }

        if (libroEncontrado != null) {
            if (datosActualizados.getTitulo() != null) {
                libroEncontrado.setTitulo(datosActualizados.getTitulo());
            }
            if (datosActualizados.getAutor() != null) {
                libroEncontrado.setAutor(datosActualizados.getAutor());
            }
            if (datosActualizados.getISBN() != null) {
                libroEncontrado.setISBN(datosActualizados.getISBN());
            }

            data.put("mensaje", "Usuario actualizado");
            data.put("usuario", libroEncontrado);
            data.put("estatus", 200);
            return ResponseEntity.ok(data);
        } else {
            data.put("mensaje", "Usuario no encontrado");
            data.put("estatus", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Map<String, Object>> eliminarLibro(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();

        Libros libroEncontrado = null;
        for (Libros libro : libros) {
            if (libro.getId().equals(id)) {
                libroEncontrado = libro;
                break;
            }
        }

        if (libroEncontrado != null) {
            libros.remove(libroEncontrado);

            data.put("mensaje", "Usuario eliminado");
            data.put("usuarioEliminado", libroEncontrado);
            data.put("estatus", 200);
            return ResponseEntity.ok(data);
        } else {
            data.put("mensaje", "Usuario no encontrado");
            data.put("estatus", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }
}


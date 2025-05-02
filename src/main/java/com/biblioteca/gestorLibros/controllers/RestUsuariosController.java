package com.biblioteca.gestorLibros.controllers;


import com.biblioteca.gestorLibros.Data.DatStore;
import com.biblioteca.gestorLibros.entities.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestUsuariosController {

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> usuarios() {
        Map<String, Object> data = new HashMap<>();
        data.put("Mensaje", "Usuarios encontrados");
        data.put("Usuarios", DatStore.usuarios);
        data.put("estatus",200);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> usuario(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();

        Usuarios usuarioEncontrado = DatStore.usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(usuarioEncontrado != null){
            data.put("Mensaje", "Usuario encontrado");
            data.put("Usuario", usuarioEncontrado);
            data.put("estatus",200);
            return ResponseEntity.ok(data);
        }else{
            data.put("Mensaje", "Usuario no encontrado");
            data.put("Usuario", usuarioEncontrado);
            data.put("estatus",404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> addUsuario(@Valid @RequestBody Usuarios usuario, BindingResult result) {
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
        DatStore.usuarios.add(usuario);
        data.put("Mensaje", "Usuario agregado");
        data.put("Usuarios", usuario);
        data.put("estatus", 201);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios datosActualizados) {
        Map<String, Object> data = new HashMap<>();

        Usuarios usuarioEncontrado = DatStore.usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuarioEncontrado != null) {
            if (datosActualizados.getNombre() != null) {
                usuarioEncontrado.setNombre(datosActualizados.getNombre());
            }
            if (datosActualizados.getCorreo() != null) {
                usuarioEncontrado.setCorreo(datosActualizados.getCorreo());
            }
            if (datosActualizados.getFechaMiembro() != null) {
                usuarioEncontrado.setFechaMiembro(datosActualizados.getFechaMiembro());
            }

            data.put("mensaje", "Usuario actualizado");
            data.put("usuario", usuarioEncontrado);
            data.put("estatus", 200);
            return ResponseEntity.ok(data);
        } else {
            data.put("mensaje", "Usuario no encontrado");
            data.put("estatus", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();

        Usuarios usuarioEncontrado = DatStore.usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuarioEncontrado != null) {
            DatStore.usuarios.remove(usuarioEncontrado);

            data.put("mensaje", "Usuario eliminado");
            data.put("usuarioEliminado", usuarioEncontrado);
            data.put("estatus", 200);
            return ResponseEntity.ok(data);
        } else {
            data.put("mensaje", "Usuario no encontrado");
            data.put("estatus", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }
}

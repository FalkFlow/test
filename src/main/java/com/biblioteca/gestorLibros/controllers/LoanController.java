package com.biblioteca.gestorLibros.controllers;


import com.biblioteca.gestorLibros.DTO.LoanRequest;
import com.biblioteca.gestorLibros.Data.DatStore;
import com.biblioteca.gestorLibros.entities.Libros;
import com.biblioteca.gestorLibros.entities.Loan;
import com.biblioteca.gestorLibros.entities.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanRequest request) {
        Optional<Usuarios> usuario = DatStore.usuarios.stream()
                .filter(u -> u.getId().equals(request.getUserId()))
                .findFirst();
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        Optional<Libros> libro = DatStore.libros.stream()
                .filter(l -> l.getId().equals(request.getBookId()))
                .findFirst();
        if (libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }

        Libros book = libro.get();
        if (book.getCopiarDisponibles() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay copias disponibles");
        }
        
        Loan loan = new Loan();
        loan.setId((long) (DatStore.loans.size() + 1)); // Generar ID manual
        loan.setUserId(request.getUserId());
        loan.setBookId(request.getBookId());
        LocalDate dueDate;
        try{
            dueDate = LocalDate.parse(request.getDueDate());
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Due date invalido");
        }
        loan.setDueDate(dueDate);
        loan.setReturned(false);
        DatStore.loans.add(loan);

        book.setCopiarDisponibles(book.getCopiarDisponibles() - 1);

        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnLoan(@PathVariable Long id) {
        Optional<Loan> loanOpt = DatStore.loans.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
        if (loanOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }

        Loan loan = loanOpt.get();
        if (loan.isReturned()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El préstamo ya fue devuelto");
        }

        loan.setReturned(true);

        DatStore.libros.stream()
                .filter(b -> b.getId().equals(loan.getBookId()))
                .findFirst()
                .ifPresent(b -> b.setCopiarDisponibles(b.getCopiarDisponibles() + 1));

        return ResponseEntity.ok("Préstamo devuelto exitosamente");
    }
}


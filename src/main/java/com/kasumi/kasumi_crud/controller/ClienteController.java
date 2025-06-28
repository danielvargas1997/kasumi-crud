package com.kasumi.kasumi_crud.controller;

import com.kasumi.kasumi_crud.model.Cliente;
import com.kasumi.kasumi_crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> all() {
        return repo.findAll();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> one(@PathVariable Integer id) {
        Optional<Cliente> cliente = repo.findById(id);
        return cliente
                .map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente c) {
        Cliente saved = repo.save(c);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cDetails) {
        Optional<Cliente> existing = repo.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = existing.get();
        cliente.setNombre(cDetails.getNombre());
        cliente.setApellido(cDetails.getApellido());
        cliente.setTelefono(cDetails.getTelefono());
        cliente.setEmail(cDetails.getEmail());
        cliente.setFechaNacimiento(cDetails.getFechaNacimiento());
        Cliente updated = repo.save(cliente);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Cliente> existing = repo.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


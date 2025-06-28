package com.kasumi.kasumi_crud.controller;

import com.kasumi.kasumi_crud.model.Usuario;
import com.kasumi.kasumi_crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> all() {
        return repo.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> one(@PathVariable Integer id) {
        Optional<Usuario> usuario = repo.findById(id);
        return usuario
                .map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario u) {
        Usuario saved = repo.save(u);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario uDetails) {
        Optional<Usuario> existing = repo.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = existing.get();
        usuario.setNombreUsuario(uDetails.getNombreUsuario());
        usuario.setContrasena(uDetails.getContrasena());
        usuario.setRol(uDetails.getRol());
        Usuario updated = repo.save(usuario);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Usuario> existing = repo.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

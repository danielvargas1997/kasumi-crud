package com.kasumi.kasumi_crud;

import com.kasumi.kasumi_crud.model.Rol;
import com.kasumi.kasumi_crud.model.Usuario;
import com.kasumi.kasumi_crud.repository.UsuarioRepository;
import com.kasumi.kasumi_crud.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepo;
    private final UsuarioRepository userRepo;

    public DataLoader(RolRepository rolRepo, UsuarioRepository userRepo) {
        this.rolRepo = rolRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {
        // Crea rol ADMIN si no existe
        var adminRol = rolRepo.findById(1).orElseGet(() -> rolRepo.save(new Rol() {{ setNombreRol("ADMIN"); }}));
        // Crea usuario admin si no existe
        userRepo.findByNombreUsuario("admin")
                .orElseGet(() -> {
                    var u = new Usuario();
                    u.setNombreUsuario("admin");
                    u.setContrasena("admin123");
                    u.setRol(adminRol);
                    return userRepo.save(u);
                });
        System.out.println(">>> DataLoader: Rol y usuario admin asegurados");
    }
}

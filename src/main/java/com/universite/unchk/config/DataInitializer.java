package com.universite.unchk.config;

import com.universite.unchk.entity.Role;
import com.universite.unchk.entity.User;
import com.universite.unchk.entity.enums.ERole;
import com.universite.unchk.repository.RoleRepository;
import com.universite.unchk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Créer l'utilisateur admin s'il n'existe pas
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@unchk.sn");

            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
            admin.getRoles().add(adminRole);

            userRepository.save(admin);
            System.out.println("Utilisateur admin créé avec succès.");
        } else {
            // Mettre à jour le mot de passe s'il est incorrect (optionnel)
            User admin = userRepository.findByUsername("admin").orElse(null);
            if (admin != null && !passwordEncoder.matches("admin123", admin.getPassword())) {
                admin.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(admin);
                System.out.println("Mot de passe admin mis à jour.");
            }
        }
    }
}
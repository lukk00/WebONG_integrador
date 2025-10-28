package com.UnidosCorazones.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ¡ADVERTENCIA! Esto es obsoleto y muy inseguro.
        // Esto le dice a Spring que las contraseñas NO están codificadas.
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ... tu configuración de HttpSecurity sigue igual ...
        // (La lógica de .authorizeHttpRequests, .formLogin, etc., no cambia)

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMINISTRADOR") // (Asegúrate que el rol sea correcto)
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/inicio.html", "/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/admin-login")
                        .defaultSuccessUrl("/admin/campanias", true)
                        .usernameParameter("username")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}

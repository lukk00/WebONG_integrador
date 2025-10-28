package com.UnidosCorazones.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "campania")
@Getter @Setter
public class Campania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_campania;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha_inicio;

    private LocalDate fecha_fin;

    @Column(nullable = false, length = 200)
    private String lugar;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "imagen_url", length = 500) // Nueva columna
    private String imagenUrl;

    private Boolean visible;

    // Muchas campañas pertenecen a un administrador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false)
    @JsonBackReference("admin-campanias")
    private Administrador administrador;

    // Una campaña tiene muchas inscripciones
    @OneToMany(mappedBy = "campania", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("campania-inscripciones")
    private Set<Inscripcion> inscripciones;
}

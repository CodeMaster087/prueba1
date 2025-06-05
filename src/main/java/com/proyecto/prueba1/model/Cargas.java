package com.proyecto.prueba1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty; 

/**
 * Entidad que representa una carga en el sistema de transporte.
 * Contiene información esencial sobre la carga y su estado.
 */
@Entity
@Data
public class Cargas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idCarga;

    @Column
    private String descripcion;

    @Column
    private Double pesoKg;

    @Column
    private String dimensiones;

    @Column
    private String puntoOrigen;

    @Column
    private String puntoDestino;

    @Column
    private Double valorAPagar;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoCarga estado;

    // Relación con el usuario que publica la carga
    @ManyToOne
    @JoinColumn(name = "id_usuario_publicador")
    @JsonProperty("idUsuarioPublicador") 
    private Usuarios usuarioPublicador; // El usuario que ha publicado esta carga
}
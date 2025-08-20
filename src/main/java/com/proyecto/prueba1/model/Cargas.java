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
import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;




/**
 * Entidad que representa una carga en el sistema de transporte Carga Extra.
 * Contiene información sobre la descripción, peso, dimensiones,
 * puntos de origen y destino, valor a pagar, estado y relación con el usuario que la publica.
 */
@Entity
@Data
@Table(name = "cargas")
public class Cargas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga")
    private Long idCarga;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descripcion;

    @NotNull(message = "El peso no puede ser nulo")
    @Positive(message = "El peso debe ser mayor que 0")
    @Column(nullable = false)
    private Double pesoKg;

    @NotBlank(message = "Las dimensiones no pueden estar vacías")
    @Pattern(
        regexp = "^[0-9]+(\\.[0-9]+)?\\*[0-9]+(\\.[0-9]+)?\\*[0-9]+(\\.[0-9]+)?$",
        message = "Las dimensiones deben tener el formato L*W*H (ejemplo: 1.5*0.5*1.0)"
    )
    @Column(nullable = false, length = 50)
    private String dimensiones;

    @NotBlank(message = "El punto de origen no puede estar vacío")
    @Size(max = 100, message = "El punto de origen no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String puntoOrigen;

    @NotBlank(message = "El punto de destino no puede estar vacío")
    @Size(max = 100, message = "El punto de destino no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String puntoDestino;

    @NotNull(message = "El valor a pagar no puede ser nulo")
    @Positive(message = "El valor a pagar debe ser mayor que 0")
    @Column(nullable = false)
    private Double valorAPagar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCarga estado; // valor por defecto

    // Relación con el usuario que publica la carga
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("idUsuarioPublicador")
    private Usuarios usuarioPublicador; 
}
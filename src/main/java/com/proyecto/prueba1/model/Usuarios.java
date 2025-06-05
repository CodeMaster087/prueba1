package com.proyecto.prueba1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Table;          
import lombok.Data; 

/**
 * Entidad que representa a un usuario en el sistema de transporte de carga.
 * Puede ser una empresa, una persona que envía carga o un conductor.
 */
@Entity
@Table(name = "usuarios") // Explicitar el nombre de la tabla en la BD
@Data // Lombok genera automáticamente los getters, setters, constructor vacío, equals, hashCode, toString
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "iduser") // Define el nombre de la columna en la base de datos
    private Long iduser;

    @Column(name = "nomuser") // Define el nombre de la columna en la base de datos
    private String nomuser;

    @Column(name = "apellido") // Define el nombre de la columna en la base de datos
    private String apellido;

    @Column(name = "email") // Define el nombre de la columna en la base de datos
    private String email;

    // Con @Data, Lombok se encarga de generar automáticamente
    
    
}
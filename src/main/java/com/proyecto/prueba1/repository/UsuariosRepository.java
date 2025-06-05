
package com.proyecto.prueba1.repository;

import com.proyecto.prueba1.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de repositorio para la entidad Usuarios.
 * Extiende JpaRepository para obtener operaciones CRUD básicas.
 */
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    
}

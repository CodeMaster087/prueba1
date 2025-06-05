package com.proyecto.prueba1.repository;

import com.proyecto.prueba1.model.Cargas;
import com.proyecto.prueba1.model.EstadoCarga; // Necesario para buscar por estado
import org.springframework.data.jpa.repository.JpaRepository; // La interfaz base de Spring Data JPA
import org.springframework.stereotype.Repository; // Indica que es un componente de repositorio
import java.util.List; // Para retornar listas de cargas

/**
 * Interfaz de repositorio para la entidad Cargas.
 * Extiende JpaRepository para obtener automáticamente métodos CRUD básicos.
 * Permite definir métodos de consulta personalizados basados en nombres de métodos.
 */
@Repository 
public interface CargasRepository extends JpaRepository<Cargas, Long> {

    /**
     * Busca y retorna una lista de cargas que se encuentran en un estado específico.
     * @param estado El estado de la carga por el cual filtrar (ej. DISPONIBLE).
     * @return Una lista de cargas que coinciden con el estado dado.
     */
    List<Cargas> findByEstado(EstadoCarga estado);
    
}
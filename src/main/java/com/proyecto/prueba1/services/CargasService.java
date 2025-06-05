package com.proyecto.prueba1.services;

import com.proyecto.prueba1.model.Cargas;
import com.proyecto.prueba1.model.EstadoCarga; 
import com.proyecto.prueba1.model.Usuarios; // Necesario para referencias a Usuarios/conductores
import java.util.List;
import java.util.Optional; // Para buscar por ID

/**
 * Interfaz que define las operaciones de negocio para la gestión de cargas.
 * Incluye la publicación, listado, modificación, eliminación y la lógica de selección/entrega.
 */
public interface CargasService {

    /**
     * Publica una nueva carga en el sistema.
     * @param nuevaCarga La carga a guardar.
     * @return La carga guardada con su ID.
     */
    Cargas publicarNuevaCarga(Cargas nuevaCarga);

    /**
     * Obtiene una lista de todas las cargas registradas.
     * @return Lista de cargas.
     */
    List<Cargas> obtenerTodasLasCargas();

    /**
     * Obtiene una lista de cargas que están disponibles para ser seleccionadas.
     * @return Lista de cargas en estado DISPONIBLE.
     */
    List<Cargas> obtenerCargasDisponibles();

    /**
     * Busca una carga por su ID.
     * @param idCarga El ID de la carga a buscar.
     * @return Un Optional que contiene la carga si existe, o vacío.
     */
    Optional<Cargas> obtenerCargaPorId(Long idCarga);

    /**
     * Actualiza los detalles de una carga existente.
     * @param cargaActualizar La carga con los datos actualizados (debe incluir el ID).
     * @return La carga actualizada, o null si no se encontró.
     */
    Cargas actualizarCarga(Cargas cargaActualizar);

    /**
     * Elimina una carga por su ID.
     * @param idCarga El ID de la carga a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    Boolean eliminarCarga(Long idCarga);

    /**
     * Permite a un conductor seleccionar una carga disponible.
     * @param idCarga El ID de la carga a seleccionar.
     * @param idConductor El ID del conductor.
     * @return La carga actualizada a EN_PROCESO, o null si no fue posible.
     */
    Cargas seleccionarCarga(Long idCarga, Long idConductor);

    /**
     * Confirma la entrega de una carga por parte de un conductor.
     * @param idCarga El ID de la carga entregada.
     * @param idConductor El ID del conductor.
     * @return La carga actualizada a ENTREGADA, o null si no fue posible.
     */
    Cargas confirmarEntregaCarga(Long idCarga, Long idConductor);
}
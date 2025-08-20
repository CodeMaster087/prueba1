package com.proyecto.prueba1.controller;

import com.proyecto.prueba1.model.Cargas;
import com.proyecto.prueba1.services.CargasService; 
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional; // Para buscar por ID

/**
 * Controlador REST para la gestión de cargas.
 * Maneja las solicitudes HTTP relacionadas con la publicación, consulta,
 * actualización, eliminación, selección y confirmación de entrega de cargas.
 */
@RestController // Combina @Controller y @ResponseBody para construir APIs REST
@RequestMapping("/cargas") // Prefijo base para todos los endpoints de cargas
public class CargasController {

    @Autowired // Inyecta el servicio de cargas
    private CargasService cargasService;

    /**
     * Endpoint para **publicar una nueva carga**.
     * Mapea solicitudes POST a `/cargas`.
     * @param nuevaCarga El objeto Cargas a publicar.
     * @return ResponseEntity con la carga creada y estado HTTP 201 (Created), o un error.
     */
    @PostMapping("/publicar") // Mapea a POST /cargas/publicar
    public ResponseEntity<Cargas> publicarCarga(@Valid @RequestBody Cargas nuevaCarga) {
        Cargas cargaGuardada = cargasService.publicarNuevaCarga(nuevaCarga);
        if (cargaGuardada != null) {
            return new ResponseEntity<>(cargaGuardada, HttpStatus.CREATED); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    /**
     * Endpoint para **obtener todas las cargas** registradas.
     * Mapea solicitudes GET a `/cargas/mostrar`.
     * @return ResponseEntity con una lista de todas las cargas y estado HTTP 200 (OK).
     */
    @GetMapping("/mostrar") // Mapea a GET /cargas/mostrar
    public ResponseEntity<List<Cargas>> obtenerTodasLasCargas() {
        List<Cargas> cargas = cargasService.obtenerTodasLasCargas();
        return new ResponseEntity<>(cargas, HttpStatus.OK); // 200 OK
    }

    /**
     * Endpoint para obtener **cargas disponibles** para ser seleccionadas por conductores.
     * Mapea solicitudes GET a `/cargas/disponibles`.
     * @return ResponseEntity con una lista de cargas en estado DISPONIBLE y estado HTTP 200 (OK).
     */
    @GetMapping("/disponibles") // Mapea a GET /cargas/disponibles
    public ResponseEntity<List<Cargas>> obtenerCargasDisponibles() {
        List<Cargas> cargasDisponibles = cargasService.obtenerCargasDisponibles();
        return new ResponseEntity<>(cargasDisponibles, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener **una carga específica por su ID**.
     * Mapea solicitudes GET a `/cargas/{id}`.
     * @param id El ID de la carga a buscar.
     * @return ResponseEntity con la carga encontrada y 200 (OK), o 404 (Not Found).
     */
    @GetMapping("/{id}") // Mapea a GET /cargas/{id}
    public ResponseEntity<Cargas> obtenerCargaPorId(@PathVariable("id") Long id) {
        return cargasService.obtenerCargaPorId(id)
                .map(carga -> new ResponseEntity<>(carga, HttpStatus.OK)) // Si existe, retorna 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Si no, retorna 404 Not Found
    }

    /**
     * Endpoint para **actualizar una carga existente**.
     * Mapea solicitudes PUT a `/cargas/modificar`.
     * @param cargaActualizar La carga con los datos actualizados (debe incluir el ID).
     * @return ResponseEntity con la carga actualizada y 200 (OK), o 404 (Not Found).
     */
    @PutMapping("/modificar") // Mapea a PUT /cargas/modificar
    public ResponseEntity<Cargas> actualizarCarga(@RequestBody Cargas cargaActualizar) {
        Cargas cargaModificada = cargasService.actualizarCarga(cargaActualizar);
        if (cargaModificada != null) {
            return new ResponseEntity<>(cargaModificada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para **eliminar una carga** por su ID.
     * Mapea solicitudes DELETE a `/cargas/eliminar/{id}`.
     * @param id El ID de la carga a eliminar.
     * 
     */
    @DeleteMapping("/eliminar/{id}") // Mapea a DELETE /cargas/eliminar/{id}
    public ResponseEntity<Void> eliminarCarga(@PathVariable("id") Long id) {
        Boolean eliminado = cargasService.eliminarCarga(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    /**
     * Endpoint para que un conductor **seleccione una carga disponible**.
     * Mapea solicitudes POST a `/cargas/{idCarga}/seleccionar/{idConductor}`.
     * @param idCarga El ID de la carga a seleccionar.
     * @param idConductor El ID del conductor.
     * @return ResponseEntity con la carga actualizada o un estado de error.
     */
    @PostMapping("/{idCarga}/seleccionar/{idConductor}")
    public ResponseEntity<Cargas> seleccionarCarga(
            @PathVariable("idCarga") Long idCarga,
            @PathVariable("idConductor") Long idConductor) {

        Cargas cargaSeleccionada = cargasService.seleccionarCarga(idCarga, idConductor);
        if (cargaSeleccionada != null) {
            return new ResponseEntity<>(cargaSeleccionada, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }

    /**
     * Endpoint para que un conductor **confirme la entrega de una carga**.
     * 
     * @param idCarga El ID de la carga entregada.
     * @param idConductor El ID del conductor.
     * 
     */
    @PostMapping("/{idCarga}/confirmar-entrega/{idConductor}")
    public ResponseEntity<Cargas> confirmarEntregaCarga(
            @PathVariable("idCarga") Long idCarga,
            @PathVariable("idConductor") Long idConductor) {

        Cargas cargaConfirmada = cargasService.confirmarEntregaCarga(idCarga, idConductor);
        if (cargaConfirmada != null) {
            return new ResponseEntity<>(cargaConfirmada, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }
}
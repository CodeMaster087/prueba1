package com.proyecto.prueba1.services;

import com.proyecto.prueba1.model.Cargas;
import com.proyecto.prueba1.model.EstadoCarga;
import com.proyecto.prueba1.model.Usuarios;
import com.proyecto.prueba1.repository.CargasRepository;
import com.proyecto.prueba1.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CargasServiceImpl implements CargasService {

    @Autowired
    private CargasRepository cargasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    @Transactional
    public Cargas publicarNuevaCarga(Cargas nuevaCarga) {
        // --- CÓDIGO MODIFICADO Y CORRECTO PARA ESTE MÉTODO ---

        // 1. Verificar si se proporcionó un usuario publicador y su ID
        if (nuevaCarga.getUsuarioPublicador() == null || nuevaCarga.getUsuarioPublicador().getIduser() == null) {
            System.err.println("Error al publicar: No se especificó el ID del usuario publicador en la petición.");
            return null; // Esto hará que el controlador retorne un 400 Bad Request
        }

        Long idUsuarioPublicador = nuevaCarga.getUsuarioPublicador().getIduser();

        // 2. Buscar el usuario publicador en la base de datos usando el ID
        Optional<Usuarios> usuarioPublicadorOpt = usuariosRepository.findById(idUsuarioPublicador);

        // 3. Si el usuario no se encuentra en la base de datos, retornar null
        if (!usuarioPublicadorOpt.isPresent()) {
            System.err.println("Error al publicar: El usuario publicador con ID " + idUsuarioPublicador + " no existe en la base de datos.");
            return null; // Esto hará que el controlador retorne un 400 Bad Request
        }

        // 4. Asignar el objeto Usuarios *gestionado por JPA* a la nueva carga
        nuevaCarga.setUsuarioPublicador(usuarioPublicadorOpt.get());

        // 5. Establecer el estado inicial y guardar la carga
        nuevaCarga.setEstado(EstadoCarga.DISPONIBLE);
        return cargasRepository.save(nuevaCarga);

        
    }

    

    @Override
    @Transactional(readOnly = true)
    public List<Cargas> obtenerTodasLasCargas() {
        return cargasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargas> obtenerCargasDisponibles() {
        return cargasRepository.findByEstado(EstadoCarga.DISPONIBLE);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cargas> obtenerCargaPorId(Long idCarga) {
        return cargasRepository.findById(idCarga);
    }

    @Override
    @Transactional
    public Cargas actualizarCarga(Cargas cargaActualizar) {
        Optional<Cargas> cargaEncontrada = cargasRepository.findById(cargaActualizar.getIdCarga());

        if (cargaEncontrada.isPresent()) {
            Cargas cargaExistente = cargaEncontrada.get();
            cargaExistente.setDescripcion(cargaActualizar.getDescripcion());
            cargaExistente.setPesoKg(cargaActualizar.getPesoKg());
            cargaExistente.setDimensiones(cargaActualizar.getDimensiones());
            cargaExistente.setPuntoOrigen(cargaActualizar.getPuntoOrigen());
            cargaExistente.setPuntoDestino(cargaActualizar.getPuntoDestino());
            cargaExistente.setValorAPagar(cargaActualizar.getValorAPagar());
            return cargasRepository.save(cargaExistente);
        }
        System.out.println("Carga con ID " + cargaActualizar.getIdCarga() + " no encontrada para actualizar.");
        return null;
    }

    @Override
    @Transactional
    public Boolean eliminarCarga(Long idCarga) {
        if (cargasRepository.existsById(idCarga)) {
            cargasRepository.deleteById(idCarga);
            return true;
        }
        System.out.println("Carga con ID " + idCarga + " no encontrada para eliminar.");
        return false;
    }

    @Override
    @Transactional
    public Cargas seleccionarCarga(Long idCarga, Long idConductor) {
        Optional<Cargas> cargaOpt = cargasRepository.findById(idCarga);
        Optional<Usuarios> conductorOpt = usuariosRepository.findById(idConductor);

        if (cargaOpt.isPresent() && conductorOpt.isPresent()) {
            Cargas carga = cargaOpt.get();
            Usuarios conductor = conductorOpt.get();

            if (carga.getEstado() == EstadoCarga.DISPONIBLE) {
                carga.setEstado(EstadoCarga.EN_PROCESO);
                return cargasRepository.save(carga);
            } else {
                System.out.println("Carga ID " + idCarga + " no está DISPONIBLE para selección. Estado actual: " + carga.getEstado());
                return null;
            }
        }
        System.out.println("Carga ID " + idCarga + " o Conductor ID " + idConductor + " no encontrado.");
        return null;
    }

    @Override
    @Transactional
    public Cargas confirmarEntregaCarga(Long idCarga, Long idConductor) {
        Optional<Cargas> cargaOpt = cargasRepository.findById(idCarga);
        Optional<Usuarios> conductorOpt = usuariosRepository.findById(idConductor);

        if (cargaOpt.isPresent() && conductorOpt.isPresent()) {
            Cargas carga = cargaOpt.get();
            Usuarios conductor = conductorOpt.get();

            if (carga.getEstado() == EstadoCarga.EN_PROCESO) {
                carga.setEstado(EstadoCarga.ENTREGADA);
                return cargasRepository.save(carga);
            } else {
                System.out.println("Carga ID " + idCarga + " no está EN_PROCESO para confirmar su entrega. Estado actual: " + carga.getEstado());
                return null;
            }
        }
        System.out.println("Carga ID " + idCarga + " o Conductor ID " + idConductor + " no encontrado para confirmar entrega.");
        return null;
    }
}

package com.proyecto.prueba1.services;

import com.proyecto.prueba1.model.Usuarios;

/**
 * Interfaz de la capa de Servicio para la entidad Usuarios.
 * Define las operaciones de lógica de negocio relacionadas con los usuarios.
 */

public interface UsuariosService {
    // * Guarda un nuevo usuario en la base de datos.
    Usuarios newUsuarios (Usuarios newUsuarios);
    // * Obtiene una colección de todos los usuarios registrados.
    Iterable<Usuarios> getAll();
    // * Modifica un usuario existente en la base de datos.
    Usuarios modifyUsuarios (Usuarios usuarios);
    // * Elimina un usuario de la base de datos por su ID.
    Boolean deleteUsuarios (Long iduser);
    
}

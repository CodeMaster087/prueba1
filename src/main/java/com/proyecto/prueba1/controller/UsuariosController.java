
package com.proyecto.prueba1.controller;

import com.proyecto.prueba1.model.Usuarios;
import com.proyecto.prueba1.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de Usuarios.
 * Expone endpoints HTTP para realizar operaciones CRUD sobre los usuarios.
 */

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    
    @Autowired
    private UsuariosService usuariosService;
    
    @PostMapping("/nuevo")    
    public Usuarios newUsuarios(@RequestBody Usuarios newUsuarios) {
        return this.usuariosService.newUsuarios(newUsuarios);
    }
    
    @GetMapping("/mostrar")
    public Iterable<Usuarios> getAll(){
        return usuariosService.getAll();
    }
    
    @PostMapping("/modificar")
    public Usuarios updateUsuarios(@RequestBody Usuarios usuarios){
        return this.usuariosService.modifyUsuarios(usuarios);
    }
    
    @PostMapping (value="/{id}")
    public Boolean deleteUsuarios(@PathVariable(value="id") Long id){
        return this.usuariosService.deleteUsuarios(id);
    }
}

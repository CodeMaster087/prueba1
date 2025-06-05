
package com.proyecto.prueba1.services;

//

import com.proyecto.prueba1.model.Usuarios;
import com.proyecto.prueba1.repository.UsuariosRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements UsuariosService{
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public Usuarios newUsuarios(Usuarios newUsuarios) {
        return usuariosRepository.save(newUsuarios);
    }

    @Override
    public Iterable<Usuarios> getAll() {
        return this.usuariosRepository.findAll();
    }

    @Override
    public Usuarios modifyUsuarios(Usuarios usuarios) {
        
        Optional<Usuarios> usuariosEncontrado= this.usuariosRepository.findById(usuarios.getIduser());
        if(usuariosEncontrado.get()!=null){
            usuariosEncontrado.get().setNomuser(usuarios.getNomuser());
            usuariosEncontrado.get().setApellido(usuarios.getApellido());
            usuariosEncontrado.get().setEmail(usuarios.getEmail());
            return this.newUsuarios(usuariosEncontrado.get());
        }
        
        return null;
    }

    @Override
    public Boolean deleteUsuarios(Long iduser) {
        this.usuariosRepository.deleteById(iduser);
        return true;
    }
    
}


package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Interfaces.IUsuarioService;
import com.example.Ventas3718.Repositorios.IUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuario data;
    
    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Usuario> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}

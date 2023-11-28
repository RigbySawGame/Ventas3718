package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Repositorios.IMediopago;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediopagoService implements IMediopagoService {

    @Autowired
    private IMediopago data;
    
    @Override
    public List<Mediopago> Listar() {
        return (List<Mediopago>) data.findAll();
    }

    @Override
    public Optional<Mediopago> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Mediopago c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Mediopago> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}

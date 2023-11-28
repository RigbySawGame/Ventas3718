package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Proveedor;
import com.example.Ventas3718.Interfaces.IProveedorService;
import com.example.Ventas3718.Repositorios.IProveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private IProveedor data;
    
    @Override
    public List<Proveedor> Listar() {
        return (List<Proveedor>) data.findAll();
    }

    @Override
    public Optional<Proveedor> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Proveedor c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Proveedor> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}

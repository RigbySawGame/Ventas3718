package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Repositorios.IProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProducto data;
    
    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}

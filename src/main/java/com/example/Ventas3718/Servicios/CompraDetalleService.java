package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.CompraDetalle;
import com.example.Ventas3718.Interfaces.ICompraDetalleService;
import com.example.Ventas3718.Repositorios.ICompraDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraDetalleService implements ICompraDetalleService {

    @Autowired
    private ICompraDetalle data;
    
    @Override
    public List<CompraDetalle> Listar() {
        return (List<CompraDetalle>) data.findAll();
    }

    @Override
    public Optional<CompraDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(CompraDetalle c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<CompraDetalle> Buscar(String desc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CompraDetalle> BuscarPorIdCompras(int id) {
        return data.FindByCompra(id);
    }
    
    
}

package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Venta;
import com.example.Ventas3718.Interfaces.IVentaService;
import com.example.Ventas3718.Repositorios.IVenta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVenta data;
    
    @Override
    public List<Venta> Listar() {
        return (List<Venta>) data.findAll();
    }

    @Override
    public Optional<Venta> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Venta c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Venta> Buscar(String desc) {
        return data.findForAll(desc);
    }

    @Override
    public int UltimoIdVenta() {
        return data.ConsultarIdVenta();
    }

    @Override
    public Venta BuscarId(int cod) {
        return data.BuscarXId(cod);
    }

    @Override
    public Double PrecioTotal() {
        return data.calcularPrecioTotal();
    }

    
}

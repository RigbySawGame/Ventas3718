package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.VentaDetalle;
import com.example.Ventas3718.Interfaces.IVentaDetalleService;
import com.example.Ventas3718.Repositorios.IVentaDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDetalleService implements IVentaDetalleService {

    @Autowired
    private IVentaDetalle data;
    
    @Override
    public List<VentaDetalle> Listar() {
        return (List<VentaDetalle>) data.findAll();
    }

    @Override
    public Optional<VentaDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(VentaDetalle c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<VentaDetalle> Buscar(String desc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VentaDetalle> BuscarPorIdVentas(int id) {
        return data.FindByVenta(id);
    }


    
}

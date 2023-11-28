package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Compra;
import com.example.Ventas3718.Interfaces.ICompraService;
import com.example.Ventas3718.Repositorios.ICompra;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private ICompra data;
    
    @Override
    public List<Compra> Listar() {
        return (List<Compra>) data.findAll();
    }

    @Override
    public Optional<Compra> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compra c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Compra> Buscar(String desc) {
        return data.findForAll(desc);
    }

    @Override
    public int UltimoIdCompra() {
        return data.ConsultarIdCompra();
    }

    @Override
    public Compra BuscarId(int cod) {
        return data.BuscarXId(cod);
    }

    @Override
    public List<Compra> ListarPorNombreAsc() {
        return data.findAllOrderedByDateAsc();    
    }

    @Override
    public List<Compra> ListarPorNombreDesc() {
        return data.findAllOrderedByDateDesc();
    }

    @Override
    public Double PrecioTotal() {
        return data.calcularPrecioTotal();
    }

     
}

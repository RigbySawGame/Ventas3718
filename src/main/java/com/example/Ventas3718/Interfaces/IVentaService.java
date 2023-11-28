package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Venta;
import java.util.List;
import java.util.Optional;


public interface IVentaService {
    public List<Venta> Listar();
    public Optional<Venta> ConsultarId(int id);
    public void Guardar(Venta c);
    public void Eliminar(int id);
    public List<Venta> Buscar(String desc);
    public int UltimoIdVenta();
    
    public Venta BuscarId(int cod);
    
    public Double PrecioTotal();
}

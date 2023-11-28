package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.VentaDetalle;
import java.util.List;
import java.util.Optional;


public interface IVentaDetalleService {
    public List<VentaDetalle> Listar();
    public Optional<VentaDetalle> ConsultarId(int id);
    public void Guardar(VentaDetalle c);
    public void Eliminar(int id);
    public List<VentaDetalle> Buscar(String desc);
    public List<VentaDetalle> BuscarPorIdVentas(int id);
    
}

package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.CompraDetalle;
import java.util.List;
import java.util.Optional;


public interface ICompraDetalleService {
    public List<CompraDetalle> Listar();
    public Optional<CompraDetalle> ConsultarId(int id);
    public void Guardar(CompraDetalle c);
    public void Eliminar(int id);
    public List<CompraDetalle> Buscar(String desc);
    public List<CompraDetalle> BuscarPorIdCompras(int id);
    
}

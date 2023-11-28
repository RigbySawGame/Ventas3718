package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Compra;
import java.util.List;
import java.util.Optional;


public interface ICompraService {
    public List<Compra> Listar();
    public Optional<Compra> ConsultarId(int id);
    public void Guardar(Compra c);
    public void Eliminar(int id);
    public List<Compra> Buscar(String desc);
    public int UltimoIdCompra();
    
    public Compra BuscarId(int cod);
    
    public List<Compra> ListarPorNombreAsc();
    public List<Compra> ListarPorNombreDesc();
    
    public Double PrecioTotal();

}

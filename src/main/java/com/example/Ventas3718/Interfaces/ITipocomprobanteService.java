package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Tipocomprobante;
import java.util.List;
import java.util.Optional;


public interface ITipocomprobanteService {
    public List<Tipocomprobante> Listar();
    public Optional<Tipocomprobante> ConsultarId(int id);
    public void Guardar(Tipocomprobante c);
    public void Eliminar(int id);
    public List<Tipocomprobante> Buscar(String desc);
}

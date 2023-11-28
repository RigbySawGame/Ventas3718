package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Mediopago;
import java.util.List;
import java.util.Optional;


public interface IMediopagoService {
    public List<Mediopago> Listar();
    public Optional<Mediopago> ConsultarId(int id);
    public void Guardar(Mediopago c);
    public void Eliminar(int id);
    public List<Mediopago> Buscar(String desc);
}

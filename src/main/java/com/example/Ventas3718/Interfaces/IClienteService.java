package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Cliente;
import java.util.List;
import java.util.Optional;


public interface IClienteService {
    public List<Cliente> Listar();
    public Optional<Cliente> ConsultarId(int id);
    public void Guardar(Cliente c);
    public void Eliminar(int id);
    public List<Cliente> Buscar(String desc);
}

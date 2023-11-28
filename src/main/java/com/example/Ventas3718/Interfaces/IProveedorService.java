
package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Proveedor;
import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    public List<Proveedor> Listar();
    public Optional<Proveedor> ConsultarId(int id);
    public void Guardar(Proveedor c);
    public void Eliminar(int id);
    public List<Proveedor> Buscar(String desc);
}

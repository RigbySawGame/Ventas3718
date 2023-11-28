
package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public List<Producto> Listar();
    public Optional<Producto> ConsultarId(int id);
    public void Guardar(Producto c);
    public void Eliminar(int id);
    public List<Producto> Buscar(String desc);
}

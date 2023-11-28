
package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario c);
    public void Eliminar(int id);
    public List<Usuario> Buscar(String desc);

}

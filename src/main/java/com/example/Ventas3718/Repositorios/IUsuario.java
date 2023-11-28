
package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario,Integer>{
    @Query(value = "SELECT * FROM usuario "
            + "WHERE nombre LIKE %?1% "
            + "OR apellido LIKE %?1% "
            + "OR dni LIKE %?1% "
            + "OR celular LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1% "
            + "OR user LIKE %?1% "
            + "OR password LIKE %?1%", nativeQuery=true)
    List<Usuario> findForAll (String desc);
}

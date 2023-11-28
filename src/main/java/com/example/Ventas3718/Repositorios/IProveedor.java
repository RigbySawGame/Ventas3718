
package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedor extends CrudRepository<Proveedor,Integer>{
    @Query(value = "SELECT * FROM proveedor "
            + "WHERE nombre LIKE %?1% "
            + "OR ruc LIKE %?1% "
            + "OR celular LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1%", nativeQuery=true)
    List<Proveedor> findForAll (String desc);
}

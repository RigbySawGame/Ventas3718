
package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Tipocomprobante;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipocomprobante extends CrudRepository<Tipocomprobante,Integer>{
    @Query(value = "SELECT * FROM tipocomprobante "
            + "WHERE nombre LIKE %?1%", nativeQuery=true)
    List<Tipocomprobante> findForAll (String desc);
}

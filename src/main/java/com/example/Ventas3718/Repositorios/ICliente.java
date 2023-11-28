package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends CrudRepository<Cliente,Integer> {
    @Query(value = "SELECT * FROM cliente "
            + "WHERE nombre LIKE %?1% "
            + "OR apellido LIKE %?1% "
            + "OR dni LIKE %?1% "
            + "OR celular LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1%", nativeQuery=true)
    List<Cliente> findForAll (String desc);
}

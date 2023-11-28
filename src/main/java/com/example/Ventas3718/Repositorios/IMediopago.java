
package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Mediopago;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediopago extends CrudRepository<Mediopago,Integer>{
    @Query(value = "SELECT * FROM mediopago "
            + "WHERE nombre LIKE %?1%", nativeQuery=true)
    List<Mediopago> findForAll (String desc);
}

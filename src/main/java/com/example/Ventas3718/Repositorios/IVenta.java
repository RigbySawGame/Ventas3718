package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVenta extends CrudRepository<Venta,Integer> {
    
    @Query(value = "SELECT id FROM venta "
            +"ORDER BY id DESC "
            +"LIMIT 1 ", nativeQuery=true)
    public int ConsultarIdVenta();
    
    
    @Query(value = "SELECT v.id as id,"
            +"v.cliente_id,"
            + "v.mediopago_id,"
            + "v.tipocomprobante_id,"
            + "v.fecha,"
            + "v.estado,"
            + "v.usuario_id,"
            + "c.id as id_c,"
            + "mp.id as id_mp,"
            + "tc.id as id_tc,"
            + "c.nombre,"
            + "c.apellido,"
            + "mp.nombre,"
            + "tc.nombre,"
            + "u.user "
            + " FROM venta v "
            + "INNER JOIN cliente c ON v.cliente_id = c.id "
            + "INNER JOIN mediopago mp ON v.mediopago_id = mp.id "
            + "INNER JOIN tipocomprobante tc ON v.tipocomprobante_id = tc.id "
            + "INNER JOIN usuario u ON v.usuario_id = u.id "
            + "WHERE c.nombre LIKE %?1% "
            + "OR c.apellido LIKE %?1% "
            + "OR mp.nombre LIKE %?1% "
            + "OR tc.nombre LIKE %?1%", nativeQuery=true)
    List<Venta> findForAll (String desc);
    
    //// Buscar ////
    @Query(value = "SELECT * FROM venta "
            +"WHERE venta.id LIKE ?1", nativeQuery=true)
    Venta BuscarXId(int cod);

    
    /////Calculo total 
    @Query(value = "SELECT SUM(cd.total) FROM venta v "
             + "INNER JOIN ventadetalle cd ON v.id = cd.venta_id "
             + "WHERE v.estado LIKE 'Activo'", nativeQuery = true)
     Double calcularPrecioTotal();
}

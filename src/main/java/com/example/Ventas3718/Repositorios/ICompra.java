
package com.example.Ventas3718.Repositorios;
import com.example.Ventas3718.Clases.Compra;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompra extends CrudRepository<Compra,Integer> {
    
    @Query(value = "SELECT id FROM compra "
            +"ORDER BY id DESC "
            +"LIMIT 1 ", nativeQuery=true)
    public int ConsultarIdCompra();
   
    
    @Query(value = "SELECT c.id as id,"
            +"c.proveedor_id,"
            + "c.mediopago_id,"
            + "c.tipocomprobante_id,"
            + "c.fecha,"
            + "c.estado,"
            + "c.usuario_id,"
            + "prov.id as id_prov,"
            + "mp.id as id_mp,"
            + "tc.id as id_tc,"
            + "prov.nombre,"
            + "mp.nombre,"
            + "tc.nombre,"
            + "u.user "
            + " FROM compra c "
            + "INNER JOIN proveedor prov ON c.proveedor_id = prov.id "
            + "INNER JOIN mediopago mp ON c.mediopago_id = mp.id "
            + "INNER JOIN tipocomprobante tc ON c.tipocomprobante_id = tc.id "
            + "INNER JOIN usuario u ON c.usuario_id = u.id "
            + "WHERE prov.nombre LIKE %?1% "
            + "OR mp.nombre LIKE %?1% "
            + "OR tc.nombre LIKE %?1%", nativeQuery=true)
    List<Compra> findForAll (String desc);
    
    
    
    
        /*@Query(value = "SELECT c.id as id,"
            +"c.proveedor_id,"
            + "c.mediopago_id,"
            + "c.tipocomprobante_id,"
            + "c.fecha,"
            + "c.estado,"
            + "c.usuario_id,"
            + "pro.id as id_pro,"
            + "mp.id as id_mp,"
            + "tc.id as id_tc,"
            + "pro.nombre,"
            + "mp.nombre,"
            + "tc.nombre,"
            + "u.user "
            + " FROM compra c "
            + "INNER JOIN proveedor pro ON c.proveedor_id = pro.id "
            + "INNER JOIN mediopago mp ON v.mediopago_id = mp.id "
            + "INNER JOIN tipocomprobante tc ON v.tipocomprobante_id = tc.id "
            + "INNER JOIN usuario u ON v.usuario_id = u.id "
            + "WHERE pro.nombre LIKE %?1% "
            + "OR mp.nombre LIKE %?1% "
            + "OR tc.nombre LIKE %?1%", nativeQuery=true)
    List<Compra> findForAll (String desc);*/
    
    @Query(value = "SELECT * FROM compra "
            +"WHERE compra.id LIKE ?1", nativeQuery=true)
    Compra BuscarXId(int cod);
    
    
    @Query(value = "SELECT * FROM compra ORDER BY fecha ASC", nativeQuery = true)
    List<Compra> findAllOrderedByDateAsc();

    @Query(value = "SELECT * FROM compra ORDER BY fecha DESC", nativeQuery = true)
    List<Compra> findAllOrderedByDateDesc();
    
    /*@Query(value = "SELECT SUM(cd.total) FROM compra c "
             + "INNER JOIN compradetalle cd "
             + "ON c.id = cd.compra_id "
             + "WHERE c.estado LIKE 'Activo'", nativeQuery = true)
     Double calcularPrecioTotal();*/
    
    @Query(value = "SELECT SUM(cd.total) FROM compra c "
             + "INNER JOIN compradetalle cd ON c.id = cd.compra_id "
             + "WHERE c.estado LIKE 'Activo'", nativeQuery = true)
    Double calcularPrecioTotal();
}

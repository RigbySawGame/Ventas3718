package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Carrito;
import com.example.Ventas3718.Clases.Proveedor;
import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Clases.Compra;
import com.example.Ventas3718.Clases.CompraDetalle;
import com.example.Ventas3718.Interfaces.IProveedorService;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
import com.example.Ventas3718.Interfaces.ICompraDetalleService;
import com.example.Ventas3718.Interfaces.ICompraService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/compra/")
@Controller
public class ControladorCompra {

    //ArrayList<Compra> lista = new ArrayList();
    String carpeta = "Compra/";
    
    String carpetadetalle = "CompraDetalle/"; 
    
    @Autowired
    ICompraService service;
    
    @Autowired
    ICompraDetalleService service_cd;
    
    @Autowired
    IProveedorService service_proveedor;
    
    @Autowired
    IMediopagoService service_mediopago;
    
    @Autowired
    ITipocomprobanteService service_tipocomprobante;
    
    @Autowired
    IProductoService service_producto;
    
    ArrayList<Carrito> carrito = new ArrayList();
    
    @GetMapping("/nuevo") //localhost/venta/nuevo
    public String NuevoCompra(Model model) 
    {
        model.addAttribute("proveedores", service_proveedor.Listar());
        model.addAttribute("mediopagos", service_mediopago.Listar());
        model.addAttribute("tipocomprobantes", service_tipocomprobante.Listar());
        model.addAttribute("productos", service_producto.Listar());
        
        model.addAttribute("carritos", carrito);
        
        return carpeta+"nuevoCompra"; //nuevoVenta.html
    }

    @PostMapping("/registrar") //localhost/venta/registrar
    public String RegistrarCompra(
            @RequestParam("proveedor_id") Proveedor proveedor_id,
            @RequestParam("tipocomprobante_id") Tipocomprobante tipocomprobante_id,
            @RequestParam("mediopago_id") Mediopago mediopago_id,
            @RequestParam("fec") String fec,
            
            Model model) throws ParseException
    {
        //Aqui va el proceso de registrar
        String[] parts = fec.split("T");
        String part1 = parts[0];
        String part2 = parts[1];
        String fec_ = part1 +  " " + part2 +":00";
        
        SimpleDateFormat formateador_fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = formateador_fecha.parse(fec_);
        
        Usuario usuario_id = new Usuario();
        usuario_id.setId(1);
        
        Compra cmp = new Compra();
        cmp.setTipocomprobante(tipocomprobante_id);
        cmp.setProveedor(proveedor_id);
        cmp.setUsuario(usuario_id);
        cmp.setMediopago(mediopago_id);
        cmp.setFecha(fecha);
        cmp.setEstado("Activo");
        
        service.Guardar(cmp);
        
        
        int id_compra = service.UltimoIdCompra();
        Compra cc = new Compra();
        cc.setId(id_compra);
        
        //Registrar la venta detalle
        for(int i=0; i<carrito.size(); i++)
        {
            int id_prod = carrito.get(i).getId();
            
            Producto p = new Producto();
            p.setId(id_prod);
            
            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();
            
            CompraDetalle vd = new CompraDetalle();
            vd.setCompra(cc);
            vd.setProducto(p);
            vd.setCantidad(cantidad);
            vd.setPrecio(precio);
            vd.setTotal(total);
            
            service_cd.Guardar(vd);
        }

        //Vaciar carrito
        carrito.clear();
        
        return ListarCompra(model);
    }

    @GetMapping("/") //localhost/
    public String ListarCompra(Model model) 
    {
        //model.addAttribute("ventas", lista);
        model.addAttribute("compras", service.Listar());
        return carpeta+"listaCompra"; //listaVenta.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarCompra(@RequestParam("cod") int cod,
                                  Model model)
    {
        List<CompraDetalle> vd = service_cd.BuscarPorIdCompras(cod);
        for (int i = 0; 1 < vd.size(); i++) {
            
            int id_vd = vd.get(i).getId();
            service_cd.Eliminar(id_vd);
        }
        //Eliminar la venta
        service.Eliminar(cod);
        
        return ListarCompra(model);
    }
    
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarCompra(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Compra> compras = service.Buscar(desc);
        model.addAttribute("compras",compras);
        return carpeta+"listaCompra";
    }
     
    @GetMapping("/anular")
    public String AnularCompra(@RequestParam("id") int id,
                                Model model)
    {
        //Optional<Venta> vt = service.ConsultarId(id);
        //model.addAttribute("venta", vt);
        Compra v = service.BuscarId(id);
        
        v.setEstado("Anulado");
        
        service.Guardar(v);

        return ListarCompra(model);
    }
     
    @PostMapping("/agregar")
    public String AgregarCarrito(@RequestParam("producto_id") int producto_id,
                                 @RequestParam("cant") Double cant,
                                 Model model)
    {
        Optional<Producto> producto = service_producto.ConsultarId(producto_id);
        String nombre = producto.get().getNombre();
        Double precio = producto.get().getPreciov();
        Double total = cant * precio;
        
        Carrito carro = new Carrito();
        carro.setId(producto_id);
        carro.setProducto(nombre);
        carro.setPrecio(precio);
        carro.setCantidad(cant);
        carro.setTotal(total);
        
        carrito.add(carro);
        
        return NuevoCompra(model);
    }
    
    @GetMapping("/eliminarcarrito") //localhost/eliminar
    public String EliminarCarrito(@RequestParam("id") int id,
                                  Model model)
    {
        carrito.remove(id-1);
        
        return NuevoCompra(model);
    }
    
    @GetMapping("/cdetalle")
    public String ListarDetalle(@RequestParam("id") int id,
                                Model model) {
        List <CompraDetalle> lista = service_cd.BuscarPorIdCompras(id);
        model.addAttribute("compradetalles", lista);        
        return carpetadetalle + "listaCompraDetalle"; 
    } 
    
    
    @GetMapping("/listar-asc") // Ordenar de forma ascendente por nombre
    public String ListarAsc(Model model) 
    {       
        model.addAttribute("compras", service.ListarPorNombreAsc());
        return carpeta + "listaCompra"; // listaCliente.html
    }

    @GetMapping("/listar-desc") // Ordenar de forma descendente por nombre
    public String ListarDesc(Model model) 
    {
        model.addAttribute("compras", service.ListarPorNombreDesc());
        return carpeta + "listaCompra"; // listaCliente.html
    }
}

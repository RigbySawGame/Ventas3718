package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Carrito;
import com.example.Ventas3718.Clases.Cliente;
import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Clases.Venta;
import com.example.Ventas3718.Clases.VentaDetalle;
import com.example.Ventas3718.Interfaces.IClienteService;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
import com.example.Ventas3718.Interfaces.IVentaDetalleService;
import com.example.Ventas3718.Interfaces.IVentaService;
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

@RequestMapping("/venta/")
@Controller
public class ControladorVenta {

    String carpeta = "Venta/";
    
    String carpetadetalle = "VentaDetalle/"; 
    
    @Autowired
    IVentaService service;
    
    @Autowired
    IVentaDetalleService service_vd;
    
    @Autowired
    IClienteService service_cliente;
    
    @Autowired
    IMediopagoService service_mediopago;
    
    @Autowired
    ITipocomprobanteService service_tipocomprobante;
    
    @Autowired
    IProductoService service_producto;
    
    ArrayList<Carrito> carrito = new ArrayList();
    
    @GetMapping("/nuevo") //localhost/venta/nuevo
    public String NuevoVenta(Model model) 
    {
        model.addAttribute("clientes", service_cliente.Listar());
        model.addAttribute("mediopagos", service_mediopago.Listar());
        model.addAttribute("tipocomprobantes", service_tipocomprobante.Listar());
        model.addAttribute("productos", service_producto.Listar());
        
        model.addAttribute("carritos", carrito);
        
        return carpeta+"nuevoVenta"; //nuevoVenta.html
    }

    @PostMapping("/registrar") //localhost/venta/registrar
    public String RegistrarVenta(            
            @RequestParam("cliente_id") Cliente cliente_id,
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
        
        Venta venta = new Venta();
        venta.setCliente(cliente_id);
        venta.setMediopago(mediopago_id);
        venta.setTipocomprobante(tipocomprobante_id);
        venta.setFecha(fecha);
        venta.setUsuario(usuario_id);
        
        venta.setEstado("Activo");
        
        service.Guardar(venta);
        
        
        int id_venta = service.UltimoIdVenta();
        Venta vv = new Venta();
        vv.setId(id_venta);
        
        //Registrar la venta detalle
        for(int i=0; i<carrito.size(); i++)
        {
            int id_prod = carrito.get(i).getId();
            
            Producto p = new Producto();
            p.setId(id_prod);
            
            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();
            
            VentaDetalle vd = new VentaDetalle();
            vd.setVenta(vv);
            vd.setProducto(p);
            vd.setCantidad(cantidad);
            vd.setPrecio(precio);
            vd.setTotal(total);
            
            service_vd.Guardar(vd);
        }

        //Vaciar carrito
        carrito.clear();
        
        return ListarVenta(model);
    }

    @GetMapping("/") //localhost/
    public String ListarVenta(Model model) 
    {
        model.addAttribute("ventas", service.Listar());
        return carpeta+"listaVenta"; //listaVenta.html
        
        //return carpeta+"nuevoVenta";
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarVenta(@RequestParam("cod") int cod,
                                  Model model)
    {
        List<VentaDetalle> vd = service_vd.BuscarPorIdVentas(cod);
        for (int i = 0; 1 < vd.size(); i++) {
            
            int id_vd = vd.get(i).getId();
            service_vd.Eliminar(id_vd);
        }
        //Eliminar la venta
        service.Eliminar(cod);
        
        return ListarVenta(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarVenta(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Venta> ventas = service.Buscar(desc);
        model.addAttribute("ventas",ventas);
        return carpeta+"listaVenta";
        //return carpeta+"nuevoVenta";
    }
    
    @GetMapping("/anular")
    public String AnularVenta(@RequestParam("id") int id,
                                Model model)
    {
        //Optional<Venta> vt = service.ConsultarId(id);
        //model.addAttribute("venta", vt);
        Venta v = service.BuscarId(id);
        
        v.setEstado("Anulado");
        
        service.Guardar(v);

        return ListarVenta(model);
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
        
        return NuevoVenta(model);
    }
    
    @GetMapping("/eliminarcarrito") //localhost/eliminar
    public String EliminarCarrito(@RequestParam("id") int id,
                                  Model model)
    {
        carrito.remove(id-1);
        
        return NuevoVenta(model);
    }
    
    @GetMapping("/vdetalle")
    public String ListarDetalle(@RequestParam("id") int id, Model model) {
        List <VentaDetalle> lista = service_vd.BuscarPorIdVentas(id);
        model.addAttribute("ventadetalles", lista);        
        return carpetadetalle + "listaVentaDetalle"; 
    } 
}

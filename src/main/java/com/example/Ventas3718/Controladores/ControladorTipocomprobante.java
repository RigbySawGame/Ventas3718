package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/tipocomprobante/")
@Controller
public class ControladorTipocomprobante {

    ArrayList<Tipocomprobante> lista = new ArrayList();
    String carpeta = "Tipocomprobante/";
    
    @Autowired
    ITipocomprobanteService service;
    
    @GetMapping("/nuevo") //localhost/cliente/nuevo
    public String NuevoTipocomprobante() 
    {

        return carpeta+"nuevoTipocomprobante"; //nuevoCliente.html
    }

    @PostMapping("/registrar") //localhost/cliente/registrar
    public String RegistrarTipocomprobante(
            @RequestParam("nom") String nom,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Tipocomprobante c = new Tipocomprobante();
        //c.setId(cod);
        c.setNombre(nom);

        //lista.add(c);
        service.Guardar(c);

        return ListarTipocomprobante(model);
    }

    @GetMapping("/") //localhost/
    public String ListarTipocomprobante(Model model) 
    {
        //model.addAttribute("clientes", lista);
        model.addAttribute("tipocomprobantes", service.Listar());
        return carpeta+"listaTipocomprobante"; //listaCliente.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String Eliminartipocomprobante(@RequestParam("cod") int cod,
                                  Model model)
    {
        /*
        for (int i = 0; i < lista.size(); i++) 
        {
            int c = lista.get(i).getCodigo();
            if(c==cod){lista.remove(i);}
        }
        */
        
        service.Eliminar(cod);
        
        return ListarTipocomprobante(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarTipocomprobante(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        /*
        Cliente cli = new Cliente();
        for (int i = 0; i < lista.size(); i++) 
        {
            int c = lista.get(i).getCodigo();
            if(c==cod)
            {
                String nom = lista.get(i).getNombre();
                String ape = lista.get(i).getApellido();
                String dni = lista.get(i).getDni();
                String cel = lista.get(i).getCelular();
                String email = lista.get(i).getEmail();
                String dir = lista.get(i).getDireccion();
                
                cli.setCodigo(cod);
                cli.setNombre(nom);
                cli.setApellido(ape);
                cli.setDni(dni);
                cli.setCelular(cel);
                cli.setEmail(email);
                cli.setDireccion(dir);
            }
        }
        */
        
        Optional<Tipocomprobante> cli = service.ConsultarId(cod);
        
        model.addAttribute("tipocomprobante", cli);
        return carpeta+"editarTipocomprobante"; //editarCliente.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarTipocomprobante(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            Model model)
    {
        /*
            for (int i = 0; i < lista.size(); i++) 
            {
                int c = lista.get(i).getCodigo();
                if(c==cod)
                {
                    lista.get(i).setNombre(nom);
                    lista.get(i).setApellido(ape);
                    lista.get(i).setDni(dni);
                    lista.get(i).setCelular(cel);
                    lista.get(i).setEmail(email);
                    lista.get(i).setDireccion(dir);
                }
            }
        */
        
         //Aqui va el proceso de registrar
        Tipocomprobante c = new Tipocomprobante();
        c.setId(cod);
        c.setNombre(nom);
        
        service.Guardar(c); //Cuando se envia el ID -> Actualizar
        
        return ListarTipocomprobante(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarTipocomprobante(@RequestParam("desc") String desc,
                                Model model)
    {
        /*
        ArrayList<Cliente> lc = new ArrayList();
        
        for (int i = 0; i < lista.size(); i++) 
        {
            if(String.valueOf(lista.get(i).getCodigo()).equals(desc) ||
               lista.get(i).getNombre().equals(desc) || 
               lista.get(i).getApellido().equals(desc) || 
               lista.get(i).getDni().equals(desc) ||
               lista.get(i).getEmail().equals(desc) ||
               lista.get(i).getCelular().equals(desc) ||
               lista.get(i).getDireccion().equals(desc))
            {
                int cod = lista.get(i).getCodigo();
                String nom = lista.get(i).getNombre();
                String ape = lista.get(i).getApellido();
                String dni = lista.get(i).getDni();
                String cel = lista.get(i).getCelular();
                String email = lista.get(i).getEmail();
                String dir = lista.get(i).getDireccion();
                
                Cliente cli = new Cliente();
                
                cli.setCodigo(cod);
                cli.setNombre(nom);
                cli.setApellido(ape);
                cli.setDni(dni);
                cli.setCelular(cel);
                cli.setEmail(email);
                cli.setDireccion(dir);
                
                lc.add(cli);
                
            }
        }
        
        model.addAttribute("clientes", lc);
        return carpeta+"listaCliente"; //listaCliente.html
        */
        List<Tipocomprobante> tipocomprobantes = service.Buscar(desc);
        model.addAttribute("tipocomprobantes",tipocomprobantes);
        return carpeta+"listaTipocomprobante";
    }
      
}

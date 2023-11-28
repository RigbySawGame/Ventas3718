package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Interfaces.IMediopagoService;
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

@RequestMapping("/mediopago/")
@Controller
public class ControladorMediopago {

    ArrayList<Mediopago> lista = new ArrayList();
    String carpeta = "Mediopago/";
    
    @Autowired
    IMediopagoService service;
    
    @GetMapping("/nuevo") //localhost/cliente/nuevo
    public String NuevoMediopago() 
    {

        return carpeta+"nuevoMediopago"; //nuevoCliente.html
    }

    @PostMapping("/registrar") //localhost/cliente/registrar
    public String RegistrarMediopago(
            @RequestParam("nom") String nom,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Mediopago c = new Mediopago();
        //c.setId(cod);
        c.setNombre(nom);

        //lista.add(c);
        service.Guardar(c);

        return ListarMediopago(model);
    }

    @GetMapping("/") //localhost/
    public String ListarMediopago(Model model) 
    {
        //model.addAttribute("clientes", lista);
        model.addAttribute("mediopagos", service.Listar());
        return carpeta+"listaMediopago"; //listaCliente.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarMediopago(@RequestParam("cod") int cod,
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
        
        return ListarMediopago(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarMediopago(@RequestParam("cod") int cod,
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
        
        Optional<Mediopago> cli = service.ConsultarId(cod);
        
        model.addAttribute("mediopago", cli);
        return carpeta+"editarMediopago"; //editarCliente.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarMediopago(@RequestParam("id") int cod,
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
        Mediopago c = new Mediopago();
        c.setId(cod);
        c.setNombre(nom);
        
        service.Guardar(c); //Cuando se envia el ID -> Actualizar
        
        return ListarMediopago(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarMediopago(@RequestParam("desc") String desc,
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
        List<Mediopago> mediopagos = service.Buscar(desc);
        model.addAttribute("mediopagos",mediopagos);
        return carpeta+"listaMediopago";
    }
      
}

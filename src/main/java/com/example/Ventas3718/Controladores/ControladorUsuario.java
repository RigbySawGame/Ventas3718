package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Interfaces.IUsuarioService;
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

@RequestMapping("/usuario/")
@Controller
public class ControladorUsuario {

    ArrayList<Usuario> lista = new ArrayList();
    String carpeta = "Usuario/";
    
    @Autowired
    IUsuarioService service;
    
    @GetMapping("/nuevo") //localhost/cliente/nuevo
    public String NuevoUsuario() 
    {

        return carpeta+"nuevoUsuario"; //nuevoCliente.html
    }

    @PostMapping("/registrar") //localhost/cliente/registrar
    public String RegistrarUsuario(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            @RequestParam("use") String use,
            @RequestParam("pas") String pas,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Usuario c = new Usuario();
        //c.setId(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        c.setUser(use);
        c.setPassword(pas);

        //lista.add(c);
        service.Guardar(c);

        return ListarUsuario(model);
    }

    @GetMapping("/") //localhost/
    public String ListarUsuario(Model model) 
    {
        //model.addAttribute("clientes", lista);
        model.addAttribute("usuarios", service.Listar());
        return carpeta+"listaUsuario"; //listaCliente.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarUsuario(@RequestParam("cod") int cod,
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
        
        return ListarUsuario(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarUsuario(@RequestParam("cod") int cod,
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
        
        Optional<Usuario> cli = service.ConsultarId(cod);
        
        model.addAttribute("usuario", cli);
        return carpeta+"editarUsuario"; //editarCliente.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarUsuario(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            @RequestParam("user") String use,
            @RequestParam("password") String pas,
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
        Usuario c = new Usuario();
        c.setId(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        c.setUser(use);
        c.setPassword(pas);
        
        service.Guardar(c); //Cuando se envia el ID -> Actualizar
        
        return ListarUsuario(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarUsuario(@RequestParam("desc") String desc,
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
        List<Usuario> usuarios = service.Buscar(desc);
        model.addAttribute("usuarios",usuarios);
        return carpeta+"listaUsuario";
    }
      
}

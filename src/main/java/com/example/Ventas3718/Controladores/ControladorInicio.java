
package com.example.Ventas3718.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/inicio/")
@Controller
public class ControladorInicio {
    
    @GetMapping("/") //localhost/
    public String MostrarInicio() 
    {
        return "menu2"; //menu2.html
    }
}

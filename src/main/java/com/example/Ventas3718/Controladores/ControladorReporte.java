package com.example.Ventas3718.Controladores;
import com.example.Ventas3718.Interfaces.ICompraService;
import com.example.Ventas3718.Interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reporte/")
@Controller
public class ControladorReporte {

    String carpeta = "Reporte/";
        
    @Autowired
    IVentaService service;
    
    @Autowired
    ICompraService servicec;
        
    @GetMapping("/lista") //localhost/
    public String ListarReporte(Model model) 
    {
        
        model.addAttribute("totalv", service.PrecioTotal());
        model.addAttribute("totalc", servicec.PrecioTotal());
        
        Double  ganan = service.PrecioTotal() - servicec.PrecioTotal();
        model.addAttribute("ganancia", ganan);
        return carpeta + "reporte";
    }
    
}

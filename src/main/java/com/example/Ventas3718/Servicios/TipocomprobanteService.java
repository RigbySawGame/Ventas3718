package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
import com.example.Ventas3718.Repositorios.ITipocomprobante;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipocomprobanteService implements ITipocomprobanteService {

    @Autowired
    private ITipocomprobante data;
    
    @Override
    public List<Tipocomprobante> Listar() {
        return (List<Tipocomprobante>) data.findAll();
    }

    @Override
    public Optional<Tipocomprobante> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Tipocomprobante c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Tipocomprobante> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}

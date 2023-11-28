package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="venta")
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private Date fecha;
    private String estado;
    
    
    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name = "tipocomprobante_id")
    private Tipocomprobante tipocomprobante;
    
    @ManyToOne()
    @JoinColumn(name = "mediopago_id")
    private Mediopago mediopago;
    
    
}


package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity //Entidad de base de datos
@Table(name="tipocomprobante")
public class Tipocomprobante {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public String nombre;
}

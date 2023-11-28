
package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity //Entidad de base de datos
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public String nombre;
    public String apellido;
    public String dni;
    public String celular;
    public String email;
    public String direccion;
    public String user;
    public String password;
}

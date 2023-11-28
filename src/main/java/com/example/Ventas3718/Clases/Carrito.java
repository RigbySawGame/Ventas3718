package com.example.Ventas3718.Clases;
import lombok.Data;

@Data
public class Carrito {
    public int id;
    public String producto;
    public Double precio;
    public Double cantidad;
    private Double total;
}

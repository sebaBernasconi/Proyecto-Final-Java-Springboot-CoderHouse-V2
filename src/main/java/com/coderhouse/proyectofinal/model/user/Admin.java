package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.transactions.Venta;

import java.util.List;

public class Admin extends User {

    private List<Venta> ventas;

    //Constructor

    public Admin(int cuil, String nombre,
                 String mail, String password, List<Venta> ventas) {
        super(cuil, nombre, mail, password);
        this.ventas = ventas;
    }

    //Getters y Setters

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}

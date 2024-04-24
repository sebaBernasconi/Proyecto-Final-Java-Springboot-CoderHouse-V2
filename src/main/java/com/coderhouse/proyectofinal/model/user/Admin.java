package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.transactions.Venta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Schema(description = "Modelo del Admin")
@Entity
@Table(name = "administradores")
public class Admin extends User {

    @Schema(description = "Ventas asociadas al Admin", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Venta> ventas;

    //Constructor

    public Admin(int cuil, String nombre,
                 String mail, String password, List<Venta> ventas) {
        super(cuil, nombre, mail, password);
        this.ventas = ventas;
    }

    //Constructor vacio para la persistencia
    public Admin() {

    }

    //Getters y Setters

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}

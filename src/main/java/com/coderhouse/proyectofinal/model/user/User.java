package com.coderhouse.proyectofinal.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Modelo del User")
@Entity
public abstract class User {

    @Schema(description = "Cuil del User", requiredMode = Schema.RequiredMode.REQUIRED, example = "20123120")
    @Id
    @Column(name = "cuil")
    private int cuil;

    @Schema(description = "Nombre del User", requiredMode = Schema.RequiredMode.REQUIRED, example = "Sebastian Bernasconi")
    @Column(name = "nombre")
    private  String nombre;

    @Schema(description = "Mail del User", requiredMode = Schema.RequiredMode.REQUIRED, example = "sebas@gmail.com")
    @Column(name = "mail")
    private String mail;

    @Schema(description = "Contraseña del User", requiredMode = Schema.RequiredMode.REQUIRED, example = "contraseña")
    @Column(name = "password")
    private String password;

    //Constructor
    public User( int cuil, String nombre, String mail, String password) {

        this.cuil = cuil;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
    }

    //constructor vacio para poder persistir
    public User() {

    }

    //Getters y Setters

    public int getCuil() {
        return cuil;
    }

    public void setCuil(int cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

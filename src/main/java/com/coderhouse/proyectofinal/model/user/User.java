package com.coderhouse.proyectofinal.model.user;

import jakarta.persistence.*;

@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idUsr;
    @Column(name = "cuil")
    private int cuil;
    @Column(name = "nombre")
    private  String nombre;
    @Column(name = "mail")
    private String mail;
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


    public int getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }

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

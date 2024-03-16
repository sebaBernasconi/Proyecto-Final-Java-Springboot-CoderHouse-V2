package com.coderhouse.proyectofinal.model.user;

public abstract class User {
    private int idUsr;
    private int cuil;
    private  String nombre;
    private String mail;
    private String password;

    //Constructor
    public User(int idUsr, int cuil, String nombre, String mail, String password) {
        this.idUsr = idUsr;
        this.cuil = cuil;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
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

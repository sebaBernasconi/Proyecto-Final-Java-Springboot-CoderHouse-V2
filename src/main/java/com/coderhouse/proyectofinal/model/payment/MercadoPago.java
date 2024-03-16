package com.coderhouse.proyectofinal.model.payment;

public class MercadoPago extends MedioDePago{

    private  String user;
    private String alias;
    private float saldo;

    //Constructor

    public MercadoPago(String user, String alias, float saldo) {
        this.user = user;
        this.alias = alias;
        this.saldo = saldo;
    }
    //Metodos abstractos de la super clase desarrollados
    @Override
    public void pagar(float total) {
        if (this.saldo > total){
            this.saldo = this.saldo - total;
            System.out.println("Carrito abonado!");
        }else {
            System.out.println("El saldo de la cuenta es menor al" +
                    "total del carrito. No se puede realizar el pago");
            System.out.println("Saldo: " + this.saldo +
                    "\ntotal: " + total);
        }
    }

    //Getters y Setters

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}

package com.coderhouse.proyectofinal.model.payment;

public class Debito extends Tarjeta{

    private float saldo;

    //Constructor

    public Debito(int nroTarjeta, String titular, int codigoDeSeguridad, float saldo) {
        super(nroTarjeta, titular, codigoDeSeguridad);
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
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}

package com.coderhouse.proyectofinal.model.payment;

import com.coderhouse.proyectofinal.model.user.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "t_debito")
public class Debito extends Tarjeta{

    @Schema(description = "Cliente al que esta asociada la tarjeta", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuil")
    private Client client;

    @Schema(description = "Saldo de la tarjeta", requiredMode = Schema.RequiredMode.REQUIRED, example = "2344.56")
    @Column(name = "saldo")
    private float saldo;

    //Constructor


    public Debito(int nroTarjeta, String titular, int codigoDeSeguridad, Client client, float saldo) {
        super(nroTarjeta, titular, codigoDeSeguridad);
        this.client = client;
        this.saldo = saldo;
    }

    public Debito(Client client, float saldo) {
        this.client = client;
        this.saldo = saldo;
    }

    //Constructor vacio para la persistencia
    public Debito() {
        super();
    }

    //Metodos abstractos de la super clase desarrollados
    @Override
    public boolean pagar(float total) {
        if (this.saldo > total){
            this.saldo = this.saldo - total;
            System.out.println("Carrito abonado!");
            return true;
        }else {
            System.out.println("El saldo de la cuenta es menor al" +
                    "total del carrito. No se puede realizar el pago");
            System.out.println("Saldo: " + this.saldo +
                    "\ntotal: " + total);
            return false;
        }
    }

    //Getters y Setters
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return
                super.toString() +
                "\n client=" + client +
                "\n saldo=" + saldo;
    }
}

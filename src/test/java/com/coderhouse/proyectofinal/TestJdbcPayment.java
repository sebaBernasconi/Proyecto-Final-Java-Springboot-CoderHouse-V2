package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerPayment;
import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;

public class TestJdbcPayment {

    public static void main(String[] args) throws SQLException {

        JavaDataBaseControllerPayment javaDataBaseControllerPayment = new JavaDataBaseControllerPayment();
        JavaDataBaseControllerCliente  javaDataBaseControllerCliente = new JavaDataBaseControllerCliente();

        //Instanciando tarjetas para asignar

        Debito debito = new Debito(34214124,"Sebastian Bernasconi",
                2342,123143F);

        Debito debito2 = new Debito(123464,"Sebastian Bernasconi",
                1542,127343F);

       //Instanciando clientes para darles tarjeta
        Client client = new Client(1231234,"Juan","mail","password",
                null,null,null);

        Client client2 = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        javaDataBaseControllerCliente.getConnection();
        //Guardo el cliente para poder asignarle la tarjeta
        javaDataBaseControllerCliente.guardarCliente(client);
        javaDataBaseControllerCliente.guardarCliente(client2);

        //Le setteo la tarjeta
        client.settDebito(debito);
        client2.settDebito(debito2);

        javaDataBaseControllerPayment.getConnection();

        javaDataBaseControllerPayment.guardarTarjetaDebitoCliente(client);
        javaDataBaseControllerPayment.guardarTarjetaDebitoCliente(client2);

        System.out.println("--------------------MOSTRANDO LAS TARJETAS--------------------");

        javaDataBaseControllerPayment.mostrarTarjetasDebito();

        //modificando
        javaDataBaseControllerPayment.actualizarSaldoTarjeta(client2.gettDebito().getNroTarjeta(),
                100000);

        System.out.println("--------------------MOSTRANDO LAS TARJETAS DESPUES DE MODIFICAR--------------------");
        javaDataBaseControllerPayment.mostrarTarjetasDebito();

        //Borrando
        javaDataBaseControllerPayment.borrarTarjeta(client2.gettDebito().getNroTarjeta());

        System.out.println("--------------------MOSTRANDO LAS TARJETAS DESPUES DE BORRAR--------------------");
        javaDataBaseControllerPayment.mostrarTarjetasDebito();

        //borrando para proxima ejecucion
        javaDataBaseControllerPayment.borrarTarjeta(client.gettDebito().getNroTarjeta());
        javaDataBaseControllerCliente.borrarCliente(client.getCuil());
        javaDataBaseControllerCliente.borrarCliente(client2.getCuil());

        javaDataBaseControllerPayment.closeConnection();
        javaDataBaseControllerCliente.closeConnection();

        System.out.println("--------------------FIN TEST JDBC PAYMENT--------------------");


    }
}

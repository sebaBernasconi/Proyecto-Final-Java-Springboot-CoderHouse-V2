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

        Debito debito = new Debito(34214124,"Sebastian Bernasconi",
                2342,123143F);

        Client client = new Client(1231234,"Juan","mail","password",
                debito,null,null);

        javaDataBaseControllerCliente.getConnection();
        javaDataBaseControllerCliente.guardarCliente(client);
        javaDataBaseControllerCliente.closeConnection();

        javaDataBaseControllerPayment.getConnection();
        javaDataBaseControllerPayment.guardarTarjetaDebitoCliente(client);

        javaDataBaseControllerPayment.closeConnection();

    }
}

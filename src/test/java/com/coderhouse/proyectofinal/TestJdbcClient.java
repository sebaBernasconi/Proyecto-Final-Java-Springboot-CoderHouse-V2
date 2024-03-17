package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;

public class TestJdbcClient {

    public static void main(String[] args) throws SQLException {

        JavaDataBaseControllerCliente controller = new JavaDataBaseControllerCliente();

        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        controller.getConnection();

        controller.guardarCliente(client);

        controller.mostrarClientes();

        controller.modificarMailCliente(2043900195,"sebernasconi@uade.edu.ar");
        controller.modificarPasswordCliente(2043900195,"nueva pass");

        controller.mostrarClientes();

        controller.closeConnection();
    }
}

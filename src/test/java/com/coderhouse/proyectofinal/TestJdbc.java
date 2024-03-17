package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;

public class TestJdbc {

    public static void main(String[] args) throws SQLException {

        JavaDataBaseController controller = new JavaDataBaseController();

        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        controller.getConnection();

        controller.guardarCliente(client);

        controller.mostrarClientes();

        controller.closeConnection();


    }
}

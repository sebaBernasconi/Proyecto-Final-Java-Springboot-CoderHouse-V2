package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCarrito;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;

public class TestJdbcCarrito {

    public static void main(String[] args) throws SQLException {

        Client client = new Client(2043900195, "Sebastian", "bernaseba1@gmail.com", "secreta",
                null, null, null);

        Carrito carrito = new Carrito(client, 3, null, true, 1120);

        client.setCarrito(carrito);

        JavaDataBaseControllerCarrito javaDataBaseControllerCarrito = new JavaDataBaseControllerCarrito();

        javaDataBaseControllerCarrito.getConnection();

        javaDataBaseControllerCarrito.mostrarCarritoDelCliente(2);

        javaDataBaseControllerCarrito.closeConnection();

    }
}

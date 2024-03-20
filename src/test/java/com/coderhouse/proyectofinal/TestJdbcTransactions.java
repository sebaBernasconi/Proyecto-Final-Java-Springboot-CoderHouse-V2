package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerTransactions;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestJdbcTransactions {

    public static void main(String[] args) throws SQLException {
        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        Carrito carrito = new Carrito(23,client,0,null,false,20);

        Admin admin = new Admin(111222333,"Juan","jj@gmail.com","ACDC",
                new ArrayList<>());

        Compra compra = new Compra(new Date(2020,4,1),carrito,carrito.getTotal(),admin);

        JavaDataBaseControllerTransactions javaDataBaseControllerTransactions = new JavaDataBaseControllerTransactions();

        javaDataBaseControllerTransactions.getConnection();

        javaDataBaseControllerTransactions.guardarCompra(compra);
        javaDataBaseControllerTransactions.mostrarCompras();

        javaDataBaseControllerTransactions.closeConnection();

    }
}

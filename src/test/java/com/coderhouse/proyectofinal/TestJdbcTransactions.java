package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerTransactions;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestJdbcTransactions {

    public static void main(String[] args) throws SQLException {

        //instanciando los objetos necesarios
        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        Carrito carrito = new Carrito(1,client,0,null,false,20);

        Admin admin = new Admin(111222333,"Juan","jj@gmail.com","ACDC",
                new ArrayList<>());

        Compra compra = new Compra(new Date(2020,4,1),carrito,carrito.getTotal(),carrito.getCliente()
                ,admin);

        Venta venta = new Venta(new Date(2020,4,1),carrito,carrito.getTotal(),client,admin);

        JavaDataBaseControllerTransactions javaDataBaseControllerTransactions = new JavaDataBaseControllerTransactions();

        javaDataBaseControllerTransactions.getConnection();

        javaDataBaseControllerTransactions.guardarCompra(compra);
        javaDataBaseControllerTransactions.mostrarCompras();

        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("CRUD Venta");

        javaDataBaseControllerTransactions.guardarVenta(venta);

        javaDataBaseControllerTransactions.mostrarVentas();

        javaDataBaseControllerTransactions.borrarVenta(venta.getIdTransaccion());

        System.out.println("Despues de borrar y antes de intentar mostrar");
        javaDataBaseControllerTransactions.mostrarVentas();
        System.out.println("Despues de borrar y despues de intentar");

        javaDataBaseControllerTransactions.closeConnection();

    }
}

package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerAdmins;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCarrito;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
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

        Carrito carrito = new Carrito(client,0,null,false,20);

        Admin admin = new Admin(111222333,"Juan","jj@gmail.com","ACDC",
                new ArrayList<>());

        Compra compra = new Compra(new Date(2020,4,1),carrito,carrito.getTotal(),carrito.getCliente()
                ,admin);

        Venta venta = new Venta(new Date(2020,4,1),carrito,carrito.getTotal(),client,admin);

        JavaDataBaseControllerTransactions javaDataBaseControllerTransactions = new JavaDataBaseControllerTransactions();
        JavaDataBaseControllerCliente javaDataBaseControllerCliente = new JavaDataBaseControllerCliente();
        JavaDataBaseControllerAdmins javaDataBaseControllerAdmins = new JavaDataBaseControllerAdmins();
        JavaDataBaseControllerCarrito javaDataBaseControllerCarrito = new JavaDataBaseControllerCarrito();

        javaDataBaseControllerCliente.getConnection();
        javaDataBaseControllerAdmins.getConnection();
        javaDataBaseControllerCarrito.getConnection();
        javaDataBaseControllerTransactions.getConnection();

        javaDataBaseControllerCliente.guardarCliente(client);
        javaDataBaseControllerAdmins.guardarAdmin(admin);

        client.setCarrito(carrito);
        javaDataBaseControllerCarrito.guardarCarrito(client.getCarrito());

        javaDataBaseControllerTransactions.guardarCompra(compra);
        javaDataBaseControllerTransactions.guardarVenta(venta);

        System.out.println("-------------------MOSTRANDO LA VENTA-------------------");
        javaDataBaseControllerTransactions.mostrarVentas();

        System.out.println("-------------------MOSTRANDO LA COMPRA-------------------");
        javaDataBaseControllerTransactions.mostrarCompras();


        //No se considero que se pudiera modificar ningun campo de compra ni venta.
        // en cualquier caso se borra y se guarda una nueva

        //Borrando
        javaDataBaseControllerTransactions.borrarVenta(venta.getIdTransaccion());
        javaDataBaseControllerTransactions.borrarCompra(compra.getIdTransaccion());

        System.out.println("-------------------MOSTRANDO LA VENTA DESPUES DE BORRAR-------------------");
        javaDataBaseControllerTransactions.mostrarVentas();

        System.out.println("-------------------MOSTRANDO LA COMPRA DESPUES DE BORRAR-------------------");
        javaDataBaseControllerTransactions.mostrarCompras();

        javaDataBaseControllerCliente.borrarCliente(client.getCuil());
        javaDataBaseControllerAdmins.borrarAdmin(admin.getCuil());
        javaDataBaseControllerCarrito.borrarCarrito
                (javaDataBaseControllerCarrito.obtenerIdDeCarritoPorCuil(carrito.getIdCarrito()));

        javaDataBaseControllerCarrito.closeConnection();
        javaDataBaseControllerCliente.closeConnection();
        javaDataBaseControllerAdmins.closeConnection();
        javaDataBaseControllerTransactions.closeConnection();

        System.out.println("-------------------FIN TEST JDBC Transactions-------------------");
    }
}

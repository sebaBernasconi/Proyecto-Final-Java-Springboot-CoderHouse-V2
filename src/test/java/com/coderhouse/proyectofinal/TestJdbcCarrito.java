package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCarrito;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerProductos;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;

public class TestJdbcCarrito {

    public static void main(String[] args) throws SQLException {

        Client client = new Client(2043900195, "Sebastian", "bernaseba1@gmail.com", "secreta",
                null, null, null);

        Client client2 = new Client(1231234,"Juan","mail","password",
                null,null,null);

        Carrito carrito = new Carrito(client, 3, null, true, 1120);

        Carrito carrito2 = new Carrito(client2, 1, null, false, 10);

        Comic comic = new Comic(1089,"Flash: Born To Run","Comic centrado en los primeros años de wally"
                ,5,15.000F,"Mark Weid", "Ingles",
                true);

        client.setCarrito(carrito);
        client2.setCarrito(carrito2);

        JavaDataBaseControllerCarrito javaDataBaseControllerCarrito = new JavaDataBaseControllerCarrito();
        JavaDataBaseControllerCliente javaDataBaseControllerCliente = new JavaDataBaseControllerCliente();
        JavaDataBaseControllerProductos javaDataBaseControllerProductos = new JavaDataBaseControllerProductos();

        javaDataBaseControllerCliente.getConnection();
        javaDataBaseControllerCarrito.getConnection();
        javaDataBaseControllerProductos.getConnection();

        javaDataBaseControllerCliente.guardarCliente(client);
        javaDataBaseControllerCliente.guardarCliente(client2);

        javaDataBaseControllerCarrito.guardarCarrito(client.getCarrito());
        javaDataBaseControllerCarrito.guardarCarrito(client2.getCarrito());

        javaDataBaseControllerProductos.guardarComic(comic);

        System.out.println("-----------------------MOSTRANDO CARRITOS---------------");

        javaDataBaseControllerCarrito.mostrarCarritos();

        //modificando
        javaDataBaseControllerCarrito.agregarArticuloAlCarrito(client2.getCarrito().getIdCarrito(),
                2);
        javaDataBaseControllerCarrito.pagarCarrito
                (javaDataBaseControllerCarrito.obtenerIdDeCarritoPorCuil(client2.getCuil()),true);

        System.out.println("-----------------------MOSTRANDO CARRITOS DESPUES DE MODIFICAR---------------");
        javaDataBaseControllerCarrito.mostrarCarritoDelCliente
                (javaDataBaseControllerCarrito.obtenerIdDeCarritoPorCuil(client2.getCuil()));

        //Borrando
        javaDataBaseControllerCarrito.borrarCarrito
                (javaDataBaseControllerCarrito.obtenerIdDeCarritoPorCuil(client2.getCuil()));

        System.out.println("-----------------------MOSTRANDO CARRITOS DESPUES DE BORRAR---------------");
        javaDataBaseControllerCarrito.mostrarCarritos();

        //borro lo demas para proxima ejecucion

        javaDataBaseControllerCarrito.borrarCarrito
                (javaDataBaseControllerCarrito.obtenerIdDeCarritoPorCuil(client.getCuil()));

        javaDataBaseControllerProductos.eliminarComic(comic.getCodigoDeProducto());

        javaDataBaseControllerCliente.borrarCliente(client.getCuil());
        javaDataBaseControllerCliente.borrarCliente(client2.getCuil());

        javaDataBaseControllerCarrito.closeConnection();

    }
}

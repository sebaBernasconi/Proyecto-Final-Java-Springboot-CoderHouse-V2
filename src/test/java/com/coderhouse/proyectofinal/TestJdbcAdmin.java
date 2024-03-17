package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerAdmins;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJdbcAdmin {

    public static void main(String[] args) throws SQLException {
        JavaDataBaseControllerAdmins controller = new JavaDataBaseControllerAdmins();

        //Este cliente no va a ser persistido ya que es solo una prueba de los admins.
        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

       //instanciando un admin
        Admin admin = new Admin(111222333,"Juan","jj@gmail.com","ACDC",
                new ArrayList<>());

        //instanciando una venta para guardar en el listado
        Venta v = new Venta(new Date(),null,104.4F,client,admin);
        v.setIdTransaccion(2);

        //listado de ventas del admin
        List<Venta> ventasDelAdmin = new ArrayList<>();
        ventasDelAdmin.add(v);
        //setteando las ventas del admin
        admin.setVentas(ventasDelAdmin);

        //Obteniendo conexion
        controller.getConnection();

        controller.borrarAdmin(111222333);
        controller.guardarAdmin(admin);

        controller.mostrarAdmins();

        controller.modificarMailAdmin(111222333,"Juan@gmail.com");
        controller.modificarPasswordAdmin(111222333,"Angus");

        controller.mostrarAdmins();

        //Cerrando conexion
        controller.closeConnection();
    }
}

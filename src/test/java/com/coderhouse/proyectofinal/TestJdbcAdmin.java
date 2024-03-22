package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerAdmins;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJdbcAdmin {

    public static void main(String[] args) throws SQLException {
        JavaDataBaseControllerAdmins controllerAdmins = new JavaDataBaseControllerAdmins();
        JavaDataBaseControllerCliente controllerCliente = new JavaDataBaseControllerCliente();

        //cliente para persistir y probar
        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        Carrito carrito = new Carrito(client, 3, null, true, 1120);


        controllerCliente.getConnection();

        controllerCliente.guardarCliente(client);
        client.setCarrito(carrito);
        controllerCliente.agregarIdCarrito(client);



        //instanciando un admin
        List<Venta> ventasAdminUno = new ArrayList<>();
        Admin admin = new Admin(111222333,"Juan","jj@gmail.com","ACDC",
                new ArrayList<>());

        Admin admin2 = new Admin(2020123321,"Lucas","LucasB@gmail.com","1234",
                new ArrayList<>());

        //instanciando una venta para guardar en el listado
        Venta venta = new Venta(new java.sql.Date(2020,4,1),client.getCarrito(),
                client.getCarrito().getTotal(),client,admin);
        ventasAdminUno.add(venta);
        admin.setVentas(ventasAdminUno);

        controllerAdmins.getConnection();

        controllerAdmins.guardarAdmin(admin);
        controllerAdmins.guardarAdmin(admin2);

        System.out.println("--------------------------------MOSTRANDO TODOS LOS ADMINS----------------");

        controllerAdmins.mostrarAdmins();

        //modificando un admin
        controllerAdmins.modificarMailAdmin(admin.getCuil(),"JuanJose@gmail.com");
        controllerAdmins.modificarPasswordAdmin(admin.getCuil(),"nueva password");

        System.out.println("--------------------------------MOSTRANDO ADMIN DESPUES DE ACUTALIZACION--------------------------------");
        controllerAdmins.mostrarAdminPorCuil(admin.getCuil());

        //borrando los admins
        controllerAdmins.borrarAdmin(admin.getCuil());


        System.out.println("--------------------------------MOSTRANDO ADMINS DESPUES DE BORRAR--------------------------------");

        controllerAdmins.mostrarAdmins();

        controllerAdmins.closeConnection();

        //Borrando el cliente guardado


        controllerCliente.borrarCliente(client.getCuil());

        controllerCliente.closeConnection();

        System.out.println("--------------------------FIN TEST JDBCADMIN--------------------------");
    }
}

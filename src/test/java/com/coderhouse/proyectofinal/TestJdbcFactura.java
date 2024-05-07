package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerFactura;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class TestJdbcFactura {

    public static void main(String[] args) throws SQLException {
        /*
        Client client = new Client(2043900195,"Sebastian","bernaseba1@gmail.com","secreta",
                null,null,null);

        FacturaA facturaA = new FacturaA(129,2043900195,
                LocalDate.of(2001, 12, 1),1399F);
        FacturaB facturaB = new FacturaB(139,2043900195,
                LocalDate.of(2024,3,23),1454F);
        FacturaC facturaC = new FacturaC(283,2043900195,
                LocalDate.of(2021,3,3),1111F);

        JavaDataBaseControllerFactura jdbcFactura = new JavaDataBaseControllerFactura();
        JavaDataBaseControllerCliente jdbcClient = new JavaDataBaseControllerCliente();

        jdbcClient.getConnection();
        jdbcClient.guardarCliente(client);

        jdbcFactura.getConnection();

        jdbcFactura.guardarFacturaA(facturaA);
        jdbcFactura.guardarFacturaB(facturaB);
        jdbcFactura.guardarFacturaC(facturaC);

        System.out.println("Mostrando facturas a");
        jdbcFactura.mostrarFacturasA();

        System.out.println("Mostrando facturas b");
        jdbcFactura.mostrarFacturasB();

        System.out.println("Mostrando facturas c");
        jdbcFactura.mostrarFacturasC();

        jdbcFactura.borrarFacturaA(129);
        jdbcFactura.borrarFacturaB(139);
        jdbcFactura.borrarFacturaC(283);

        System.out.println("-------------------------------------------");
        System.out.println("Mostrando despues del borrado");
        jdbcFactura.mostrarFacturasA();
        jdbcFactura.mostrarFacturasB();
        jdbcFactura.mostrarFacturasC();

        jdbcClient.borrarCliente(client.getCuil());

        jdbcClient.closeConnection();
        jdbcFactura.closeConnection();

        System.out.println("-------------------------------------------");
        System.out.println("Fin Test JDBC Factura");

         */
    }
}

package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.*;

public abstract class JavaDataBaseController {

    //declarando constantes
    private static final String DATA_BASE = "proyectoFinal";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    //instanciando la conexion
    public Connection connection;

    //obteniendo la conexion
    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println();
            System.out.println("conexion establecida");
        }else {
            System.out.println();
            System.out.println("conexion establecida");
            return connection;

        }
        return connection;
    }

    //cerrando la conexion
    public void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
        System.out.println();
        System.out.println("conexion cerrada");
    }




}

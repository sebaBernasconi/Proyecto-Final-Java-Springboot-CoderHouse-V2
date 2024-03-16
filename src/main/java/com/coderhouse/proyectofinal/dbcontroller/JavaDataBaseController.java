package com.coderhouse.proyectofinal.dbcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaDataBaseController {

    //declarando constantes
    private static final String DATA_BASE = "proyectoFinal";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //instanciando la conexion
    private Connection connection;

    //obteniendo la conexion
    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }else {
            return connection;
        }
        return connection;
    }

    //cerrando la conexion
    public void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }
}

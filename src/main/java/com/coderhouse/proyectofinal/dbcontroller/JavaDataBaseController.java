package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.*;

public class JavaDataBaseController {

    //declarando constantes
    private static final String DATA_BASE = "proyectoFinal";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    //instanciando la conexion
    private Connection connection;

    //obteniendo la conexion
    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conexion establecida");
        }else {
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
        System.out.println("conexion cerrada");
    }

    //CRUD Cliente

    public void mostrarClientes() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT cuil,nombre FROM clientes;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int cuil = resultSet.getInt("cuil");
            String nombre = resultSet.getString("nombre");

            System.out.println("El cliente con cuil: " + cuil +
                    " es " + nombre);
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    //Guardar cliente
    public void guardarCliente(Client client) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO clientes(cuil,nombre,mail,password)" +
                       "VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);

        statement.setInt(1,client.getCuil());
        statement.setString(2,client.getNombre());
        statement.setString(3,client.getMail());
        statement.setString(4,client.getPassword());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("El cliente con cuil " +
                    client.getCuil() + "fue guardado en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Borrar cliente
    public void borrarCliente(int cuil) throws SQLException {
        PreparedStatement  statement = null;

        String query = "DELETE FROM clientes WHERE cuil = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0){
            System.out.println("El cliente con cuil: " + cuil +
                    " fue eliminado correctamente");
        }

        if (statement != null){
            statement.close();
        }
    }



}

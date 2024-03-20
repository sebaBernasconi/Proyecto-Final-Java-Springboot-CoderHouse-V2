package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.transactions.Compra;

import java.sql.*;

public class JavaDataBaseControllerTransactions extends JavaDataBaseController{

    //Al heredar de JavaDataBaseController tiene todas las constantes y el objeto Connection

    //CRUD Compra

    //Read
    public void mostrarCompras() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM compra;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilAdmin = resultSet.getInt("cuil_admin");
            float total = resultSet.getFloat("total");

            System.out.println("--------------------------------------------------------");
            System.out.println("numero de compra: " + id);
            System.out.println("Administrador : " + cuilAdmin);
            System.out.println("Total: " + total);
            System.out.println("--------------------------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    //Create
    public void guardarCompra(Compra compra) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO compra(fecha,cuil_admin,total)" +
                "       VALUES(?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setDate(1, (Date) compra.getFecha());
        statement.setInt(2,compra.getVendedor().getCuil());
        statement.setFloat(3,compra.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 ){
            System.out.println("Compra guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Considero que la compra no tiene nada que actualizar

    //Delete
    public void borrarCompra(int idCompra) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM comrpa WHERE id = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,idCompra);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 ){
            System.out.println("Compra con id: " + idCompra + " borrada de la base de datos");
        }
    }

    //Crud Venta
}

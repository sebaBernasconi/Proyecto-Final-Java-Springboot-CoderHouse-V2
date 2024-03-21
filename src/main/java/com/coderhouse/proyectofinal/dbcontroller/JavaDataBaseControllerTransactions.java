package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            int cuilCliente = resultSet.getInt("cuil_cliente");
            int cuilAdmin = resultSet.getInt("cuil_admin");
            float total = resultSet.getFloat("total");

            System.out.println("--------------------------------------------------------");
            System.out.println("Numero de compra: " + id);
            System.out.println("Cliente: " + cuilCliente);
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

        String query = "INSERT INTO compra(fecha,cuil_admin,cuil_cliente,id_carrito,total)" +
                "       VALUES(?,?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setDate(1, (Date) compra.getFecha());
        statement.setInt(2,compra.getVendedor().getCuil());
        statement.setInt(3,compra.getClient().getCuil());
        statement.setInt(4,compra.getCarrito().getIdCarrito());
        statement.setFloat(5,compra.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 ){
            System.out.println("La compra se guardo en la base de datos");
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

    //Read
    public void  mostrarVentas() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM venta;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilCliente = resultSet.getInt("cuil_admin");
            int cuilAdmin = resultSet.getInt("cuil_admin");
            float total = resultSet.getFloat("total");

            System.out.println("--------------------------------------------------------");
            System.out.println("Numero de venta: " + id);
            System.out.println("Cliente : " + cuilCliente);
            System.out.println("Administrador: " + cuilAdmin);
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
    public void guardarVenta(Venta venta) throws SQLException{
        PreparedStatement statement = null;

        String query = "INSERT INTO venta(fecha,cuil_cliente,cuil_admin,id_carrito,total)" +
                "       VALUES(?,?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setDate(1, (Date) venta.getFecha());
        statement.setInt(2,venta.getClient().getCuil());
        statement.setInt(3,venta.getVendedor().getCuil());
        statement.setInt(4,venta.getCarrito().getIdCarrito());
        statement.setFloat(5,venta.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 ){
            System.out.println("Venta guaradada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }

    }

    //Considero que la venta no tiene nada que actualizar

    //Delete
    public void borrarVenta(int idVenta) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM venta WHERE id = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,idVenta);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0 ){
            System.out.println("Venta con id " +  idVenta + " eliminadad de la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Metodos aparte

    public void mostrarComprasDeUnCliente(Client client) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM compra WHERE ciul_cliente = ?;";
        statement = connection.prepareStatement(query);
        statement.setInt(1,client.getCuil());

        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            int cuilAdmin = resultSet.getInt("cuil_admin");
            float total = resultSet.getFloat("total");

            System.out.println("--------------------------------------------------------");
            System.out.println("Numero de compra: " + id);
            System.out.println("Cliente: " + cuilCliente);
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

    public void mostrarVentasDeUnAdmin(Admin admin) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query =  "SELECT * FROM venta WHERE cuil_admin = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,admin.getCuil());

        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilCliente = resultSet.getInt("cuil_admin");
            int cuilAdmin = resultSet.getInt("cuil_admin");
            float total = resultSet.getFloat("total");

            System.out.println("--------------------------------------------------------");
            System.out.println("Numero de venta: " + id);
            System.out.println("Cliente : " + cuilCliente);
            System.out.println("Administrador: " + cuilAdmin);
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


}

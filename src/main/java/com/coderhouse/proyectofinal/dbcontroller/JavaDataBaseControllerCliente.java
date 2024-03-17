package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.*;

public class JavaDataBaseControllerCliente  extends  JavaDataBaseController{

    //Al heredar de JavaDataBaseController tiene todas las constantes y el objeto Connection
    //CRUD Cliente

    //Read
    public void mostrarClientes() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT cuil, nombre, mail, password FROM clientes;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int cuil = resultSet.getInt("cuil");
            String nombre = resultSet.getString("nombre");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");


            System.out.println();
            System.out.println("El cliente con cuil " + cuil +
                    " es " + nombre + ". Su mail es " + mail +
                    " y su contraseña " + password);
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    //Create
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
            System.out.println();
            System.out.println("El cliente con cuil " +
                    client.getCuil() + " fue guardado en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Create
    private void guarComprasDeUnCliente(Client client){
        PreparedStatement statement = null;

        for (Compra compra :
                client.getCompras()) {

        }

    }
    //Update
    public void modificarMailCliente( int cuil, String nuevoMail) throws SQLException {
        PreparedStatement statement = null;

        String query ="UPDATE clientes SET mail = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setString(1,nuevoMail);
        statement.setInt(2,cuil);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.printf("Mail del cleinte con cuil " + cuil +
                    " modificado con exito");
        }

        if (statement != null){
            statement.close();
        }

    }

    //Update
    public void modificarPasswordCliente(int cuil,String nuevaPassword) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE clientes SET password = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setString(1,nuevaPassword);
        statement.setInt(2,cuil);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Password del usuario con cuil " +
                    cuil + " modificada con exito");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Delete
    public void borrarCliente(int cuil) throws SQLException {
        PreparedStatement  statement = null;

        String query = "DELETE FROM clientes WHERE cuil = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0){
            System.out.println();
            System.out.println("El cliente con cuil: " + cuil +
                    " fue eliminado correctamente");
        }

        if (statement != null){
            statement.close();
        }
    }

}

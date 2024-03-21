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

        String query = "SELECT * FROM clientes;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int cuil = resultSet.getInt("cuil");
            String nombre = resultSet.getString("nombre");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            int idCarrito = resultSet.getInt("id_carrito");
            int nroTarjeta = resultSet.getInt("nro_tarjeta");

            System.out.println("--------------------------------------------------------------------");
            System.out.println("Cuil: " + cuil);
            System.out.println("Nombre: " + nombre);
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + password);
            System.out.println("Id Carrito: " + idCarrito);
            System.out.println("Numero de Tarjeta: " + nroTarjeta);
            System.out.println();
            System.out.println("Facturas del cliente {");
            verFacturas(cuil);
            System.out.println("}");
            System.out.println();
            System.out.println("Compras del cliente {");
            verCompras(cuil);
            System.out.println("}");
            System.out.println("--------------------------------------------------------------------");
        }

        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    public void mostrarUnCliente(int cuil) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM clientes WHERE cuil = ?;";
        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);

        resultSet = statement.executeQuery();

        while (resultSet.next()){

            String nombre = resultSet.getString("nombre");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            int idCarrito = resultSet.getInt("id_carrito");
            int nroTarjeta = resultSet.getInt("nro_tarjeta");

            System.out.println("--------------------------------------------------------------------");
            System.out.println("Cuil: " + cuil);
            System.out.println("Nombre: " + nombre);
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + password);
            System.out.println("Id Carrito: " + idCarrito);
            System.out.println("Numero de Tarjeta: " + nroTarjeta);
            System.out.println();
            System.out.println("Facturas del cliente {");
            verFacturas(cuil);
            System.out.println("}");
            System.out.println();
            System.out.println("Compras del cliente {");
            verCompras(cuil);
            System.out.println("}");
            System.out.println("--------------------------------------------------------------------");
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
    public void guarComprasDeUnCliente(Client client) throws SQLException {
        JavaDataBaseControllerTransactions jdbcT = new JavaDataBaseControllerTransactions();

        jdbcT.getConnection();

        for (Compra compra :
                client.getCompras()) {
            jdbcT.guardarCompra(compra);
        }

        jdbcT.closeConnection();

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

    //Update
    public void agregarIdCarrito(Client client) throws SQLException {
        //Primero guardo el carrito en su tabla
        //Instancio jdbc carrito
        JavaDataBaseControllerCarrito jdbcC = new JavaDataBaseControllerCarrito();
        jdbcC.getConnection();

        jdbcC.guardarCarrito(client.getCarrito());

        int idCarrito = jdbcC.obtenerIdDeCarritoPorCuil(client.getCuil());

        jdbcC.closeConnection();

        //Se lo asigno al cliente tambien.
        client.getCarrito().setIdCarrito(idCarrito);

        //Ahora actualizo la tabla cliente
        PreparedStatement statement = null;

        String query = "UPDATE clientes SET id_carrito = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,client.getCuil());
        statement.setInt(2,idCarrito);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("id del carrito guardado para el cliente con cuil: " + client.getCuil());
        }

        if (statement != null){
            statement.close();
        }

    }

    public void agregarNroTarjeta(Client client) throws SQLException {
       //PRimero guardo la tarjeta
        //Instancio el jdbc payment
        JavaDataBaseControllerPayment jdbcP = new JavaDataBaseControllerPayment();

        jdbcP.getConnection();

        jdbcP.guardarTarjetaDebitoCliente(client);

        jdbcP.closeConnection();

        //actualizo el valor en la tabla cliente

        PreparedStatement statement = null;

        String query = "UPDATE clientes SET nro_tarjeta = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,client.gettDebito().getNroTarjeta());
        statement.setInt(2,client.getCuil());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0){
            System.out.println("Numero de tarjeta guardado para el cliente con cuil: " + client.getCuil());
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

    private void verFacturas(int cuilCliente) throws SQLException {
        JavaDataBaseControllerFactura jdbcF = new JavaDataBaseControllerFactura();
        jdbcF.getConnection();

        jdbcF.mostrarFacturasDeUnCliente(cuilCliente);

        jdbcF.closeConnection();

    }

    private void verCompras(int cuilCliente)throws SQLException{
        JavaDataBaseControllerTransactions jdbcT = new JavaDataBaseControllerTransactions();
        jdbcT.getConnection();

        jdbcT.mostrarComprasDeUnCliente(cuilCliente);

        jdbcT.closeConnection();
    }

}

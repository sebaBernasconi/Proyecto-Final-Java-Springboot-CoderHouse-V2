package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.user.Carrito;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDataBaseControllerCarrito extends JavaDataBaseController {
    //Al heredar de JavaDataBaseController tiene todas las constantes y el objeto Connection

    //CRUD Carrito

    //Read
    public void mostrarCarritos() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM carrito;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            int cantProds = resultSet.getInt("cantidad_de_articulos");
            boolean pagado = resultSet.getBoolean("pagado");
            float total = resultSet.getFloat("total");

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Id: " + id);
            System.out.println("Cuil cliente: " + cuilCliente);
            System.out.println("Cantidad de articulos: " + cantProds);
            if (pagado == true){
                System.out.println("Pagado: Si");
            }else {
                System.out.println("Pagado: No");
            }
            System.out.println("Total: " + total);
            System.out.println("------------------------------------------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    public void mostrarCarritoDelCliente(int idCarrito) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM carrito WHERE id = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,idCarrito);
        resultSet = statement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            int cantProds = resultSet.getInt("cantidad_de_articulos");
            boolean pagado = resultSet.getBoolean("pagado");
            float total = resultSet.getFloat("total");

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Id: " + id);
            System.out.println("Cuil cliente: " + cuilCliente);
            System.out.println("Cantidad de articulos: " + cantProds);
            if (pagado == true){
                System.out.println("Pagado: Si");
            }else {
                System.out.println("Pagado: No");
            }
            System.out.println("Total: " + total);
            System.out.println("------------------------------------------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

    }

    //Create

    public void guardarCarrito(Carrito carrito) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO carrito (cuil_cliente,cantidad_de_articulos,pagado,total)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,carrito.getCliente().getCuil());
        statement.setInt(2,carrito.getCantidadDeArticulos());
        statement.setBoolean(3,carrito.isPagado());
        statement.setFloat(4,carrito.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Carrito guardado con en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Update
    public void pagarCarrito(int idCarrito, boolean nuevoPagado) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE carrito SET pagado = ? WHERE id = ? ;";

        statement = connection.prepareStatement(query);
        statement.setBoolean(1,nuevoPagado);
        statement.setInt(2,idCarrito);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Carrito con id: " + idCarrito + " abonado!");
        }

        if (statement != null){
            statement.close();
        }
    }

    public void agregarArticuloAlCarrito(int idCarrito, int nuevaCantidadArticulos) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE carrito SET cantidad_de_articulos = ? WHERE id = ? ;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nuevaCantidadArticulos);
        statement.setInt(2,idCarrito);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Producto sumado al carrito con id: " + idCarrito);
        }

        if (statement != null){
            statement.close();
        }
    }

    //Delete
    public void borrarCarrito(int idCarrito) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM carrito WHERE id = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,idCarrito);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Carrito con id: " + idCarrito + " eliminado de la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    public int obtenerIdDeCarritoPorCuil(int cuil) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;

        String query = "SELECT id FROM carrito WHERE cuil_cliente = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);

        resultSet = statement.executeQuery();


        while (resultSet.next()){
            id = resultSet.getInt("id");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

        return id;


    }


}

package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;

import java.sql.*;

public class JavaDataBaseControllerAdmins extends  JavaDataBaseController{
    //Al heredar de JavaDataBaseController tiene todas las constantes y el objeto Connection
    //CRUD Admin

    //Read
    public void mostrarAdmins() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT cuil, nombre, mail, password FROM administradores;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int cuil = resultSet.getInt("cuil");
            String nombre = resultSet.getString("nombre");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");


            System.out.println("--------------------------------------------------------------------");
            System.out.println("Cuil: " + cuil);
            System.out.println("Nombre: " + nombre);
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + password);
            System.out.println();
            System.out.println("Ventas: ");
            verVentas(cuil);
            System.out.println("--------------------------------------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    public void mostrarAdminPorCuil(int cuil) throws SQLException{

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM administradores WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);
        resultSet = statement.executeQuery();

        while (resultSet.next()){
            String nombre = resultSet.getString("nombre");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");


            System.out.println("--------------------------------------------------------------------");
            System.out.println("Cuil: " + cuil);
            System.out.println("Nombre: " + nombre);
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + password);
            System.out.println();
            System.out.println("Ventas: ");
            verVentas(cuil);
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
    public void guardarAdmin(Admin admin) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO administradores(cuil,nombre,mail,password)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,admin.getCuil());
        statement.setString(2,admin.getNombre());
        statement.setString(3,admin.getMail());
        statement.setString(4,admin.getPassword());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("El adminstrador con cuil " + admin.getCuil() +
                    " fue guardado en la base de datos");
        }

        if (statement != null){
            statement.close();
        }

        //utilizando el metodo privado para guardar las ventas
        guardarVentasDeUnAdmin(admin);

    }
    //Create
    private void guardarVentasDeUnAdmin(Admin admin) throws SQLException {
        JavaDataBaseControllerTransactions jdbcT = new JavaDataBaseControllerTransactions();

        jdbcT.getConnection();

        for (Venta venta :
                admin.getVentas()) {
            jdbcT.guardarVenta(venta);
        }
        
        jdbcT.closeConnection();
    }

    private void verVentas(int cuil) throws SQLException {
        JavaDataBaseControllerTransactions jdbcT = new JavaDataBaseControllerTransactions();

        jdbcT.getConnection();

        jdbcT.mostrarVentasDeUnAdmin(cuil);

        jdbcT.closeConnection();
    }

    //Update
    public void modificarMailAdmin( int cuil, String nuevoMail) throws SQLException {
        PreparedStatement statement = null;

        String query ="UPDATE administradores SET mail = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setString(1,nuevoMail);
        statement.setInt(2,cuil);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.printf("Mail del administrador con cuil " + cuil +
                    " modificado con exito");
        }

        if (statement != null){
            statement.close();
        }

    }

    //Update
    public void modificarPasswordAdmin(int cuil,String nuevaPassword) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE administradores SET password = ? WHERE cuil = ?;";

        statement = connection.prepareStatement(query);
        statement.setString(1,nuevaPassword);
        statement.setInt(2,cuil);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Password del administrador con cuil " +
                    cuil + " modificada con exito");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Delete
    public void borrarAdmin(int cuil) throws SQLException {
        PreparedStatement  statement = null;

        String query = "DELETE FROM administradores WHERE cuil = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,cuil);

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0){
            System.out.println();
            System.out.println("El administrador con cuil: " + cuil +
                    " fue eliminado correctamente");
        }

        if (statement != null){
            statement.close();
        }
    }
}

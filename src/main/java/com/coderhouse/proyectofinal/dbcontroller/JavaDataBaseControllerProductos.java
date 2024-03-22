package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;

import java.sql.*;

public class JavaDataBaseControllerProductos extends JavaDataBaseController{

    //Al heredar de JavaDataBaseController tiene todas las constantes y el objeto Connection

    //CRUD comic

    //Read
    public void mostrarComics() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM comic;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int codigoDeProducto = resultSet.getInt("codigo_de_producto");
            String nombre = resultSet.getString("nombre");
            String desc = resultSet.getString("descripcion");
            String autor = resultSet.getString("autor");
            String idioma = resultSet.getString("idioma");
            boolean tapaDura = resultSet.getBoolean("tapa_dura");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");

            System.out.println("---------------------------------------------------------");
            System.out.println("codigo: " + codigoDeProducto);
            System.out.println("nombre: " + nombre);
            System.out.println("desc: " + desc);
            System.out.println("autor: " + autor);
            System.out.println("idioma: " + idioma);
            System.out.println("tapa dura: " + tapaDura);
            System.out.println("stock: " + stock);
            System.out.println("precio: " + precio);
            System.out.println("---------------------------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
    }

    //Create
    public void guardarComic(Comic comic) throws SQLException{
        PreparedStatement statement = null;

        String query = "INSERT INTO comic(codigo_de_producto,nombre,descripcion," +
                "stock,precio,autor,idioma,tapa_dura)" +
                "VALUES(?,?,?,?,?,?,?,?);";

        statement = connection.prepareStatement(query);

        statement.setInt(1,comic.getCodigoDeProducto());
        statement.setString(2,comic.getNombre());
        statement.setString(3, comic.getDescripcion());
        statement.setInt(4,comic.getStock());
        statement.setFloat(5,comic.getPrecio());
        statement.setString(6,comic.getAutor());
        statement.setString(7,comic.getIdioma());
        statement.setBoolean(8,comic.isTapaDura());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("comic con codigo " + comic.getCodigoDeProducto() +
                    " guardado en la base de datos");
        }

    }

    //Update
    public void modificarComic(int codigo,String nuevoNombre, String nuevaDesc, float nuevoPrecio,
                               String nuevoAutor, String nuevoIdioma) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE comic SET nombre = ? , descripcion = ? , precio = ?, " +
                "autor = ?, idioma = ? WHERE codigo_de_producto = ?;";

        statement = connection.prepareStatement(query);

        statement.setString(1,nuevoNombre);
        statement.setString(2,nuevaDesc);
        statement.setFloat(3,nuevoPrecio);
        statement.setString(4,nuevoAutor);
        statement.setString(5,nuevoIdioma);
        statement.setInt(6,codigo);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Comic con codigo " + codigo +
                    "modificado con exito");
        }

        if (statement != null){
            statement.close();
        }
    }
    //Delete
    public void eliminarComic(int codigo) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM comic WHERE codigo_de_producto = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,codigo);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Comic con codigo " + codigo +
                    " borrado de forma exitosa");
        }

        if (statement != null){
            statement.close();
        }
    }


    //Crud figura de accion

    //Read
    public void mostrarFigurasDeAccion() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM figura_de_accion ;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){

            int codigoDeProducto = resultSet.getInt("codigo_de_producto");
            String nombre = resultSet.getString("nombre");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");

            System.out.println("----------------------------------------");
            System.out.println("codigo: " + codigoDeProducto);
            System.out.println("nombre: " + nombre);
            System.out.println("stock: " + stock);
            System.out.println("precio: $" + precio );
            System.out.println("----------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    //Create
    public void guardarFiguraDeAccion(FiguraDeAccion figuraDeAccion) throws SQLException {
        PreparedStatement statement = null;

        String query = "INSERT INTO figura_de_accion(codigo_de_producto,nombre,descripcion,stock,precio," +
                "fabricante,es_articulado)" +
                "VALUES(?,?,?,?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,figuraDeAccion.getCodigoDeProducto());
        statement.setString(2,figuraDeAccion.getNombre());
        statement.setString(3,figuraDeAccion.getDescripcion());
        statement.setInt(4,figuraDeAccion.getStock());
        statement.setFloat(5,figuraDeAccion.getPrecio());
        statement.setString(6,figuraDeAccion.getFabricante());
        statement.setBoolean(7,figuraDeAccion.isEsArticulado());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Figura de accion con codigo " + figuraDeAccion.getCodigoDeProducto() +
                    " guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Update
    public void actualizarFiguraDeAccion(int codigoProducto, int nuevoStock, float nuevoPrecio) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE figura_de_accion SET stock = ?, precio = ? WHERE codigo_de_producto = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nuevoStock);
        statement.setFloat(2,nuevoPrecio);
        statement.setInt(3,codigoProducto);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Figura de accion con codigo: " + codigoProducto + " modificada exitosamente");
        }

        if (statement != null){
            statement.close();
        }

    }

    //Delete
    public void borrarFiguraDeAccion(int codigoProducto) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM figura_de_accion WHERE codigo_de_producto = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,codigoProducto);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Figura de accion con codigo: " + codigoProducto + " borrado de la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }
}

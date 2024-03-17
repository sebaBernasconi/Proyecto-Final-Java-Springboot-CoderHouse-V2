package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerProductos;
import com.coderhouse.proyectofinal.model.product.Comic;

import java.sql.SQLException;

public class TestJdbcProductos {

    public static void main(String[] args) throws SQLException {

        Comic comic = new Comic(1089,"Flash: Born To Run","Comic centrado en los primeros años de wally"
                ,5,15.000F,"Mark Weid", "Ingles",
                true);

        JavaDataBaseControllerProductos controllerProductos = new JavaDataBaseControllerProductos();

        controllerProductos.getConnection();

        controllerProductos.guardarComic(comic);
        controllerProductos.mostrarComics();

        controllerProductos.modificarComic(1089,"Flash Rebirth","Barry year 1",
                100.0F,"Desconocido","Español");

        controllerProductos.mostrarComics();

        controllerProductos.eliminarComic(1089);

        System.out.println("Antes");
        controllerProductos.mostrarComics();
        System.out.println("Despues");

        controllerProductos.closeConnection();
    }
}

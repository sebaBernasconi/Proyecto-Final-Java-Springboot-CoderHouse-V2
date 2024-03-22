package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerProductos;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;

import java.sql.SQLException;

public class TestJdbcProductos {

    public static void main(String[] args) throws SQLException {

        Comic comic = new Comic(1089,"Flash: Born To Run","Comic centrado en los primeros años de wally"
                ,5,15.000F,"Mark Weid", "Ingles",
                true);

        Comic comic2 = new Comic(1049,"Flash: Terminal Velocity","Comic centrado en la speedforce"
                ,3,1400F,"Mark Weid", "Ingles",
                true);

        FiguraDeAccion figuraDeAccion = new FiguraDeAccion(1110,"The Batman",
                "muñeco de la pelicula the batman",2,4000F,"Mattel",true);

        FiguraDeAccion figuraDeAccion2 = new FiguraDeAccion(1150,"Superman",
                "muñeco de la pelicula man of steel",1,24000F,"Mattel",true);

        JavaDataBaseControllerProductos controllerProductos = new JavaDataBaseControllerProductos();

        controllerProductos.getConnection();

        controllerProductos.guardarComic(comic);
        controllerProductos.guardarComic(comic2);

        System.out.println("-------------------------MOSTRANDO LOS COMICS-------------------------");
        controllerProductos.mostrarComics();

        controllerProductos.modificarComic(1089,"Flash Rebirth","Barry year 1",
                100.0F,"Desconocido","Español");

        System.out.println("-------------------------MOSTRANDO LOS COMICS DESPUES DE MODIFICAR-------------------------");

        controllerProductos.mostrarComics();

        controllerProductos.eliminarComic(comic.getCodigoDeProducto());

        System.out.println("System.out.println(\"-------------------------MOSTRANDO LOS COMICS DESPUES DE BORRAR-------------------------\");");
        controllerProductos.mostrarComics();

        //Borrando el otro comic para proxima ejecucion
        controllerProductos.eliminarComic(comic2.getCodigoDeProducto());


        //metodos de figura de accion
        controllerProductos.guardarFiguraDeAccion(figuraDeAccion);
        controllerProductos.guardarFiguraDeAccion(figuraDeAccion2);

        System.out.println("-------------------------MOSTRANDO LAS FIGURAS-------------------------");

        controllerProductos.mostrarFigurasDeAccion();

        //Actualizando una
        controllerProductos.actualizarFiguraDeAccion(figuraDeAccion.getCodigoDeProducto(), 23,1);

        System.out.println("-------------------------MOSTRANDO LAS FIGURAS DESPUES DE MODIFICAR-------------------------");

        controllerProductos.mostrarFigurasDeAccion();

        //borrando una
        controllerProductos.borrarFiguraDeAccion(figuraDeAccion2.getCodigoDeProducto());

        System.out.println("-------------------------MOSTRANDO LAS FIGURAS DESPUES DE BORRAR-------------------------");

        controllerProductos.mostrarFigurasDeAccion();

        //borrando la otra para la proxima ejecucion
        controllerProductos.borrarFiguraDeAccion(figuraDeAccion.getCodigoDeProducto());
        System.out.println("System.out.println(\"-------------------------FIN TEST JDBCPRODUCTOS-------------------------\");");

        controllerProductos.closeConnection();
    }
}

package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestObjetos {

    public static void main(String[] args) {
       /* Client client = new Client(2012345678,"Juan","jj@gmail.com","contraseña original",
                null,null, null);

        Admin admin = new Admin(111222333,"Ramiro","rama@gmail.com","ACDC",
                new ArrayList<>());

        Debito tarjeta = new Debito(44455566,"Juan",253,3242.33F);

        Carrito carrito = new Carrito(client, 3,null , true, 1120);

        Compra compra = new Compra(new Date(2020,4,1),carrito,carrito.getTotal(),carrito.getCliente()
                ,admin);

        Venta venta = new Venta(new Date(2020,4,1),carrito,carrito.getTotal(),carrito.getCliente()
                ,admin);

        Comic comic = new Comic(1089,"Flash: Born To Run","Comic centrado en los primeros años de wally"
                ,5,15.000F,"Mark Weid", "Ingles",
                true);

        FiguraDeAccion figuraDeAccion = new FiguraDeAccion(1110,"The Batman",
                "muñeco de la pelicula the batman",2,4000F,"Mattel",true);

        List<Compra> listaCompras = new ArrayList<>();
        listaCompras.add(compra);

        List<Venta>listaVentas = new ArrayList<>();
        listaVentas.add(venta);


        List< Producto > productos = new ArrayList<>();

        carrito.setProductos(productos);

        client.setCarrito(carrito);
        client.settDebito(tarjeta);
        client.setCompras(listaCompras);

        admin.setVentas(listaVentas);

        System.out.println("Metodos del client");
        client.agregarAlCarrito(comic);
        System.out.println();
        client.agregarAlCarrito(figuraDeAccion);
        System.out.println();
        client.pagarCarrito();
        System.out.println();

        System.out.println("Metodos del carrito");
        carrito.sacarDelCarrito(comic);
        System.out.println();
        carrito.estaEnElCarrito(figuraDeAccion.getCodigoDeProducto());
        System.out.println();
        carrito.pagarCarrito();
        System.out.println();
        carrito.verProductosEnElCarrito();
        System.out.println();

        System.out.println("Metodos de compra");
        compra.verDetalleDeCompra();
        System.out.println();

        System.out.println("Metodos de comic");
        comic.actualizarStock();
        System.out.println();
        comic.modificarPrecio(1999);
        System.out.println();
        comic.editarComic("nuevo nombre de prueba","nueva descripcion de prueba",
                "nuevo autor de prueba","nuevo idioma de prueba",true);
        System.out.println();

        System.out.println("Metodos de figura de accion");
        figuraDeAccion.modificarPrecio(12434);
        System.out.println();
        figuraDeAccion.editarFiguraDeAccion("nuevo nombre de prueba","nueva descripcion de prueba",
                "nuevo fabricante de prueba",false);
        System.out.println();


        System.out.println("Fin prueba objetos");


        */

    }
}

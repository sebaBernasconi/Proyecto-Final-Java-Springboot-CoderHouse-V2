package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseController;
import com.coderhouse.proyectofinal.dbcontroller.JavaDataBaseControllerCliente;
import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestJdbcClient {

    public static void main(String[] args) throws SQLException {

        //Instanciando el jdbc
        JavaDataBaseControllerCliente controller = new JavaDataBaseControllerCliente();


        //Instanciando al cliente
        Client client = new Client(2012345678,"Juan","jj@gmail.com","contraseña original",
                null,null, null);

        //Instanciando las demas clases que necesito
        Admin admin = new Admin(111222333,"Ramiro","rama@gmail.com","ACDC",
                new ArrayList<>());

        Debito tarjeta = new Debito(44455566,"Juan",253,3242.33F);

        Carrito carrito = new Carrito(client, 3, null, true, 1120);

        Compra compra = new Compra(new Date(2020,4,1),carrito,carrito.getTotal(),carrito.getCliente()
                ,admin);

        List<Compra> listaCompras = new ArrayList<>();
        listaCompras.add(compra);





        controller.getConnection();

        //Primero tengo que guardarlo con los valores en null para id carrito, tarjeta y compras
        //Por que si no da error la fk del cuil en dichas tablas. Una vez guardado, actualizo los valores para
        //ese cliente
        controller.guardarCliente(client);


        System.out.println("----------------------------------Mostrando todos los clientes----------------------------------");
        controller.mostrarClientes();
        System.out.println();


        //Setteo lo que faltaba del cliente
        client.setCarrito(carrito);
        client.settDebito(tarjeta);
        client.setCompras(listaCompras);


        //cambiando el mail y la password y agrego el id carrito y nro tarjeta
        controller.modificarMailCliente(client.getCuil(),"JuanJose@gmail.com");
        controller.modificarPasswordCliente(client.getCuil(),"cont actualizada");
        //Puse asi la nueva contraseña porque es varchar(30). No entran mas caracteres jeje
        controller.agregarIdCarrito(client);

        //Guardando las compras
        controller.guarComprasDeUnCliente(client);


        controller.agregarNroTarjeta(client.getCuil(),client.gettDebito().getNroTarjeta());
        System.out.println();

        System.out.println("----------------------------------Mostrando los cambios en el cliente----------------------------------");
        controller.mostrarUnCliente(client.getCuil());
        System.out.println();


        //borrando al cliente
        controller.borrarCliente(client.getCuil());
        System.out.println("----------------------------------Buscando al cliente borrado---------------------------------");
        System.out.println("Antes de la busqueda");
        controller.mostrarUnCliente(client.getCuil());
        System.out.println("Despues de la busqueda");

        controller.closeConnection();
    }
}

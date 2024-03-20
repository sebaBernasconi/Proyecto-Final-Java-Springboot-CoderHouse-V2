package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.CarritoNotFoundException;
import com.coderhouse.proyectofinal.exception.ProductNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.util.ArrayList;
import java.util.List;

public class ControllerUser {

    private int idClient = 0;
    private int idAdmin = 0;

    private List<Client> listadoDeClientes;
    private List<Admin> listadoDeAdmins;
    private static  ControllerUser instancia;


    //Constructor
    public ControllerUser() {
        this.listadoDeClientes = new ArrayList<>();
        this.listadoDeAdmins = new ArrayList<>();
    }

  //getInstancia para que sea singleton
    public static ControllerUser getInstancia(){
        if (instancia == null){
            return  instancia = new ControllerUser();
        }else {
            return instancia;
        }
    }

    //Metodos del Controller
    public void registrarCliente(int cuil, String nombre, String mail,
                                 String password)
            throws UserNotFoundException, CarritoNotFoundException {
        if (buscarCliente(cuil) == null){
            Client c = new Client(cuil,nombre,mail,password,
                    null,null,null);
            idClient ++;

            ControllerCarrito controllerCarrito = ControllerCarrito.getInstancia();
            c.setCarrito(controllerCarrito.crearCarrito(cuil));

            listadoDeClientes.add(c);
        }else {
            System.out.println("Ya hay un cliente registrado con el cuil: " + cuil);
        }


    }

    public void registrarAdmin(int cuil, String nombre, String mail,
                               String password) throws UserNotFoundException {
        if (buscarAdmin(cuil) == null){
            Admin a = new Admin(cuil,nombre,mail,password,null);
            idAdmin ++;
            listadoDeAdmins.add(a);
        }else {
            System.out.println("Ya hay un admin registrado con el cuil: " + cuil);
        }
    }

    public void agregarMedioDePagoAlCliente(int cuil, Debito tDebito)
        throws UserNotFoundException{
        Client c = buscarCliente(cuil);
        c.settDebito(tDebito);
    }

    public void agregarFiguraDeAccionAlCarrito(int cuil, int codigoDeProducto) throws UserNotFoundException,
            ProductNotFoundException {

        Client c = buscarCliente(cuil);
        ControllerProducto controllerProducto = ControllerProducto.getIntancia();
        FiguraDeAccion f = controllerProducto.getFiguraDeAccion(codigoDeProducto);
        if((c != null && f != null)){
            c.agregarAlCarrito(f);
        }
    }

    public void agregarComicAlCarrito(int cuil, int codigoDeProducto)
    throws UserNotFoundException, ProductNotFoundException{
        Client c = buscarCliente(cuil);

        ControllerProducto controllerProducto = ControllerProducto.getIntancia();
        Comic comic = controllerProducto.getComic(codigoDeProducto);

        if (c != null && comic != null){
            c.agregarAlCarrito(comic);
        }
    }

    public void sacarFiguraDeAccionDelCarrito(int cuil, int codigoDeProducto)
            throws UserNotFoundException, ProductNotFoundException {
        Client c = buscarCliente(cuil);

        ControllerProducto controllerProducto = ControllerProducto.getIntancia();
        FiguraDeAccion f = controllerProducto.getFiguraDeAccion(codigoDeProducto);

        Carrito carrito = c.getCarrito();

        if((c != null && f != null) && carrito.estaEnElCarrito(codigoDeProducto)){
            c.sacarDelCarrito(f);
        }
    }

    public void sacarComicDelCarrito(int cuil, int codigoDeProducto)
        throws UserNotFoundException, ProductNotFoundException{
        Client c = buscarCliente(cuil);

        ControllerProducto controllerProducto = ControllerProducto.getIntancia();
        Comic comic = controllerProducto.getComic(codigoDeProducto);

        Carrito carrito = c.getCarrito();

        if ((c != null && comic != null) && carrito.estaEnElCarrito(codigoDeProducto)){
            c.sacarDelCarrito(comic);
        }
    }

    public void pagarCarrito(int cuil) throws UserNotFoundException {
        Client c = buscarCliente(cuil);
        if (c != null) {
            c.pagarCarrito();
        }
    }

    public void verComprasDeUnCliente(int cuil) throws UserNotFoundException {
        Client c = buscarCliente(cuil);
        if (c != null) {
            List<Compra> compras = c.getCompras();

            for (Compra compra :
                    compras) {
                System.out.println(compra.toString());
            }
        }

    }

    public void verVentasDeUnAdmin(int cuil) throws UserNotFoundException {
        Admin a = buscarAdmin(cuil);
        if (a != null){
            List<Venta> ventas = a.getVentas();

            for (Venta v :
                    ventas) {
                System.out.println(v.toString());
            }
        }

    }

    public void mostrarClientes(){
        if (!listadoDeClientes.isEmpty()){
            for (Client c :
                    listadoDeClientes) {
                System.out.println(c.toString());
            }
        }else {
            System.out.println("No hay ningun cliente registrado todavia");
        }

    }

    public void mostrarAdmins(){
        if (!listadoDeAdmins.isEmpty()){
            for (Admin a :
                    listadoDeAdmins) {
                System.out.println(a.toString());
            }
        }else {
            System.out.println("No hay ningun admin registrado todavia");
        }

    }

    //Getter del cliente
    //Malas practicas?
    public Client getClient(int cuil)throws UserNotFoundException{
        return buscarCliente(cuil);
    }

    //Metodos privados que devuelven objetos que el cliente nunca debe ver
    private Client buscarCliente(int cuil) throws UserNotFoundException {
        for (Client c :
                listadoDeClientes) {
            if (cuil == c.getCuil()) {
                return c;
            }
        }
        throw new UserNotFoundException("No hay ningun cliente registrado " +
                "con el cuil: " + cuil);
    }

    private Admin buscarAdmin(int cuil) throws UserNotFoundException{
        for (Admin a :
                listadoDeAdmins) {
            if (cuil == a.getCuil()){
                return a;
            }
        }
        throw new UserNotFoundException("No hay ningun admin registrado " +
                "con el cuil: " + cuil);
    }

}

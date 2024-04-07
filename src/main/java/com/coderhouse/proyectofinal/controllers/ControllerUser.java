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
import com.coderhouse.proyectofinal.service.user.AdminService;
import com.coderhouse.proyectofinal.service.user.ClientService;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class ControllerUser {

    @Autowired
    ClientService clientService;

    @Autowired
    AdminService adminService;
    private static  ControllerUser instancia;


    //Constructor
    public ControllerUser() {

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

    @PostMapping(value ="/agregarClient", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client>guardarCliente(@RequestBody Client client){
        clientService.guardarCliente(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping(value = "/agregarAdmin", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Admin>guardarAdmin(@RequestBody Admin admin){
        adminService.guardarAdmin(admin);
        return new ResponseEntity<>(admin,HttpStatus.CREATED);
    }

    @PutMapping(value = "/modificarMailClient/{id}")
    public ResponseEntity<Client>modificarMailClient(@PathVariable("id") Integer cuil,
                                                     String nuevoMail){
        try {
            Client cliente = clientService.modificarMail(cuil,nuevoMail);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/modificarMailAdmin/{id}")
    public ResponseEntity<Admin>modificarMailAdmin(@PathVariable("id") Integer cuil,
                                                     String nuevoMail){
        try {
            Admin admin = adminService.modificarMail(cuil,nuevoMail);
            return new ResponseEntity<>(admin,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/modificarPasswordClient/{id}")
    public ResponseEntity<Client>modificarPasswordCliente(@PathVariable("id") Integer cuil,
                                                          String nuevaPassword){
        try {
            Client cliente = clientService.modificarPassword(cuil,nuevaPassword);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/modificarPasswordAdmin/{id}")
    public ResponseEntity<Admin>modificarPasswordAdmin(@PathVariable("id") Integer cuil,
                                                          String nuevaPassword){
        try {
            Admin admin = adminService.modificarPassword(cuil,nuevaPassword);
            return new ResponseEntity<>(admin,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/borrarCliente/{id}")
    public ResponseEntity<Void>borrarCliente(@PathVariable("id")Integer cuil){
        boolean clienteEliminado = clientService.eliminarCliente(cuil);

        if (clienteEliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/borrarAdmin/{id}")
    public ResponseEntity<Void>borrarAdmin(@PathVariable("id") Integer cuil){
        boolean adminEliminado = adminService.eliminarAdmin(cuil);

        if (adminEliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listarClientes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Client>>listarClientes(){
        try {
            List<Client>listadoClientes = clientService.listarClientes();
            return new ResponseEntity<>(listadoClientes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarAdmins", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Admin>>listarAdmins(){
        try {
            List<Admin>listadoAdmins = adminService.listarAdmins();
            return new ResponseEntity<>(listadoAdmins,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
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
*/
}

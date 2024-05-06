package com.coderhouse.proyectofinal;

import com.coderhouse.proyectofinal.controllers.ControllerPayment;
import com.coderhouse.proyectofinal.controllers.ControllerProducto;
import com.coderhouse.proyectofinal.controllers.ControllerUser;
import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.payment.Tarjeta;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalApplication.class, args);
    }


   @Override
    public void run(String...args) throws Exception{
        mostrarMenu();
    }

    //Instanciando los controllers
    ControllerProducto controllerProducto = ControllerProducto.getIntancia();
    ControllerUser controllerUser = ControllerUser.getInstancia();

    ControllerPayment controllerPayment = ControllerPayment.getInstancia();

    public void mostrarMenu(){
        try{
            Scanner sc = new Scanner(System.in);

            int opcion = -1; //Inicializamos la variable con una opcion invalida

            do {
                try {
                    System.out.println("Menu: \n" +
                            "1. Metodos Client \n" +
                            "2. Metodos Admin \n" +
                            "3. Metodos Comic \n" +
                            "4. Metodos FiguraDeAccion \n" +
                            "5. Metodos Carrito \n" +
                            "6. Metodos Compra \n" +
                            "7. Metodos Venta \n" +
                            "8. Metodos Debito \n" +
                            "9. Metodos Factura \n" +
                            "0. Salir \n");
                    System.out.println("Ingrese una opcion");

                    if (sc.hasNextInt()){
                        opcion = sc.nextInt();
                        sc.nextLine();
                    }else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        sc.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            subMenuClientes();
                            break;
                        case 2:
                            subMenuAdmins();
                            break;
                        case 3:
                            subMenuComics();
                            break;
                        case 4:
                            subMenuFigurasDeAccion();
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        case 0:
                            System.out.println("Fin del programa...");
                            break;
                    }
                }catch (InputMismatchException e){
                    System.err.println("Error: Ingrese un numero valido");
                    sc.nextLine();
                    opcion = -1;
                }
            }while (opcion != 0);

            sc.close();

        }catch (Exception e){
            e.getMessage();
        }
    }


    //Metodos de los clientes
    public void subMenuClientes(){
        try {
            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            do {
                try {
                    System.out.println("Sub Menu Clientes: \n" +
                            "1. Guardar cliente \n" +
                            "2. Modificar mail \n" +
                            "3. Modificar password \n" +
                            "4. Listar facturas \n" +
                            "5. Eliminar cliente \n" +
                            "6. Listar clientes \n" +
                            "0. Volver al menu principal \n");
                    System.out.println("Ingrese una opcion: ");

                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            guardarCliente();
                            //Hay que completar el metodo cuando esten el resto de los sub menues
                            break;
                        case 2:
                            modificarMailCliente();
                            break;
                        case 3:
                            modificarPasswordCliente();
                        case 4:
                            listarFacturasCliente();
                            break;
                        case 5:
                            eliminarCliente();
                            break;
                        case 6:
                            listarClientes();
                            break;
                        case 0:
                            mostrarMenu();
                        default:
                            System.err.println("Opcion invlida. Ingrese un numero del menu.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Ingrese un numero valido");
                    scanner.nextLine();
                    opcion = -1;
                }
            } while (opcion != 0);

            scanner.close();

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void guardarCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese por teclado los siguientes datos");

        System.out.println("Cuil del cliente: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Nombre del Cliente: ");
        String nombre = scanner.nextLine();
        System.out.println();

        System.out.println("Mail del cliente: ");
        String mail = scanner.nextLine();
        System.out.println();

        System.out.println("Password del cliente: ");
        String password = scanner.nextLine();
        System.out.println();
        //El resto cuando haga los otros menus y metodos
        System.out.println();
    }

    public void modificarMailCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del cliente cuyo mail desea modificar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el nuevo mail: ");
        String nuevoMail = scanner.nextLine();
        System.out.println();

        controllerUser.modificarMailClient(cuil,nuevoMail);

    }

    public void modificarPasswordCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del cliente cuya password desea modificar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese la nueva password: ");
        String nuevaPass = scanner.nextLine();
        System.out.println();

        controllerUser.modificarPasswordCliente(cuil,nuevaPass);
    }

    public void listarFacturasCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del cliente del que quiere ver las facturas: ");
        int cuil = scanner.nextInt();
        System.out.println();

        controllerUser.listarFacturasDeUnCliente(cuil);
    }

    public void eliminarCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del cliente que desea eliminar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        controllerUser.borrarCliente(cuil);
    }

    public void listarClientes(){
        List<Client>listadoClientes = controllerUser.listarClientes().getBody();
        System.out.println("Listado de clientes: { ");

        for (Client c :
                listadoClientes) {
            System.out.println(" [ ");
            c.toString();
            System.out.println(" ], ");
        }
        System.out.println(" }");
    }

    //Metodos de los admins
    public void subMenuAdmins(){
        try {
            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            do {
                try {
                    System.out.println("Sub Menu Admins: \n" +
                            "1. Guardar admin \n" +
                            "2. Modificar mail \n" +
                            "3. Modificar password \n" +
                            "4. Eliminar admin \n" +
                            "5. Listar admin \n" +
                            "0. Volver al menu principal \n");
                    System.out.println("Ingrese una opcion: ");

                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            guardarAdmin();
                        case 2:
                            modificarMailAdmin();
                        case 3:
                            modificarPasswordAdmin();
                        case 4:
                            elimarAdmin();
                        case 5:
                            listarAdmins();
                        case 0:
                            mostrarMenu();
                        default:
                            System.err.println("Opcion invlida. Ingrese un numero del menu.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Ingrese un numero valido");
                    scanner.nextLine();
                    opcion = -1;
                }
            } while (opcion != 0);

            scanner.close();

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void guardarAdmin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese por teclado los siguientes datos");

        System.out.println("Cuil del admin: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Nombre del admin: ");
        String nombre = scanner.nextLine();
        System.out.println();

        System.out.println("Mail del admin: ");
        String mail = scanner.nextLine();
        System.out.println();

        System.out.println("Password del admin: ");
        String password = scanner.nextLine();
        System.out.println();

        List<Venta>ventas = new ArrayList<>();
        Admin admin = new Admin(cuil,nombre,mail,password,ventas);
        controllerUser.guardarAdmin(admin);
    }

    public void modificarMailAdmin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del admin cuyo mail desea modificar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el nuevo mail: ");
        String nuevoMail = scanner.nextLine();
        System.out.println();

        controllerUser.modificarMailAdmin(cuil,nuevoMail);
    }

    public void modificarPasswordAdmin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del admin cuya password desea modificar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese la nueva password: ");
        String nuevaPass = scanner.nextLine();
        System.out.println();

        controllerUser.modificarPasswordAdmin(cuil,nuevaPass);
    }

    public void elimarAdmin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuil del admin que desea eliminar: ");
        int cuil = scanner.nextInt();
        System.out.println();

        controllerUser.borrarAdmin(cuil);
    }

    public void listarAdmins(){
        List<Admin>listadoAdmins = controllerUser.listarAdmins().getBody();
        System.out.println("Listado de admins: { ");

        for (Admin a :
                listadoAdmins) {
            System.out.println(" [ ");
            a.toString();
            System.out.println(" ], ");
        }
        System.out.println(" }");
    }

    //Metodos de los comics
    public void subMenuComics(){
        try {
            Scanner scComic = new Scanner(System.in);
            int opcion = -1;

            do {
                try {
                    System.out.println("Sub Menu Comics: \n" +
                            "1. Guardar Comic \n" +
                            "2. Actualizar stock del comic post venta \n" +
                            "3. Editar Comic \n" +
                            "4. Actualizar precio \n" +
                            "5. Eliminar Comic \n" +
                            "6. Buscar Comic \n" +
                            "7. Listar Comics \n" +
                            "0. Volver al menu principal \n");
                    System.out.println("Ingrese una opcion: ");

                    if (scComic.hasNextInt()){
                        opcion = scComic.nextInt();
                        scComic.nextLine();
                    }else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        scComic.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            guardarComic();
                            break;
                        case 2:
                            actualizarStockComicPostVenta();
                            break;
                        case 3:
                            editarComic();
                            break;
                        case 4:
                            actualizarPrecioComic();
                            break;
                        case 5:
                            eliminarComic();
                            break;
                        case 6:
                            buscarComic();
                            break;
                        case 7:
                            listarComics();
                            break;
                        case 0:
                            mostrarMenu();
                        default:
                            System.err.println("Opcion invlida. Ingrese un numero del menu.");
                            break;
                    }

                }catch (InputMismatchException e){
                    System.err.println("Error: Ingrese un numero valido");
                    scComic.nextLine();
                    opcion = -1;
                }

            }while (opcion != 0);

            scComic.close();
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void guardarComic(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingerese por teclado los siguientes datos");

        System.out.println("Codigo de comic: ");
        int codigoDeProducto = sc.nextInt();
        System.out.println();

        System.out.println("Nombre del comic: ");
        String nombre = sc.nextLine();
        System.out.println();

        System.out.println("Descripcion del comic: ");
        String descripcion = sc.nextLine();
        System.out.println();

        System.out.println("Stock del comic: ");
        int stock = sc.nextInt();
        System.out.println();

        System.out.println("Precio del comic: ");
        float precio = sc.nextFloat();
        System.out.println();

        System.out.println("Autor del comic: ");
        String autor = sc.nextLine();
        System.out.println();

        System.out.println("Idioma del comic: ");
        String idioma = sc.nextLine();
        System.out.println();

        System.out.println("Tapa dura(boolean): ");
        boolean tapaDura = sc.nextBoolean();
        System.out.println();

        Comic comic = new Comic(codigoDeProducto,nombre,descripcion,stock,precio,autor,
                idioma,tapaDura);
        controllerProducto.registrarComic(comic);

    }

    public void actualizarStockComicPostVenta(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto del comic cuyo stock desea actualizar: ");
        int codigo = scanner.nextInt();
        System.out.println();

        controllerProducto.actualizarStockComic(codigo);
    }

    public void editarComic(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto del comic que desea actualizar: ");
        int codigo = sc.nextInt();
        System.out.println();

        System.out.println("Ingrese los datos que desea modificar del comic: ");
        System.out.println();

        System.out.println("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.println();

        System.out.println("Nueva descripcion: ");
        String nuevaDesc = sc.nextLine();
        System.out.println();

        System.out.println("Nuevo autor: ");
        String nuevoAutor = sc.nextLine();
        System.out.println();

        System.out.println("Nuevo idioma: ");
        String nuevoIdioma = sc.nextLine();
        System.out.println();

        System.out.println("Nuevo esTapaDura(boolean): ");
        boolean nuevoEsTapaDura = sc.nextBoolean();
        System.out.println();

        Comic comicActualizar = controllerProducto.obtenerComicPorCodigo(codigo).getBody();

        comicActualizar.setNombre(nuevoNombre);
        comicActualizar.setDescripcion(nuevaDesc);
        comicActualizar.setAutor(nuevoAutor);
        comicActualizar.setIdioma(nuevoIdioma);
        comicActualizar.setTapaDura(nuevoEsTapaDura);

        controllerProducto.editarComic(codigo,comicActualizar);
    }

    public void actualizarPrecioComic(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto del comic cuyo precio desea actualizar: ");
        int codigo = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el nuevo precio: ");
        float nuevoPrecio = scanner.nextFloat();
        System.out.println();

        controllerProducto.modificarPrecioComic(codigo,nuevoPrecio);

    }

    public void eliminarComic(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto del comic que desea eliminar: ");
        int codigo = sc.nextInt();
        System.out.println();

        controllerProducto.eliminarComic(codigo);
    }

    public void buscarComic(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto del comic que desea buscar: ");
        int codigo = sc.nextInt();
        System.out.println();

        Comic comic = controllerProducto.obtenerComicPorCodigo(codigo).getBody();

        comic.toString();
    }

    public void listarComics(){
        System.out.println("Listado de comics: { ");

        List<Comic> listadoComics = controllerProducto.obtenerComics().getBody();

        for (Comic c :
                listadoComics) {
            System.out.println("[");
            c.toString();
            System.out.println("],");
        }

        System.out.println(" }");
    }

    //Metodo de las figuras de accion

    public void subMenuFigurasDeAccion(){
        try {
            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            do {
                try {
                    System.out.println("Sub Menu FiguraDeAccion: \n" +
                            "1. Guardar FiguraDeAccion \n" +
                            "2. Actualizar stock del figura post venta \n" +
                            "3. Editar FiguraDeAccion \n" +
                            "4. Actualizar precio \n" +
                            "5. Eliminar FiguraDeAccion \n" +
                            "6. Buscar FiguraDeAccion \n" +
                            "7. Listar FigurasDeAccion \n" +
                            "0. Volver al menu principal \n");
                    System.out.println("Ingrese una opcion: ");

                    if (scanner.hasNextInt()){
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                    }else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            guardarFiguraDeAccion();
                            break;
                        case 2:
                            actualizarStockFiguraPostVenta();
                            break;
                        case 3:
                            editarFigura();
                            break;
                        case 4:
                            actualizarPrecioFigura();
                            break;
                        case 5:
                            eliminarFigura();
                            break;
                        case 6:
                            buscarFigura();
                            break;
                        case 7:
                            listarFiguras();
                            break;
                        case 0:
                            mostrarMenu();
                        default:
                            System.err.println("Opcion invlida. Ingrese un numero del menu.");
                            break;
                    }

                }catch (InputMismatchException e){
                    System.err.println("Error. Ingrese un numero valido");
                    scanner.nextLine();
                    opcion = -1;
                }
            }while (opcion != 0);

            scanner.close();
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void guardarFiguraDeAccion(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingerese por teclado los siguientes datos");

        System.out.println("Codigo de la figura de accion: ");
        int codigoDeProducto = sc.nextInt();
        System.out.println();

        System.out.println("Nombre del comic: ");
        String nombre = sc.nextLine();
        System.out.println();

        System.out.println("Descripcion de la figura de accion: ");
        String descripcion = sc.nextLine();
        System.out.println();

        System.out.println("Stock de la figura de accion: ");
        int stock = sc.nextInt();
        System.out.println();

        System.out.println("Precio de la figura de accion: ");
        float precio = sc.nextFloat();
        System.out.println();

        System.out.println("Fabricante de la figura de accion: ");
        String fabricante = sc.nextLine();
        System.out.println();

        System.out.println("EsArticulado(boolean): ");
        boolean esArticulado = sc.nextBoolean();
        System.out.println();

        FiguraDeAccion figuraDeAccion = new FiguraDeAccion(codigoDeProducto,nombre,descripcion,stock,precio,
                fabricante,esArticulado);
        controllerProducto.registrarFiguraDeAccion(figuraDeAccion);

    }

    public void actualizarStockFiguraPostVenta(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto de la figura cuyo stock desea actualizar: ");
        int codigo = scanner.nextInt();
        System.out.println();

        controllerProducto.actualizarStockFiguraDeAccion(codigo);
    }

    public void editarFigura(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto de la figura de accion que desea actualizar: ");
        int codigo = sc.nextInt();
        System.out.println();

        System.out.println("Ingrese los datos que desea modificar de la figura: ");
        System.out.println();

        System.out.println("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.println();

        System.out.println("Nueva descripcion: ");
        String nuevaDesc = sc.nextLine();
        System.out.println();

        System.out.println("Nuevo fabricante: ");
        String nuevoFabricante = sc.nextLine();
        System.out.println();

        System.out.println("Nuevo esArticulado(boolean): ");
        boolean nuevoEsArticulado = sc.nextBoolean();
        System.out.println();

        FiguraDeAccion figuraDeAccion = controllerProducto.obtenerFiguraPorCodigo(codigo).getBody();

        figuraDeAccion.setNombre(nuevoNombre);
        figuraDeAccion.setDescripcion(nuevaDesc);
        figuraDeAccion.setFabricante(nuevoFabricante);
        figuraDeAccion.setEsArticulado(nuevoEsArticulado);

        controllerProducto.editarFiguraDeAccion(codigo,figuraDeAccion);

    }

    public void actualizarPrecioFigura(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de producto de la figura cuyo precio desea actualizar: ");
        int codigo = sc.nextInt();
        System.out.println();

        System.out.println("Ingrese el nuevo precio: ");
        float nuevoPrecio = sc.nextFloat();
        System.out.println();

        controllerProducto.modificarPrecioFiguraDeAccion(codigo,nuevoPrecio);
    }

    public void eliminarFigura(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de la figua de accion que desea eliminar: ");
        int codigo = scanner.nextInt();
        System.out.println();

        controllerProducto.eliminarFiguraDeAccion(codigo);
    }

    public void buscarFigura(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de la figura que desea buscar: ");
        int codigo = scanner.nextInt();
        System.out.println();

        FiguraDeAccion figuraDeAccion = controllerProducto.obtenerFiguraPorCodigo(codigo).getBody();

        figuraDeAccion.toString();
    }

    public void listarFiguras(){
        List<FiguraDeAccion>listadoFiguras = controllerProducto.obtenerFigurasDeAccion().getBody();

        System.out.println("Listado de FigurasDeAccion: { ");
        for (FiguraDeAccion f :
                listadoFiguras) {
            System.out.println("[ ");
            f.toString();
            System.out.println(" ], ");
        }

        System.out.println(" }");
    }

    //Metodos debito

    public void subMenuDebito(){
        //guardar,pagar,eliminar,listar
        try {
            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            do {
                try {
                    System.out.println("Sub Menu Debito: \n" +
                            "1. Guardar Tarjeta Debito \n" +
                            "2. Pagar un Carrito \n" +
                            "3. Eliminar una Tarjeta \n" +
                            "4. Listar Tarjetas \n" +
                            "0. Volver al menu principal \n");
                    System.out.println("Ingrese una opcion: ");

                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion){
                        case 1:
                            guardarTarjeta();
                        case 2:
                            pagarCarritoConTarjeta();
                        case 3:
                            eliminarTarjeta();
                        case 4:
                            listarTarjetas();
                        case 0:
                            mostrarMenu();
                        default:
                            System.err.println("Opcion invlida. Ingrese un numero del menu.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Ingrese un numero valido");
                    scanner.nextLine();
                    opcion = -1;
                }
            } while (opcion != 0);

            scanner.close();

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void guardarTarjeta(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un numero para la tarjeta: ");
        int numeroTarjeta = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el nombre del titular: ");
        String nombre = scanner.nextLine();
        System.out.println();

        System.out.println("Ingrese un codigo de seguridaad para la tarjeta(3 digitos): ");
        int codigo = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el cuil del cliente para asociarlo a la tarjeta: ");
        int cuilCliente = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese un saldo para la tarjeta: ");
        float saldo = scanner.nextFloat();
        System.out.println();

        Client cliente = controllerUser.buscarClientePorCuil(cuilCliente).getBody();

        Debito debito = new Debito(numeroTarjeta,nombre,codigo,cliente,saldo);
        controllerPayment.guardarTarjeta(debito);

    }

    public void pagarCarritoConTarjeta(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el numero de la tarjeta con la que va a pagar: ");
        int numeroTarjeta = scanner.nextInt();
        System.out.println();

        System.out.println("Ingrese el id del carrito par poder obtener el total: ");
        int idCarrito = scanner.nextInt();
        System.out.println();

        //Buscar Carrito y hacer un get total en el metodo pagar de ahi abajo

        //controllerPayment.pagar(numeroTarjeta);
    }

    public void eliminarTarjeta(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el numero de la tarjeta que desea eliminar: ");
        int numeroTarjeta = scanner.nextInt();
        System.out.println();

        controllerPayment.eliminarTarjetaDebito(numeroTarjeta);
    } //COMPLETAR

    public void listarTarjetas(){
        List<Debito>listadoTarjetas = controllerPayment.listarTarjetas().getBody();

        System.out.println("Listado de tarjetas: { ");

        for (Debito tDebito :
                listadoTarjetas) {
            System.out.println(" [ ");
            tDebito.toString();
            System.out.println(" ], ");
        }
        System.out.println(" } ");
    }



}

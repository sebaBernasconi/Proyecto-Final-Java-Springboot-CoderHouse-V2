package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.FacturaNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerFactura {

    private int idFactura;
    private List<FacturaA> listadoDeFacturaA;

    private List<FacturaB> listadoDeFacturaB;

    private List<FacturaC> listadoDeFacturaC;

    private static ControllerFactura instancia;

    //Constructor
    public ControllerFactura(){
        this.listadoDeFacturaA = new ArrayList<>();
        this.listadoDeFacturaB = new ArrayList<>();
        this.listadoDeFacturaC = new ArrayList<>();
    }

    //Metodo getInstancia para que sea singleton
    public static ControllerFactura getInstancia(){
        if (instancia == null){
            return instancia = new ControllerFactura();
        }else {
            return instancia;
        }
    }

    //Metodos de la clase

    public void generarFacturaA(int nroFactura, int cuilCliente, LocalDate fecha, int idCarrito)
            throws UserNotFoundException, FacturaNotFoundException {
        if (buscarFacturaA(nroFactura) == null){
            ControllerUser controllerUser = ControllerUser.getInstancia();
            Client cliente = controllerUser.getClient(cuilCliente);

            Carrito carrito = cliente.getCarrito();

            FacturaA facturaA = new FacturaA(nroFactura,
                    cliente.getCuil(), LocalDate.of(fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth())
                    ,carrito.getTotal());

            idFactura ++;

            listadoDeFacturaA.add(facturaA);
        }
    }

    public void generarFacturaB(int nroFactura, int cuilCliente,LocalDate fecha,int idCarrito)
            throws UserNotFoundException, FacturaNotFoundException {
        if (buscarFacturaB(nroFactura) == null){
            ControllerUser controllerUser = ControllerUser.getInstancia();
            Client cliente = controllerUser.getClient(cuilCliente);

            Carrito carrito = cliente.getCarrito();

            FacturaB facturaB = new FacturaB(nroFactura,
                    cliente.getCuil(), LocalDate.of(fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth())
                    ,carrito.getTotal());

            idFactura ++;

            listadoDeFacturaB.add(facturaB);
        }
    }
    public void generarFacturaC(int nroFactura, int cuilCliente,LocalDate fecha,int idCarrito)
            throws UserNotFoundException, FacturaNotFoundException{
        if (buscarFacturaC(nroFactura) == null){
            ControllerUser controllerUser = ControllerUser.getInstancia();
            Client cliente = controllerUser.getClient(cuilCliente);

            Carrito carrito = cliente.getCarrito();

            FacturaC facturaC = new FacturaC(nroFactura,
                    cliente.getCuil(), LocalDate.of(fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth())
                    ,carrito.getTotal());

            idFactura ++;

            listadoDeFacturaC.add(facturaC);
        }
    }


    //Metodo privado que devuelve un objeto de negocio que el cliente nunca debe ver
    private FacturaA buscarFacturaA(int nroFactura)
        throws FacturaNotFoundException {
        for (FacturaA fA :
                listadoDeFacturaA) {
            if (nroFactura == fA.getNroFactura()) {
                return fA;
            }
        }
        throw new FacturaNotFoundException("No hay una factura de tipo A " +
                "creada con el numero: " + nroFactura);
    }

    private FacturaB buscarFacturaB(int nroFactura)
            throws FacturaNotFoundException {
        for (FacturaB fB :
                listadoDeFacturaB) {
            if (nroFactura == fB.getNroFactura()) {
                return fB;
            }
        }
        throw new FacturaNotFoundException("No hay una factura de tipo B " +
                "creada con el numero: " + nroFactura);
    }

    private FacturaC buscarFacturaC(int nroFactura)
            throws FacturaNotFoundException {
        for (FacturaC fC :
                listadoDeFacturaC) {
            if (nroFactura == fC.getNroFactura()) {
                return fC;
            }
        }
        throw new FacturaNotFoundException("No hay una factura de tipo C " +
                "creada con el numero: " + nroFactura);
    }

}

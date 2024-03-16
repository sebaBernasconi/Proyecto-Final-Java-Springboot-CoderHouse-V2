package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.CarritoNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;

import java.util.ArrayList;
import java.util.List;

public class ControllerCarrito {

    private int idCarrito = 0;
    private List<Carrito> listadoDeCarritos;

    public static ControllerCarrito instancia;

    //Constructor
    public ControllerCarrito(){
        this.listadoDeCarritos = new ArrayList<>();
    }

    //Metodo get instancia para que sea un singleton
    public static ControllerCarrito getInstancia(){
        if (instancia == null){
            return instancia = new ControllerCarrito();
        }else {
            return instancia;
        }
    }

    //Metodos del controller
    //Mala practica que devuelva un carrito?
    public Carrito crearCarrito(int cuilCliente)
            throws UserNotFoundException{
        /*Solo lo uso en el metodo crear cliente, que se le asigne
        * un carrito vacio, pero es mala practica que devuelva un
        * objeto de negocio un metodo publico*/
        ControllerUser controllerUser = ControllerUser.getInstancia();
        Client c = controllerUser.getClient(cuilCliente);

        Carrito carrito = new Carrito(idCarrito,c,0,
                new ArrayList<>(),false,0);
        this.idCarrito ++;
        listadoDeCarritos.add(carrito);

        return carrito;
    }




    //Metodos privados que devuelven objetos que el cliente nunca debe ver
    private Carrito buscarCarrito(int idCarrito)
            throws CarritoNotFoundException {
        for (Carrito c :
                listadoDeCarritos) {
            if (idCarrito == c.getIdCarrito()) {
                return c;
            }
        }
        throw new CarritoNotFoundException("No hay un carrito cargado con el id: "
                + idCarrito);
    }
}

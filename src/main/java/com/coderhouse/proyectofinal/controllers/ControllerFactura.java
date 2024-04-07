package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.FacturaNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.service.ticket.FacturaAService;
import com.coderhouse.proyectofinal.service.ticket.FacturaBService;
import com.coderhouse.proyectofinal.service.ticket.FacturaCService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/facturas")
public class ControllerFactura {

    //Declarando los servicios que voy a necesitar
    private FacturaAService facturaAService;
    private FacturaBService facturaBService;
    private FacturaCService facturaCService;

    private static ControllerFactura instancia;

    //Constructor
    public ControllerFactura(){

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
    @PostMapping(value = "/agregarFacturaA", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaA> guardarFacturaA(@RequestBody FacturaA facturaA){
        facturaAService.guardarFacturaA(facturaA);
        return new ResponseEntity<>(facturaA, HttpStatus.OK);
    }

    @PostMapping(value = "/agregarFacturaB", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaB>guardarFacturaB(@RequestBody FacturaB facturaB){
        facturaBService.guardarFacuraB(facturaB);
        return new ResponseEntity<>(facturaB,HttpStatus.OK);
    }

    @PostMapping(value = "/agregarFacturaC", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaC>guardarFacturaC(@RequestBody FacturaC facturaC){
        facturaCService.guardarFacutraC(facturaC);
        return new ResponseEntity<>(facturaC,HttpStatus.OK);
    }


    @GetMapping(value = "/facturaA", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaA>>obtnenerFacturasA(){
        try {
            List<FacturaA> listadoDeFacturaA = facturaAService.listarFacturasA();
            return new ResponseEntity<>(listadoDeFacturaA,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/facturaB", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaB>>obtenerFacturasB(){
        try {
            List<FacturaB> listadoDeFacturaB = facturaBService.listarFacturasB();
            return new ResponseEntity<>(listadoDeFacturaB,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/facturaC", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaC>>obtenerFacturasC(){
        try {
            List<FacturaC> listadoDeFacturaC = facturaCService.listarFacturasC();
            return new ResponseEntity<>(listadoDeFacturaC,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @GetMapping(value = "/facturaA/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaA>>obtenerFacturasADeUnCliente(@PathVariable("id") Integer cuil){
        try {
            List<FacturaA>facturasCliente = facturaAService.listarFacturasADeUnCliente(cuil);
            return new ResponseEntity<>(facturasCliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/facturaB/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaB>>obtenerFacturasBDeUnCliente(@PathVariable("id") Integer cuil){
        try {
            List<FacturaB>facturascliente = facturaBService.listarFacturasBDeUnCliente(cuil);
            return new ResponseEntity<>(facturascliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/facturaC/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaC>>obtenerFacutrasCDeUnCliente(@PathVariable("id") Integer cuil){
        try {
            List<FacturaC>facturasCliente = facturaCService.listarFacturasCDeUnCliente(cuil);
            return new ResponseEntity<>(facturasCliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


}

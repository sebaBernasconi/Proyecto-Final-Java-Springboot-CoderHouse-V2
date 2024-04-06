package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.service.payment.DebitoService;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payment")
public class ControllerPayment {

    //Instanciando el servicio
    @Autowired
    private DebitoService debitoService;

    //Constructor

    public ControllerPayment() {

    }

    //Instancia del controller
    private static ControllerPayment instancia;

    //Get instance para que sea singleton
    public ControllerPayment getInstancia(){
        if (instancia == null){
            return instancia = new ControllerPayment();
        }else {
            return instancia;
        }
    }

    //Metodos del Controller

    @PostMapping(value = "/guardar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Debito>guardarTarjeta(@RequestBody Debito tDebito){
        debitoService.guardarTarjetaDeDebito(tDebito);
        return new ResponseEntity<>(tDebito, HttpStatus.CREATED);
    }

    @PutMapping(value = "/pagar/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Debito>pagar(@PathVariable("id")Integer nro, float total){
        //Sacar el consumes json value? creo que no tiene que estar
        try {
            Debito tarjeta = debitoService.pagar(nro,total);
            return new ResponseEntity<>(tarjeta,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminarTarjeta/{id}")
    public ResponseEntity<Void>eliminarTarjetaDebito(@PathVariable("id") Integer nroTarjeta){
        boolean tarjetaEliminada = debitoService.eliminarTarjedaDeDebito(nroTarjeta);

        if (tarjetaEliminada){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Debito>>listarTarjetas(){
        try {
            List<Debito>listadoTarjetas = debitoService.listarTarjetas();
            return new ResponseEntity<>(listadoTarjetas,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

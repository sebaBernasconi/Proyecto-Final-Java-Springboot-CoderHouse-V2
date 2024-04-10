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

    @PutMapping(value = "/pagar/{id}/{total}")
    public ResponseEntity<String>pagar(@PathVariable("id")Integer nro,
                                       @PathVariable("total") float total){
        boolean pagado = debitoService.pagar(nro,total);
        if (pagado) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            //no es claro. Tendria que devolver not possible o un msj en el postman de alguna forma?
            return new ResponseEntity<>("No tiene saldo suficiente para abonar",HttpStatus.INTERNAL_SERVER_ERROR);
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

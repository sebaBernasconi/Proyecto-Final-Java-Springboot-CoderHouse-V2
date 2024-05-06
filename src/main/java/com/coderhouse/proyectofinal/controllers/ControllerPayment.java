package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.service.payment.DebitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestion de pagos",description = "Endpoints de los MediosDePago")
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
    public static ControllerPayment getInstancia(){
        if (instancia == null){
            return instancia = new ControllerPayment();
        }else {
            return instancia;
        }
    }

    //Metodos del Controller

    @Operation(summary = "Guardar TarjetaDebito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Se guardo el MedioDePago, en este caso, una " +
                    "tarjeta de debito en la base de datos",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Debito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/guardar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Debito>guardarTarjeta(@RequestBody Debito tDebito){
        debitoService.guardarTarjetaDeDebito(tDebito);
        return new ResponseEntity<>(tDebito, HttpStatus.CREATED);
    }

    @Operation(summary = "Pagar el total de un Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "El pago fue exitoso",content = {
                    @Content(mediaType = "applicatin/json", schema = @Schema(implementation = Debito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
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

    @Operation(summary = "Eliminar una Tarjeta de Debito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "El No Content hace referencia a que se elimino" +
                    " lo solicitado de la base de datos",content ={
                    @Content(mediaType = "application/j son",schema = @Schema(implementation = Debito.class))
            }),
            @ApiResponse(responseCode = "404",description = "El Not Found hace referencia a que lo que se " +
                    "solicita eliminar no esta en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping(value = "/eliminarTarjeta/{id}")
    public ResponseEntity<Void>eliminarTarjetaDebito(@PathVariable("id") Integer nroTarjeta){
        boolean tarjetaEliminada = debitoService.eliminarTarjedaDeDebito(nroTarjeta);

        if (tarjetaEliminada){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Listar TarjetaDebito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de las tarjetas de debito",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Debito.class))
            })
    })
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

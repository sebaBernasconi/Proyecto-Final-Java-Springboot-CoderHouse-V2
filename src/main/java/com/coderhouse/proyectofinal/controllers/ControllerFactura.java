package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.FacturaNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.service.ticket.FacturaAService;
import com.coderhouse.proyectofinal.service.ticket.FacturaBService;
import com.coderhouse.proyectofinal.service.ticket.FacturaCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
@Tag(name = "Gestion de Facutras",description = "Endpoints de las Facturas")
public class ControllerFactura {

    //Declarando los servicios que voy a necesitar

    @Autowired
    private FacturaAService facturaAService;

    @Autowired
    private FacturaBService facturaBService;

    @Autowired
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
    @Operation(summary = "Agregar Factura A")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Factura A guardada en la base de datos",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @PostMapping(value = "/agregarFacturaA", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaA> guardarFacturaA(@RequestBody FacturaA facturaA){
        facturaAService.guardarFacturaA(facturaA);
        return new ResponseEntity<>(facturaA, HttpStatus.CREATED);
    }

    @Operation(summary = "Agregar Factura B")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Factura B guardada en la base de datos",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
                    }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @PostMapping(value = "/agregarFacturaB", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaB>guardarFacturaB(@RequestBody FacturaB facturaB){
        facturaBService.guardarFacuraB(facturaB);
        return new ResponseEntity<>(facturaB,HttpStatus.CREATED);
    }

    @Operation(summary = "Agregar Factura C")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Factura C guardada en la base de datos",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
                    }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @PostMapping(value = "/agregarFacturaC", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FacturaC>guardarFacturaC(@RequestBody FacturaC facturaC){
        facturaCService.guardarFacutraC(facturaC);
        return new ResponseEntity<>(facturaC,HttpStatus.CREATED);
    }


    @Operation(summary = "Listar Factura A")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de todas las FacturaA hechas",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(value = "/facturaA", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaA>>obtnenerFacturasA(){
        try {
            List<FacturaA> listadoDeFacturaA = facturaAService.listarFacturasA();
            return new ResponseEntity<>(listadoDeFacturaA,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar Factura B")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de todas las FacturaB hechas",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
                    }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(value = "/facturaB", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaB>>obtenerFacturasB(){
        try {
            List<FacturaB> listadoDeFacturaB = facturaBService.listarFacturasB();
            return new ResponseEntity<>(listadoDeFacturaB,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar Factura C")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de todas las FacturaC hechas",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Factura.class))
                    }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(value = "/facturaC", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FacturaC>>obtenerFacturasC(){
        try {
            List<FacturaC> listadoDeFacturaC = facturaCService.listarFacturasC();
            return new ResponseEntity<>(listadoDeFacturaC,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

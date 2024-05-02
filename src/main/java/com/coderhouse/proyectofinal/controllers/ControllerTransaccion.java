package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.service.transactions.CompraService;
import com.coderhouse.proyectofinal.service.transactions.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion de Transacciones",description = "Endpoints de las Compras y Ventas")
@RestController
@RequestMapping(value = "/transaction")
public class ControllerTransaccion {

    //Instanciando los servicios
    @Autowired
    CompraService compraService;

    @Autowired
    VentaService ventaService;

    //Instanciando el controller
    private static ControllerTransaccion instancia;

    //getInstance para que sea singleton
    public ControllerTransaccion getInstancia(){
        if (instancia == null) {
            return instancia = new ControllerTransaccion();
        }else {
            return instancia;
        }
    }

    //Metodos de la clase

    @Operation(summary = "Guardar Compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Compra guardada en la base de datos", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Compra.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/guardarCompra", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Compra>guardarCompra(@RequestBody Compra compra){
        compraService.guardarCompra(compra);
        return new ResponseEntity<>(compra, HttpStatus.CREATED);
    }

    @Operation(summary = "Guardar Venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Venta guardada en la base de datos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @PostMapping(value = "/guardarVenta", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Venta>guardarVenta(@RequestBody Venta venta){
        ventaService.guardarVenta(venta);
        return new ResponseEntity<>(venta,HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar Compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "No Content hace referencia a que se borro lo solicitado",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Compra.class))
            }),
            @ApiResponse(responseCode = "404",description = "Not Found hace referencia a que no se encontro lo" +
                    " que se solicito borrar en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @DeleteMapping(value = "/eliminarCompra/{id}")
    public ResponseEntity<Void>eliminarCompra(@PathVariable("id")Integer idCompra){
        boolean compraEliminada = compraService.eliminarCompra(idCompra);

        if (compraEliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar Venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "No Content hace referencia a que se borro lo solicitado",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = Venta.class))
                    }),
            @ApiResponse(responseCode = "404",description = "Not Found hace referencia a que no se encontro lo" +
                    " que se solicito borrar en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @DeleteMapping(value = "/eliminarVenta/{id}")
    public ResponseEntity<Void>elimianrVenta(@PathVariable("id")Integer idVenta){
        boolean ventaEliminada = ventaService.eliminarVenta(idVenta);

        if (ventaEliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Listar Comrpas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de las Comrpas",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Compra.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found(no hay Compras guardadas en la base de datos)",
                    content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/listarCompras", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Compra>>listarCompras(){
        try {
            List<Compra>listadoCompras = compraService.listarCompras();
            return new ResponseEntity<>(listadoCompras,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar Ventas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve un listado de las Ventas",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Venta.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found(no hay Ventas guardadas en la base de datos)",
                    content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/listarVentas",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Venta>>listarVentas(){
        try {
            List<Venta>listadoVentas = ventaService.listarVentas();
            return new ResponseEntity<>(listadoVentas,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


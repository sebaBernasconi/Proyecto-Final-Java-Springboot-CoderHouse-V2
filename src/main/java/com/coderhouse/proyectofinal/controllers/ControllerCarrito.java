package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.CarritoNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.service.user.CarritoService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/carrito")
@Tag(name = "Gestion del carrito", description = "Endpoints del Carrito")
public class ControllerCarrito {

    @Autowired
    private CarritoService carritoService;

    public static ControllerCarrito instancia;

    //Constructor
    public ControllerCarrito(){

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
    @Operation(summary = "Agregar un Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Se guarda un nuevo Carrito en la base de datos",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>agregarCarrito(@RequestBody Carrito carrito){

        carritoService.guardarCarrito(carrito);
        return new ResponseEntity<>(carrito, HttpStatus.CREATED);
    }

    @Operation(summary = "Pagar un Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El Carrito fue pagado con exito",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/pagar/{id}")
    public ResponseEntity<Carrito>pagarCarrito(@PathVariable("id") Integer codigo){
        try {
            Carrito carritoPagado = carritoService.pagarCarrito(codigo);
            return new ResponseEntity<>(carritoPagado,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Agregar Comic al Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Se agrego un producto, en este caso, " +
                    "un comic al Carrito", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/agregarComic/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>ageregarComic(@PathVariable("id")Integer codigoCarrito,
                                                @RequestBody Comic comic){
        try {
            Carrito carrito = carritoService.agregarComic(codigoCarrito,comic);
            return new ResponseEntity<>(carrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Agregar FiguraDeAccion al Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Se agrego un producto, en este caso, " +
                    "una FiguraDeAccion al Carrito", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/agregarFigura/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>agregarFiguraDeAccion(@PathVariable("id")Integer codigoCarrito,
                                                        @RequestBody FiguraDeAccion figuraDeAccion){
        try {
            Carrito carrito = carritoService.agregarFiguraDeAccion(codigoCarrito,figuraDeAccion);
            return new ResponseEntity<>(carrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "El No Content hace referencia a que SI se" +
                    " borro lo solicitado",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "404",description = "El Not Found hace referencia a que lo que queremos" +
                    " borrar no se encuentra en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void>eliminarCarrito(@PathVariable("id") Integer codigo){
        boolean carritoEliminado = carritoService.borrarCarrito(codigo);

        if (carritoEliminado){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Listar Carritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de Carritos recuperada",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @GetMapping(value = "/listar",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Carrito>>listarCarritos(){
        try {
            List<Carrito>listadoDeCarritos = carritoService.listarCarritos();
            return new ResponseEntity<>(listadoDeCarritos,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar los Productos de un Carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de Productos del carrito recuperado",
            content = {
                    @Content(mediaType = "application/json|", schema = @Schema(implementation = Carrito.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor", content = @Content)

    })
    @GetMapping(value = "/listarProductos/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Producto>>listarProductos(@PathVariable("id")Integer id){
        try {
            List<Producto>productosDelCarrito = carritoService.verProductosDelCarrito(id);
            return new ResponseEntity<>(productosDelCarrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

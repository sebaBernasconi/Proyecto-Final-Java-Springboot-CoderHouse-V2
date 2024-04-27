package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.ProductNotFoundException;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.service.product.ComicService;
import com.coderhouse.proyectofinal.service.product.FiguraDeAccionService;
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
@RequestMapping(value = "/productos")
@Tag(name = "Gestion de productos", description = "Endpoints de los productos")
public class ControllerProducto {

    //Declarando los servicios que voy a necesitar

    @Autowired
    private ComicService comicService;

    @Autowired
    private FiguraDeAccionService figuraDeAccionService;

    //Creando instancia de controller
    private static ControllerProducto intancia;

    //Constructor
    public ControllerProducto(){

    }

    //getInstancia para que sea singleton
    public static ControllerProducto getIntancia(){
        if (intancia == null){
            return intancia = new ControllerProducto();
        }else {
            return intancia;
        }
    }

    //Metodos del controller

    @Operation(summary = "Registrar una nueva figura de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guarda una figura de accion en la base de datos", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = FiguraDeAccion.class))
                 }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/agregarFigura", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> registrarFiguraDeAccion(@RequestBody FiguraDeAccion figuraDeAccion)  {

        figuraDeAccionService.guardarFiguraDeAccion(figuraDeAccion);
         return new ResponseEntity<>(figuraDeAccion, HttpStatus.CREATED);

    }

    @Operation(summary = "Registrar un nuevo Comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guarda un Comic en la base de datos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/agregarComic", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comic> registrarComic(@RequestBody Comic comic) {
        comicService.guardarComic(comic);
        return new ResponseEntity<>(comic,HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar el stock de las figuras de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualiza el stock del producto despues de que este es" +
                    "agregado a un carrito", content = {
                    @Content(mediaType = "application/json",schema  = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/actualizarStockFigura/{id}")
    public ResponseEntity<FiguraDeAccion> actualizarStockFiguraDeAccion(@PathVariable("id") Integer codigoDeProducto) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.actualizarStockPostVenta(codigoDeProducto);
            return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar el stock de los comics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualiza el stock del producto despues de que este es" +
                    "agregado a un carrito", content = {
                    @Content(mediaType = "application/json",schema  = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/actualizarStockComic/{id}")
    public ResponseEntity<Comic> actualizarStockComic(@PathVariable("id") Integer codigoDeProducto) {
        try {
            Comic comic = comicService.actualizarStockPostVenta(codigoDeProducto);
            return new ResponseEntity<>(comic,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Edita algunos atributos de las figuras de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permite editar los atributos: nombre, descripcion," +
                    " fabricante, esArticulado de la figura", content = {
                    @Content(mediaType = "Application/json", schema = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/editarFigura/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> editarFiguraDeAccion(@PathVariable("id") Integer codigoDeProducto,
                                                               @RequestBody FiguraDeAccion figuraDeAccion) {
      try {
          FiguraDeAccion figuraActualizada = figuraDeAccionService.editarFiguraDeAccion(codigoDeProducto,
                  figuraDeAccion);
          return new ResponseEntity<>(figuraActualizada,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @Operation(summary = "edita algunos atributos de los comics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permite editar los atributos: nombre, descripcion," +
                    "autor, idioma, esTapaDura ", content = {
                    @Content(mediaType = "Application/json", schema = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/editarComic/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comic> editarComic(@PathVariable("id") Integer codigo,
                                           @RequestBody Comic comic ) {

        try {
            Comic comicActualizado = comicService.editarComic(codigo, comic);
            return new ResponseEntity<>(comicActualizado,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Actualiza el precio de una figura de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permite actualizar el precio de un producto" +
                    "especifico", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/actualizarPrecioFigura/{id}/{precio}")
    public ResponseEntity<FiguraDeAccion> modificarPrecioFiguraDeAccion(@PathVariable("id")
                                                                            Integer codigoDeProducto,
                                                                       @PathVariable("precio") float nuevoPrecio) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.modificarPrecio(codigoDeProducto,nuevoPrecio);
            return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Actualiza el precio de un comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permite actualizar el precio de un producto" +
                    "especifico", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/actualizarPrecioComic/{id}/{precio}")
    public ResponseEntity<Comic> modificarPrecioComic(@PathVariable("id") Integer codigoDeProducto,
                                     @PathVariable("precio") float nuevoPrecio){
        try {
            Comic comic = comicService.modificarPrecio(codigoDeProducto,nuevoPrecio);
            return new ResponseEntity<>(comic,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Elimina un comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "El not found hace referencia a que no se encontro" +
                    "lo solicitado", content = {
                    @Content(schema = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "204", description = "El no content hace referencia a que SI se" +
                    " borro lo solicitado",content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping(value = "/eliminarComic/{id}")
    public ResponseEntity<Void>eliminarComic(@PathVariable("id") Integer codigo){
        boolean comicEliminado = comicService.eliminarComic(codigo);
        if (comicEliminado){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Elimina una figura de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "El not found hace referencia a que no se encontro" +
                    "lo solicitado", content = {
                    @Content(schema = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "204", description = "El no content hace referencia a que SI se" +
                    " borro lo solicitado",content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping(value = "/eliminarFigura/{id}")
    public ResponseEntity<Void>eliminarFiguraDeAccion(@PathVariable("id") Integer codigo){
        boolean figuraEliminada = figuraDeAccionService.eliminarFiguraDeAccion(codigo);

        if (figuraEliminada){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Getter de los productos

    @Operation(summary = "Devuelve una figura de accion especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Recupera de la base de datos una fiugra especifica",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "404",description = "Devuelve un not found si el id no corresponde con una" +
                    "figura de accion guardada en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/buscarFigura/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> obtenerFiguraPorCodigo(@PathVariable("id") Integer codigoDeProducto) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.buscarFiguraPorCodigo(codigoDeProducto);

            if (figuraDeAccion != null){
                return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(figuraDeAccion,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Devuelve un comic especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Recupera de la base de datos un comic especifico",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Comic.class))
                    }),
            @ApiResponse(responseCode = "404",description = "Devuelve un not found si el id no corresponde con un" +
                    "comic guardado en la base de datos",content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/buscarComic/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comic> obtenerComicPorCodigo(@PathVariable("id") Integer codigoDeProducto) {
        try {
            Comic comic = comicService.buscarComicPorCodigo(codigoDeProducto);
            if (comic != null){
                return new ResponseEntity<>(comic,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(comic,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Devuelve un listado de las figuras de accion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Nos muestra el listado de todas las figuras de accion" +
                    "guardadas en la base de datos", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = FiguraDeAccion.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    //Metodos privados que devuelven objetos que el cliente nunca debe ver
    @GetMapping(value = "/listarFiguras", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<List<FiguraDeAccion>> obtenerFigurasDeAccion() {
        try {
            List<FiguraDeAccion> listadoFiguras = figuraDeAccionService.listarFigurasDeAccion();
            return new ResponseEntity<>(listadoFiguras,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Devuelve un listado de los comics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Nos muestra el listado de todos los comics" +
                    "guardados en la base de datos", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Comic.class))
            }),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/listarComics", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<List<Comic>> obtenerComics() {
      try {
          List<Comic> listadoComics = comicService.listarComics();
          return new ResponseEntity<>(listadoComics,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }


    }
}

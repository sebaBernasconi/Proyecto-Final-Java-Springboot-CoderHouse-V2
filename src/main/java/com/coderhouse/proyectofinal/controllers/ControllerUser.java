package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.CarritoNotFoundException;
import com.coderhouse.proyectofinal.exception.ProductNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.service.user.AdminService;
import com.coderhouse.proyectofinal.service.user.ClientService;
import com.sun.jdi.event.ExceptionEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Tag(name = "Gestion de Users", description = "Endopoints del Cliente y Admin")
public class ControllerUser {

    @Autowired
    ClientService clientService;

    @Autowired
    AdminService adminService;
    private static ControllerUser instancia;


    //Constructor
    public ControllerUser() {

    }

    //getInstancia para que sea singleton
    public static ControllerUser getInstancia() {
        if (instancia == null) {
            return instancia = new ControllerUser();
        } else {
            return instancia;
        }
    }

    //Metodos del Controller
    @Operation(summary = "Agregar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente guardado en la base de datos",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/agregarClient", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> guardarCliente(@RequestBody Client client) {
        clientService.guardarCliente(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @Operation(summary = "Agregar Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin guardado en la base de datos",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping(value = "/agregarAdmin", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Admin> guardarAdmin(@RequestBody Admin admin) {
        adminService.guardarAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar mail Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mail del Cliente actualizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/modificarMailClient/{id}/{mail}")
    public ResponseEntity<Client> modificarMailClient(@PathVariable("id") Integer cuil,
                                                      @PathVariable("mail") String nuevoMail) {
        try {
            Client cliente = clientService.modificarMail(cuil, nuevoMail);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar mail Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mail del Admin actualizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/modificarMailAdmin/{id}/{mail}")
    public ResponseEntity<Admin> modificarMailAdmin(@PathVariable("id") Integer cuil,
                                                    @PathVariable("mail") String nuevoMail) {
        try {
            Admin admin = adminService.modificarMail(cuil, nuevoMail);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar password Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contraseña del Cliente actualizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/modificarPasswordClient/{id}/{password}")
    public ResponseEntity<Client> modificarPasswordCliente(@PathVariable("id") Integer cuil,
                                                           @PathVariable("password") String nuevaPassword) {
        try {
            Client cliente = clientService.modificarPassword(cuil, nuevaPassword);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar password Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contraseña del Admin actualizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping(value = "/modificarPasswordAdmin/{id}/{password}")
    public ResponseEntity<Admin> modificarPasswordAdmin(@PathVariable("id") Integer cuil,
                                                        @PathVariable("password") String nuevaPassword) {
        try {
            Admin admin = adminService.modificarPassword(cuil, nuevaPassword);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar Facturas de un Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve un listado de objetos Factura", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
            })
    })
    @GetMapping(value = "/listarFacturas/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Factura>> listarFacturasDeUnCliente(@PathVariable("id") Integer cuil) {
        try {
            List<Factura> listadoDeFacturas = clientService.listarFacturas(cuil);
            return new ResponseEntity<>(listadoDeFacturas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El No Content hace referencia a que se borro lo solicitado" +
                    " en la base de datos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "404", description = "El Not Found hace referencia a que lo que se solicito borrar" +
                    " no se encontro en la base de datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping(value = "/borrarCliente/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable("id") Integer cuil) {
        boolean clienteEliminado = clientService.eliminarCliente(cuil);

        if (clienteEliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El No Content hace referencia a que se borro lo solicitado" +
                    " en la base de datos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))
            }),
            @ApiResponse(responseCode = "404", description = "El Not Found hace referencia a que lo que se solicito borrar" +
                    " no se encontro en la base de datos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping(value = "/borrarAdmin/{id}")
    public ResponseEntity<Void> borrarAdmin(@PathVariable("id") Integer cuil) {
        boolean adminEliminado = adminService.eliminarAdmin(cuil);

        if (adminEliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Listar Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve un listado de todos los Clientes", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(value = "/listarClientes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Client>> listarClientes() {
        try {
            List<Client> listadoClientes = clientService.listarClientes();
            return new ResponseEntity<>(listadoClientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar Admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve un listado de todos los Admins", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping(value = "/listarAdmins", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Admin>> listarAdmins() {
        try {
            List<Admin> listadoAdmins = adminService.listarAdmins();
            return new ResponseEntity<>(listadoAdmins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar Cliente por cuil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el cliente con el cuil solicitado",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "404",description = "No se encontro el cliente con el cuil solicitado",
            content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/buscarClientePorCuil/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client>buscarClientePorCuil(@PathVariable("id")Integer cuil){
        try {
            Client client = clientService.buscarClientePorCuil(cuil);
            if (client != null) {
                return new ResponseEntity<>(client,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(client,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar Admin por cuil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el Admin con el cuil solicitado",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Client.class))
            }),
            @ApiResponse(responseCode = "404",description = "No se encontro el Admin con el cuil solicitado",
                    content = @Content),
            @ApiResponse(responseCode = "500",description = "Error interno del servidor",content = @Content)
    })
    @GetMapping(value = "/buscarAdminPorCuil/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Admin>buscarAdminPorCuil(@PathVariable("id")Integer cuil){
        try {
            Admin admin = adminService.buscarAdminPorCuil(cuil);
            if (admin != null) {
                return new ResponseEntity<>(admin,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(admin,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

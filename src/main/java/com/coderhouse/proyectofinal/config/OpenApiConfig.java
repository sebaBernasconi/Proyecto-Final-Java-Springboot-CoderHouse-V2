package com.coderhouse.proyectofinal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Proyecto Final | Curso Java y Springboot")
                        .description("Proyecto sobre un e-comerce de venta de comics y figuras de accion" +
                                "CRUD(CREATE, READ, UPDATE, DELETE) para los clientes, admins, productos, " +
                                "transacciones, medios de pago. Todo esto puede ser accedido mediante los endpoints" +
                                "a continuacion." +
                                "El lenguaje utilizado fue Java con el framework de Springboot para la conexion a la " +
                                "base de datos y la conformacion de la API REST Full." +
                                "Swagger para la documentacion de la api.")
                );
    }
}

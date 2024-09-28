package com.example.usuario_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
			version = "v1",
			title = "Prácica 2 - Microservice Endpoints", 
			description = "Definición de los Endpoints para "
					+ "registro de usuarios "
					+ "y registro-consulta de estados", 
			contact = @Contact(
				name = "Michelle Caterine García Velasco", 
				url = "https://www.iis.unam.mx/sobre-el-instituto/directorio/departamento-de-computo/garcia-velasco-michelle-caterine/", 
			email = "michelle.garcia@sociales.unam.mx")))
public class OpenApiConfig {

}
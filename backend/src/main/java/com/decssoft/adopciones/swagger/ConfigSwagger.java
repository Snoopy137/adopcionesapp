package com.decssoft.adopciones.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author mis_p
 */
@Configuration
public class ConfigSwagger {

    private String server = "adopciones.ddns.net";

    @Bean
    public OpenAPI myOpenAPI() {
        Server prodServer = new Server();
        prodServer.setUrl("");
        prodServer.setDescription("API para llever el registro de las actividades de proteccionistas animales");

        Contact contact = new Contact();
        contact.setEmail("alandsn137@gmail.com");
        contact.setName("Alan Decunto");
        contact.setUrl("http://adopciones.ddns.net");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Adopciones API")
                .version("1.0")
                .contact(contact)
                .description("Esta API permite registrar los animales que se enucentran alojados en refugios, en casas de transitos y para poder relizar la conexion con un posible adoptante.")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(prodServer));
    }
}

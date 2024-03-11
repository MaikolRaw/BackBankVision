package Utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionCors implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/ruta")
	            .allowedOrigins("http://dominio-permitido.com", "http://localhost:4200")
	            .allowedMethods("*") // Permitir todos los métodos HTTP
	            .allowedHeaders("*") // Permitir todos los encabezados
	            .allowCredentials(true); // Permitir credenciales
	}
}
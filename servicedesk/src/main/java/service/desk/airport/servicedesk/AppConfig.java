package service.desk.airport.servicedesk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ComponentScan
@PropertySource({"classpath:application.properties", "classpath:application-secret.properties"})
public class AppConfig {



}

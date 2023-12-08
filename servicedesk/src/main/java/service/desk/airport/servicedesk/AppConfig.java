package service.desk.airport.servicedesk;
import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties", "classpath:application-secret.properties"})
public class AppConfig {
    // Configuration code
}

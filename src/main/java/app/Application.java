package app;

import app.config.DateTimeFormatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@EnableConfigurationProperties
@Import(DateTimeFormatConfiguration.class)
@EntityScan(basePackages = {"app.model"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}

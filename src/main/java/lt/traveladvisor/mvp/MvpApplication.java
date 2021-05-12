package lt.traveladvisor.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@PropertySources(@PropertySource("classpath:application.yaml"))
@ConfigurationPropertiesScan
public class MvpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpApplication.class, args);
    }
}

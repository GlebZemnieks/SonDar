package ru.sondar.sdserver;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author GlebZemnieks
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        String globalPath = (new File("")).getAbsolutePath();
        ServerController.server = new SonDarServer(globalPath);
        SpringApplication.run(Application.class, args);
    }
}

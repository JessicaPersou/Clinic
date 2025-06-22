package br.com.jpersou.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.ApplicationModule;

@SpringBootApplication
@ApplicationModule(displayName = "Clinic", allowedDependencies = {"*"})
public class ClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }

}

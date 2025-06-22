package br.com.jpersou.clinic.integration;

import br.com.jpersou.clinic.ClinicApplication;
import br.com.jpersou.clinic.integration.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestClinicApplication {

    public static void main(String[] args) {
        SpringApplication.from(ClinicApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

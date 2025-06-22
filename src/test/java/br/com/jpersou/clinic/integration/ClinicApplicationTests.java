package br.com.jpersou.clinic.integration;

import br.com.jpersou.clinic.integration.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class ClinicApplicationTests {

    @Test
    void contextLoads() {
    }

}

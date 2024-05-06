package ru.netology.springbootdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> devapp1 = new GenericContainer("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodapp1 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp1.start();
        prodapp1.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + devapp1.getMappedPort(8080), String.class);
        System.out.println(forEntity1.getBody());
        assertEquals(devapp1.getMappedPort(8080), 8080);
        ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + prodapp1.getMappedPort(8081), String.class);
        System.out.println(forEntity2.getBody());
        assertEquals(prodapp1.getMappedPort(8081), 8081);
    }
}

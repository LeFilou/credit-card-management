package org.melsif.creditcardmanagement.acceptancetests;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.stream.Stream;

import static org.melsif.creditcardmanagement.acceptancetests.BaseIntegrationTest.Initializer;

@ContextConfiguration(initializers = Initializer.class)
public abstract class BaseIntegrationTest {

    public static final String AXON_SERVER_IMAGE = "axoniq/axonserver:4.5.5";

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        static GenericContainer axonServer = new GenericContainer(DockerImageName.parse(AXON_SERVER_IMAGE))
            .withExposedPorts(8024)
            .withEnv("AXONIQ_AXONSERVER_DEVMODE_ENABLED", "true");

        private static void startContainers() {
            Startables.deepStart(Stream.of(axonServer)).join();
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
        }
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("axon.axonserver.servers",
            () -> "localhost:" + Initializer.axonServer.getMappedPort(8124));
    }
}

package com.mikusher.apieurom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:apieurom_test;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "twilio.account-sid=AC00000000000000000000000000000000",
        "twilio.auth-token=test-only-placeholder"
})
class ApieuromApplicationTests {

    // Prevent outbound crawling and writes during this context-only smoke test.
    @MockBean
    private DemoCommandLineRunner startupImport;

    @Test
    void contextLoads() {
    }
}

package com.ok.dvweb.config;

import com.ok.dvweb.DVApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DVApplication.class)
public class SecurityConfigTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenCallingHealthEndpoint_thenStatusShouldBeUp() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/actuator/health", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("\"status\":\"UP\"");
    }

    @Test
    public void whenAccessOtherWithoutAuth_thenNotOk(){
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/public/send", String.class);
        assertThat(entity.getStatusCode()).isNotEqualTo(HttpStatus.OK);
    }

    @Test
    public void whenAccessActuatorOtherWithoutAuth_thenForbidden() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/actuator/metrics", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.net.http.HttpClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeCheckerApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void timeCheckerShouldReturn12HoursTimeForMobileDevices() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent",
                    "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 " +
                            "Mobile Safari/537.36");
        headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        DateFormat dateFormat = new SimpleDateFormat(TimeChecker.HOUR_12_FORMAT_PATTERN);
        String expectedTime = dateFormat.format(new Date());
        assertThat(this.restTemplate.exchange("http://localhost:" + port + "/", HttpMethod.GET, entity, String.class).getBody())
                .withFailMessage("timeChecker should return 12 hours time for Mobile Devices")
                .contains(expectedTime);
    }

    @Test
    void timeCheckerShouldReturn24HoursTimeForOtherDevices() {
        DateFormat dateFormat = new SimpleDateFormat(TimeChecker.HOUR_24_FORMAT_PATTERN);
        String expectedTime = dateFormat.format(new Date());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .withFailMessage("timeChecker should return 24 hours time for Other Devices")
                .contains(expectedTime);
    }
}

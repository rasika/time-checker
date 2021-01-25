/*
 *  Copyright (c) 2021, com.github.rasika (https://github.com/rasika) All Rights Reserved.
 *
 *  com.github.rasika licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for accessing basic functionality
 *
 * @since 1.0.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeCheckerApplicationTests {
    private static final String URL = "http://localhost:";

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
        assertThat(this.restTemplate.exchange(URL + port + "/", HttpMethod.GET, entity, String.class).getBody())
                .withFailMessage("timeChecker should return 12 hours time for Mobile Devices")
                .contains(expectedTime);
    }

    @Test
    void timeCheckerShouldReturn24HoursTimeForOtherDevices() {
        DateFormat dateFormat = new SimpleDateFormat(TimeChecker.HOUR_24_FORMAT_PATTERN);
        String expectedTime = dateFormat.format(new Date());
        assertThat(this.restTemplate.getForObject(URL + port + "/", String.class))
                .withFailMessage("timeChecker should return 24 hours time for Other Devices")
                .contains(expectedTime);
    }
}

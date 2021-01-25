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

import au.com.flyingkite.mobiledetect.UAgentInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a REST service to get current server time.
 *
 * @since 1.0.0
 */
@RestController
public class TimeChecker {
    public static final String HOUR_12_FORMAT_PATTERN = "hh.mm aa";
    public static final String HOUR_24_FORMAT_PATTERN = "HH.mm aa";

    @GetMapping("/")
    public String getTime(@RequestHeader("User-Agent") String userAgent, @RequestHeader("Accept") String httpAccept) {
        final UAgentInfo uAgentInfo = new UAgentInfo(userAgent, httpAccept);
        DateFormat dateFormat = new SimpleDateFormat(uAgentInfo.detectMobileQuick() ?
                                                             HOUR_12_FORMAT_PATTERN :
                                                             HOUR_24_FORMAT_PATTERN);
        return dateFormat.format(new Date());
    }
}

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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot main application.
 *
 * @since 1.0.0
 */
@SpringBootApplication
public class TimeCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeCheckerApplication.class, args);
    }
}

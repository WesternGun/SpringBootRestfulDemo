package com.privalia.binlookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BinLookupApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinLookupApplication.class, args);
        log.info("Application launched. ");
    }

}

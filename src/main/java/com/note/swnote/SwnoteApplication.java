package com.note.swnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/s3.properties")
public class SwnoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwnoteApplication.class, args);
    }

}

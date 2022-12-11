package com.example.codeit_db_com.arch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class CodeItDbComApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeItDbComApplication.class, args);
    }
}

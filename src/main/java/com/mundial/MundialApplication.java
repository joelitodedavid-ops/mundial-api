package com.mundial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Al estar en el paquete raiz "com.mundial", Spring encuentra solo los
// subpaquetes: entidades, repository y controller.
@SpringBootApplication
public class MundialApplication {

    public static void main(String[] args) {
        SpringApplication.run(MundialApplication.class, args);
    }
}

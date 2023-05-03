package com.etiya.ecommerceDemo;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EcommerceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceDemoApplication.class, args);
    }

}

// TO-DO update değişmeyen fieldlar için verilen zaten mevcut hatasını düzelt.
// delete işlemleri yapılacak.
// testler gözden geçirilecek.
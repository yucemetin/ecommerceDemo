package com.etiya.ecommerceDemo.entities.concrete;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@Table(name = "product_supplier")
public class ProductSupplier {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;

    @Column(name = "review_text")
    private int supplier_price;


    /// doldurulacak product  ;

    /// doldurulacak private user;

}


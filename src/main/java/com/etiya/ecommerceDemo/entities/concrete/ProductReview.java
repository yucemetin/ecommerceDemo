package com.etiya.ecommerceDemo.entities.concrete;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "review_date")
    private Date review_date;

    @Column(name = "review_text")
    private String reviewText;

    /// doldurulacak product  ;

    /// doldurulacak private user;

}

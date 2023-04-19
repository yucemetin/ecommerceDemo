package com.etiya.ecommerceDemo.entities.concrete;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "returns")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "return_reason")
    private String returnReason;

    /// doldurulacak private Order order;

    /// doldurulacak private attribute attribute;

}


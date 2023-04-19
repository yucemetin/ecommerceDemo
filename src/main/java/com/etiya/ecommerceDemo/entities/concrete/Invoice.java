package com.etiya.ecommerceDemo.entities.concrete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}

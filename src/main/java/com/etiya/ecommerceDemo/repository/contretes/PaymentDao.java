package com.etiya.ecommerceDemo.repository.contretes;

import com.etiya.ecommerceDemo.entities.concrete.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {
}

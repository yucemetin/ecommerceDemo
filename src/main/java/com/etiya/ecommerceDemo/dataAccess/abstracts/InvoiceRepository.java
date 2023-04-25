package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query(value = "SELECT inv.* FROM invoices inv INNER JOIN orders o ON inv.order_id = o.id", nativeQuery = true)
    List<Invoice> getInvoiceByOrderId();
}

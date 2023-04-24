package com.etiya.ecommerceDemo.dataAccess.contretes;
import com.etiya.ecommerceDemo.entities.concrete.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice, Long> {
}

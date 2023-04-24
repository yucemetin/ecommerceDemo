package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

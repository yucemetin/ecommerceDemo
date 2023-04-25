package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(value = "SELECT a.* FROM addresses a INNER JOIN users u ON u.id = a.user_id", nativeQuery = true)
    List<Address> getAddressByUserId();
}

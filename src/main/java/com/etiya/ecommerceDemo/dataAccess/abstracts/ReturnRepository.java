package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Return;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRepository extends JpaRepository<Return,Integer> {
}

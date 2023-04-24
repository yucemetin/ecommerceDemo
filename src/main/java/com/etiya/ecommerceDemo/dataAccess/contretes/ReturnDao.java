package com.etiya.ecommerceDemo.dataAccess.contretes;

import com.etiya.ecommerceDemo.entities.concrete.Return;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnDao extends JpaRepository<Return, Long> {
}

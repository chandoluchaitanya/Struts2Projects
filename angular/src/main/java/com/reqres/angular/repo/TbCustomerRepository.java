package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbCustomer;

@Repository("tbCustomerRepository")
public interface TbCustomerRepository extends JpaRepository<TbCustomer, Long> {

}

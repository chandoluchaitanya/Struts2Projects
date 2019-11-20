package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbBrand;

@Repository("tbBrandRepository")
public interface TbBrandRepository extends JpaRepository<TbBrand, Long>{

}

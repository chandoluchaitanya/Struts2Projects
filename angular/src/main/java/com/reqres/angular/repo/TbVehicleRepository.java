package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVehicle;

@Repository("tbVehicleRepository")
public interface TbVehicleRepository extends JpaRepository<TbVehicle, Long> {

}

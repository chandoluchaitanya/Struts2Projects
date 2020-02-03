package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVehicleType;

@Repository("tbVehicleTypeRepository")
public interface TbVehicleTypeRepository extends JpaRepository<TbVehicleType, Long> {

	public TbVehicleType findByVehicleType(String vehicleType);

}

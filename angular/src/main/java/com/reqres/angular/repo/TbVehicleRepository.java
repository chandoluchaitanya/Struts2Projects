package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVehicle;

@Repository("tbVehicleRepository")
public interface TbVehicleRepository extends JpaRepository<TbVehicle, Long> {

	@Query("from TbVehicle v where v.chassisNo = :chassisNo ")
	List<TbVehicle> findByChassisNo(@Param("chassisNo") String vehicleChassisNo);

	@Query("from TbVehicle v where v.engineNo = :engineNo ")
	List<TbVehicle> findByEngineNo(@Param("engineNo") String vehicleEngineNo);

}

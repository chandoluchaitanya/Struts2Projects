package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbEndorse;

@Repository("tbEndorseRepository")
public interface TbEndorseRepository extends JpaRepository<TbEndorse, Long> {

	@Query(" from TbEndorse e  where e.vehicleId=:vehicleId")
	public List<TbEndorse> findEndorseDetailsByVehicleId(@Param("vehicleId") Long vehicleId);

}

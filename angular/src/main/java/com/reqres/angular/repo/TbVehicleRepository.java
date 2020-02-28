package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVehicle;

@Repository("tbVehicleRepository")
public interface TbVehicleRepository extends JpaRepository<TbVehicle, Long> {

	@Query(" from TbVehicle v where v.chassisNo = :chassisNo ")
	public List<TbVehicle> findByChassisNo(@Param("chassisNo") String vehicleChassisNo);

	@Query(" from TbVehicle v where v.engineNo = :engineNo ")
	public List<TbVehicle> findByEngineNo(@Param("engineNo") String vehicleEngineNo);

	@Query(" from TbVehicle v left outer join fetch  v.tbVariant variant "
			+ " left outer join fetch  v.tbColour colour left outer join fetch  v.tbConfigStatus status "
			+ " left outer join fetch  v.tbVehicleType vt " + " left outer join fetch  v.tbCustomer cus "
			+ " where v.id =:vehicleId ")
	public TbVehicle findByVehicleIdWithInitialization(@Param("vehicleId") Long vehicleId);

	@Query(" from TbVehicle v left outer join fetch  v.tbCustomer cus  where v.id =:vehicleId ")
	public TbVehicle findByVehicleId(@Param("vehicleId") Long vehicleId);

}

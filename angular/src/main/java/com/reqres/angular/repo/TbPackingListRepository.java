package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbPackingList;

@Repository("tbPackingListRepository")
public interface TbPackingListRepository extends JpaRepository<TbPackingList, Long> {

	@Query(" from TbPackingList p  where p.tbVehicle.id = :vehicleId")
	public List<TbPackingList> findPackingListByVehicleId(@Param("vehicleId") Long vehicleId);

}

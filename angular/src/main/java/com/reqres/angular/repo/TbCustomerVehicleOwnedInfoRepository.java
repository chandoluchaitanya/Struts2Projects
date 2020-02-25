package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reqres.angular.bean.TbCustomerVehicleOwnedInfo;

@Repository("tbCustomerVehicleOwnedInfoRepository")
public interface TbCustomerVehicleOwnedInfoRepository extends JpaRepository<TbCustomerVehicleOwnedInfo, Long> {

	@Query(" from TbCustomerVehicleOwnedInfo cvoi left outer join fetch  cvoi.tbCustomer customer "
			+ " left outer join fetch  customer.tbGender gender left outer join fetch  cvoi.tbVehicle vehicle "
			+ " where vehicle.id =:vehicleId ")
	public List<TbCustomerVehicleOwnedInfo> findCustomerByVehicleId(Long vehicleId);

}

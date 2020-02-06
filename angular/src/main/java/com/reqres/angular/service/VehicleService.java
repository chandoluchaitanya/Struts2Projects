package com.reqres.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.SearchVehicleBean;
import com.reqres.angular.dto.VehicleFilterDTO;
import com.reqres.angular.repo.VehicleDao;

@Service("vehicleService")
public class VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	public PaginationUtilDTO getVehicleDetails(SearchVehicleBean searchVehicleBean) {
		Integer start = (searchVehicleBean.getPageNumber() - 1) * 5;
		Integer maxResults = 5;
		List<VehicleFilterDTO> list = searchVehicleDetails(searchVehicleBean, start, maxResults);
		Integer count = countVehicleDetails(searchVehicleBean).intValue();
		PaginationUtilDTO dto = new PaginationUtilDTO();
		// set to dto
		dto.setData(list);
		dto.setCount(count);
		return dto;
	}

	private List<VehicleFilterDTO> searchVehicleDetails(SearchVehicleBean searchVehicleBean, Integer start,
			Integer maxResults) {
		List<VehicleFilterDTO> list = vehicleDao.searchVehicleDetails(searchVehicleBean, start, maxResults);
		return list;
	}

	private Integer countVehicleDetails(SearchVehicleBean searchVehicleBean) {
		return vehicleDao.countVehicleDetails(searchVehicleBean).intValue();
	}
}

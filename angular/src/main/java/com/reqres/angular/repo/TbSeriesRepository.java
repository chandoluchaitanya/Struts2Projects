package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbSeries;

@Repository("tbSeriesRepository")
public interface TbSeriesRepository extends JpaRepository<TbSeries, Long> {

	@Query("from TbSeries s where s.tbBrand.id = ?1")
	List<TbSeries> getSeriesDetailsByBrandId(Long brandId);

}

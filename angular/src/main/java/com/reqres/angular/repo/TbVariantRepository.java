package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVariant;

@Repository("tbVariantRepository")
public interface TbVariantRepository extends JpaRepository<TbVariant, Long> {

	public TbVariant findOneById(Long id);

	public TbVariant findByVariantName(String vehicleModel);

	@Query("from TbVariant v where v.seriesId.id = ?1")
	public List<TbVariant> getVariantDetailsBySeriesId(Long seriesId);

}

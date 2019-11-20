package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVariantColour;

@Repository("tbVariantColourRepository")
public interface TbVariantColourRepository extends JpaRepository<TbVariantColour, Long> {

	@Query("from TbVariantColour vc left outer join fetch vc.tbVariant v where v.id= :variantId")
	public List<TbVariantColour> findByVariantId(@Param("variantId") Long variantId);

}

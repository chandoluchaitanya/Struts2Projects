package com.reqres.angular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbColour;

@Repository("tbColourRepository")
public interface TbColourRepository extends JpaRepository<TbColour, Long> {

	@Query("from TbColour c where c.id not in (:ids)")
	public List<TbColour> findColoursOtherThanVariantColoursIds(@Param("ids") List<Long> ids);

	public TbColour findOneById(Long id);
}

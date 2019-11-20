package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVariant;

@Repository("tbVariantRepository")
public interface TbVariantRepository extends JpaRepository<TbVariant, Long> {

	public TbVariant findOneById(Long id);

}

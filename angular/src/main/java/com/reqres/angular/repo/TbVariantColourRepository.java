package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbVariantColour;

@Repository("tbVariantColourRepository")
public interface TbVariantColourRepository extends JpaRepository<TbVariantColour, Long> {

}

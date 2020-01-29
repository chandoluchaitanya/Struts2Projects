package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbPaintType;

@Repository("tbPaintTypeRepository")
public interface TbPaintTypeRepository extends JpaRepository<TbPaintType, Integer> {

}

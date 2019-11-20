package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbColour;

@Repository("tbColourRepository")
public interface TbColourRepository extends JpaRepository<TbColour, Long> {

}

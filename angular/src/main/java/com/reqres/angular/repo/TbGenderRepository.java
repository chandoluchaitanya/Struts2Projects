package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbGender;

@Repository("tbGenderRepository")
public interface TbGenderRepository extends JpaRepository<TbGender, Long> {

}

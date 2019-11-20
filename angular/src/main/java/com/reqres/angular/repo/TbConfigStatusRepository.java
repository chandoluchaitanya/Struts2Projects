package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbConfigStatus;

@Repository("tbConfigStatusRepository")
public interface TbConfigStatusRepository extends JpaRepository<TbConfigStatus, Long> {

}

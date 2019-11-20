package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbLog;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<TbLog, Long> {

}

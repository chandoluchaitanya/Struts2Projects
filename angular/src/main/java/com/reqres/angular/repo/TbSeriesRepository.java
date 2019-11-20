package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbSeries;

@Repository("tbSeriesRepository")
public interface TbSeriesRepository extends JpaRepository<TbSeries, Long> {

}

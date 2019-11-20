package com.reqres.angular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reqres.angular.model.TbUser;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<TbUser, Long> {
	public TbUser findByEmail(String email);

	public TbUser findOneByEmail(String email);

	public TbUser findOneById(Long id);
}

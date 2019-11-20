package com.reqres.angular.service;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.reqres.angular.bean.LoginBean;
import com.reqres.angular.bean.RegistrationBean;
import com.reqres.angular.bean.UserBean;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.model.TbLog;
import com.reqres.angular.model.TbUser;
import com.reqres.angular.repo.LogRepository;
import com.reqres.angular.repo.UserRepository;
import com.reqres.angular.repo.UserSearchDao;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private LogRepository logRepository;
	private UserSearchDao userSearchDao;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			LogRepository logRepository, UserSearchDao userSearchDao) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.logRepository = logRepository;
		this.userSearchDao = userSearchDao;
	}

	public TbUser findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public TbUser saveUser(TbUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		return userRepository.save(user);
	}

	public void saveUserLog(TbUser user) {
		TbLog tbLog = new TbLog();
		tbLog.setLogTime(new Date());
		tbLog.setLogUserId(user.getId());
		tbLog.setLogType("Login");
		tbLog.setIpaddress(findIpAddress());
		logRepository.save(tbLog);
	}

	private static String findIpAddress() {
		String ip = null;
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}

	public String saveRegistrationDetails(RegistrationBean registrationBean) throws Exception {
		TbUser tbUser = new TbUser();
		tbUser.setActive(0);
		tbUser.setUserName(registrationBean.getUserName());
		tbUser.setEmail(registrationBean.getEmail());
		tbUser.setLastName(registrationBean.getLastName());
		tbUser.setName(registrationBean.getFirstName());
		tbUser.setPassword(bCryptPasswordEncoder.encode(registrationBean.getPwd()));
		tbUser.setCreatedDt(new Date());
		userRepository.save(tbUser);
		return "1";
	}

	public TbUser findOneByEmail(String userName) {
		return userRepository.findOneByEmail(userName);
	}

	public List<String> validateLoginDetails(LoginBean loginBean) {
		List<String> responseList = null;
		boolean result = false;
		TbUser user = userRepository.findOneByEmail(loginBean.getUsername());
		if (user != null) {
			result = bCryptPasswordEncoder.matches(loginBean.getPassword(), user.getPassword());
			if (result) {
				responseList = new ArrayList<String>();
				responseList.add(user.getId().toString());
				saveUserLog(user);
			}
		}
		return responseList;
	}

	public List<TbUser> searchUserDetails(UserBean userBean, Integer start, Integer maxResults) {
		List<TbUser> users = userSearchDao.searchUserDetails(userBean, start, maxResults);
		return users;
	}

	public Long countUserDetails(UserBean userBean) {
		return userSearchDao.countUserDetails(userBean);
	}

	public PaginationUtilDTO getUserDetails(UserBean userBean) {
		Integer start = (userBean.getPageNumber() - 1) * 5;
		Integer maxResults = 5;
		List<TbUser> tbUsersList = searchUserDetails(userBean, start, maxResults);
		Integer count = countUserDetails(userBean).intValue();
		// set user details
		List<RegistrationBean> list = new ArrayList<RegistrationBean>();
		if (!CollectionUtils.isEmpty(tbUsersList)) {
			for (TbUser user : tbUsersList) {
				RegistrationBean bean = new RegistrationBean();
				bean.setId(user.getId().toString());
				bean.setEmail(user.getEmail());
				bean.setFirstName(user.getName());
				bean.setLastName(user.getLastName());
				bean.setUserName(user.getUserName());
				list.add(bean);
			}
		}
		PaginationUtilDTO dto = new PaginationUtilDTO();
		// set to dto
		dto.setData(list);
		dto.setCount(count);
		return dto;
	}

	public RegistrationBean getUserDetailsById(String id) {
		TbUser user = userRepository.findOneById(Long.parseLong(id));
		RegistrationBean registrationBean = null;
		if (user != null) {
			registrationBean = new RegistrationBean();
			registrationBean.setId(user.getId().toString());
			registrationBean.setEmail(user.getEmail());
			registrationBean.setFirstName(user.getName());
			registrationBean.setLastName(user.getLastName());
			registrationBean.setUserName(user.getUserName());
			registrationBean.setIsActive(user.getActive() == 0 ? false : true);
		}
		return registrationBean;
	}

	public String deleteUserDetailsById(String id) throws Exception {
		TbUser user = userRepository.findOneById(Long.parseLong(id));
		userRepository.delete(user);
		return "1";
	}

	public String updateRegistrationDetails(RegistrationBean registrationBean) {
		TbUser tbUser = userRepository.findOneById(Long.parseLong(registrationBean.getId()));
		tbUser.setActive(registrationBean.getIsActive() == null ? 0 : 1);
		tbUser.setUserName(registrationBean.getUserName());
		tbUser.setEmail(registrationBean.getEmail());
		tbUser.setLastName(registrationBean.getLastName());
		tbUser.setName(registrationBean.getFirstName());
		tbUser.setCreatedDt(new Date());
		userRepository.save(tbUser);
		return "1";
	}
}
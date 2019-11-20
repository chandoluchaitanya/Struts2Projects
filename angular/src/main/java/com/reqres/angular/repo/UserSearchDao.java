package com.reqres.angular.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.reqres.angular.bean.UserBean;
import com.reqres.angular.model.TbUser;

@Repository("userSearchDao")
@Transactional
public class UserSearchDao {

	// @Autowired
	// private EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<TbUser> searchUserDetails(UserBean userBean, Integer start, Integer maxResults) {
		// Session session =
		// entityManagerFactory.unwrap(SessionFactory.class).openSession();
		List<TbUser> users = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbUser.class);
			if (!StringUtils.isEmpty(userBean.getFirstName())) {
				criteria.add(Restrictions.like("name", "%" + userBean.getFirstName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getLastName())) {
				criteria.add(Restrictions.like("lastName", "%" + userBean.getLastName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getUserName())) {
				criteria.add(Restrictions.like("userName", "%" + userBean.getUserName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getEmail())) {
				criteria.add(Restrictions.like("email", "%" + userBean.getEmail() + "%"));
			}
			if ("asc".equalsIgnoreCase(userBean.getSortType())) {
				criteria.addOrder(Order.asc(userBean.getSortBy()));
			} else if ("desc".equalsIgnoreCase(userBean.getSortType())) {
				criteria.addOrder(Order.desc(userBean.getSortBy()));
			} else {
				criteria.addOrder(Order.asc(userBean.getSortBy()));
			}
			criteria.setFirstResult(start).setMaxResults(maxResults);
			users = (List<TbUser>) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return users;
	}

	@SuppressWarnings("deprecation")
	public Long countUserDetails(UserBean userBean) {
		Long totalResult = 0L;
		try {
			// Session session =
			// entityManagerFactory.unwrap(SessionFactory.class).openSession();
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbUser.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (!StringUtils.isEmpty(userBean.getFirstName())) {
				criteria.add(Restrictions.like("name", "%" + userBean.getFirstName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getLastName())) {
				criteria.add(Restrictions.like("lastName", "%" + userBean.getLastName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getUserName())) {
				criteria.add(Restrictions.like("userName", "%" + userBean.getUserName() + "%"));
			}
			if (!StringUtils.isEmpty(userBean.getEmail())) {
				criteria.add(Restrictions.like("email", "%" + userBean.getEmail() + "%"));
			}
			criteria.setProjection(Projections.rowCount());
			totalResult = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return totalResult;
	}
}

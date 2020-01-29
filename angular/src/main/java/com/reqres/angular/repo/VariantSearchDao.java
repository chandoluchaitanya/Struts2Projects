package com.reqres.angular.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.reqres.angular.bean.VariantBean;
import com.reqres.angular.model.TbVariant;

@Repository("variantSearchDao")
@Transactional
public class VariantSearchDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TbVariant> searchVariantDetails(VariantBean variantBean, Integer start, Integer maxResults) {
		List<TbVariant> variants = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbVariant.class);
			// search with variantCode
			if (!StringUtils.isEmpty(variantBean.getVariantCode())) {
				criteria.add(Restrictions.like("variantCode", "%" + variantBean.getVariantCode() + "%"));
			}
			// search with variantName
			if (!StringUtils.isEmpty(variantBean.getVariantName())) {
				criteria.add(Restrictions.like("variantName", "%" + variantBean.getVariantName() + "%"));
			}
			criteria.setFirstResult(start).setMaxResults(maxResults);
			variants = (List<TbVariant>) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return variants;
	}

	@SuppressWarnings("deprecation")
	public Long countVariantDetails(VariantBean variantBean) {
		Long totalResult = 0L;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbVariant.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			// search with variantCode
			if (!StringUtils.isEmpty(variantBean.getVariantCode())) {
				criteria.add(Restrictions.like("variantCode", "%" + variantBean.getVariantCode() + "%"));
			}
			// search with variantName
			if (!StringUtils.isEmpty(variantBean.getVariantName())) {
				criteria.add(Restrictions.like("variantName", "%" + variantBean.getVariantName() + "%"));
			}
			criteria.setProjection(Projections.rowCount());
			totalResult = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return totalResult;
	}
}

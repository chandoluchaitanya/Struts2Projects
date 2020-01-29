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

import com.reqres.angular.bean.ColourMngBean;
import com.reqres.angular.model.TbColour;

@Repository("colourMngSearchDao")
@Transactional
public class ColourMngSearchDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TbColour> searchColourDetails(ColourMngBean colourMngBean, Integer start, Integer maxResults) {
		List<TbColour> colours = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbColour.class);
			// search with colourName
			if (!StringUtils.isEmpty(colourMngBean.getColourName())) {
				criteria.add(Restrictions.like("colourName", "%" + colourMngBean.getColourName() + "%"));
			}
			// search with colourCode
			if (!StringUtils.isEmpty(colourMngBean.getColourCode())) {
				criteria.add(Restrictions.like("colourCode", "%" + colourMngBean.getColourCode() + "%"));
			}
			criteria.setFirstResult(start).setMaxResults(maxResults);
			colours = (List<TbColour>) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return colours;
	}

	@SuppressWarnings("deprecation")
	public Long countColourDetails(ColourMngBean colourMngBean) {
		Long totalResult = 0L;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbColour.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			// search with colourName
			if (!StringUtils.isEmpty(colourMngBean.getColourName())) {
				criteria.add(Restrictions.like("colourName", "%" + colourMngBean.getColourName() + "%"));
			}
			// search with colourCode
			if (!StringUtils.isEmpty(colourMngBean.getColourCode())) {
				criteria.add(Restrictions.like("colourCode", "%" + colourMngBean.getColourCode() + "%"));
			}
			criteria.setProjection(Projections.rowCount());
			totalResult = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return totalResult;
	}
}

package com.reqres.angular.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.reqres.angular.bean.SearchVehicleBean;
import com.reqres.angular.dto.VehicleFilterDTO;
import com.reqres.angular.model.TbVehicle;

@SuppressWarnings("deprecation")
@Repository("vehicleDao")
@Transactional
public class VehicleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VehicleFilterDTO> searchVehicleDetails(SearchVehicleBean searchVehicleBean, Integer start,
			Integer maxResults) {
		List<VehicleFilterDTO> list = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			StringBuilder sql = new StringBuilder();
			sql.append(
					" select new com.reqres.angular.dto.VehicleFilterDTO(veh.id,veh.chassisNo,veh.engineNo,v.variantName,c.colourName,veh.yearMade,vt.vehicleType) ");
			sql.append(" FROM TbVehicle veh LEFT JOIN veh.tbVariant v ");
			sql.append(" LEFT JOIN veh.tbColour c LEFT JOIN veh.tbVehicleType vt WHERE 1=1 ");
			if (!StringUtils.isEmpty(searchVehicleBean.getChassisNo())) {
				sql.append(" AND veh.chassisNo like :chassisNo ");
			}
			if (!StringUtils.isEmpty(searchVehicleBean.getEngineNo())) {
				sql.append(" AND veh.engineNo like :engineNo ");
			}
			Query q = session.createQuery(sql.toString());
			if (!StringUtils.isEmpty(searchVehicleBean.getChassisNo())) {
				q.setParameter("chassisNo", "%" + searchVehicleBean.getChassisNo() + "%");
			}
			if (!StringUtils.isEmpty(searchVehicleBean.getEngineNo())) {
				q.setParameter("engineNo", "%" + searchVehicleBean.getEngineNo() + "%");
			}
			q.setFirstResult(start);
			q.setMaxResults(maxResults);
			list = q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Long countVehicleDetails(SearchVehicleBean searchVehicleBean) {
		Long totalResult = 0L;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(TbVehicle.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			// search with chassisNo
			if (!StringUtils.isEmpty(searchVehicleBean.getChassisNo())) {
				criteria.add(Restrictions.like("chassisNo", "%" + searchVehicleBean.getChassisNo() + "%"));
			}
			// search with engineNo
			if (!StringUtils.isEmpty(searchVehicleBean.getEngineNo())) {
				criteria.add(Restrictions.like("engineNo", "%" + searchVehicleBean.getEngineNo() + "%"));
			}
			criteria.setProjection(Projections.rowCount());
			totalResult = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return totalResult;
	}
}

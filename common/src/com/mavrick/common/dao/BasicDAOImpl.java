package com.mavrick.common.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.mavrick.common.json.ResponseGenerator;
import com.mavrick.common.json.ResponseGeneratorTools;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

@Transactional
@Repository
public class BasicDAOImpl implements BasicDAO {

	 Logger logger= Logger.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	private ResponseGenerator responseGenerator;
	@Autowired
	ResponseGeneratorTools responseGeneratorTools;
	/**
	 * The purpose of this method is to save all Entities in to the database which are of type MavrickEntity.
	 */
	@Override
	public boolean addEntity(MavrickEntity mEnty) {
		// Session session =getSessionFactory().openSession();
		logger.info("Entity to be saved: "+mEnty.getClass().getName());
		getSessionFactory().getCurrentSession().save(mEnty);
		logger.info("Entity saved successfully...");
		
		return true;
	}
	@Deprecated
	@Override
	public MavrickEntity getEntityById(String mId, MavrickEntity className) {
		return getEntityById(mId, className, null);
	}
	/**
	 * 
	 * @param mId
	 * @param className
	 * @param fetchProperties
	 * @return
	 */
	@Override
	public MavrickEntity getEntityById(String mId, MavrickEntity className,
			List<String> fetchProperties) {
		logger.info("GetItem on Entity :" + className.getClass().getName()
				+ " with ID:" + mId);// evictCollectionRegions();

		MavrickEntity result = (MavrickEntity) getSessionFactory()
				.getCurrentSession().get(className.getClass(), mId);
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheHitCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheHitCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheMissCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheMissCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheRegionNames() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheRegionNames());

		initializeProperties(result, fetchProperties);
		return result;
	}
	
	
	public MavrickEntity getEntityById(String mId, MavrickEntity className,
			List<String> fetchProperties,boolean fullRecursive) {
		logger.info("GetItem on Entity :" + className.getClass().getName()
				+ " with ID:" + mId);// evictCollectionRegions();

		MavrickEntity result = (MavrickEntity) getSessionFactory()
				.getCurrentSession().get(className.getClass(), mId);
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheHitCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheHitCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheMissCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheMissCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheRegionNames() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheRegionNames());

		initializeProperties(result, fetchProperties,fullRecursive);
		return result;
	}
	
	
	@Override
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId, MavrickEntity className) {
		logger.info("GetItem on Entity :"+className.getClass().getName()+" with ID:"+mId);
		MavrickEntity result= (MavrickEntity) getSessionFactory().getCurrentSession().get(className.getClass(), mId);
		return result;
	}
	
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId, Class<? extends MavrickEntity> className) {
		//logger.info("GetItem on Entity :"+className.getClass().getName()+" with ID:"+mId);
		MavrickEntity result= (MavrickEntity) getSessionFactory().getCurrentSession().get(className, mId);
		return result;
	}

	@Override
	public boolean deleteEntity(MavrickEntity mEnty) {
		logger.info("Entity to be deleted: "+mEnty.getClass().getName());
		getSessionFactory().getCurrentSession().delete(mEnty);
		logger.info("Entity deleted successfully...");
		return false;
	}

	@Override
	public boolean deleteEntityById(String mId, MavrickEntity className) {

		MavrickEntity mvk=getEntityById(mId, className,null);
		getSessionFactory().getCurrentSession().delete(mvk);
		return false;
	}
	@Deprecated
	@Override
	public List<MavrickEntity> runQuery(String mQuery) {
		return runQuery(mQuery,null);
	}
	/**
	 * This method has an additional parameter where you can specify the property names for which you need to extract
	 * the lazy loaded properties..
	 */
	public List<MavrickEntity> runQuery(String mQuery,
			List<String> fetchProperties) {
		List<MavrickEntity> results = getSessionFactory().getCurrentSession()
				.createQuery(mQuery).list();
		if (null != fetchProperties && !fetchProperties.isEmpty()
				&& results != null && !results.isEmpty()) {

			for (MavrickEntity mv : results) {
				initializeProperties(mv, fetchProperties);
			}
		}
		return results;
	}
	
	
	@Override
	public boolean updateEntity(MavrickEntity mEntity) {
		getSessionFactory().getCurrentSession().flush();
		getSessionFactory().getCurrentSession().saveOrUpdate(mEntity); 
		return false;
	}

	

	public ResponseGenerator getResponseGenerator() {
		return responseGenerator;
	}

	public void setResponseGenerator(ResponseGenerator responseGenerator) {
		this.responseGenerator = responseGenerator;
	}

	@Override
	public void flush2ndLevelCache() {
		
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheHitCount() >>"+ getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheMissCount() >>"+ getSessionFactory().getStatistics().getSecondLevelCacheMissCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheRegionNames() >>"+ getSessionFactory().getStatistics().getSecondLevelCacheRegionNames());
		getSessionFactory().getCache().evictEntityRegions();
		getSessionFactory().getCache().evictEntityRegions();
		getSessionFactory().getCache().evictCollectionRegions();
		getSessionFactory().getCache().evictDefaultQueryRegion();//getSessionFactory().getCache().containsEntity(Product.class, "prd10011");
		getSessionFactory().getCache().evictQueryRegions() ;
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 
	 * @param mv
	 * @param fetchProperties
	 */
	public void initializeProperties(MavrickEntity mv,
			List<String> fetchProperties) {
		if (null != fetchProperties && !fetchProperties.isEmpty()&&mv!=null) {
			for (String prop : fetchProperties) {
				try {
					logger.debug("Initializing property:" + prop);
					Hibernate.initialize(PropertyUtils.getProperty(mv, prop));
					logger.debug("Initializing property:" + prop
							+ " successful.");
				} catch (HibernateException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);

				} catch (InvocationTargetException e) {
					logger.error(e);
				} catch (NoSuchMethodException e) {
					logger.error(e);
				}
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param mv
	 * @param fetchProperties
	 */
	public void initializeProperties(MavrickEntity mv,
			List<String> unboundProperties,boolean fullRecursive) {
		if (null != unboundProperties && !unboundProperties.isEmpty()&&mv!=null) {
			for (String prop : unboundProperties) {
				try {
					logger.debug("Initializing property:" + prop);
					Hibernate.initialize(PropertyUtils.getProperty(mv, prop));
					if (fullRecursive
							&& PropertyUtils.getProperty(mv, prop) instanceof Collection<?>) {
						Collection<MavrickEntity> propValue = (Collection<MavrickEntity>) PropertyUtils
								.getProperty(mv, prop);

						if (null != propValue) {

							for (MavrickEntity et : propValue) {

								List<String> unboundProps = responseGeneratorTools
										.getExtendedProperties(et);

								initializeProperties(et, unboundProps,
										true);
							}
						}
					}

					logger.debug("Initializing property:" + prop
							+ " successful.");
				} catch (HibernateException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);

				} catch (InvocationTargetException e) {
					logger.error(e);
				} catch (NoSuchMethodException e) {
					logger.error(e);
				}
			}
		}
	}
	
	@Override
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId,
			Class<? extends MavrickEntity> className,
			List<String> fetchProperties) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId,
			MavrickEntity className, List<String> fetchProperties) {
		logger.info("GetItem on Entity :" + className.getClass().getName()
				+ " with ID:" + mId);// evictCollectionRegions();

		MavrickEntity result = (MavrickEntity) getSessionFactory()
				.getCurrentSession().get(className.getClass(), mId);
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheHitCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheHitCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheMissCount() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheMissCount());
		logger.info("getSessionFactory().getStatistics().getSecondLevelCacheRegionNames() >>"
				+ getSessionFactory().getStatistics()
						.getSecondLevelCacheRegionNames());

		initializeProperties(result, fetchProperties);
		return result;
	}

	
	public ResponseGeneratorTools getResponseGeneratorTools() {
		return responseGeneratorTools;
	}
	public void setResponseGeneratorTools(
			ResponseGeneratorTools responseGeneratorTools) {
		this.responseGeneratorTools = responseGeneratorTools;
	}

	/**
	 * Accepts only string parameters.
	 */
	@Override
	public List<MaverickVO> runNativeQuery(String mQuery,
			List<String> parameters,Class<? extends MavrickEntity> className) {
		List<MaverickVO> items = null;
		int position = 0;
		SQLQuery sqlQry = getSessionFactory().getCurrentSession()
				.createSQLQuery(mQuery);
		for (String param : parameters) {

			sqlQry.setString(position, param);
			++position;

		}
		sqlQry.addEntity(className);
		items = sqlQry.list();

		return items;
	}

}

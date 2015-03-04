package com.mavrick.common.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.Hibernate;
import org.springframework.util.ReflectionUtils;

import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.json.annotation.ResponseGroup;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class ResponseGeneratorTools {

	private Logger logger = Logger.getLogger(getClass());

	private Map<String, String> entityMappings;

	private List<String> unboundProperties;

	/**
	 * Copy property values from the Entity object to the VO
	 * 
	 * @param entity
	 * @param responseObj
	 * @param properties
	 * @throws ResponseGenException
	 */
	public void populateBean(MavrickEntity entity, MaverickVO responseObj,
			Groups strategy) throws ResponseGenException {
		if (entity == null || responseObj == null)
			throw new ResponseGenException("Null Required parameters.");
		Object value;

		List<String> properties = getProperties(entity, strategy);
		for (String prop : properties) {

			if (prop != null) {

				String className = getEntityMappings().get(prop);

				Object propertyObj = null;
				if (className != null) {
					logger.debug("Class name for property :" + prop + " Name:"
							+ className);
					propertyObj = getObjectFromClassName(className);
					if (propertyObj == null)
						continue;
					if (unboundProperties != null
							&& unboundProperties.contains(prop)) {
						logger.debug("Unbounded attribute identified:" + prop);
						value = getEntityPropertyValue(entity, prop);
						Collection<MavrickEntity> values = (Collection<MavrickEntity>) value;
						Collection<MaverickVO> tempCol = new ArrayList<MaverickVO>();
						if(values!=null){
						for (MavrickEntity et : values) {
							Object tempObj = getObjectFromClassName(getEntityMappings()
									.get(prop));
													populateBean(et, (MaverickVO) tempObj, strategy);
							tempCol.add((MaverickVO) tempObj);
						}
						}
						setEntityPropertyValue(responseObj, prop, tempCol);
					} else {
						value = getEntityPropertyValue(entity, prop);
						if (value != null && value instanceof MavrickEntity
								) {

							populateBean((MavrickEntity) value,
									(MaverickVO) propertyObj, strategy);

						}
						setEntityPropertyValue(responseObj, prop, value);
					}
				} else {

					value = getEntityPropertyValue(entity, prop);
					setEntityPropertyValue(responseObj, prop, value);

				}
			}

		}

	}

	/**
	 * The default behavior is to return all the basic properties, if
	 * Groups==EXTENDED then this method returns both basic and extended
	 * properties of an Entity.
	 * 
	 * @param et
	 * @param st
	 * @return
	 */
	public List<String> getProperties(MavrickEntity et, Groups st) {

		List<String> propList = new ArrayList<String>();

		Class<? extends MavrickEntity> classInstance = et.getClass();
		for (Field f : classInstance.getDeclaredFields()) {
			if (!f.isAnnotationPresent(ResponseGroup.class)) {
				if (f.isAnnotationPresent(JsonIgnore.class)
						|| f.isAnnotationPresent(OneToOne.class)
						|| f.isAnnotationPresent(OneToMany.class))
					continue;
				
				propList.add(f.getName());
				continue;
			}
			ReflectionUtils.makeAccessible(f);
			try {
				Object obj=f.get(et);
				if(obj==null)
					continue;
			} catch (IllegalArgumentException e) {
				logger.error("Error accessing field",e);
			} catch (IllegalAccessException e) {
				logger.error("Error accessing field",e);
			}
			ResponseGroup rg = f.getAnnotation(ResponseGroup.class);
			if (st == Groups.EXTENED) {
				propList.add(f.getName());
			} else if (rg.fieldType() != Groups.EXTENED) {
				propList.add(f.getName());
			}
		}

		return propList;
	}

	/**
	 * Returns an array of extended properties for an Entity.
	 * 
	 * @param et
	 * @return
	 */
	public List<String> getExtendedProperties(MavrickEntity et) {
		List<String> propList = new ArrayList<String>();
		Class<? extends MavrickEntity> classInstance = et.getClass();
		for (Field f : classInstance.getDeclaredFields()) {

			if (f.isAnnotationPresent(ResponseGroup.class)
					&& f.getAnnotation(ResponseGroup.class).fieldType() == Groups.EXTENED) {
				propList.add(f.getName());
			}

		}
		return propList;
	}

	/**
	 * 
	 * @param vo
	 * @param prop
	 * @param value
	 */
	private void setEntityPropertyValue(MaverickVO vo, String prop, Object value) {
		try {

			PropertyUtils.setProperty(vo, prop, value);
		} catch (IllegalAccessException e) {
			logger.error("Error while setting property:",e);
		} catch (InvocationTargetException e) {
			logger.error("Error while setting property:",e);
		} catch (NoSuchMethodException e) {
			logger.error("Error while setting property:",e);
		}
	}

	/**
	 * 
	 * @param entity
	 * @param prop
	 * @return
	 */
	private Object getEntityPropertyValue(MavrickEntity entity, String prop) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(entity, prop);
			if (!Hibernate.isInitialized(value)) {
				return null;
			}

		} catch (IllegalAccessException e) {
			logger.error("Error while getting property:", e);
		} catch (InvocationTargetException e) {
			logger.error("Error while getting property:", e);
		} catch (NoSuchMethodException e) {
			logger.error("Error while getting property:", e);
		}

		return value;
	}

	/**
	 * 
	 * @param cName
	 * @return
	 */
	public Object getObjectFromClassName(String cName) {
		Object responseObj = null;
		if (cName != null)
			try {
				responseObj = Class.forName(cName).newInstance();
			} catch (InstantiationException e) {
				logger.error("Error intantiating object:",e);
			} catch (IllegalAccessException e) {
				logger.error("Error intantiating object:",e);
			} catch (ClassNotFoundException e) {
				logger.error("Error intantiating object:",e);
			}
		return responseObj;
	}

	public Map<String, String> getEntityMappings() {
		return entityMappings;
	}

	public void setEntityMappings(Map<String, String> entityMappings) {
		this.entityMappings = entityMappings;
	}

	public List<String> getUnboundProperties() {
		return unboundProperties;
	}

	public void setUnboundProperties(List<String> unboundProperties) {
		this.unboundProperties = unboundProperties;
	}

}

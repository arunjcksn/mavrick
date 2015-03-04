package com.mavrick.common.dao;

import java.util.List;

import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public interface BasicDAO {

	public boolean addEntity(MavrickEntity mEntity);

	@Deprecated
	public MavrickEntity getEntityById(String mId, MavrickEntity className);
	
	public MavrickEntity getEntityById(String mId, MavrickEntity className,List<String> fetchProperties);
	public MavrickEntity getEntityById(String mId, MavrickEntity className,
			List<String> fetchProperties,boolean fullRecursive);
	
	@Deprecated
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId,MavrickEntity className);
	
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId,MavrickEntity className,List<String> fetchProperties);

	public boolean deleteEntity(MavrickEntity mEnty);

	public boolean deleteEntityById(String mId, MavrickEntity className);

	public boolean updateEntity(MavrickEntity mEntity);
	
	public List<MavrickEntity> runQuery(String mQuery,List<String> fetchProperties);
	
	public List<MaverickVO> runNativeQuery(String mQuery,List<String> fetchProperties,Class<? extends MavrickEntity> className);
	
	@Deprecated
	public List<MavrickEntity> runQuery(String mQuery);
	
	public void flush2ndLevelCache();

	@Deprecated
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId, Class<? extends MavrickEntity> className);
	
	public <T extends MavrickEntity> MavrickEntity getEntityById(int mId, Class<? extends MavrickEntity> className,List<String> fetchProperties);
	

}

package com.mavrick.common.auth;

import org.apache.log4j.Logger;

import com.mavrick.common.exceptions.UserAuthenticationException;
import com.mavrick.persistance.entities.MavrickEntity;

public interface BasicAuthenticationManager {
	
	Logger logger=Logger.getLogger("BasicAuthenticationManager");
	
	public boolean validateUserByPassword(String pwd,MavrickEntity entiry) throws UserAuthenticationException;
	
	public void hashPasswordForUpdate(MavrickEntity entity) throws UserAuthenticationException;
	public String setRandomPassword(MavrickEntity entity);
	
	
}

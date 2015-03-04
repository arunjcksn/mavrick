package com.mavrick.common.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mavrick.common.exceptions.UserAuthenticationException;
import com.mavrick.common.util.PasswordUtil;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.User;

@Service
public class UserAuthenticationManager implements BasicAuthenticationManager {

	@Autowired
	private PasswordUtil passwordUtil;

	@Override
	public boolean validateUserByPassword(String pwd, MavrickEntity entity)
			throws UserAuthenticationException {

		boolean valid = false;
		String dbSalt = null;
		String dbPassword = null;
		String inPutStaltedPwd = null;
		String hashedPwd = null;
		User usr = null;
		if (pwd != null && entity != null && entity instanceof User) {
			logger.info("Validating credentials.");
			usr = (User) entity;
			dbSalt = usr.getSalt();
			dbPassword = usr.getPassword();
			inPutStaltedPwd = getPasswordUtil().saltPassword(pwd, dbSalt);
			hashedPwd = getPasswordUtil().getHashedString(inPutStaltedPwd);
			logger.info("DBSalt=" + dbSalt + " Inputpwd=" + pwd
					+ " InputSaltedPwd=" + inPutStaltedPwd + " hashedPwd="
					+ hashedPwd + " DBhashed=" + usr.getPassword());

			if (null != hashedPwd && hashedPwd.equals(dbPassword))
				valid = true;
		} else {
			throw new UserAuthenticationException("MVK_AUTH_FAILED",
					"Invalid Username or password");
		}

		return valid;
	}

	@Override
	public void hashPasswordForUpdate(MavrickEntity entity)
			throws UserAuthenticationException {
		String hashedPwd = null;
		String rawPwd = null;
		String salt = null;
		String saltedPassword = null;
		User usr = null;

		if (entity != null && entity instanceof User) {
			logger.info("Found user entity.");
			usr = (User) entity;
			rawPwd = usr.getPassword();
			salt = passwordUtil.createSalt();
			saltedPassword = passwordUtil.saltPassword(rawPwd, salt);
			hashedPwd = passwordUtil.getHashedString(saltedPassword);
			logger.info("Updating hashed password and salt to transient entity.");
			usr.setPassword(hashedPwd);
			usr.setSalt(salt);
		} else {
			logger.error("Error occured while hashing user password.");
			throw new UserAuthenticationException("MVK_AUTH_FAILED",
					"There was an error while hashPasswordForUpdate Entity:"
							+ entity.toString());
		}

	}

	public String setRandomPassword(MavrickEntity entity) {
		String salt = null;
		String rndmGenStaltedPwd = null;
		User usr = null;
		String pwd = null;
		if (entity != null && entity instanceof User) {
			logger.info("Validating credentials.");
			usr = (User) entity;
			pwd = getPasswordUtil().generateRandomPasswd();
			salt = passwordUtil.createSalt();
			rndmGenStaltedPwd = getPasswordUtil().getHashedString(
					getPasswordUtil().saltPassword(pwd, salt));
			usr.setPassword(rndmGenStaltedPwd);
			usr.setSalt(salt);
			logger.info("Setting the random password");
		}
		return pwd;
	}
	
	public PasswordUtil getPasswordUtil() {
		return passwordUtil;
	}

	public void setPasswordUtil(PasswordUtil passwordUtil) {
		this.passwordUtil = passwordUtil;
	}

}

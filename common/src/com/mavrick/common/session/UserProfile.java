package com.mavrick.common.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mavrick.common.auth.ProfileAuthStatus;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProfile implements SessionObject {

	MavrickEntity userEntity;
	
	MaverickVO user;

	String authStatus = ProfileAuthStatus.ANONYMOUS.toString();
	// SSL session id to prevent session hijacking.
	String sslSessionId;

	public MavrickEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(MavrickEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getSslSessionId() {
		return sslSessionId;
	}

	public void setSslSessionId(String sslSessionId) {
		this.sslSessionId = sslSessionId;
	}

	public MaverickVO getUser() {
		return user;
	}

	public void setUser(MaverickVO user) {
		this.user = user;
	}

}

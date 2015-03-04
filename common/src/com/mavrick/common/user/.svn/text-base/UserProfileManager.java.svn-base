package com.mavrick.common.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavrick.common.auth.BasicAuthenticationManager;
import com.mavrick.common.constants.JSONConstants;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.exceptions.UserAuthenticationException;
import com.mavrick.common.json.EntityResponseGenerator;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.session.Cart;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.util.MavrickTools;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.user.AddressVO;
import com.mavrick.persistance.entities.MavrickEntity;
import com.mavrick.persistance.entities.PostalCode;
import com.mavrick.persistance.entities.User;
import com.mavrick.persistance.entities.order.CommerceItem;
import com.mavrick.persistance.entities.order.Order;
import com.mavrick.persistance.entities.order.PriceInfo;
import com.mavrick.persistance.entities.profile.Address;
import com.mavrick.persistance.entities.profile.UserPrimaryAddress;

@Service
@Transactional
public class UserProfileManager {

	@Autowired
	BasicDAO basicDAO;

	@Autowired
	Cart cart;

	@Autowired
	UserProfile profile;
	
	@Autowired
	EntityResponseGenerator entityResponseGenerator;
	
	@Autowired
	UserProfile userProfile;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	@Autowired
	private BasicAuthenticationManager userAuthenticationManager;

	private MavrickTools mvkTools;

	private String profileQuery;

	Logger logger = Logger.getLogger(getClass());

	static final String JSON_RESPONSE_TYPE = "USER";

	public JSONResponse createUser(User usr) throws MavrickException {

		boolean success = false;
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		usr.setCreatedDate(new Date());
		usr.setLastLoggedInDate(new Date());

		logger.info("Begin hashing password.");
		userAuthenticationManager.hashPasswordForUpdate(usr);
		logger.info("Calling DAO to save entity..");
		List<MavrickEntity> obj = basicDAO.runQuery(getProfileQuery() + "'"
				+ usr.getEmail() + "'");
		if (null != obj && !obj.isEmpty()) {

			throw new UserAuthenticationException("MVK_USR_CRT_ERR",
					getMvkTools().getLocalizedMessage("profile.exist", null));
		}
		
		basicDAO.addEntity(usr);
		
		UserPrimaryAddress primaryAddress=new UserPrimaryAddress();
		primaryAddress.setUser(usr);
		primaryAddress.setId(usr.getId());
		basicDAO.addEntity(primaryAddress);
		success = true;

		// *************************

		/**
		 * TODO JSON generation should move to a generator.
		 * 
		 */
		if (success) {
			jsn.setEntity(usr);
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Register User" }));
		} else {

			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Register User" }));

		}
		return jsn;
	}

	/**
	 * 
	 * @param postalCode
	 * @param jsn
	 */
	public void checkPostalCode(String postalCode, JSONResponse jsn) {

		MavrickEntity pstlCode = getBasicDAO().getEntityById(postalCode,
				new PostalCode(), null);
		if (null != pstlCode && pstlCode instanceof PostalCode) {
			boolean valid = ((PostalCode) pstlCode).isEnabledForDelivery();
			if (valid) {
				logger.info("Postal code enabled for delivery:" + postalCode);
				jsn.getResponsePayload().add(pstlCode);
			}
		}

	}
	
	
	/**
	 * This method authenticate the user credentials.
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public JSONResponse loginUser(User usr) throws UserAuthenticationException {

		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		User dbUser = null;

		List<String> deepCopyProperties = getEntityResponseGenerator()
				.getResponseGenTools().getExtendedProperties(usr);
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				getProfileQuery() + "'" + usr.getEmail() + "'",
				deepCopyProperties);
		if (null != entity && !entity.isEmpty()) {
			dbUser = (User) entity.get(0);
		}
		/**
		 * TODO JSON generation should move to a generator.
		 * 
		 */

		if (getUserAuthenticationManager().validateUserByPassword(
				usr.getPassword(), dbUser)) {

			jsn.setEntity(dbUser);
			List<MaverickVO> vos = new ArrayList<MaverickVO>();
			try {
				vos = getEntityResponseGenerator().generateResponse(dbUser,
						Groups.EXTENED, JSONConstants.USER);
				jsn.setResponseVOs(vos);
			} catch (ResponseGenException e) {
				logger.error(e);
			}

			getUserProfile().setUser(
					(vos != null || !vos.isEmpty()) ? vos.get(0) : null);

			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Login User" }));
		} else {
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Login User" }));
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("errorCode", "MVK_AUTH_FAILURE");
			mp.put("errorMessage",
					getMvkTools().getLocalizedMessage("profile.auth.failed",
							null));
			List<Map<String, Object>> addObjects = new ArrayList<Map<String, Object>>();
			addObjects.add(mp);
			jsn.setAdditionalObjects(addObjects);

		}

		return jsn;
	}

	public BasicDAO getBasicDAO() {
		return basicDAO;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public BasicAuthenticationManager getUserAuthenticationManager() {
		return userAuthenticationManager;
	}

	public void setUserAuthenticationManager(
			BasicAuthenticationManager userAuthenticationManager) {
		this.userAuthenticationManager = userAuthenticationManager;
	}

	public String getProfileQuery() {
		return profileQuery;
	}

	public void setProfileQuery(String profileQuery) {
		this.profileQuery = profileQuery;
	}

	public MavrickTools getMvkTools() {
		return mvkTools;
	}

	public void setMvkTools(MavrickTools mvkTools) {
		this.mvkTools = mvkTools;
	}

	public void loadCart(long id) {
		// Cart cart1=new Cart();
		Order dbOrder = null;
		List<CommerceItem> commItems = new ArrayList<CommerceItem>();
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				"from Order where userId=" + "" + id
						+ " and state='INCOMPLETE'");
		if (null != entity && !entity.isEmpty()) {
			dbOrder = (Order) entity.get(0);
			List<MavrickEntity> commerceItems = getBasicDAO().runQuery(
					"from CommerceItem where orderId=" + ""
							+ dbOrder.getOrderId() + "");
			if (null != commerceItems && !commerceItems.isEmpty()) {
				for (MavrickEntity commItem : commerceItems) {
					commItems.add((CommerceItem) commItem);
				}
			}

		} else {
			dbOrder = new Order();
			dbOrder.setState("INCOMPLETE");
			dbOrder.setUserId((int) id);
			dbOrder.setSubmittedDate(new Date());
			
			List<PriceInfo> priceInfos = new ArrayList<PriceInfo>();
			PriceInfo priceInfo = new PriceInfo();
			basicDAO.addEntity(priceInfo);
			dbOrder.setPriceId(priceInfo.getPriceId());
			priceInfos.add(priceInfo);
			dbOrder.setOrderPrice(priceInfos);
			basicDAO.addEntity(dbOrder);

		}
		getCart().setOrder(dbOrder);
		getCart().setCommerceItems(commItems);
		// this.setCart(cart1);
		return;
	}

	@Transactional
	public List<MaverickVO> buildUserVO() {
		User user = (User) getProfile().getUserEntity();
		List<MaverickVO> userVOs = new ArrayList<MaverickVO>();
		try {
			userVOs=getEntityResponseGenerator().generateResponse(user, Groups.EXTENED, "user");
		} catch (ResponseGenException e) {
			logger.error(e);
		}
	return userVOs;
	
	}

	@Transactional
	private List<MaverickVO> buildUserAddressVO(List<Address> addressItems) {

		List<MaverickVO> addressVOs = new ArrayList<MaverickVO>();
		for (Address address : addressItems) {
			AddressVO addressVO = new AddressVO();
			addressVO.setAddressId(address.getAddressId());
			addressVO.setArea(address.getArea());
			addressVO.setCity(address.getCity());
			addressVO.setPostcode(address.getPostcode());
			addressVO.setAddress1(address.getAddress1());
			addressVO.setAddress2(address.getAddress2());
			addressVO.setNickName(address.getNickName());

			addressVO.setEmail(address.getEmail());
			addressVO.setPhoneNum(address.getPhoneNum());
			addressVO.setMobilePhoneNum(address.getMobilePhoneNum());
			addressVO.setAltPhoneNum(address.getAltPhoneNum());
			addressVOs.add(addressVO);
		}

		// /////////////////////////////////////////////////////
		return addressVOs;
	}

	@Transactional
	public List<MaverickVO> addAddressToProfile(Address address) {
		User user = (User) getProfile().getUserEntity();
		user = (User)getBasicDAO().getEntityById(user.getId(), user);
		
		basicDAO.addEntity(address);
		List<Address> addressList = user.getAddress();
		addressList.add(address);
		user.setAddress(addressList);
		UserPrimaryAddress primaryAddress=user.getPrimaryAddress();
			primaryAddress.setShipAddrId(address.getAddressId());
		
		basicDAO.updateEntity(primaryAddress);
		user.setPrimaryAddress(primaryAddress);
		basicDAO.updateEntity(user);

		return buildUserVO();

	}
	
	public List<MaverickVO> updateAddressToProfile(Address address) {

		int addressId = address.getAddressId();

		Address addressEntity = (Address) basicDAO.getEntityById(addressId,
				new Address());

		if (address.getAddress1() != null) {
			addressEntity.setAddress1(address.getAddress1());
		}

		if (address.getAddress2() != null) {
			addressEntity.setAddress2(address.getAddress2());
		}

		if (address.getAltPhoneNum() != null) {
			addressEntity.setAltPhoneNum(address.getAltPhoneNum());
		}

		if (address.getArea() != null) {
			addressEntity.setArea(address.getArea());
		}

		if (address.getCity() != null) {
			addressEntity.setCity(address.getCity());
		}

		if (address.getEmail() != null) {
			addressEntity.setEmail(address.getEmail());
		}

		if (address.getMobilePhoneNum() != null) {
			addressEntity.setMobilePhoneNum(address.getMobilePhoneNum());
		}

		if (address.getNickName() != null) {
			addressEntity.setNickName(address.getNickName());

		}

		if (address.getPostcode() != null) {
			addressEntity.setPostcode(address.getPostcode());

		}

		basicDAO.updateEntity(addressEntity);

		return buildUserVO();

	}

	public List<MaverickVO> deleteAddressFromProfile(int addressId,
			UserProfile prf) {

		List<MaverickVO> userVo = null;
		try {

			if (prf != null) {
				User usr = (User) prf.getUserEntity();
				String addressIdToBeDeleted = String.valueOf(addressId);
				basicDAO.deleteEntityById(addressIdToBeDeleted, new Address());
				userVo = buildUserVO();

			}

		}

		catch (Exception e) {

			logger.info("Exception occurred :" + e);
		}

		return userVo;
	}

	
	public List<MavrickEntity> checkUserExist(String email)   {
System.out.println("checkUserExist = " + email);
		List<MavrickEntity> entity = getBasicDAO().runQuery(
				getProfileQuery() + "'" + email + "'",
				null);
		System.out.println("checkUserExist entity = " + entity);

		return entity;
	}
	
	public JSONResponse forgotMyPasswd(String email) {
		JSONResponse jsn = new JSONResponse();
		System.out.println("reset password = " + email);
		List<MavrickEntity> entity = checkUserExist(email);
		if (null != entity && !entity.isEmpty()) {
			String passwd = getUserAuthenticationManager().setRandomPassword(entity.get(0));
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Forgot Password" +"-"+passwd}));	
			basicDAO.updateEntity(entity.get(0));
		} else {
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Forgot Password" }));
			
		}
		return jsn;
		
	}
	
	
	public JSONResponse resetPassword(MavrickEntity userEntity) throws MavrickException {

		boolean success = false;
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);


		logger.info("Begin resetting password.");
		userAuthenticationManager.hashPasswordForUpdate(userEntity);
		logger.info("Calling DAO to save entity..");
		basicDAO.updateEntity(userEntity);
		
		success = true;

	
		if (success) {
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Reset Password" }));
		} else {

			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Reset Password" }));

		}
		return jsn;
	}
	
	public EntityResponseGenerator getEntityResponseGenerator() {
		return entityResponseGenerator;
	}

	public void setEntityResponseGenerator(
			EntityResponseGenerator entityResponseGenerator) {
		this.entityResponseGenerator = entityResponseGenerator;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public JSONResponse updatePersonalInfo(User user) {
		// TODO Auto-generated method stub

		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		boolean success = false;
		User dbuser = (User) getBasicDAO().getEntityById(user.getId(), user,
				null);

		if (dbuser != null) {

			if (user.getFirstName() != null || !user.getFirstName().isEmpty()) {
				dbuser.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null || !user.getLastName().isEmpty()) {
				dbuser.setLastName(user.getLastName());
			}

			basicDAO.updateEntity(dbuser);
			success = true;
		}

		if (success) {
			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.success", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.success",
					new Object[] { "Update Personal Details" }));
		} else {

			jsn.setStatusCode(getMvkTools().getLocalizedMessage(
					"status.code.failure", null));
			jsn.setStatusMessage(getMvkTools().getLocalizedMessage(
					"status.api.execution.failed",
					new Object[] { "Update Personal Details" }));

		}
		return jsn;
	}

}

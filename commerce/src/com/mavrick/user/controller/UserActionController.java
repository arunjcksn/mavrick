package com.mavrick.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavrick.common.auth.ProfileAuthStatus;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.user.UserProfileManager;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.controllers.MavrickController;
import com.mavrick.persistance.entities.User;
import com.mavrick.persistance.entities.profile.Address;

@Controller
@RequestMapping("/user")
public class UserActionController extends MavrickController {

	@Autowired
	private UserProfileManager userProfileManager;

	static final String JSON_RESPONSE_TYPE = "USER";

	/**
	 * 
	 * 
	 * 
	 * @param usr
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse createUser(@Valid @ModelAttribute("user") User usr,
			HttpServletRequest req) throws MavrickException {
		logger.info("Inside register user");
		JSONResponse jsn = getProfileManager().createUser(usr);
		if (null != jsn && jsn.getStatusCode() != null
				&& jsn.getStatusCode().equalsIgnoreCase(ACTION_SUCCESS)) {
			updateSession(usr, USER, ProfileAuthStatus.LOGIN_EXPLICIT, null,
					req);
		}
		return jsn;
	}

	/**
	 * 
	 * 
	 * @param usr
	 * @return
	 * @throws MavrickException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse loginUser(@ModelAttribute("user") User usr,
			HttpServletRequest req) throws MavrickException {
		JSONResponse jsn = getProfileManager().loginUser(usr);
		JSONResponse jsnRes = new JSONResponse(JSON_RESPONSE_TYPE);
		if (null != jsn && jsn.getStatusCode() != null
				&& jsn.getStatusCode().equalsIgnoreCase(ACTION_SUCCESS)) {
			updateSession(jsn.getEntity(), USER,
					ProfileAuthStatus.LOGIN_EXPLICIT, null, req);

			getProfileManager().loadCart(((User) jsn.getEntity()).getId());
			updateSession(jsn.getEntity(), MavrickController.CART,
					ProfileAuthStatus.LOGIN_EXPLICIT, null, req);

			//List<MaverickVO> responseVOs = getUserProfileManager().buildUserVO();

			jsnRes.setResponseVOs(jsn.getResponseVOs());
		} else {
			jsnRes.setStatusCode(jsn.getStatusCode());
			jsnRes.setStatusMessage(jsn.getStatusMessage());
		}
		
		return jsnRes;
	}

	/**
	 * Returns the user object in the session, if the profile is anonymous
	 * entity object will be null;
	 * 
	 * TODO to be refactored.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse viewUser(HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {
			// jsn.setEntity(prf.getUserEntity());
			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			responseVOs.add(prf.getUser());
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("View User API executed sucessfully");
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/viewaddress", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse viewAddress(HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {

			List<MaverickVO> responseVOs = getUserProfileManager()
					.buildUserVO();

			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("View address API executed sucessfully");
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}

	/**
	 * This action will invalidate the profile.
	 * 
	 * @TODO need to refactor.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse logoutUser(HttpServletRequest req, HttpServletResponse res) {
		updateSession(null, NONE, ProfileAuthStatus.ANONYMOUS, null, req);
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		// jsn.setEntity(getProfile().getUserEntity());
		jsn.setStatusCode("200");
		jsn.setStatusMessage("logout successfull");

		return jsn;
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse addAddress(@ModelAttribute("address") Address address,
			HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {
			address.setProfileId(((User) prf.getUserEntity()).getId());
			List<MaverickVO> responseVOs = getProfileManager()
					.addAddressToProfile(address);

			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("View address API executed sucessfully");
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}
	
	@RequestMapping(value = "/editAddress", method = RequestMethod.PUT)
	public @ResponseBody
	JSONResponse editAddress(@ModelAttribute("address") Address address,
			HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {
			
			
			
			List<MaverickVO> responseVOs = getProfileManager()
					.updateAddressToProfile(address);

			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("View address API executed sucessfully");
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}
	
	
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse deleteAddress(@ModelAttribute("addressId")String addressId ,
			HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {
			
			if(addressId!=null)
			{
			int addressIdFromRequest=Integer.valueOf(addressId);
			
			
			List<MaverickVO> responseVOs = getProfileManager()
					.deleteAddressFromProfile(addressIdFromRequest,prf);

			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("View address API executed sucessfully");
		} }else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}
	
	
	
	
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse forgotPassword(@ModelAttribute("email") String email,
			HttpServletRequest req) throws MavrickException {

		JSONResponse jsnRes = null;
		if (!StringUtils.isEmpty(email)) {
			try {
				jsnRes = getUserProfileManager().forgotMyPasswd(email);
				jsnRes.setStatusCode("200");
			} catch (Exception e) {
				e.printStackTrace();
				jsnRes = new JSONResponse();
				jsnRes.setStatusCode("500");
				jsnRes.setStatusMessage("UNKNOWN ERROR - " + e.getMessage());
			}
		}
		return jsnRes;
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse resetPassword(@ModelAttribute("password") String passwd,@ModelAttribute("resetpassword") String resetPasswd,
			HttpServletRequest req) throws MavrickException {

		JSONResponse jsnRes = null;
		UserProfile prf = getProfile();
		if (passwd!= null && resetPasswd != null && resetPasswd.equals(passwd) && prf != null && prf.getUserEntity() != null) {
		try {
			User usr = (User) prf.getUserEntity();
			usr.setPassword(resetPasswd);
			jsnRes = getUserProfileManager().resetPassword(prf.getUserEntity());
			jsnRes.setStatusCode("200");
		} catch (Exception e) {
			e.printStackTrace();
			jsnRes = new JSONResponse();
			jsnRes.setStatusCode("500");
			jsnRes.setStatusMessage("UNKNOWN ERROR - "+ e.getMessage());
		}
		} else {
			jsnRes = new JSONResponse();
			jsnRes.setStatusCode("500");
			jsnRes.setStatusMessage("User Must Login");
		}
		return jsnRes;
	}
	
	public UserProfileManager getUserProfileManager() {
		return userProfileManager;
	}

	public void setUserProfileManager(UserProfileManager userProfileManager) {
		this.userProfileManager = userProfileManager;
	}

	//guru added the edit personal info api for updating personal details starts
	
	@RequestMapping(value = "/editPersonalInfo", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse editPersonalInfo(@ModelAttribute("user")User user,HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null && prf.getUserEntity() != null) {

			jsn=getProfileManager().updatePersonalInfo(user);
			
			if (null != jsn && jsn.getStatusCode() != null
					&& jsn.getStatusCode().equalsIgnoreCase(ACTION_SUCCESS)) {
				updateSession(user, USER,
						ProfileAuthStatus.LOGIN_EXPLICIT, null, req);
			}
			
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		
		return jsn;
	}
	
	
	
	//guru added the edit personal info api for updating personal details ends
}

package com.mavrick.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.mavrick.common.auth.ProfileAuthStatus;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.session.Cart;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.user.UserProfileManager;
import com.mavrick.persistance.entities.MavrickEntity;

@Controller
@Scope("request")
@RequestMapping("/global")
public class MavrickController extends AbstractController {
	
	/**
	 * This attribute represents the user profile saved in the session.
	 */
	@Autowired
	UserProfile profile;
	
	/**
	 * This attribute represents the user's order saved in the session.
	 */
	@Autowired
	Cart cart;
	
	@Autowired
	UserProfileManager profileManager;
	/**
	 * values that represents success/failure for a transaction inside the JSONResponse object.
	 */
	protected static final String ACTION_SUCCESS="200",ACTION_FAILED="500";

	protected static final int USER=1,CART=2,NONE=0;
	
	protected Logger logger=Logger.getLogger(getClass());
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	List<String> errors=new ArrayList<String>();
	/**
	 * Still need to figure out what needs to be done with this method..
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		logger.info("Inside default controller");
		return null;
	}
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public  @ResponseBody JSONResponse handleGlobalErrors(@RequestParam("err") int err  ){
		JSONResponse jsn=new JSONResponse("ERROR");
		jsn.setStatusCode(err+"");
		logger.info("Error Code received:"+err);
		switch (err) {
		case 500:
				jsn.setStatusMessage("Sorry, there was a technical error and we could not process your request ");
			break;

		case 404:
			jsn.setStatusMessage("Sorry, the requested resource is not available.");
			break;
			
			default :jsn.setStatusMessage("Sorry, there was an unknown error while processing your request.");break;
		}
		return jsn;
	}
	
	/**
	 * 
	 * @param postalcode
	 * @return
	 * @throws MavrickException
	 */
	@RequestMapping(value = "/checkpostalcode", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse handleCheckPostalCode(HttpServletRequest req,
			HttpServletResponse res) throws MavrickException {
		String postalcode = req.getParameter("postalcode");
		JSONResponse jsn = new JSONResponse("POSTALCODE");
		if (null != postalcode) {
			profileManager.checkPostalCode(postalcode, jsn);
			if (jsn.getResponsePayload().isEmpty()) {
				addError("Not valid record found for postalcode");
				throw new MavrickException(ACTION_FAILED, getErrors());
			}
		} else {
			addError("null postal code parameter");
			throw new MavrickException(ACTION_FAILED, getErrors());
		}
		return jsn;
	}
	
	/**
	 * TODO This method should go some where else?
	 * 
	 * @param entity
	 * @param type
	 * @param status
	 * @param extraParameterMap
	 * @param req
	 */
   protected void updateSession(MavrickEntity entity,int type,ProfileAuthStatus status,Map<String, Object> extraParameterMap,HttpServletRequest req){
	   
	   switch (type){
	   
	   case USER :{
		   logger.info("Updating user object in session");
			// User logged in , so update session.
			getProfile().setAuthStatus(status.toString());
			getProfile().setUserEntity(entity);
			if(null!=extraParameterMap&&!extraParameterMap.isEmpty()){
				
			}
		   
	   }break;
	   
	   case CART :{
		   logger.info("Updating Cart object in session");
		   
		   if(null!=extraParameterMap&&!extraParameterMap.isEmpty()){
				
			}
	   } break;
	   
	   case NONE :{
		   
		   getProfile().setAuthStatus(status.toString());
		   getProfile().setUserEntity(null);
		   if(null!=req){
			   logger.info("Invalidating user session");
			   req.getSession().invalidate();
		   }
		   
	   }
	   
	   }
	   
   }
   /**
    * Add error messages to the error list
    * @param error
    */
   protected void addError(String error){
	   if(errors==null)
		   errors=new ArrayList<String>();
	   errors.add(error);
   }
   
  
   
   
	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public UserProfileManager getProfileManager() {
		return profileManager;
	}

	public void setProfileManager(UserProfileManager profileManager) {
		this.profileManager = profileManager;
	}

}

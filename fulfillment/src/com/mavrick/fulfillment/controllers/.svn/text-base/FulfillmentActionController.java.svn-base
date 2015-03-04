package com.mavrick.fulfillment.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.controllers.MavrickController;
import com.mavrick.fulfillment.user.entities.FulfillmentUser;
import com.mavrick.fulfillment.order.manager.FulfillmentOrderManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping("/order")
public class FulfillmentActionController extends MavrickController {
	
	
	
	@Autowired
	BasicDAO basicDAO;
	
	@Autowired
	FulfillmentOrderManager orderManager;

	
	
	static final String JSON_RESPONSE_TYPE = "FULFILLMENTUSER";
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse viewOrder(@ModelAttribute("orderId") String orderId,HttpServletRequest req, HttpServletResponse res) {

		JSONResponse jsn=null;
		try {
			 jsn = new JSONResponse("FULFILLMENTORDER");

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			responseVOs.add(orderManager.buildOrderVO(orderId));
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("view order API executed successfully");
			return jsn;
		} catch (MavrickException e) {
			// TODO Auto-generated catch block
			logger.info("exception ");
		} finally {
			// tx.commit();
		}
		return jsn;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse loginUser(@ModelAttribute("user") FulfillmentUser usr,
			HttpServletRequest req) throws MavrickException {
		JSONResponse jsn = getOrderManager().loginUser(usr);
		/*JSONResponse jsnRes = new JSONResponse(JSON_RESPONSE_TYPE);
		if (null != jsn && jsn.getStatusCode() != null
				&& jsn.getStatusCode().equalsIgnoreCase(ACTION_SUCCESS)) {
			updateSession(jsn.getEntity(), USER,
					ProfileAuthStatus.LOGIN_EXPLICIT, null, req);

			getProfileManager().loadCart(((User) jsn.getEntity()).getId());
			updateSession(jsn.getEntity(), MavrickController.CART,
					ProfileAuthStatus.LOGIN_EXPLICIT, null, req);

			List<MaverickVO> responseVOs =getOrderManager().buildUserVO();

			jsnRes.setResponseVOs(responseVOs);
		} else {
			jsnRes.setStatusCode(jsn.getStatusCode());
			jsnRes.setStatusMessage(jsn.getStatusMessage());
		}*/

		return jsn;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse createUser(@Valid @ModelAttribute("user") FulfillmentUser usr,
			HttpServletRequest req) throws MavrickException {
		logger.info("Inside register user");
		logger.info(usr.getCustomerId());
		JSONResponse jsn = getOrderManager().createUser(usr);
		if (null != jsn && jsn.getStatusCode() != null
				&& jsn.getStatusCode().equalsIgnoreCase(ACTION_SUCCESS)) {
			//updateSession(usr, USER, ProfileAuthStatus.LOGIN_EXPLICIT, null,
					//req);
		}
		return jsn;
	}
	
	@RequestMapping(value = "/pickItem", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse pickItem(@ModelAttribute("orderId") String orderId,@ModelAttribute("productId") String productId,
			@ModelAttribute("qty") int qty, HttpServletRequest req,
			HttpServletResponse res) {

		try {
			JSONResponse jsn = new JSONResponse("FULFILLMENTORDER");

			orderManager.updateOrderForPickedItem(orderId, productId, qty);

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			try {
				responseVOs.add(orderManager.buildOrderVO(orderId));
			} catch (MavrickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("pick item API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}
	
	
	@RequestMapping(value = "/subItem", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse subItem(@ModelAttribute("orderId") String orderId,@ModelAttribute("productId") String productId,@ModelAttribute("subProdId") String subProdId,@ModelAttribute("qty") int qty, HttpServletRequest req,
			HttpServletResponse res) {

		try {
			JSONResponse jsn = new JSONResponse("FULFILLMENTORDER");

			orderManager.updateOrderForSubItem(orderId, productId, qty);

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			try {
				responseVOs.add(orderManager.buildOrderVO(orderId));
			} catch (MavrickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("substitue API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}
	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public FulfillmentOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(FulfillmentOrderManager orderManager) {
		this.orderManager = orderManager;
	}
}

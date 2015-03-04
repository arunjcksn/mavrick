package com.mavrick.shopping.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavrick.common.auth.ProfileAuthStatus;
import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.session.Cart;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.controllers.MavrickController;
import com.mavrick.manager.order.OrderManager;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController extends MavrickController {
	@Autowired
	Cart cart;
	@Autowired
	BasicDAO basicDAO;
	@Autowired
	UserProfile profile;

	@Autowired
	OrderManager orderManager;
	
	static final String JSON_RESPONSE_TYPE = "ORDER";

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse addItemToCart(@ModelAttribute("skuId") int skuId,
			@ModelAttribute("qty") int qty, HttpServletRequest req,
			HttpServletResponse res) {

		try {
			JSONResponse jsn = new JSONResponse("CART");

			orderManager.updateOrderForAddItem(skuId, qty);

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			responseVOs.add(orderManager.buildOrderVO(cart));
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("addItem API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse viewCart(HttpServletRequest req, HttpServletResponse res) {

		try {
			JSONResponse jsn = new JSONResponse("CART");

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			responseVOs.add(orderManager.buildOrderVO(cart));
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("addItem API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}

	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse processCheckout(@ModelAttribute("payMode") String payMode,
			@ModelAttribute("nickName") String nickName,
			@ModelAttribute("cvv") String cvv, HttpServletRequest req,
			HttpServletResponse res) throws MavrickException {
		JSONResponse jsn = new JSONResponse("CART");
		int submittedOrderId = cart.getOrder().getOrderId();
		// //////////////////////////////////////////////////
		orderManager.confirmOrder(payMode,nickName,cvv);
		getProfileManager().loadCart(cart.getOrder().getUserId());
		updateSession(jsn.getEntity(), MavrickController.CART,
				ProfileAuthStatus.LOGIN_EXPLICIT, null, req);
		List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
		responseVOs.add(orderManager.buildOrderVO(submittedOrderId));
		jsn.setResponseVOs(responseVOs);
		jsn.setStatusCode("200");
		jsn.setStatusMessage("checkout API executed successfully");
		return jsn;

	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public BasicDAO getBasicDAO() {
		return basicDAO;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	// changes for order history starts

	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse getOrders(@ModelAttribute("profileId") String profileId,
			HttpServletRequest req, HttpServletResponse res) {
		JSONResponse jsn = new JSONResponse(JSON_RESPONSE_TYPE);
		UserProfile prf = getProfile();
		if (prf != null || prf.getUserEntity() != null) {

			if (profileId != null) {
				int profileIdFromRequest = Integer.valueOf(profileId);
				List<MaverickVO> responseVOs = orderManager
						.getOrders(profileIdFromRequest);

				jsn.setResponseVOs(responseVOs);
				jsn.setStatusCode("200");
				jsn.setStatusMessage("View orders API executed sucessfully");
			}
		} else {
			jsn.setStatusCode("500");
			jsn.setStatusMessage("User Must Login");

		}
		return jsn;
	}

	// changes for order history ends
	
	//changes for update item starts
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse updateItemFromCart(@ModelAttribute("commerceItemId") int commerceItemId,
			@ModelAttribute("qty") int qty, int quantityFlag,HttpServletRequest req,
			HttpServletResponse res) throws MavrickException {

		try {
			JSONResponse jsn = new JSONResponse("CART");

			orderManager.updateOrderForUpdateItem(commerceItemId, qty,quantityFlag);

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			responseVOs.add(orderManager.buildOrderVO(cart));
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("Update item API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}
	
	@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse removeItemFromCart(@ModelAttribute("commerceItemId") String commerceItemId,HttpServletRequest req,
			HttpServletResponse res) throws MavrickException {

		
		try {
			JSONResponse jsn = new JSONResponse("CART");
			if (!StringUtils.isEmpty(commerceItemId)
					&& commerceItemId.matches("\\d+(\\.\\d+)?")) {
				orderManager.removeItem(Integer.parseInt(commerceItemId));

				List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
				responseVOs.add(orderManager.buildOrderVO(cart));
				jsn.setResponseVOs(responseVOs);
				jsn.setStatusCode("200");
				jsn.setStatusMessage("Remove item API executed successfully");
			}
			return jsn;
		} finally {
			// tx.commit();
		}

	}
	//changes for update item ends
	
	//Introducing new method for populating the order details need to decide o whether we really need this method starts
	
	@RequestMapping(value = "/viewOrder", method = RequestMethod.GET)
	public @ResponseBody
	JSONResponse viewOrder(@ModelAttribute("orderId") int orderId
			,HttpServletRequest req,
			HttpServletResponse res) throws MavrickException {

		try {
			JSONResponse jsn = new JSONResponse("CART");

			orderManager.buildOrderVO(orderId);

			List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
			
			jsn.setResponseVOs(responseVOs);
			jsn.setStatusCode("200");
			jsn.setStatusMessage("addItem API executed successfully");
			return jsn;
		} finally {
			// tx.commit();
		}

	}
	
	
	//Introducing new method for populating the order details need to decide o whether we really need this method ends
	
}

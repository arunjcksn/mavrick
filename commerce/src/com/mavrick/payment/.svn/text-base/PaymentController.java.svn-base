package com.mavrick.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavrick.common.dao.BasicDAO;
import com.mavrick.common.exceptions.MavrickException;
import com.mavrick.common.json.JSONResponse;
import com.mavrick.common.session.Cart;
import com.mavrick.common.session.UserProfile;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.common.vo.order.SlotDisplayVO;
import com.mavrick.controllers.MavrickController;
import com.mavrick.manager.order.OrderManager;

@Controller
@RequestMapping("/payment")
public class PaymentController extends MavrickController {
	@Autowired
	Cart cart;
	@Autowired
	BasicDAO basicDAO;
	@Autowired
	UserProfile profile;

	@Autowired
	OrderManager orderManager;

	@RequestMapping(value = "/doPayment", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse performPayment(
			@ModelAttribute("postCode") String postCode,
			HttpServletRequest req, HttpServletResponse res) {

		JSONResponse jsn = new JSONResponse("CART");

		/*********************************************************/
		// get slot details based on postcode and date
		// generate stop id
		// generate DevlierySlotDetail object

	
		/***************************************************************/

		
		jsn.setStatusCode("200");
		jsn.setStatusMessage("addItem API executed successfully");
		return jsn;
	}

	

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

}
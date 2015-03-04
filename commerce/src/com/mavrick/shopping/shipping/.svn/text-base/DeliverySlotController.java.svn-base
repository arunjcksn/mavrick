package com.mavrick.shopping.shipping;

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
@RequestMapping("/slot")
public class DeliverySlotController extends MavrickController {
	@Autowired
	Cart cart;
	@Autowired
	BasicDAO basicDAO;
	@Autowired
	UserProfile profile;

	@Autowired
	OrderManager orderManager;

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse dispalyDeliverySlot(
			@ModelAttribute("postCode") String postCode,
			HttpServletRequest req, HttpServletResponse res) {

		JSONResponse jsn = new JSONResponse("CART");

		/*********************************************************/
		// get slot details based on postcode and date
		// generate stop id
		// generate DevlierySlotDetail object

		List<DeliverySlotDetail> deliverySlotDetails = new ArrayList<DeliverySlotDetail>();
		DeliverySlotDetail deliverySlotDetail = new DeliverySlotDetail();
		deliverySlotDetail
				.setStopId(cart.getOrder().getOrderId() + "123456789");
		deliverySlotDetail.setStartTime("8AM");
		deliverySlotDetail.setEndTime("10AM");
		deliverySlotDetail.setSlotPrice(10);

		deliverySlotDetails.add(deliverySlotDetail);
		/***************************************************************/

		jsn.setResponseVOs(this.buildDeliverySlotVO(deliverySlotDetails));
		jsn.setStatusCode("200");
		jsn.setStatusMessage("addItem API executed successfully");
		return jsn;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public @ResponseBody
	JSONResponse bookDeliverySlot(
			@ModelAttribute("deliverySlotDetail") DeliverySlotDetail deliverySlotDetail,
			HttpServletRequest req, HttpServletResponse res)
			throws MavrickException {

		JSONResponse jsn = new JSONResponse("CART");

		getOrderManager().bookDeliverySlotForOrder(deliverySlotDetail);
		List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
		responseVOs.add(orderManager.buildOrderVO(cart));
		jsn.setResponseVOs(responseVOs);
		jsn.setStatusCode("200");
		jsn.setStatusMessage("addItem API executed successfully");
		return jsn;
	}

	private List<MaverickVO> buildDeliverySlotVO(
			List<DeliverySlotDetail> deliverySlotDetails) {
		List<MaverickVO> responseVOs = new ArrayList<MaverickVO>();
		for (DeliverySlotDetail deliverySlotDetail : deliverySlotDetails) {
			SlotDisplayVO slotDisplayVO = new SlotDisplayVO();
			slotDisplayVO.setStopId(deliverySlotDetail.getStopId());
			slotDisplayVO.setSlotDate(deliverySlotDetail.getSlotDate());
			slotDisplayVO.setStartTime(deliverySlotDetail.getStartTime());
			slotDisplayVO.setEndTime(deliverySlotDetail.getEndTime());
			slotDisplayVO.setSlotPrice(deliverySlotDetail.getSlotPrice());
			responseVOs.add(slotDisplayVO);
		}
		return responseVOs;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

}
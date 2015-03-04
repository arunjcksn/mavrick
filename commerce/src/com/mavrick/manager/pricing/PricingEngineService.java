package com.mavrick.manager.pricing;

import java.util.Map;

public class PricingEngineService implements PricingEngine{

	PricingEngine nextEngine;
	
	public PricingEngineService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(Map<String, Object> params) {
		System.out.println("PricingEngineService calculate");
		if (getNextEngine() != null) {
			System.out.println("Next Engine is: "+getNextEngine());
			return getNextEngine().calculate(params);
		}
		return 0;
	}

	@Override
	public void setNextEngine(PricingEngine pe) {
		this.nextEngine = pe;
		
	}

	@Override
	public PricingEngine getNextEngine() {
		return nextEngine;
	}

}

/**
 * 
 */
package com.mavrick.common.json;

import com.mavrick.common.constants.JSONConstants;

/**
 * @author vrajeev
 *
 */
public class ResponseGenerationFactory {
	
	public static ResponseGenerator findGenerator(int type){
		
		ResponseGenerator generator= null;
		
		if (JSONConstants.CATALOG_RESPONSE_GEN == type) {
			generator = new CatalogResponseGenerator();
			
		} else if (JSONConstants.ORDER_RESPONSE_GEN == type) {
			generator= new OrderResponseGenerator();
		} else {
		}
		return generator;
		
	}
}

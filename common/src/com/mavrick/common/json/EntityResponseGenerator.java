package com.mavrick.common.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mavrick.common.exceptions.ResponseGenException;
import com.mavrick.common.json.annotation.Groups;
import com.mavrick.common.vo.MaverickVO;
import com.mavrick.persistance.entities.MavrickEntity;

public class EntityResponseGenerator {

	private ResponseGeneratorTools responseGenTools;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 
	 * @param entity
	 * @param strategy
	 * @param voName
	 * @return
	 * @throws ResponseGenException
	 */
	public List<MaverickVO> generateResponse(MavrickEntity entity,
			Groups strategy, String voName) throws ResponseGenException {

		List<MaverickVO> payLoadList = new ArrayList<MaverickVO>();
		String responseObjName = responseGenTools.getEntityMappings().get(
				voName);
		Object responseObj = responseGenTools
				.getObjectFromClassName(responseObjName);

		if (responseObj == null||!(responseObj instanceof MaverickVO))
			throw new ResponseGenException("No or Invalid  VO:" + voName);

		responseGenTools.populateBean(entity, (MaverickVO) responseObj,
				strategy);
		payLoadList.add((MaverickVO) responseObj);

		return payLoadList;
	}

	/**
	 * 
	 * @param entity
	 * @param strategy
	 * @param voName
	 * @return
	 * @throws ResponseGenException
	 */
	public List<MaverickVO> generateResponse(MavrickEntity[] entities,
			Groups strategy, String voName) throws ResponseGenException {
		List<MaverickVO> vOs = new ArrayList<MaverickVO>();
		if (entities == null || voName == null)
			throw new ResponseGenException("Null required parameters.");
		for (MavrickEntity enty : entities) {
			List temp = generateResponse(enty, strategy, voName);
			vOs.addAll(temp);
		}

		return vOs;
	}

	public ResponseGeneratorTools getResponseGenTools() {
		return responseGenTools;
	}

	public void setResponseGenTools(ResponseGeneratorTools responseGenTools) {
		this.responseGenTools = responseGenTools;
	}

}

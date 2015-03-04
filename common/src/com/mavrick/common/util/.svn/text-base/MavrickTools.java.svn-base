package com.mavrick.common.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * This component defines all util methods used across the app.
 * @author oracle
 *
 */
public class MavrickTools {

	
	private MessageSource messageSource;

	/**
	 * Returns the localized message for an error code matching the current locale of the request
	 * @param msgCode
	 * @return
	 */
	public String getLocalizedMessage(String msgCode,Object[] array){
		Locale currentLocale =  LocaleContextHolder.getLocale();
	    String localizedErrorMessage = messageSource.getMessage(msgCode,array, currentLocale);
	    return localizedErrorMessage;
		
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}	
	
}

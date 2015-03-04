package com.mavrick.common.validators;

import java.util.List;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mavrick.common.exceptions.MavrickException;

/**
 * This component handles all the errors and convert the same to a readable JSON output.
 * @author oracle
 *
 */
@ControllerAdvice
public class MavrickErrorHandler {

	
	private MessageSource messageSource;
	//Constant error code for error response.
	static final String ERROR_STATUS_CODE="500";
	//Constant error message for error response.
	static final String ERROR_STATUS_MESSAGE="There were errors while executing your request";

   //Message resource is autowired during instantiation of this component.
	@Autowired
    public MavrickErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    
    /**
     * All the bind exceptions which are handled by this method.
     * @param ex
     * @return
     */
   /* @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }
   */
    /**
     * All exceptions of type MavrickException are handled by this method.
     * @param mExp
     * @return
     */
    @ExceptionHandler(MavrickException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ValidationErrorDTO processAppError(MavrickException mExp){
    	ValidationErrorDTO dto=new ValidationErrorDTO();
    	dto.addFieldError(mExp.getErrorCode(), mExp.getErrorMessage());
    	dto.setErrors(mExp.getErrors());
    	dto.setStatusCode(ERROR_STATUS_CODE);
    	dto.setStatusMessage(ERROR_STATUS_MESSAGE);
    	return dto;
    }
    
    /**
     * This method process the error by looking up the resource bundle and populating the DTO attributes
     * @param fieldErrors
     * @return
     */
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        dto.setStatusCode(ERROR_STATUS_CODE);
        dto.setStatusMessage(ERROR_STATUS_MESSAGE);
        return dto;
    }
    /**
     * This method retrieves the error messaged from the resource bundle.
     * @param fieldError
     * @return
     */
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}

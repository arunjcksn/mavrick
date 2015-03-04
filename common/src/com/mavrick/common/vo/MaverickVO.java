/**
 * 
 */
package com.mavrick.common.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author vrajeev
 *
 */
@JsonSerialize(include=Inclusion.NON_NULL)
public interface MaverickVO  extends Serializable{

}

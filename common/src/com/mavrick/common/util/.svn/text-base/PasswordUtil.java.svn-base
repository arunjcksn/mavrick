package com.mavrick.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service
public class PasswordUtil extends MavrickTools{
	
	Logger logger=Logger.getLogger(getClass());
	
	private String hashingAlgorithm;
	
	
	/**
	 * 
	 * @param thepwd
	 * @param salt
	 * @return
	 */
	public String saltPassword(String thepwd,String salt){
		String computedSalt = null;
		String zeroString = "00000000";
		if(salt !=null){
			if (salt.length() > 8){
				computedSalt = salt.substring(salt.length() - 8);
			}
			else{
				computedSalt = zeroString.substring(8-salt.length()) + salt;
			}
		}
		return thepwd + computedSalt;
	}
	
	/**
	 * 
	 * @return
	 */
	public String createSalt(){
		String randomNum = "";
		
		//int seedByteCount = 10;  byte to generate seed
		try {
			
		    SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			
		    //byte seed[] = prng.generateSeed(seedByteCount); // generated seed
		   
		    //seed set with system time
		    prng.setSeed(System.currentTimeMillis());
		    
		    //get random Number
		    randomNum = new Integer(prng.nextInt()).toString();
			int rNum = Integer.parseInt(randomNum);
			if(rNum < 0){ // if it is negative, then make it positive
				rNum = rNum * -1;
				randomNum = "" + rNum;
			}
			int len = randomNum.length();
			
			//if it is not of length 10
			if(len != 10){ 
				
				//	if it is less than 10, append with that many random integers
				if(len < 10){ 
					int cnt = 10 - len;
					for(int iter = 0;iter < cnt;iter++){
						String ran = new Integer(prng.nextInt(9) ).toString();
						randomNum = randomNum + ran;
					}
				}else{ //if it is more than 10, get the first 10 digits
					randomNum = randomNum.substring(0,10);
				}
			}
	    	
			
		}
		catch ( NoSuchAlgorithmException ee){
			
				logger.error("error occured"+ee);
			
		}
		return randomNum;
	}
	
	public String getHashedString(String input) {
		String hashedString=null;
         
        if(null == input) return null;
         
        try {
             logger.info("Hashing algorithm :"+hashingAlgorithm);
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance(hashingAlgorithm);
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        hashedString = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
    	
		return hashedString;
	}

	public String generateRandomPasswd() {
		return Long.toHexString(Double.doubleToLongBits(Math.random()));
	}
	public String getHashingAlgorithm() {
		return hashingAlgorithm;
	}

	public void setHashingAlgorithm(String hashingAlgorithm) {
		this.hashingAlgorithm = hashingAlgorithm;
	}
	

}

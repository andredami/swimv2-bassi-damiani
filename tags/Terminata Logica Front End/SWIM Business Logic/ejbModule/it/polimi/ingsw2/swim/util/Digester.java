/**
 * 
 */
package it.polimi.ingsw2.swim.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * @author Administrator
 *
 */
public abstract class Digester {

	private static MessageDigest digestProvider = null;

	private static final String securityAlg = "AES";
	
	static{
		try {
			digestProvider = MessageDigest.getInstance(securityAlg);
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger("SECURITY").log(java.util.logging.Level.WARNING, "The " + securityAlg + " algorithm is not available. All passwords will be stored in plain-text!");
			e.printStackTrace();
		}
	}
	
	public static synchronized String digest(String s){
		if(digestProvider != null){
			byte[] digested = digestProvider.digest(s.getBytes());
			return digested.toString();
		} else {
			Logger.getLogger("SECURITY").log(java.util.logging.Level.WARNING, "The " + securityAlg + " algorithm is not available. All passwords will be stored in plain-text!");
			return s;
		}
	}
}

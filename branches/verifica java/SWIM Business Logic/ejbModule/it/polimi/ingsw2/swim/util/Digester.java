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

	private static final String securityAlg = "MD5";
	
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
			byte[] plainText = s.getBytes();
			digestProvider.reset();
			digestProvider.update(plainText);
			byte[] encodedPassword = digestProvider.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < encodedPassword.length; i++) {
				if ((encodedPassword[i] & 0xff) < 0x10) {
					sb.append("0");
				}
				sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
			}
			return sb.toString();
		} else {
			Logger.getLogger("SECURITY").log(java.util.logging.Level.WARNING, "The " + securityAlg + " algorithm is not available. All passwords will be stored in plain-text!");
			return s;
		}
	}
}

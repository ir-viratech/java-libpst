package com.pff;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumUtils {
	private static ChecksumUtils instance = null;
	
	protected ChecksumUtils() {
	}

	public static ChecksumUtils getInstance() {
		if (instance == null) {
			instance = new ChecksumUtils();
		}
		return instance;
	}

	public static String SHA1Convert(byte[] b) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
        byte[] result = mDigest.digest(b);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
	
	public static String SHA1Convert(InputStream inStream){
	    String checksum = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	      
	        byte[] buffer = new byte[8192];
	        int numOfBytesRead;
	        while( (numOfBytesRead = inStream.read(buffer)) > 0){
	            md.update(buffer, 0, numOfBytesRead);
	        }
	        byte[] hash = md.digest();
	        checksum = new BigInteger(1, hash).toString(16);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	      
	   return checksum;
	}
}
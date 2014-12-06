package ANNdroid.src;

import java.io.IOException;
import java.lang.Character;
import java.util.LinkedList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities{

	public static String hashPassword(String passwordToHash){

		String generatedPassword = null;

		try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(passwordToHash.getBytes());
				
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();
				
				for(int i=0; i< bytes.length ;i++){
						sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				generatedPassword = sb.toString();
		}catch(NoSuchAlgorithmException e){	e.printStackTrace();	}
		return generatedPassword;
	}

	public static User authenticate(String username){
	    for(User u : ANNdroid.simulator.userList){
	      if(u.getUsername().equals(username)) return u;
	    }
	    return null;
	}

	public static boolean isAlpha(String s){
		char[] chars = s.toCharArray();

		for(char c: chars){
			if(!Character.isLetter(c) && c != ' ') return false;
		}
		return true;
	}

	public static boolean isAlphaNumeric(String s){
		char[] chars = s.toCharArray();

		for(char c: chars){
			if(!Character.isLetter(c) && !Character.isDigit(c)) return false;
		}
		return true;
	}

	public static int exists(String username){
		for(User u : ANNdroid.simulator.userList){
			if(u.getUsername().equals(username)) return ANNdroid.simulator.userList.indexOf(u);
		}
		return -1;
	}

	public static int subjectExists(String subject){
		for(Subject s : ANNdroid.simulator.subjectList){
			if(s.getName().equalsIgnoreCase(subject)) return ANNdroid.simulator.subjectList.indexOf(s);
		}
		return -1;
	}
}
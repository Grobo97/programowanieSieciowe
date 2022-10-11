package lab1;

import java.util.ArrayList;
import java.util.List;

public class KeyProtocol {
	List<String> keys = new ArrayList<String>();
    final String KEY_IN_SET = "key_in-set";
    final String KEY_IN_GET = "key_in-get";
    final String PASSWORD = "tajneHaslo";
    final String KEY_OUT = "key_out";
    final String CODE_YES = "yes";
    final String CODE_NO = "no";
    final String CODE_ERROR = "error";
    
    public String encodeMessage(String message) {
    	String outputMessage = "";
    	
    	if (message.contains(KEY_IN_GET)) {
    		String key = getKey(message, false);
    		if (key.isBlank()) {
    			outputMessage = respond(key, CODE_ERROR);
    		}else if (keys.contains(key)) {
    			outputMessage = respond(key, CODE_YES);
    		}else {
    			outputMessage = respond(key, CODE_NO);
    		}
    	}else if (message.contains(KEY_IN_SET)) {
    		String pwd = message.subSequence(message.indexOf("{")+1, message.indexOf(":")).toString().trim();;
    		String key = getKey(message, true);
    		if (!pwd.equals(PASSWORD)) {
    			return respond(key, CODE_ERROR); 
    		}
    		if (keys.contains(key)) {
    			return respond(key, CODE_ERROR); 
    		}
    		keys.add(key);
    		outputMessage = respond(key, CODE_YES);
    		
    	}else {
    		outputMessage = respond(null, CODE_ERROR);
    	}
    	return outputMessage;
    }
    
    private String getKey(String message, Boolean isSet) {
    	String result = "";
    	try {
    		if (isSet) {
    			result = message.subSequence(message.indexOf(":")+1, message.indexOf("}")).toString().trim();
    		}else {
    			result = message.subSequence(message.indexOf("{")+1, message.indexOf("}")).toString().trim();
    		}
    	}catch (Exception e) {
    		return result;
    	}
    	return result;
    }
    
    private String respond(String key, String respondCode) {
    	String output = KEY_OUT + "- {" + key + " : " + respondCode + "}"; 
    	return output;
    }
}

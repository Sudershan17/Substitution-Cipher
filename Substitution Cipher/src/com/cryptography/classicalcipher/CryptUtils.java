package com.cryptography.classicalcipher;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class CryptUtils {
	public static String sortString(String k) {
        char tempArray[] = k.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
	}
	
	public static boolean duplicates(String k) {
	    for (int i = 0; i < k.length(); i++) {
	        for (int j = i + 1; j < k.length(); j++) {
	            if (k.charAt(i) == k.charAt(j)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static String randomKey() {
        StringBuilder CHARS = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder randKey = new StringBuilder();
        Random rnd = new Random();
        while (randKey.length() < 26) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            randKey.append(CHARS.charAt(index));
            CHARS.deleteCharAt(index);
        }
        return randKey.toString();
	}
	
	public static String getListString(List<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}

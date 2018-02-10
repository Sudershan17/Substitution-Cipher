package com.cryptography.classicalcipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class FrequencyAnalysis {

	private static final ArrayList<Character> LETTERS = new ArrayList<Character>(Arrays.asList('e', 't', 'a', 'o', 'i',
			'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'));

//	private static final String ENGLETTERS = "etaoinshrdlcumwfgypbvkjxqz";
	private static LinkedHashMap<Character, Integer> alphamap = null;

	public static String guessKey(String cipherText) {
		alphamap = sortHashMapByValues(countFrequency(cipherText));
		System.out.println(alphamap);
		List<Character> keys = new ArrayList<>(alphamap.keySet());
	    Map<Character,Character> map = new LinkedHashMap<Character,Character>();  // ordered
	    if(LETTERS.size() == keys.size()) {
		    for (int i=0; i<LETTERS.size(); i++) {
		      map.put(LETTERS.get(i), keys.get(i));
		    }
	    } else {
	    	System.out.println("Not enough cipher-text!");
	    }
	    Map<Character,Character> treeMap = new TreeMap<Character,Character>(map);
	    System.out.println(treeMap);
		return CryptUtils.getListString(new ArrayList<>(treeMap.values()));
	}

	private static HashMap<Character, Integer> countFrequency(String cipher) {
		cipher=cipher.toUpperCase();
		HashMap<Character, Integer> alphabetmap = new HashMap<Character, Integer>();
		for (Character ch : cipher.toCharArray()) {
			if((ch>='A' && ch<='Z')) {
				int count = alphabetmap.containsKey(ch) ? alphabetmap.get(ch) : 0;
				alphabetmap.put(ch, count + 1);
			}
		}
		return alphabetmap;
	}

	public static LinkedHashMap<Character, Integer> sortHashMapByValues(HashMap<Character, Integer> passedMap) {
		List<Character> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Integer> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues, Collections.reverseOrder());

		LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Integer val = valueIt.next();
			Iterator<Character> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Character key = keyIt.next();
				Integer comp1 = passedMap.get(key);
				Integer comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}
}

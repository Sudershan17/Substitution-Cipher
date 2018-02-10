package com.cryptography.classicalcipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

class Crypt {
	public static final String ring = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static final String defaultPath = "C:\\Users\\Susa\\Documents\\6110Demo\\";

	private static String keyInput() {
		String key = "";
		try {
			key = br.readLine().toString().toUpperCase();
			if(key.length()==26) { //key length should be 26
				if(CryptUtils.duplicates(key)) {
					System.out.println("\nDuplicate characters are found in the given key. Please try again!"); // no duplicate characters
					keyInput();
				} else {
					if(!ring.equals(CryptUtils.sortString(key))) {
						System.out.println("\nThe key should contain only Alphabets. Please try again!"); // no special characters
						keyInput();
					}
					return key;
				}
			} else {
				System.out.println("\nKey length should be 26. Please try again!");
				keyInput();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getKeyEnc() {
		String key = "";
		try {
			System.out.println("\n1 - Generate a random key \t 2 - Enter the key");
			int opt = Integer.parseInt(br.readLine());
			switch(opt) {
				case 1 : key = CryptUtils.randomKey();
				System.out.println(key);
				break;
				case 2 : key = keyInput();
				break;
				default : System.out.println("Invalid option!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	private static String getKeyDec(String cipherText) {
		String key = "";
		try {
			System.out.println("\n1 - Enter the key \t 2 - Recover key");
			int opt = Integer.parseInt(br.readLine());
			switch(opt) {
				case 1 : key = keyInput();
				break;
				case 2 : key = FrequencyAnalysis.guessKey(cipherText);
				break;
				default : System.out.println("Invalid option!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public static void encipher() {
		String key = getKeyEnc();
		String plaintext="";
		if(key != null) {
			System.out.println("\nEnter the plain-text file to encrypt:");
			try {
				String filename = br.readLine();
				String plainfile = new String(Files.readAllBytes(Paths.get(defaultPath + filename + ".txt"))).toUpperCase(); // read plain-text into a string
				for (char ch: plainfile.toCharArray()) {
					if((ch>='A' && ch<='Z')) {
						plaintext += ch;
					}
				}
				System.out.println("\nEncrypting the file " + defaultPath + filename + ".txt ....");
				System.out.println("Ring : " + ring);
				System.out.println("Key  : " + key);
				System.out.println(plaintext);
				String ciphertext = substitute(plaintext, key, 1); // 1 - encrypt
				System.out.println(ciphertext);
				System.out.println("\n*Note: Cipher-text will be written to " + defaultPath + "decrypt.txt*");
				Files.write(Paths.get(defaultPath+"decrypt.txt"), ciphertext.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void decipher() {
		try {
			String cipherfile = new String(Files.readAllBytes(Paths.get(defaultPath+"decrypt.txt"))).toUpperCase(); // read plain-text into a string
			String key = getKeyDec(cipherfile);
			System.out.println("\nDecrypting the file " + defaultPath + "decrypt.txt ....");
			System.out.println("Ring : " + ring);
			System.out.println("Key  : " + key);
			System.out.println(cipherfile);
			String plaintext = substitute(cipherfile, key, 0); // 0 - decrypt
			System.out.println(plaintext);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String substitute(String text, String key, int operation) {
		String space = ring, ciphertext = "";
		int index = -1;
		if(operation == 0) {
			String temp = key;
			key = space;
			space = temp;
		}
		for (char ch: text.toCharArray()) {
			index = space.indexOf(ch);
			if(index != -1) {
				ciphertext += key.charAt(index);
			}
		}
		return ciphertext;
	}
}

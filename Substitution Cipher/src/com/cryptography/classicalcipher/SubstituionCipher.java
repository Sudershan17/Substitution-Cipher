package com.cryptography.classicalcipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubstituionCipher {
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int choice = 0;
		System.out.println("INSE 6110 - [ SUBSTITUTION CIPHER ]");
		while(true) {
			System.out.println("\nSelect an operation\n1 - encrypt \t 2 - decrypt \t 3 - exit");
			choice = Integer.parseInt(br.readLine());
			switch(choice) {
				case 1: Crypt.encipher();
				break;
				case 2: Crypt.decipher();
				break;
				case 3: System.out.println("Received exit() signal!");
					System.exit(0);
				default: System.out.println("Invalid operations!");
			}
		}
	}
}

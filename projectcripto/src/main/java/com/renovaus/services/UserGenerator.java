package com.renovaus.services;

import java.util.Random;

public class UserGenerator {
	
	
	public static String getUser(String first, String last) {
		String username="";
		// Create random generator
        Random generator = new Random();
        int randomNumber = generator.nextInt(90) + 10;
        
        // Generate username
        username = first.charAt(0) + last.substring(0, 3) + randomNumber;
        System.out.println(username);
		
		return username;
	}

}

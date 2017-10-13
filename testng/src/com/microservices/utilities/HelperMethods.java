package com.microservices.utilities;

import java.util.Random;

public class HelperMethods {

	
	 public static String generateRandomText(int length){
		 String alphabet = 
		         new String("abcdefghijklmnopqrstuvwxyz");
		 int n = alphabet.length(); //10

		 String result = new String(); 
		 Random r = new Random(); //11

		 for (int i=0; i<length; i++) //12
		     result = result + alphabet.charAt(r.nextInt(n)); //13

		 return result;
		 }
	 
	 public static String generatePhoneNumber(int length){
		 String alphabet = 
		         new String("0123456789"); 
		 int n = alphabet.length(); //10

		 String result = new String(); 
		 Random r = new Random(); //11

		 for (int i=0; i<length; i++) //12
		     result = result + alphabet.charAt(r.nextInt(n)); //13

		 return result;
		 }
}

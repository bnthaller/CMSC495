package group2.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//beginning of class
public class Utility {
	
	private static final String SALT_VALUE = "SALT";
	
	public  final static String saltPassword(String password) {
		return password += SALT_VALUE;
	}
	
	public final static String hashPassword(String password) throws NoSuchAlgorithmException {
		password = saltPassword(password);
		
		MessageDigest md;
		
		md = MessageDigest.getInstance("SHA-256");
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
		return new String(hashedPassword, StandardCharsets.UTF_8);
	}

	public final static boolean isUserValid(String username, String password) {
		return usernameValidation(username) && passwordValidation(password);
	}

	// This method is to validate username character requirements from input field
	// at login
	public final static boolean usernameValidation(String username) {
		boolean validationOutcome = false;
		int charCount = 0;

		// ICD call for username to have 1-30 Alphanumeric characters allowed
		// Counts each character except space
		for (int i = 0; i < username.length(); i++) {
			if (username.charAt(i) != ' ')
				charCount++;
		}

		if (charCount < 1 || charCount > 30) {
			validationOutcome = false;
		} else {
			boolean characterCheck = alphanumericCheck(username);
			if (characterCheck == true) {
				validationOutcome = true;
			} else {
				validationOutcome = false;
			}

		}

		return validationOutcome;
	}

	// this method ensures ICD call for username to only have Alphanumeric
	// characters allowed
	private static boolean alphanumericCheck(String username) {
		// regex pattern checker will be utilized for this task
		// List<> is utilized for iteration of username
		boolean validationCheck = false;
		final String regex = "^[a-zA-Z0-9]+$";

		// Adding username to List for iteration with pattern matcher
		List<String> checkName = new ArrayList<String>();
		checkName.add(username);
		Pattern pattern = Pattern.compile(regex);

		for (String name : checkName) {
			Matcher matcher = pattern.matcher(name);
			// System.out.println(matcher.matches());
			if (matcher.matches() == false) {
				validationCheck = false;
			} else {
				validationCheck = true;
			}
		}

		return validationCheck;
	}

	// This method is to validate password character requirements from input field
	// at login
	public static boolean passwordValidation(String password) {
		boolean validationOutcome = false;
		int charCount = 0;

		// ICD call for username to have 1-30 Alphanumeric characters allowed
		// Counts each character except space
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) != ' ')
				charCount++;
		}

		if (charCount < 1 || charCount > 30) {
			validationOutcome = false;
		} else {
			boolean characterCheck = alphanumericSpecialCharacterCheck(password);
			System.out.println("characterCheck for password: " + characterCheck);
			if (characterCheck == true) {
				validationOutcome = true;
			} else {
				validationOutcome = false;
			}

		}

		return validationOutcome;
	}

	// this method is ensures CDI 5-255 alphanumeric or special characters is met
	private static boolean alphanumericSpecialCharacterCheck(String password) {
		// regex pattern checker will be utilized for this task
		// List<> is utilized for iteration of username
		boolean validationCheck = false;

		// listed for non approved characters
		final String regex = "^[a-zA-Z0-9!@$()\\-`.+,]*$";
		// regex
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);

		System.out.println("after matcher " + matcher.matches());
		// if pattern is found then we return false for found invalid character(s)
		if (matcher.matches() == true) {
			System.out.println("in if of matcher: " + matcher.matches());
			validationCheck = true;//
		} else {
			System.out.println("in if else of matcher: " + matcher.matches());
			validationCheck = false;
		}

		return validationCheck;
	}

}// end of class
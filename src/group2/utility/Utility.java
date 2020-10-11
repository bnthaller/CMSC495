package group2.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import group2.model.UserException;

//beginning of class
public class Utility {
	
	private static final String SALT_VALUE = "SALT";
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws UserException
	 * 
	 * Salts and Hashes a plain text password
	 * If it is unable to salt and hash the password, it will throw a UserException
	 */
	public final static String preparePassword(String password) throws UserException {
		try {
			return hashPassword(saltPassword(password));
		} catch (UserException userException) {
			throw userException;
		}
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * 
	 * Salts a password by appending the salt value to the end of the password
	 */
	private final static String saltPassword(String password) {
//		System.out.println("password: " + password);
//		System.out.println("salted: " + password + SALT_VALUE);
		return password += SALT_VALUE;
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws UserException
	 * 
	 * Hashes a password
	 * If unable to hash the password, a UserException is thrown
	 */
	private final static String hashPassword(String password) throws UserException {		
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new UserException("Unable to hash users password.");
		}
		
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//		System.out.println("hashed: " + new String(hashedPassword, StandardCharsets.UTF_8));
		return new String(hashedPassword, StandardCharsets.UTF_8);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @throws UserException
	 * 
	 * Validates that a username and password match the requirements
	 * If the username is invalid a UserException with invalid username is thrown
	 * If the password is invalid a UserException with invalid password is thrown
	 */
	public final static void isUserValid(String username, String password) throws UserException {
		if (!isUsernameValid(username)) {
			throw new UserException("The username must be 1-30 alphanumeric characters.");
		}
		
		if (!isPasswordValid(password)) {
			throw new UserException("The password must be 5-255 alphanumeric/special characters.");
		}
	}
	
	// This method is to validate username character requirements from input field
	// at login
	public final static boolean isUsernameValid(String username) {
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
	
	// This method is to validate username character requirements from input field
	// at login
	public final static boolean isPantryNameValid(String name) {
		boolean validationOutcome = false;
		int charCount = 0;

		// ICD call for username to have 1-30 Alphanumeric characters allowed
		// Counts each character except space
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != ' ')
				charCount++;
		}

		if (charCount < 1 || charCount > 255) {
			System.out.println("invalid char count: " + charCount);
			validationOutcome = false;
		} else {
			boolean characterCheck = alphanumericSpecialCharacterCheckSpacesAllowed(name);
			if (characterCheck == true) {
				validationOutcome = true;
			} else {
				validationOutcome = false;
				System.out.println("invalid alphanumeric/special chars");
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
	public static boolean isPasswordValid(String password) {
		boolean validationOutcome = false;
		int charCount = 0;

		// ICD call for username to have 5-255 Alphanumeric characters allowed
		// Counts each character except space
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) != ' ')
				charCount++;
		}

		if (charCount < 5 || charCount > 255) {
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
	// this method is ensures CDI 5-255 alphanumeric or special characters is met
	private static boolean alphanumericSpecialCharacterCheckSpacesAllowed(String password) {
		// regex pattern checker will be utilized for this task
		// List<> is utilized for iteration of username
		boolean validationCheck = false;

		// listed for non approved characters
		final String regex = "^[a-zA-Z0-9!@$()\\-`.+, ]*$";
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

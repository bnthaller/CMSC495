import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//beginning of class
public class UtilityClass {

	String passwordHashed;

	// this method is for new user registration
	public static boolean createUser(String username, String password) {
		boolean newUserCreated = false;
		//process is little different then existing user
		if(newUserCreated == true) {
			//need to apply salt and hash properly and return result of hash for db storage
		}
		
		return newUserCreated;
	}

	// this method is for checking user credentials against stored in db
	public final boolean userCredentialValidation(String username, String password, String dbPassword) {
		boolean validationOutcome = false;
		validationOutcome = usernameValidation(username);
		System.out.println("username valid: " + validationOutcome);
		if(validationOutcome == true) {
			validationOutcome = passwordValidation(password);
			System.out.println("password valid: " + validationOutcome);
		}
		if(validationOutcome == true) {
			//apply salt to user provided password and compare against
			passwordSalt(password);
		}
		

		return validationOutcome;

	}

	// This method takes the password and adds the salt before running the hash
	private final void passwordSalt(String password) {
		
		//digest for hash computation for SHA-256
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-256");//set algorithm
			
			//generate salt
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			
			//pass salt to digest
			md.update(salt);
			
			//make the salted hash
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword)
                sb.append(String.format("%02x", b));

            System.out.println(sb);
            passwordHashed = sb.toString();
			
		} catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		

		

		
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
		//final String regex = "[.\\\\'\\\\\"\\\\?\\\\/\\\\,\\\\.\\\\`\\\\~\\\\*\\\\%\\\\\\\\|+\\s]";//black listed for non approved characters
		final String regex = "^[a-zA-Z0-9!@$()\\-`.+,]*$";
		//regex		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		
		
			System.out.println("after matcher " + matcher.matches());
			//if pattern is found then we return false for found invalid character(s)
			if (matcher.matches() == true) {
				System.out.println("in if of matcher: "+ matcher.matches());
				validationCheck = true;//
			} else {
				System.out.println("in if else of matcher: "+ matcher.matches());
				validationCheck = false;
			}

		return validationCheck;
	}

}// end of class

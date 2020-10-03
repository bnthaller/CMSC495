package group2.model;

public class UserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6934723938719702344L;

	public UserException(String errorMessage) {
        super(errorMessage);
    }
}

package utils;

public class PatternUtils {

	public static boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return email.matches(emailRegex);
	}

	public static boolean isStrongPassword(String password) {
		if (password == null || password.isEmpty()) {
			return false;
		}

		// Check length
		if (password.length() < 8) {
			return false;
		}

		// Check for at least one special character
		if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
			return false;
		}

		// Check for at least one digit
		if (!password.matches(".*\\d.*")) {
			return false;
		}

		// Check for at least one uppercase letter
		if (!password.matches(".*[A-Z].*")) {
			return false;
		}

		// Check for at least one lowercase letter
		if (!password.matches(".*[a-z].*")) {
			return false;
		}

		return true;
	}

}

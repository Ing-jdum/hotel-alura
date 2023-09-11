package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.persistence.EntityManager;

import controller.UserDao;
import model.Salt;
import model.User;

public class SecurityUtils {
	private static final int SALT_LENGTH = 16; // Length of salt in bytes

	private SecurityUtils() {
	};

	// Generate a random salt
	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		random.nextBytes(salt);
		return salt;
	}

	// Hash a password using salt and SHA-256
	public static String hashPasswordWithSalt(String password, Salt salt) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(salt.getSaltValue());
			byte[] hashedBytes = messageDigest.digest(password.getBytes());

			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : hashedBytes) {
				stringBuilder.append(String.format("%02x", b));
			}

			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password: " + e.getMessage());
		}
	}

	public static boolean authenticateUser(User user, String password) {
		Salt salt = user.getSalt();
		String hashedPassword = hashPasswordWithSalt(password, salt);
		return hashedPassword.equals(user.getPassword());
	}
}

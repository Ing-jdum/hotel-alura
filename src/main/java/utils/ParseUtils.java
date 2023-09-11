package utils;

public class ParseUtils {
	public static Long tryParseLong(String searchTerm) {
		Long id = null;
		try {
			 id = Long.parseLong(searchTerm);
		} catch(NumberFormatException e) {}
		return id;
	}
}

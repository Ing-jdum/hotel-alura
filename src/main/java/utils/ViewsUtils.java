package utils;

import java.awt.Panel;

import javax.swing.JOptionPane;

public class ViewsUtils {
	
	private ViewsUtils() {};
	
	public static void confirmExit(Panel panel) {
		int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to close the program?",
				"Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}

package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserDao;
import model.User;
import utils.JPAUtils;
import utils.PatternUtils;
import utils.SecurityUtils;
import utils.ViewsUtils;

public class ChangePassword extends JFrame {

	private static final String PASSWORD_SYMBOLS = "********";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtContrasena1;
	private JPasswordField txtContrasena;
	int xMouse, yMouse;
	private JLabel labelExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ChangePassword frame = new ChangePassword();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 788, 527);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(484, 0, 304, 527);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel btnexit = ViewsUtils.createExitButton(panel_1, 250, 0, 53, 36);
		panel_1.add(btnexit);

		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		panel_1.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(ChangePassword.class.getResource("/imagenes/img-hotel-login-.png")));

		txtContrasena1 = new JPasswordField();
		txtContrasena1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena1.getPassword()).equals(PASSWORD_SYMBOLS)) {
					txtContrasena1.setText("");
					txtContrasena1.setForeground(Color.black);
				}
				if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
					txtContrasena.setText(PASSWORD_SYMBOLS);
					txtContrasena.setForeground(Color.gray);
				}
			}
		});
		txtContrasena1.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena1.setText(PASSWORD_SYMBOLS);
		txtContrasena1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena1.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena1.setBounds(65, 256, 324, 32);
		panel.add(txtContrasena1);
		txtContrasena1.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, 292, 324, 2);
		panel.add(separator);

		JLabel labelTitulo = new JLabel("CAMBIAR CONTRASENA");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(65, 149, 324, 26);
		panel.add(labelTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.textHighlight);
		separator_1.setBounds(65, 393, 324, 2);
		panel.add(separator_1);

		txtContrasena = new JPasswordField();
		txtContrasena.setText(PASSWORD_SYMBOLS);
		txtContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena.getPassword()).equals(PASSWORD_SYMBOLS)) {
					txtContrasena.setText("");
					txtContrasena.setForeground(Color.black);
				}
				if (String.valueOf(txtContrasena1.getPassword()).isEmpty()) {
					txtContrasena1.setText(PASSWORD_SYMBOLS);
					txtContrasena1.setForeground(Color.gray);
				}
			}
		});
		txtContrasena.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(65, 353, 324, 32);
		panel.add(txtContrasena);

		JLabel LabelUsuario = new JLabel("CONTRASENA");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 219, 175, 26);
		panel.add(LabelUsuario);

		JLabel lblContrasea = new JLabel("REPETIR CONTRASENA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 316, 274, 26);
		panel.add(lblContrasea);

		JPanel btnChangePswd = new JPanel();
		btnChangePswd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChangePswd.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnChangePswd.setBackground(SystemColor.textHighlight);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String pswd0 = String.valueOf(txtContrasena.getPassword());
				String pswd1 = String.valueOf(txtContrasena1.getPassword());
				if (pswd0.equals(pswd1) && PatternUtils.isStrongPassword(pswd0)) {
					UserDao userDao = new UserDao(JPAUtils.getEntityManager());
					Optional<User> optionalUser = userDao.findById(Login.getCurrentUser());
					if (optionalUser.isEmpty())
						return;
					User user = optionalUser.get();
					user.setPassword(pswd0);
					userDao.update(user);
					Exito exito = new Exito();
					exito.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, // Parent component, can be 'null' if not needed
							"Contrasenas no concuerdan o muy debiles", // Message to display
							"Error en contrasenas", // Title of the dialog box
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnChangePswd.setBackground(SystemColor.textHighlight);
		btnChangePswd.setBounds(65, 461, 122, 44);
		panel.add(btnChangePswd);
		btnChangePswd.setLayout(null);
		btnChangePswd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblNewLabel = new JLabel("ENTRAR");
		lblNewLabel.setBounds(0, 0, 122, 44);
		btnChangePswd.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(ChangePassword.class.getResource("/imagenes/lOGO-50PX.png")));
		lblNewLabel_1.setBounds(65, 65, 48, 59);
		panel.add(lblNewLabel_1);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setBackground(SystemColor.window);
		header.setBounds(0, 0, 784, 36);
		panel.add(header);
		header.setLayout(null);

	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}// GEN-LAST:event_headerMousePressed

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}

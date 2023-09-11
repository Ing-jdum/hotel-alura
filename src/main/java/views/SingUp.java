package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.UserDao;
import model.User;
import utils.JPAUtils;
import utils.PatternUtils;

public class SingUp extends JFrame {

	private static final String TYPOGRAPHY = "Roboto";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtCorreo;
	private JPasswordField txtContrasena;
	private boolean isConditionsAccepted = Boolean.FALSE;
	int xMouse, yMouse;
	private JLabel labelExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SingUp frame = new SingUp();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SingUp() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setBounds(65, 293, 324, 2);
		panel.add(separator_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(484, 0, 304, 527);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		panel_1.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-hotel-login-.png")));

		JPanel btnexit = new JPanel();
		btnexit.setBounds(251, 0, 53, 36);
		panel_1.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setForeground(SystemColor.text);
		labelExit.setFont(new Font(TYPOGRAPHY, Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);

		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
					txtUsuario.setText("");
					txtUsuario.setForeground(Color.black);
				}
				if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
					txtContrasena.setText("********");
					txtContrasena.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setFont(new Font(TYPOGRAPHY, Font.PLAIN, 16));
		txtUsuario.setText("Ingrese su nombre de usuario");
		txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsuario.setForeground(SystemColor.activeCaptionBorder);
		txtUsuario.setBounds(65, 162, 324, 32);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		// CORREO
		txtCorreo = new JTextField();
		txtCorreo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtCorreo.getText().equals("Ingrese su correo")) {
					txtCorreo.setText("");
					txtCorreo.setForeground(Color.black);
				}
			}
		});
		txtCorreo.setFont(new Font(TYPOGRAPHY, Font.PLAIN, 16));
		txtCorreo.setText("Ingrese su correo");
		txtCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCorreo.setForeground(SystemColor.activeCaptionBorder);
		txtCorreo.setBounds(65, 256, 324, 32);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);

		// FIN CORREO
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, 204, 324, 2);
		panel.add(separator);

		JLabel labelTitulo = new JLabel("Registrarse");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(156, 79, 202, 26);
		panel.add(labelTitulo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.textHighlight);
		separator_1.setBounds(65, 384, 324, 2);
		panel.add(separator_1);

		txtContrasena = new JPasswordField();
		txtContrasena.setText("********");
		txtContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
					txtContrasena.setText("");
					txtContrasena.setForeground(Color.black);
				}
				if (txtUsuario.getText().isEmpty()) {
					txtUsuario.setText("Ingrese su nombre de usuario");
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtContrasena.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena.setFont(new Font(TYPOGRAPHY, Font.PLAIN, 16));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(65, 344, 324, 32);
		panel.add(txtContrasena);

		JLabel LabelUsuario = new JLabel("CORREO");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 218, 107, 26);
		panel.add(LabelUsuario);

		JLabel LabelCorreo = new JLabel("USUARIO");
		LabelCorreo.setForeground(SystemColor.textInactiveText);
		LabelCorreo.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelCorreo.setBounds(65, 136, 107, 26);
		panel.add(LabelCorreo);

		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 306, 140, 26);
		panel.add(lblContrasea);

		JPanel btnSingUp = new JPanel();
		btnSingUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSingUp.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSingUp.setBackground(SystemColor.textHighlight);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				singUpButton();
			}
		});
		btnSingUp.setBackground(SystemColor.textHighlight);
		btnSingUp.setBounds(65, 431, 122, 44);
		panel.add(btnSingUp);
		btnSingUp.setLayout(null);
		btnSingUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblNewLabel = new JLabel("ENTRAR");
		lblNewLabel.setBounds(0, 0, 122, 44);
		btnSingUp.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font(TYPOGRAPHY, Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(SingUp.class.getResource("/imagenes/lOGO-50PX.png")));
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

		JCheckBox chckbxNewCheckBox = new JCheckBox("Aceptar terminos y condiciones ");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(65, 403, 324, 20);
		panel.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isConditionsAccepted = !isConditionsAccepted; // Toggle the variable
            }
        });
	}

	private void singUpButton() {
		String userName = txtUsuario.getText();
		String email = txtCorreo.getText();
		EntityManager em = JPAUtils.getEntityManager();
		UserDao userDao = new UserDao(em);
		boolean userExists = userDao.userExists(email, userName);
		String password = new String(txtContrasena.getPassword());
		boolean passwordComply = PatternUtils.isStrongPassword(password);
		boolean emailComply = PatternUtils.isValidEmail(email);

		if (!passwordComply) {
			JOptionPane.showMessageDialog(this,
					"Contraseña no válida por favor agregue por lo menos 8 digitos, una letra mayuscula, una letra miniscula, un caracter especial y un numero");
		} else if (!emailComply) {
			JOptionPane.showMessageDialog(this, "Email no valido");
		} else if (userExists) {
			JOptionPane.showMessageDialog(this, "usuario o correo ya en existencia");
		} else if (!isConditionsAccepted) {
			JOptionPane.showMessageDialog(this, "Debe aceptar los terminos y condiciones");
		} else {
			User user = new User();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(password);
			userDao.createUser(user);
			Exito exito = new Exito();
			exito.setVisible(Boolean.TRUE);
			dispose();
		}
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

package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserDao;
import model.User;
import utils.EmailUtils;
import utils.JPAUtils;
import utils.SecurityUtils;
import utils.ViewsUtils;

public class ForgotPassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	int xMouse, yMouse;
	private JLabel labelExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ForgotPassword frame = new ForgotPassword();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
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
		imgHotel.setIcon(new ImageIcon(ForgotPassword.class.getResource("/imagenes/img-hotel-login-.png")));

		txtEmail = new JTextField();
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtEmail.getText().equals("Ingrese su email")) {
					txtEmail.setText("");
					txtEmail.setForeground(Color.black);
				}
			}
		});
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtEmail.setText("Ingrese su email");
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmail.setForeground(SystemColor.activeCaptionBorder);
		txtEmail.setBounds(65, 256, 324, 32);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, 292, 324, 2);
		panel.add(separator);

		JLabel labelTitulo = new JLabel("Recuperar contrasena");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(65, 149, 324, 26);
		panel.add(labelTitulo);

		JLabel LabelUsuario = new JLabel("EMAIL");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 219, 107, 26);
		panel.add(LabelUsuario);

		JPanel btnLogin = new JPanel();
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(SystemColor.textHighlight);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtEmail.getText() == null) {
					return;
				}
				
				Optional<User> user = new UserDao(JPAUtils.getEntityManager()).getUserByEmail(txtEmail.getText());
				
				if (!user.isPresent()) {
					JOptionPane.showMessageDialog(
						    contentPane, // Parent component, can be 'null' if not needed
						    "El correo que usted coloco no existe", // Message to display
						    "Correo inexistente", // Title of the dialog box
						    JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				String tempPassword = SecurityUtils.generateTemporaryPassword();
				StringBuilder body = new StringBuilder();
				body.append("SU CONTRASENA TEMPORAL ES: ");
				body.append(tempPassword);
				body.append("\n POR FAVOR CAMBIAR DE INMEDIATO");
				EmailUtils.sendEmail("CONTRASENA TEMPORAL POR FAVOR CAMBIAR", body.toString(), txtEmail.getText());
				user.get().setPassword(tempPassword);
				new UserDao(JPAUtils.getEntityManager()).update(user.get());
				
				JOptionPane.showMessageDialog(
					    contentPane, // Parent component, can be 'null' if not needed
					    "Email enviado satisfactoriamente", // Message to display
					    "Email Exitoso", // Title of the dialog box
					    JOptionPane.INFORMATION_MESSAGE);
				
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setBounds(65, 431, 122, 44);
		panel.add(btnLogin);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblNewLabel = new JLabel("ENTRAR");
		lblNewLabel.setBounds(0, 0, 122, 44);
		btnLogin.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(ForgotPassword.class.getResource("/imagenes/lOGO-50PX.png")));
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

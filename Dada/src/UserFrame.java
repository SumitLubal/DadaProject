import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class UserFrame extends JFrame {
	JButton logout;
	private JPanel contentPane;
	String userName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame("user");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param user 
	 */
	public UserFrame(String user) {
		userName = user;
		viewnFeel();
		listener();
	}

	private void listener() {
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserAndPass();
				dispose();
				DB.createOrReplaceTable(userName);
				ActivityLogger.writeToDB(userName);
				ActivityLogger.write(userName);
			}
		});
	}

	private void viewnFeel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(WindowProperty.getWidth() - 100,WindowProperty.getHeight() - 50,100,50);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		logout = new JButton("**Logout**");
		contentPane.add(logout, BorderLayout.CENTER);
	}
	
}

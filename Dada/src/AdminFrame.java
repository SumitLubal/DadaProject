import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.EventObject;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.log.LogUtils;

public class AdminFrame extends JFrame implements ActionListener {
	private JButton addUser, deleteUser, modifyUser, setImage, logout;
	Scanner sc;

	String username, pass, imagePath, adminName;
	JButton showGraph;
	Statement st;
	Connection conn;
	private String fileList;
	private JLabel statusbar;

	AdminFrame(String userName2) {
		adminName = userName2;
		sc = new Scanner(System.in);
		setSize(800, 600);
		setVisible(true);
		propertySetter();
	}

	private void propertySetter() {
		setImage = new JButton("Set Button");
		addUser = new JButton("Add User");
		deleteUser = new JButton("Delete User");
		modifyUser = new JButton("Modify User");
		logout = new JButton("Logout");
		showGraph = new JButton("Show Graph");
		addUser.setBounds(100, 50, 100, 50);
		deleteUser.setBounds(300, 50, 100, 50);
		modifyUser.setBounds(500, 50, 150, 50);
		setImage.setBounds(350, 350, 150, 50);
		showGraph.setBounds(350, 200, 150, 50);
		logout.setBounds(350, 250, 150, 50);
		setLayout(null);
		
		setImage.setVisible(false);
		add(showGraph);
		add(addUser);
		add(modifyUser);
		add(deleteUser);
		add(setImage);
		add(logout);
		addUser.addActionListener(this);
		deleteUser.addActionListener(this);
		modifyUser.addActionListener(this);
		setImage.addActionListener(this);
		logout.addActionListener(this);
		showGraph.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = ((EventObject) e).getSource();
		if (object.equals(addUser)) {
			setImage.setVisible(false);
			System.out.println("Add");
			int count = 0;
			while (username == null && pass == null) {
				username = JOptionPane.showInputDialog(this,
						"What is user's name?");
				pass = JOptionPane.showInputDialog(this, "Enter  pasword ");

				count++;
				if (count > 2) {
					System.out.println("Cant takeInput");
					break;

				}
			}
			if (count < 2) {
				DB.fireQuerry("INSERT INTO auth (`user`,`pass` ,	`pat1x`,`pat1y`,`pat2x`,`pat2y` ,`pat3x` ,`pat3y`,`type` ,`filepath`)VALUES ('"
						+ username
						+ "', '"
						+ pass
						+ "','0', '0', '0', '0', '0', '0', 'user', 'null')");
				DB.createOrReplaceTable(username);
				JOptionPane.showMessageDialog(null,
						"User Added..he need to log in to set graphical pass",
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if (object.equals(modifyUser)) {
			System.out.println("Modify user");
		} else if (object.equals(deleteUser)) {
			System.out.println("Delete User");
		} else if (object.equals(setImage)) {
		} else if (object.equals(logout)) {
			dispose();
			DB.createOrReplaceTable(adminName);
			ActivityLogger.writeToDB(adminName);
			ActivityLogger.write(adminName);
			new UserAndPass();

		}else if(object.equals(showGraph)){
			System.out.println("Graph created");
			new GraphFrame().setVisible(true);
		}
	}
}

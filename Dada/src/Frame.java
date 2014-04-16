import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.crypto.spec.OAEPParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

class Frame extends JPanel implements MouseListener, Runnable {
	private static final long serialVersionUID = 1L;
	private Point clicked;
	private Pattern pattern;
	private int radius = 50;
	private boolean justClicked;
	private String filepath, userName;
	private double factorX, factorY;
	private UserAndPass parent;
	private String authenticationType;
	private ActivityLogger logger;
	private boolean dragging = false;
	static boolean toSet = false; // this is for setting pattern

	Frame(String username, Pattern pattern, String authenticationType,
			String FilePath, double factX, double factY, UserAndPass parent) {
		factorX = factX;
		logger = new ActivityLogger();
		this.authenticationType = authenticationType;
		factorY = factY;
		factorX = 1 / factorX;
		factorY = 1 / factorY;
		this.parent = parent;
		setVisible(true);
		this.pattern = pattern;
		toSet = false;
		Thread monitor = new Thread(this);
		clicked = new Point();
		addMouseListener(this);
		this.filepath = FilePath;
		userName = username;
		repaint();
		monitor.start();
	}

	public Frame(String username2, Pattern pattern2,
			String authenticationType2, String filePath2, double factorX2,
			double factorY2, UserAndPass userAndPass, boolean b) {
		// TODO Auto-generated constructor stub
		// this constructor sets graphical password
		pattern = new Pattern(0, 0, 0, 0, 0, 0);
		parent = userAndPass;
		String imagePath = null;
		toSet = true;
		boolean itsDone = false;
		while (!itsDone) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(true);
			int option = chooser.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File[] sf = chooser.getSelectedFiles();
				String filelist = "nothing";
				if (sf.length > 0)
					filelist = sf[0].getName();
				System.out.println(sf[0].getAbsolutePath());
				imagePath = sf[0].getAbsolutePath();
				imagePath = imagePath.replace("\\", "/");
				System.out.println("Now set the clicks");
			} else {
			}

			// firing new query
			if (imagePath != null) {
				filepath = imagePath;
				itsDone = true;
				break;
			}
		}
		System.out.println("set pattern and fire querry");
		addMouseListener(this);
		userName = username2;
		repaint();
		Thread monitor = new Thread(this);
		clicked = new Point();
		System.out.println("Strting monitor");
		monitor.start();
	}

	@Override
	public void paint(Graphics g) {
		try {
			BufferedImage imageOfBackground = ImageIO.read(new File(filepath));
			g.drawImage(imageOfBackground, 0, 0, this.getWidth(),
					this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
		clicked.setLocation((int) (e.getX()), (int) (e.getY()));
		justClicked = true;
		System.out.println("x = " + clicked.getX() + " Y = " + clicked.getY());
		System.out.println("Xpat=" + pattern.getClick2x() + "Ypat = "
				+ pattern.getClick2y());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		System.out.println("monitor started");
		boolean pass1 = false, pass2 = false, pass3 = false;
		boolean first = false, second = false, third = false;
		while (true) {
			if (justClicked && !toSet) {
				justClicked = false;
				System.out.println("reverse");
				if (!pass1) {
					if (checkPointsInCircle(1))
						pass1 = true;
				} else if (!pass2) {
					if (checkPointsInCircle(2)) {
						pass1 = true;
						pass2 = true;
					} else {
						pass1 = false;
						pass2 = false;
					}
				} else if (!pass3) {
					if (checkPointsInCircle(3)) {
						System.out.println("Pattern recognised");
						JOptionPane.showMessageDialog(null, "Welcome "
								+ userName, "Login Successfull",
								JOptionPane.ERROR_MESSAGE);
						parent.dispose();
						System.out.println(" " + userName + " "
								+ authenticationType);
						logger.startLogging = true;
						if (authenticationType.equals("admin")) {
							new AdminFrame(userName);
							parent.dispose();
							System.out.println("Admin = " + userName);
						} else {
							UserFrame tmp = new UserFrame(userName);
							tmp.setVisible(true);
							parent.dispose();
							System.out.println("User = " + userName);
						}
					} else {
						pass1 = false;
						pass2 = false;
						pass3 = false;
					}
				}
				justClicked = false;
			} else if (toSet && justClicked) {
				System.out.println("in here");
				if (!first) {
					System.out.println("for pat1");
					pattern.setClick1x(clicked.x);
					pattern.setClick1y(clicked.y);
					first = true;
				} else if (!second) {
					System.out.println("for pat2");
					pattern.setClick2x(clicked.x);
					pattern.setClick2y(clicked.y);
					first = true;
					second = true;
				} else if (!third) {
					System.out.println("for pat3");
					pattern.setClick3x(clicked.x);
					pattern.setClick3y(clicked.y);
					third = true;
					toSet = false;
					System.out.println("setting the querry for" + userName);
					/*
					 * DB.fireQuerry(
					 * "UPDATE `test`.`auth` SET `filepath` = 'hi ','pat1x'='" +
					 * pattern.getClick1x() + "','pat1y'='" +
					 * pattern.getClick1y() + "','pat2x'=" +
					 * pattern.getClick2x() + "','pat2y'='" +
					 * pattern.getClick2y() + "','pat3x'='" +
					 * pattern.getClick3x() + "','pat3y'='" +
					 * pattern.getClick3y() + "' WHERE `user` = '" + userName +
					 * "';");
					 */
					DB.fireQuerry("UPDATE `auth` SET `pat1x` ="
							+ pattern.getClick1x() + ",`pat1y` ="
							+ pattern.getClick1y() + ",`pat2x` ="
							+ pattern.getClick2x() + ",`pat2y` ="
							+ pattern.getClick2y() + ",`pat3x` ="
							+ pattern.getClick3x() + ",`pat3y` ="
							+ pattern.getClick3y() + ",`filepath` = '"
							+ filepath + "' WHERE `user` = '"+userName+"'");
					parent.dispose();
					new UserAndPass().setVisible(true);
					break;
				}
				justClicked = false;
			}
		}
	}

	private boolean checkPointsInCircle(int clickQueue) {
		if (clickQueue == 1) {
			System.out.println("Clicked And checking for pattern 1");
			if (circle(pattern.getClick1x(), pattern.getClick1y(),
					clicked.getX(), clicked.getY())) {
				return true;
			}
		} else if (clickQueue == 2) {
			System.out.println("Clicked And checking for pattern 2");
			if (circle(pattern.getClick2x(), pattern.getClick2y(),
					clicked.getX(), clicked.getY())) {
				return true;
			}

		} else if (clickQueue == 3) {
			System.out.println("Clicked And checking for pattern 3");
			if (circle(pattern.getClick3x(), pattern.getClick3y(),
					clicked.getX(), clicked.getY())) {
				return true;
			}
		}
		return false;

	}

	private boolean circle(int click1x, int click1y, double x, double y) {
		int dist = (int) Math.sqrt(((click1x - x) * (click1x - x))
				+ ((click1y - y) * (click1y - y)));
		if (dist <= radius) {
			return true;
		}
		return false;
	}
}
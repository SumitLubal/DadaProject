import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class GraphFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel graphPane;
	private JButton Count;
	private JButton ApplicationPerDay;
	private JButton Clear;
	private JButton exit;
	private String date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphFrame frame = new GraphFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraphFrame() {
		setBounds(100, 100, 671, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		graphPane = new JPanel();
		graphPane.setBounds(90, 11, 563, 340);
		contentPane.add(graphPane);

		Count = new JButton("Count");
		Count.setBounds(3, 139, 77, 23);
		contentPane.add(Count);

		ApplicationPerDay = new JButton("PerDay");
		ApplicationPerDay.setBounds(3, 187, 77, 23);
		contentPane.add(ApplicationPerDay);

		Clear = new JButton("Clear");
		Clear.setBounds(3, 234, 77, 23);
		contentPane.add(Clear);

		exit = new JButton("Exit");
		exit.setBounds(3, 281, 77, 23);
		contentPane.add(exit);
		Count.addActionListener(this);
		ApplicationPerDay.addActionListener(this);
		Clear.addActionListener(this);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressedButton = (JButton) e.getSource();
		if (pressedButton.equals(Count)) {
			showCount();
		} else if (pressedButton.equals(ApplicationPerDay)) {
			date = JOptionPane.showInputDialog(this,
					"Enter Date to be analysed? in dd//mm//yyyy format");
			showApp();
		} else if (pressedButton.equals(Clear)) {
			clearPanel();
		} else if (pressedButton.equals(exit)) {
			this.dispose();
		}
	}

	private void clearPanel() {
		Graphics g = graphPane.getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, graphPane.getWidth(), graphPane.getHeight());
	}

	private void showApp() {
		Graphics g = graphPane.getGraphics();
		BufferedImage paint = new BufferedImage(graphPane.getWidth(), graphPane.getHeight(), BufferedImage.TYPE_INT_RGB);
		new GraphManager(paint.getGraphics(),graphPane.getWidth(),graphPane.getHeight(),date);
		g.drawImage(paint, 0, 0, graphPane.getWidth(), graphPane.getHeight(), this);
	}

	private void showCount() {
		Graphics g = graphPane.getGraphics();
		BufferedImage paint = new BufferedImage(graphPane.getWidth(), graphPane.getHeight(), BufferedImage.TYPE_INT_RGB);
		new GraphManager(paint.getGraphics(),graphPane.getWidth(),graphPane.getHeight());
		g.drawImage(paint, 0, 0, graphPane.getWidth(), graphPane.getHeight(), this);
	}
}




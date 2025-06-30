package tcms.students;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonUI;

import tcms.custom_gui_components.CustomJButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentDashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4669474195873028109L;
	private String studentName;
	private String studentID;

	public StudentDashboard(String studentName, String studentID) {
		this.studentName = studentName;
		this.studentID = studentID;

		setResizable(false);
		setSize(1200, 750);

		setTitle("Student Dashboard");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Color darkGrey = new Color(34, 34, 34);
		Color blueAcc = new Color(70, 130, 180);
		Color whiteText = Color.WHITE;

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(darkGrey);
		setContentPane(mainPanel);

		// TOP PART
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBounds(0, 0, 1196, 351);
		topPanel.setBackground(darkGrey);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		// LEFt PROFILE PICTURE
		ImageIcon origIcon = new ImageIcon("src\\tcms\\resources\\student_profile_picture.jpg");
		Image scaledImg = origIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(scaledImg));
		picLabel.setBorder(BorderFactory.createLineBorder(blueAcc, 2));
		picLabel.setBorder(BorderFactory.createEmptyBorder(30, 50, 0, 0));
		topPanel.add(picLabel, BorderLayout.WEST);

		// CAROUSEL
		JPanel rightTop = new JPanel();
		rightTop.setLayout(new BoxLayout(rightTop, BoxLayout.Y_AXIS));
		rightTop.setBackground(darkGrey);

		// Info Row
		JPanel rightTopRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		rightTopRow.setBackground(darkGrey);

		JLabel studentLabel = new JLabel(studentName + "  |  ID: " + studentID);
		studentLabel.setForeground(whiteText);
		studentLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));

		JButton notifications = new JButton("🔔");
		JButton logout = new JButton("⍈ Logout");
		for (JButton b : new JButton[] { notifications, logout }) {
			b.setBackground(blueAcc);
			b.setForeground(whiteText);
			b.setFocusPainted(false);
		}

		notifications.addActionListener(e -> JOptionPane.showMessageDialog(this, "No new notifications."));
		logout.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Logging Out...");
			dispose();
		});

		rightTopRow.add(studentLabel);
		rightTopRow.add(notifications);
		rightTopRow.add(logout);

		// IMAGE CAROUSEL SECTION
		CardLayout cardLay = new CardLayout();
		JPanel whatsNewPan = new JPanel(cardLay);
		whatsNewPan.setBackground(darkGrey);
		whatsNewPan.setPreferredSize(new Dimension(300, 300));

		String[] imagePaths = { "src\\tcms\\resources\\carousel_schedule.jpg",
				"src\\tcms\\resources\\carousel_briefing.png",
				"src\\tcms\\resources\\carousel_enroll.jpg" };

		for (int i = 0; i < imagePaths.length; i++) {
			ImageIcon icon = new ImageIcon(imagePaths[i]);
			Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			JLabel imageLabel = new JLabel(new ImageIcon(scaled), JLabel.RIGHT);
			whatsNewPan.add(imageLabel, "card" + i);
		}

		Timer autoSlide = new Timer(4000, new ActionListener() {
			int currentIndex = 0;

			public void actionPerformed(ActionEvent e) {
				currentIndex = (currentIndex + 1) % imagePaths.length;
				cardLay.show(whatsNewPan, "card" + currentIndex);
			}
		});
		autoSlide.start();

		// Arrows
		JButton prev = new JButton("◀");
		JButton next = new JButton("▶");
		prev.setFocusPainted(false);
		next.setFocusPainted(false);

		prev.addActionListener(e -> cardLay.previous(whatsNewPan));
		next.addActionListener(e -> cardLay.next(whatsNewPan));

		JPanel carouselContainer = new JPanel(new BorderLayout());
		carouselContainer.setBackground(darkGrey);
		carouselContainer.add(prev, BorderLayout.WEST);
		carouselContainer.add(whatsNewPan, BorderLayout.CENTER);
		carouselContainer.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 25));
		carouselContainer.add(next, BorderLayout.EAST);

		rightTop.add(rightTopRow);
		rightTop.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel carouselWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		carouselWrapper.setBackground(darkGrey);
		carouselWrapper.add(carouselContainer);

		rightTop.add(carouselWrapper);

		topPanel.add(rightTop, BorderLayout.CENTER);

		// BUTTON PANEL (BOTTOM)
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 404, 1196, 309);
		bottomPanel.setBackground(darkGrey);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

		CustomJButton btn1 = new CustomJButton();
		btn1.setLocation(50, 30);
		btn1.setColor(new Color(70, 130, 180));
		btn1.setRadius(30);
		btn1.setText("Class Schedule");
		btn1.setSize(500,51);
		btn1.setBackground(blueAcc);

		CustomJButton btn2 = new CustomJButton();
		btn2.setLocation(570, 30);
		btn2.setRadius(30);
		btn2.setText("Requests");
		btn2.setSize(533,53);
		btn2.setColor(blueAcc);

		CustomJButton btn3 = new CustomJButton();
		btn3.setLocation(50, 132);
		btn3.setRadius(30);
		btn3.setText("Payment Status");
		btn3.setSize(450,75);
		btn3.setColor(blueAcc);

		CustomJButton btn4 = new CustomJButton();
		btn4.setLocation(570, 156);
		btn4.setRadius(30);
		btn4.setText("View Profile");
		btn4.setSize(533,51);
		btn4.setColor(blueAcc);

		btn1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Loading Schedule..."));
		btn2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Requests..."));
		btn3.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Payment Window..."));
		btn4.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing Profile..."));
		mainPanel.setLayout(null);

		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		bottomPanel.setLayout(null);
		bottomPanel.add(btn1);
		bottomPanel.add(btn2);
		bottomPanel.add(btn3);
		bottomPanel.add(btn4);

		setVisible(true);
	}
	public static void main(String[] args) {
		new StudentDashboard("Gracious Rusike", "S12");
	}
}

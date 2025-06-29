package tcms.students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.io.File;

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

        setTitle("Student Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        Color darkGrey = new Color(34, 34, 34);
        Color blueAcc = new Color(70, 130, 180);
        Color whiteText = Color.WHITE;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(darkGrey);
        setContentPane(mainPanel);

        //TOP PART
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(darkGrey);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //LEFt PROFILE PICTURE
        File imgFile = new File("data/ProfilePicture.jpg");
        ImageIcon origIcon = new ImageIcon(imgFile.getAbsolutePath());
        Image scaledImg = origIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImg));
        picLabel.setBorder(BorderFactory.createLineBorder(blueAcc, 2));
        picLabel.setBorder(BorderFactory.createEmptyBorder(30, 50, 0, 0));
        topPanel.add(picLabel, BorderLayout.WEST);

        //CAROUSEL
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
        for (JButton b : new JButton[]{notifications, logout}) {
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

        //IMAGE CAROUSEL SECTION 
        CardLayout cardLay = new CardLayout();
        JPanel whatsNewPan = new JPanel(cardLay);
        whatsNewPan.setBackground(darkGrey);
        whatsNewPan.setPreferredSize(new Dimension(300, 300));

        String[] imagePaths = {
            "data/ScheduleEDITED.jpg",
            "data/briefingEDITED.png",
            "data/enrollEDITED.jpg"
        };

        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(imagePaths[i]);
            Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled), JLabel.RIGHT);
            whatsNewPan.add(imageLabel, "card" + i);
        }

        Timer autoSlide = new Timer(6000, new ActionListener() {
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

        //BUTTON PANEL (BOTTOM)
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        bottomPanel.setBackground(darkGrey);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

        RoundedButton btn1 = new RoundedButton("Class Schedule", blueAcc);
        RoundedButton btn2 = new RoundedButton("Requests", blueAcc);
        RoundedButton btn3 = new RoundedButton("Payment Status", blueAcc);
        RoundedButton btn4 = new RoundedButton("View Profile", blueAcc);

        bottomPanel.add(btn1);
        bottomPanel.add(btn2);
        bottomPanel.add(btn3);
        bottomPanel.add(btn4);

        btn1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Loading Schedule..."));
        btn2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Requests..."));
        btn3.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Payment Window..."));
        btn4.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing Profile..."));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    class RoundedButton extends JButton {
        public RoundedButton(String text, Color bg) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 16));
            setForeground(Color.WHITE);
            setBackground(bg);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        public void updateUI() {
            setUI(new BasicButtonUI());
        }
    }

    public static void main(String[] args) {
        new StudentDashboard("Gracious Rusike", "S12");
    }
}

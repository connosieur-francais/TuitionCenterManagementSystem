package tcms.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tcms.custom_gui_components.CustomJButton;
import tcms.receptionists.ReceptionistManager;
import tcms.users.UserManager;
import tcms.custom_gui_components.CustomTextField;

public class addReceptionistFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    private static UserManager userManager;
    private static ReceptionistManager receptionistManager;

    // Discord Color Scheme
    private final Color canvasColor = new Color(30, 33, 36); // Discord canvas
    private final Color blurple = new Color(88, 101, 242);
    private final Color blurpleHover = new Color(79, 82, 196);
    private final Color blurpleClick = new Color(60, 69, 165);
    private final Color red = new Color(237, 66, 69);
    private final Color redHover = new Color(200, 50, 52);
    private final Color redClick = new Color(165, 30, 33);
    private final Color textColor = Color.WHITE;
    private final Color borderColor = new Color(43, 45, 49);
    private final Color darkGray = new Color(43, 45, 49);

    private CustomJButton cancelButton;
    private CustomJButton okButton;

    public addReceptionistFrame(UserManager um, ReceptionistManager rm) {
        setUndecorated(true);
        setResizable(false);
        setBounds(100, 100, 738, 570);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        userManager = um;
        receptionistManager = rm;

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(canvasColor);

        // === Custom Title Bar ===
        getContentPane().add(new CustomTitleBar(), BorderLayout.NORTH);

        // === Content Panel ===
        contentPanel.setBackground(canvasColor);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Add Receptionist");
        titleLabel.setBounds(271, 20, 196, 28);
        titleLabel.setForeground(new Color(220, 221, 222));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        
        JLabel recepIDLabel = new JLabel("Receptionist ID: ");
        recepIDLabel.setForeground(new Color(220, 221, 222));
        recepIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recepIDLabel.setBounds(100, 70, 125, 30);
        contentPanel.add(recepIDLabel);
        
        CustomTextField customTextField = new CustomTextField();
        customTextField.setBounds(235, 70, 403, 30);
        contentPanel.add(customTextField);
        
        JLabel recepNameLabel = new JLabel("Name :");
        recepNameLabel.setForeground(new Color(220, 221, 222));
        recepNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recepNameLabel.setBounds(100, 120, 125, 30);
        contentPanel.add(recepNameLabel);
        
        CustomTextField customTextField_1 = new CustomTextField();
        customTextField_1.setBounds(235, 120, 403, 30);
        contentPanel.add(customTextField_1);
        
        JLabel recepIDLabel_1_1 = new JLabel("Receptionist ID: ");
        recepIDLabel_1_1.setForeground(new Color(220, 221, 222));
        recepIDLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
        recepIDLabel_1_1.setBounds(100, 170, 125, 30);
        contentPanel.add(recepIDLabel_1_1);
        
        CustomTextField customTextField_1_1 = new CustomTextField();
        customTextField_1_1.setBounds(235, 170, 403, 30);
        contentPanel.add(customTextField_1_1);
        
        JLabel recepIDLabel_1_1_1 = new JLabel("Receptionist ID: ");
        recepIDLabel_1_1_1.setForeground(new Color(220, 221, 222));
        recepIDLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
        recepIDLabel_1_1_1.setBounds(100, 220, 125, 30);
        contentPanel.add(recepIDLabel_1_1_1);
        
        CustomTextField customTextField_1_1_1 = new CustomTextField();
        customTextField_1_1_1.setBounds(235, 220, 403, 30);
        contentPanel.add(customTextField_1_1_1);
        
        CustomTextField customTextField_1_1_1_1 = new CustomTextField();
        customTextField_1_1_1_1.setBounds(235, 270, 403, 30);
        contentPanel.add(customTextField_1_1_1_1);
        
        JLabel recepIDLabel_1_1_1_1 = new JLabel("Receptionist ID: ");
        recepIDLabel_1_1_1_1.setForeground(new Color(220, 221, 222));
        recepIDLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
        recepIDLabel_1_1_1_1.setBounds(100, 270, 125, 30);
        contentPanel.add(recepIDLabel_1_1_1_1);

        // === Bottom Button Panel ===
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBackground(canvasColor);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton = new CustomJButton();
        okButton.setText("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setBackground(blurple);
        okButton.setColor(blurple);
        okButton.setColorOver(blurpleHover);
        okButton.setColorClick(blurpleClick);
        okButton.setForeground(textColor);
        okButton.setBorderColor(borderColor);
        okButton.setPreferredSize(new Dimension(60, 30));
        okButton.setFocusPainted(false);
        okButton.setRadius(8);
        okButton.addActionListener(this);
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new CustomJButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(red);
        cancelButton.setColor(red);
        cancelButton.setColorOver(redHover);
        cancelButton.setColorClick(redClick);
        cancelButton.setForeground(textColor);
        cancelButton.setBorderColor(borderColor);
        cancelButton.setPreferredSize(new Dimension(80, 30));
        cancelButton.setFocusPainted(false);
        cancelButton.setRadius(8);
        cancelButton.addActionListener(this);
        buttonPane.add(cancelButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
        } else if (e.getSource() == okButton) {
            // Handle validation or saving logic
            dispose();
        }
    }

    // === Inner Class: Custom Title Bar ===
    private class CustomTitleBar extends JPanel {
        private Point initialClick;

        public CustomTitleBar() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(getWidth(), 40));
            setBackground(darkGray);

            JLabel title = new JLabel("   Add Receptionist");
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Arial", Font.BOLD, 14));
            add(title, BorderLayout.WEST);

            JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            controls.setOpaque(false);

            // === Minimize Button ===
            CustomJButton minBtn = new CustomJButton();
            minBtn.setText("—");
            minBtn.setFont(new Font("Arial", Font.BOLD, 12));
            minBtn.setBackground(darkGray);
            minBtn.setColor(darkGray);
            minBtn.setColorOver(new Color(60, 63, 68));
            minBtn.setColorClick(new Color(40, 42, 46));
            minBtn.setForeground(Color.WHITE);
            minBtn.setBorderColor(borderColor);
            minBtn.setFocusPainted(false);
            minBtn.setPreferredSize(new Dimension(45, 40));
            minBtn.setRadius(0);
            minBtn.addActionListener(e -> setState(JFrame.ICONIFIED));
            controls.add(minBtn);

            // === Close Button ===
            CustomJButton closeBtn = new CustomJButton();
            closeBtn.setText("X");
            closeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
            closeBtn.setBackground(red);
            closeBtn.setColor(red);
            closeBtn.setColorOver(redHover);
            closeBtn.setColorClick(redClick);
            closeBtn.setForeground(Color.WHITE);
            closeBtn.setBorderColor(borderColor);
            closeBtn.setFocusPainted(false);
            closeBtn.setPreferredSize(new Dimension(45, 40));
            closeBtn.setRadius(0);
            closeBtn.addActionListener(e -> dispose());
            controls.add(closeBtn);

            add(controls, BorderLayout.EAST);

            // === Drag Support ===
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    int thisX = addReceptionistFrame.this.getLocation().x;
                    int thisY = addReceptionistFrame.this.getLocation().y;
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;
                    addReceptionistFrame.this.setLocation(thisX + xMoved, thisY + yMoved);
                }
            });
        }
    }
}

package tcms.main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import tcms.admin.backend.AdminManager;
import tcms.admin.frontend.AdminPage;
import tcms.data.SystemInitializer.ManagerBundle;
import tcms.receptionists.ReceptionistManager;
import tcms.students.StudentDashboard;
import tcms.students.StudentManager;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class LoginPage extends JFrame implements ActionListener {

    private final UserManager userManager;
    private final AdminManager adminManager;
    private final ReceptionistManager receptionistManager;
    private final TutorManager tutorManager;
    private final StudentManager studentManager;

    private final JFrame frame = new JFrame();
    private final JPanel contentPane;
    private final JPanel loginPanel;
    private final JPanel decoPanel;
    private final JLabel loginLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JTextField usernameTxtfield;
    private final JPasswordField passwordField;
    private final JToggleButton showPasswordBtn;
    private final JButton loginBtn;
    private final JButton resetBtn;
    private final JLabel usernameErrorLabel;
    private final JLabel passwordErrorLabel;
    private final JLabel loginAttemptLabel;
    private final JLabel attemptsLeftLabel;
    private final JLabel loginDisabledLabel;
    private final JLabel backgroundIconDeco;

    private static final long serialVersionUID = 1L;

    public LoginPage(ManagerBundle bundle) {
        userManager = bundle.getUserManager();
        adminManager = bundle.getAdminManager();
        receptionistManager = bundle.getReceptionistManager();
        tutorManager = bundle.getTutorManager();
        studentManager = bundle.getStudentManager();

        frame.setTitle("Tuition Center Management System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 700, 450);

        contentPane = new JPanel();
        contentPane.setBackground(Constants.CANVAS_COLOR);
        contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        loginPanel = new JPanel();
        loginPanel.setBackground(Constants.DARK_GRAY);
        loginPanel.setBorder(null);
        loginPanel.setBounds(100, 80, 500, 250);
        loginPanel.setLayout(null);
        contentPane.add(loginPanel);

        loginLabel = new JLabel("Account Login", SwingConstants.CENTER);
        loginLabel.setForeground(Constants.BLURPLE);
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        loginLabel.setBounds(100, 20, 300, 50);
        loginPanel.add(loginLabel);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBackground(new Color(220, 221, 222));
        usernameLabel.setForeground(Constants.TEXT_COLOR);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        usernameLabel.setBounds(70, 90, 80, 25);
        loginPanel.add(usernameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBackground(new Color(220, 221, 222));
        passwordLabel.setForeground(Constants.TEXT_COLOR);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        passwordLabel.setBounds(70, 140, 80, 25);
        loginPanel.add(passwordLabel);

        usernameTxtfield = new JTextField();
        usernameTxtfield.setBackground(new Color(220, 221, 222));
        usernameTxtfield.setBounds(150, 90, 270, 25);
        loginPanel.add(usernameTxtfield);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(220, 221, 222));
        passwordField.setBounds(150, 140, 270, 25);
        passwordField.setEchoChar('*');
        loginPanel.add(passwordField);

        showPasswordBtn = new JToggleButton("Show Password");
        showPasswordBtn.setBackground(Constants.CANVAS_COLOR);
        showPasswordBtn.setForeground(Constants.TEXT_COLOR);
        showPasswordBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
        showPasswordBtn.setBounds(300, 165, 120, 15);
        showPasswordBtn.setBorder(null);
        showPasswordBtn.setFocusable(false);
        showPasswordBtn.addItemListener(e -> {
            passwordField.setEchoChar(e.getStateChange() == ItemEvent.SELECTED ? (char) 0 : '*');
        });
        loginPanel.add(showPasswordBtn);

        loginBtn = new JButton("Login");
        loginBtn.setBackground(Constants.BLURPLE);
        loginBtn.setForeground(Constants.TEXT_COLOR);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        loginBtn.setBounds(320, 200, 100, 25);
        loginBtn.setBorder(null);
        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);
        loginPanel.add(loginBtn);

        resetBtn = new JButton("Reset");
        resetBtn.setBackground(Constants.CANVAS_COLOR);
        resetBtn.setForeground(Constants.TEXT_COLOR);
        resetBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        resetBtn.setBounds(220, 200, 100, 25);
        resetBtn.setBorder(null);
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);
        loginPanel.add(resetBtn);

        usernameErrorLabel = new JLabel("* Username not registered");
        usernameErrorLabel.setForeground(Constants.RED);
        usernameErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        usernameErrorLabel.setBounds(150, 75, 200, 15);
        usernameErrorLabel.setVisible(false);
        loginPanel.add(usernameErrorLabel);

        passwordErrorLabel = new JLabel("* Invalid password");
        passwordErrorLabel.setForeground(Constants.RED);
        passwordErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        passwordErrorLabel.setBounds(150, 125, 200, 15);
        passwordErrorLabel.setVisible(false);
        loginPanel.add(passwordErrorLabel);

        loginAttemptLabel = new JLabel("Attempts left:");
        loginAttemptLabel.setForeground(new Color(235, 69, 158));
        loginAttemptLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        loginAttemptLabel.setBounds(70, 205, 80, 15);
        loginAttemptLabel.setVisible(false);
        loginPanel.add(loginAttemptLabel);

        attemptsLeftLabel = new JLabel();
        attemptsLeftLabel.setForeground(new Color(235, 69, 158));
        attemptsLeftLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        attemptsLeftLabel.setBounds(150, 205, 50, 15);
        attemptsLeftLabel.setVisible(false);
        loginPanel.add(attemptsLeftLabel);

        loginDisabledLabel = new JLabel("Login Disabled - contact admin to reset!");
        loginDisabledLabel.setForeground(Constants.RED);
        loginDisabledLabel.setFont(new Font("SansSerif", Font.ITALIC, 10));
        loginDisabledLabel.setBounds(220, 185, 200, 15);
        loginDisabledLabel.setVisible(false);
        loginPanel.add(loginDisabledLabel);

        decoPanel = new JPanel();
        decoPanel.setBackground(new Color(30, 33, 36));
        decoPanel.setBorder(null);
        decoPanel.setBounds(90, 70, 520, 270);
        contentPane.add(decoPanel);

        backgroundIconDeco = new JLabel();
        backgroundIconDeco.setBounds(0, 0, 686, 413);
        backgroundIconDeco.setIcon(new ImageIcon(Constants.LOGIN_PAGE_BACKGROUND_FILE));
        contentPane.add(backgroundIconDeco);

        frame.getRootPane().setDefaultButton(loginBtn);
        frame.setVisible(true);
        
        // Data Init Logic
        List<User> users = userManager.getAllUsers();
        for (User user : users) {
            userManager.resetLoginAttempts(user);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetBtn) {
            passwordField.setText("");
            usernameTxtfield.setText("");
            usernameErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(false);
            loginAttemptLabel.setVisible(false);
            loginDisabledLabel.setVisible(false);
            attemptsLeftLabel.setVisible(false);
            loginBtn.setEnabled(true);
            usernameTxtfield.grabFocus();
        }

        if (e.getSource() == loginBtn) {
            String username = usernameTxtfield.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (!checkUsername(username)) {
                usernameErrorLabel.setVisible(true);
                passwordErrorLabel.setVisible(false);
                passwordField.setText("");
            } else {
                User user = userManager.findUserByUsername(username);

                if (!checkPassword(username, password)) {
                    passwordErrorLabel.setVisible(true);
                    usernameErrorLabel.setVisible(false);
                    loginAttemptLabel.setText("Attempts left:");
                    loginAttemptLabel.setVisible(true);
                    attemptsLeftLabel.setVisible(true);
                    checkLoginAttempts(user);
                    passwordField.setText("");
                } else {
                    selectRoleLoginPage(user);
                }
            }
        }
    }

    private boolean checkUsername(String username) {
        return userManager.findUserByUsername(username) != null;
    }

    private boolean checkPassword(String username, String password) {
        User user = userManager.findUserByUsername(username);
        return user != null && password.equals(user.getPassword());
    }

    private void checkLoginAttempts(User user) {
        if (user.getLoginAttempts() <= 0) {
            loginDisabledLabel.setVisible(true);
            loginBtn.setEnabled(false);
            userManager.saveUsers(Constants.USERS_CSV);
        } else {
            userManager.decrementLoginAttempts(user);
            attemptsLeftLabel.setText(String.valueOf(user.getLoginAttempts()));
        }
    }

    private void selectRoleLoginPage(User user) {
        String role = user.getRole();
        switch (role) {
            case "admin":
                userManager.saveUsers(Constants.USERS_CSV);
                new AdminPage(user, adminManager, userManager, receptionistManager, tutorManager);
                frame.dispose();
                break;
            case "student":
                userManager.saveUsers(Constants.USERS_CSV);
                new StudentDashboard(user, userManager, studentManager);
                frame.dispose();
                break;
            case "receptionist":
            	// to-do
            	break;
            case "tutor":
                // to-do
                break;
        }
    }
}

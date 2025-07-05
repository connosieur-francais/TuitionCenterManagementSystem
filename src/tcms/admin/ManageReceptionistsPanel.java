package tcms.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.receptionists.ReceptionistManager;
import tcms.users.UserManager;

public class ManageReceptionistsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ReceptionistManager receptionistManager;
	private static UserManager userManager;
	
	// === Frames ===
	private addReceptionistFrame addReceptionistFrame = null;

	// === Panels ===
	private CustomRoundedPanel totalReceptionistsPanel;
	private CustomRoundedPanel receptionistInfoPanel;
	

	// === Labels ===
	private JLabel totalRecepLabel;
	private JLabel totalRecepNumberLbl;
	private JLabel receptionistLbl;
	private JLabel manageReceptionistsLabel;

	// === Buttons ===
	private CustomJButton addReceptionistBtn;

	// === Scroll Pane ===
	private JScrollPane scrollPane;

	// === Table ===
	private JTable receptionistTable;

	// Icon image files
	private String user_icon_img = new File("src/tcms/resources/receptionist_user_icon.png").getAbsolutePath();

	/**
	 * Create the panel.
	 */
	public ManageReceptionistsPanel(UserManager um, ReceptionistManager rm) {
		userManager = um;
		receptionistManager = rm;

		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		// Total Receptionists Panel
		totalReceptionistsPanel = new CustomRoundedPanel();
		totalReceptionistsPanel.setBackground(new Color(88, 101, 242));
		totalReceptionistsPanel.setRoundTopRight(10);
		totalReceptionistsPanel.setRoundTopLeft(10);
		totalReceptionistsPanel.setRoundBottomRight(10);
		totalReceptionistsPanel.setRoundBottomLeft(10);
		totalReceptionistsPanel.setBounds(926, 10, 250, 98);
		totalReceptionistsPanel.setLayout(null);
		add(totalReceptionistsPanel);

		totalRecepLabel = new JLabel("Total Receptionists: ");
		totalRecepLabel.setForeground(new Color(255, 255, 255));
		totalRecepLabel.setFont(new Font("Arial", Font.BOLD, 20));
		totalRecepLabel.setBounds(10, 10, 230, 20);
		totalReceptionistsPanel.add(totalRecepLabel);

		totalRecepNumberLbl = new JLabel("<dynamic>");
		totalRecepNumberLbl.setForeground(new Color(255, 255, 255));
		totalRecepNumberLbl.setFont(new Font("Arial", Font.BOLD, 48));
		totalRecepNumberLbl.setBounds(20, 40, 120, 48);
		totalReceptionistsPanel.add(totalRecepNumberLbl);

		// Resize icon image
		ImageIcon user_icon = new ImageIcon(user_icon_img.toString());
		Image user_img = user_icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		ImageIcon resized_img = new ImageIcon(user_img);

		JLabel userIconLabel = new JLabel(resized_img);
		userIconLabel.setBounds(150, 40, 90, 48);
		totalReceptionistsPanel.add(userIconLabel);

		// Register Receptionist Panel
		receptionistInfoPanel = new CustomRoundedPanel();
		receptionistInfoPanel.setBorder(null);
		receptionistInfoPanel.setBackground(new Color(35, 39, 42));
		receptionistInfoPanel.setBounds(10, 118, 1166, 500);
		receptionistInfoPanel.setLayout(null);
		add(receptionistInfoPanel);

		addReceptionistBtn = new CustomJButton();
		addReceptionistBtn.addActionListener(this);
		addReceptionistBtn.setFont(new Font("Arial", Font.BOLD, 16));
		addReceptionistBtn.setBounds(956, 10, 200, 40);
		addReceptionistBtn.setRadius(10);
		addReceptionistBtn.setText("+ Add Receptionist");
		addReceptionistBtn.setBackground(new Color(96, 76, 195));
		addReceptionistBtn.setForeground(new Color(255, 255, 255));
		addReceptionistBtn.setBorder(null);
		addReceptionistBtn.setColorClick(new Color(60, 69, 165));
		addReceptionistBtn.setColor(new Color(88, 101, 242));
		addReceptionistBtn.setColorOver(new Color(79, 82, 196));
		addReceptionistBtn.setBorderColor(new Color(43, 45, 49));
		addReceptionistBtn.setFocusPainted(false);
		addReceptionistBtn.setFocusable(true);
		receptionistInfoPanel.add(addReceptionistBtn);

		receptionistLbl = new JLabel("Receptionists");
		receptionistLbl.setHorizontalAlignment(SwingConstants.LEFT);
		receptionistLbl.setForeground(new Color(220, 221, 222));
		receptionistLbl.setFont(new Font("Arial", Font.BOLD, 32));
		receptionistLbl.setBounds(10, 10, 350, 40);
		receptionistInfoPanel.add(receptionistLbl);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 1146, 430);
		receptionistInfoPanel.add(scrollPane);

		manageReceptionistsLabel = new JLabel("Manage Receptionists");
		manageReceptionistsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manageReceptionistsLabel.setBounds(418, 10, 350, 40);
		manageReceptionistsLabel.setForeground(new Color(220, 221, 222));
		manageReceptionistsLabel.setFont(new Font("Arial", Font.BOLD, 32));
		add(manageReceptionistsLabel);

		// Get Data from CSV - Receptionist and User

		// Create the table
		receptionistTable = new JTable();
		receptionistTable.setFillsViewportHeight(true);
		receptionistTable.setRowHeight(28);
		receptionistTable.setFont(new Font("Arial", Font.PLAIN, 14));
		receptionistTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		receptionistTable.setBackground(new Color(54, 57, 63));
		receptionistTable.setForeground(new Color(220, 221, 222));
		receptionistTable.setGridColor(new Color(80, 80, 80));
		receptionistTable.setShowGrid(true);

		// Set header style
		receptionistTable.getTableHeader().setBackground(new Color(64, 68, 75));
		receptionistTable.getTableHeader().setForeground(Color.WHITE);
		receptionistTable.setSelectionBackground(new Color(88, 101, 242));
		receptionistTable.setSelectionForeground(Color.WHITE);
		receptionistTable.setShowVerticalLines(false);

		// Disable column reordering
		receptionistTable.getTableHeader().setReorderingAllowed(false);

		// Add table to scroll pane
		scrollPane.setViewportView(receptionistTable);

		refreshManageReceptionistsPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addReceptionistBtn) {
			if (addReceptionistFrame == null || !addReceptionistFrame.isDisplayable()) {
				addReceptionistFrame = new addReceptionistFrame(userManager, receptionistManager);
				addReceptionistBtn.setEnabled(false);

				// Add a listener to re-enable the button when frame is closed
				addReceptionistFrame.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent e) {
						addReceptionistBtn.setEnabled(true);
						addReceptionistFrame = null; // reset the reference
						refreshManageReceptionistsPanel(); // refresh the count in case a receptionist was added
					}
				});
			}
		}
	}

	public void refreshManageReceptionistsPanel() {
		int total_receptionists = receptionistManager.getAllReceptionists().size(); // Returns the total amount of
																					// elements in receptionists list
		totalRecepNumberLbl.setText(String.valueOf(total_receptionists));
	}
}

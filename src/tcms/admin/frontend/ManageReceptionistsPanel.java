package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.receptionists.Receptionist;
import tcms.receptionists.ReceptionistManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;
import tcms.custom_gui_components.CustomComponents;

public class ManageReceptionistsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ReceptionistManager receptionistManager;
	private static UserManager userManager;

	// === Frames ===
	private AddReceptionistFrame addReceptionistFrame = null;

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
	private CustomJButton removeReceptionistBtn;

	// === Scroll Pane ===
	private JScrollPane scrollPane;

	// === Table ===
	private JTable receptionistTable;
	private String[] columnNames = { "Receptionist ID", "User ID", "Name", "Contact", "Email", "Address" };
	private Object[][] data;

	// Icon image files
	private String user_icon_img = Constants.RECEPTIONIST_USER_ICON_FILE;

	/**
	 * Create the panel.
	 */
	public ManageReceptionistsPanel(UserManager um, ReceptionistManager rm) {
		userManager = um;
		receptionistManager = rm;

		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

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

		ImageIcon user_icon = new ImageIcon(user_icon_img.toString());
		Image user_img = user_icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		ImageIcon resized_img = new ImageIcon(user_img);

		JLabel userIconLabel = new JLabel(resized_img);
		userIconLabel.setBounds(150, 40, 90, 48);
		totalReceptionistsPanel.add(userIconLabel);

		receptionistInfoPanel = new CustomRoundedPanel();
		receptionistInfoPanel.setBorder(null);
		receptionistInfoPanel.setBackground(new Color(35, 39, 42));
		receptionistInfoPanel.setBounds(10, 118, 1166, 500);
		receptionistInfoPanel.setLayout(null);
		add(receptionistInfoPanel);

		addReceptionistBtn = CustomComponents.createAccountButton();
		addReceptionistBtn.addActionListener(this);
		addReceptionistBtn.setBounds(956, 10, 200, 40);
		addReceptionistBtn.setText("+ Add Receptionist");
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

		receptionistTable = new JTable();
		receptionistTable.setFillsViewportHeight(true);
		receptionistTable.setRowHeight(28);
		receptionistTable.setFont(new Font("Arial", Font.PLAIN, 14));
		receptionistTable.setBackground(Constants.CANVAS_COLOR);
		receptionistTable.setForeground(Constants.TEXT_COLOR);
		receptionistTable.setGridColor(Constants.DEEP_DARK);
		receptionistTable.setSelectionBackground(Constants.BLURPLE);
		receptionistTable.setSelectionForeground(Color.WHITE);
		receptionistTable.setShowGrid(true);
		receptionistTable.getTableHeader().setReorderingAllowed(false);

		loadReceptionistTableData();

		JTableHeader tableHeader = receptionistTable.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tableHeader.setBackground(Constants.CANVAS_COLOR);
		tableHeader.setForeground(Constants.TEXT_COLOR);
		tableHeader.setBorder(null);
		UIManager.put("TableHeader.cellBorder", BorderFactory.createLineBorder(Constants.DARK_GRAY));
		tableHeader.setReorderingAllowed(false);

		scrollPane.setViewportView(receptionistTable);

		removeReceptionistBtn = CustomComponents.customRemoveButton();
		removeReceptionistBtn.setText("- Remove Receptionist");
		removeReceptionistBtn.setBounds(746, 10, 200, 40);
		removeReceptionistBtn.addActionListener(this);
		receptionistInfoPanel.add(removeReceptionistBtn);

		refreshManageReceptionistsPanel();
	}

	private Object[][] getData() {
		List<Object[]> rows = new ArrayList<>();
		List<Receptionist> receptionists = receptionistManager.getAllReceptionists();

		for (Receptionist r : receptionists) {
			User user = userManager.findUserByUserID(r.getUserID());
			String name = user.getUsername();
			rows.add(new Object[] { r.getReceptionistID(), r.getUserID(), name, r.getContact(), r.getEmail(), r.getAddress() });
		}
		return rows.toArray(new Object[0][]);
	}

	private void loadReceptionistTableData() {
		data = getData();
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 3791679638516387380L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		receptionistTable.setModel(model);
	}

	private void refreshManageReceptionistsPanel() {
		int total_receptionists = receptionistManager.getAllReceptionists().size();
		totalRecepNumberLbl.setText(String.valueOf(total_receptionists));
		loadReceptionistTableData();
	}

	private void showCreateReceptionistWindow() {
		if (addReceptionistFrame != null && addReceptionistFrame.isDisplayable()) return;
		addReceptionistFrame = new AddReceptionistFrame(userManager, receptionistManager);
		addReceptionistBtn.setEnabled(false);
		addReceptionistFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				addReceptionistBtn.setEnabled(true);
				addReceptionistFrame = null;
				refreshManageReceptionistsPanel();
			}
		});
	}

	private void deleteReceptionist() {
		int selected_row = receptionistTable.getSelectedRow();
		if (selected_row == -1) {
			JOptionPane.showMessageDialog(this, "Please select a receptionist from the table to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int recepID = (int) receptionistTable.getValueAt(selected_row, 0);
		int userID = (int) receptionistTable.getValueAt(selected_row, 1);
		String name = (String) receptionistTable.getValueAt(selected_row, 2);
		String contact = (String) receptionistTable.getValueAt(selected_row, 3);
		String email = (String) receptionistTable.getValueAt(selected_row, 4);
		String address = (String) receptionistTable.getValueAt(selected_row, 5);

		String message = String.format("Are you sure you want to delete this receptionist?\n\nID: %d\nUserID: %d\nName: %s\nContact: %s\nEmail: %s\nAddress: %s",
			recepID, userID, name, contact, email, address);
		int confirm = JOptionPane.showConfirmDialog(this, message, "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			Receptionist selected_receptionist = receptionistManager.findReceptionistByUserID(userID);
			receptionistManager.removeReceptionist(selected_receptionist);
			receptionistManager.saveReceptionists(Constants.RECEPTIONISTS_CSV);
			userManager.saveUsers(Constants.USERS_CSV);
			refreshManageReceptionistsPanel();
			JOptionPane.showMessageDialog(this, "Receptionist deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addReceptionistBtn) {
			showCreateReceptionistWindow();
		} else if (e.getSource() == removeReceptionistBtn) {
			deleteReceptionist();
		}
	}
}

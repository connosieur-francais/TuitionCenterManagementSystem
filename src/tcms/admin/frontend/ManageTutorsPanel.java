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

import tcms.custom_gui_components.CustomComponents;
import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomRoundedPanel;
import tcms.tutors.Subject;
import tcms.tutors.Tutor;
import tcms.tutors.TutorManager;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class ManageTutorsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static TutorManager tutorManager;
	private static UserManager userManager;

	// === Frames ===
	private AddTutorFrame addTutorFrame = null;

	// === Panels ===
	private CustomRoundedPanel totalTutorsPanel;
	private CustomRoundedPanel tutorInfoPanel;

	// === Labels ===
	private JLabel totalTutorLabel;
	private JLabel totalTutorNumberLbl;
	private JLabel tutorLbl;
	private JLabel manageTutorsLabel;

	// === Buttons ===
	private CustomJButton addTutorBtn;
	private CustomJButton removeTutorsBtn;
	private CustomJButton assignSubjectBtn;

	// === Scroll Pane ===
	private JScrollPane scrollPane;

	// === Table ===
	private JTable tutorTable;
	private String[] columnNames = { "Tutor ID", "User ID", "Name", "Contact", "Email", "Address", "Level", "Subject 1",
			"Subject 2", "Subject 3" };
	private Object[][] data;

	// Icon image files
	private String user_icon_img = Constants.TUTOR_USER_ICON_FILE;

	/**
	 * Create the panel.
	 */
	public ManageTutorsPanel(UserManager um, TutorManager tm) {
		userManager = um;
		tutorManager = tm;

		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		totalTutorsPanel = new CustomRoundedPanel();
		totalTutorsPanel.setBackground(new Color(88, 101, 242));
		totalTutorsPanel.setRoundTopRight(10);
		totalTutorsPanel.setRoundTopLeft(10);
		totalTutorsPanel.setRoundBottomRight(10);
		totalTutorsPanel.setRoundBottomLeft(10);
		totalTutorsPanel.setBounds(926, 10, 250, 98);
		totalTutorsPanel.setLayout(null);
		add(totalTutorsPanel);

		totalTutorLabel = new JLabel("Total Tutors :");
		totalTutorLabel.setForeground(new Color(255, 255, 255));
		totalTutorLabel.setFont(new Font("Arial", Font.BOLD, 20));
		totalTutorLabel.setBounds(10, 10, 230, 20);
		totalTutorsPanel.add(totalTutorLabel);

		totalTutorNumberLbl = new JLabel("<dynamic>");
		totalTutorNumberLbl.setForeground(new Color(255, 255, 255));
		totalTutorNumberLbl.setFont(new Font("Arial", Font.BOLD, 48));
		totalTutorNumberLbl.setBounds(20, 40, 120, 48);
		totalTutorsPanel.add(totalTutorNumberLbl);

		ImageIcon user_icon = new ImageIcon(user_icon_img.toString());
		Image user_img = user_icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		ImageIcon resized_img = new ImageIcon(user_img);

		JLabel userIconLabel = new JLabel(resized_img);
		userIconLabel.setBounds(150, 40, 90, 48);
		totalTutorsPanel.add(userIconLabel);

		tutorInfoPanel = new CustomRoundedPanel();
		tutorInfoPanel.setBorder(null);
		tutorInfoPanel.setBackground(new Color(35, 39, 42));
		tutorInfoPanel.setBounds(10, 118, 1166, 500);
		tutorInfoPanel.setLayout(null);
		add(tutorInfoPanel);

		addTutorBtn = CustomComponents.createAccountButton();
		addTutorBtn.addActionListener(this);
		addTutorBtn.setBounds(956, 10, 200, 40);
		addTutorBtn.setText("+ Add Tutor");
		tutorInfoPanel.add(addTutorBtn);

		tutorLbl = new JLabel("Tutors");
		tutorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tutorLbl.setForeground(new Color(220, 221, 222));
		tutorLbl.setFont(new Font("Arial", Font.BOLD, 32));
		tutorLbl.setBounds(10, 10, 350, 40);
		tutorInfoPanel.add(tutorLbl);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 1146, 430);
		tutorInfoPanel.add(scrollPane);

		manageTutorsLabel = new JLabel("Manage Tutors");
		manageTutorsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manageTutorsLabel.setBounds(418, 10, 350, 40);
		manageTutorsLabel.setForeground(new Color(220, 221, 222));
		manageTutorsLabel.setFont(new Font("Arial", Font.BOLD, 32));
		add(manageTutorsLabel);

		tutorTable = new JTable();
		tutorTable.setFillsViewportHeight(true);
		tutorTable.setRowHeight(28);
		tutorTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tutorTable.setBackground(Constants.CANVAS_COLOR);
		tutorTable.setForeground(Constants.TEXT_COLOR);
		tutorTable.setGridColor(Constants.DEEP_DARK);
		tutorTable.setSelectionBackground(Constants.BLURPLE);
		tutorTable.setSelectionForeground(Color.WHITE);
		tutorTable.setShowGrid(true);
		tutorTable.getTableHeader().setReorderingAllowed(false);

		loadTutorTableData();

		JTableHeader tableHeader = tutorTable.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 12));
		tableHeader.setBackground(Constants.CANVAS_COLOR);
		tableHeader.setForeground(Constants.TEXT_COLOR);
		tableHeader.setBorder(null);
		UIManager.put("TableHeader.cellBorder", BorderFactory.createLineBorder(Constants.DARK_GRAY));
		tableHeader.setReorderingAllowed(false);

		scrollPane.setViewportView(tutorTable);

		removeTutorsBtn = CustomComponents.customRemoveButton();
		removeTutorsBtn.setText("- Remove Tutor");
		removeTutorsBtn.setBounds(746, 10, 200, 40);
		removeTutorsBtn.addActionListener(this);
		tutorInfoPanel.add(removeTutorsBtn);

		assignSubjectBtn = new CustomJButton();
		assignSubjectBtn.setColor(Constants.GREEN_BUTTON);
		assignSubjectBtn.setColorOver(Constants.GREEN_BUTTON_HOVER);
		assignSubjectBtn.setColorClick(Constants.GREEN_BUTTON_CLICK);
		assignSubjectBtn.setBorderColor(Constants.GREEN_BUTTON_BORDER);
		assignSubjectBtn.setForeground(Constants.TEXT_COLOR);
		assignSubjectBtn.setFocusable(true);
		assignSubjectBtn.setRadius(10);
		assignSubjectBtn.setFocusPainted(false);
		assignSubjectBtn.setFont(new Font("Arial", Font.BOLD, 16));
		assignSubjectBtn.setText(
				"<html><div style='text-align: center;'>Assign Subjects to Tutor\r\n<span style='font-family: Segoe UI Emoji, Noto Color Emoji; font-size: 12px;'>📖</span>\r\n</div></html>");
		assignSubjectBtn.setBounds(526, 10, 210, 40);
		assignSubjectBtn.addActionListener(this);
		tutorInfoPanel.add(assignSubjectBtn);
		
		refreshManageTutorsPanel();
	}
	
	// =========== Methods for Tutor Table =============
	private Object[][] getData() {
		List<Object[]> rows = new ArrayList<>();
		List<Tutor> tutors = tutorManager.getAllTutors();

		for (Tutor t : tutors) {
			User user = userManager.findUserByUserID(t.getUserID());
			String name = user.getUsername();
			Subject assignedSubject1 = tutorManager.findSubjectBySubjectID(t.getAssigned_subjectID_1());
			Subject assignedSubject2 = tutorManager.findSubjectBySubjectID(t.getAssigned_subjectID_2());
			Subject assignedSubject3 = tutorManager.findSubjectBySubjectID(t.getAssigned_subjectID_3());

			rows.add(new Object[] { t.getTutorID(), t.getUserID(), name, t.getContact(), t.getEmail(), t.getAddress(),
					t.getAssigned_level(), assignedSubject1.getSubjectName(), assignedSubject2.getSubjectName(),
					assignedSubject3.getSubjectName() });
		}
		return rows.toArray(new Object[0][]);
	}

	private void loadTutorTableData() {
		data = getData();
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 3791679638516387380L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tutorTable.setModel(model);
	}
	
	public void refreshTutorTable() {
	    loadTutorTableData();
	}

	private void refreshManageTutorsPanel() {
		int total_tutors = tutorManager.getAllTutors().size();
		totalTutorNumberLbl.setText(String.valueOf(total_tutors));
		loadTutorTableData();
	}

	// ======== Create & Delete Tutor ==============

	private void showCreateTutorWindow() {
		if (addTutorFrame != null && addTutorFrame.isDisplayable())
			return;
		addTutorFrame = new AddTutorFrame(userManager, tutorManager);
		addTutorBtn.setEnabled(false);
		addTutorFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				addTutorBtn.setEnabled(true);
				addTutorFrame = null;
				refreshManageTutorsPanel();
			}
		});
	}

	private void deleteTutor() {
		int selected_row = tutorTable.getSelectedRow();
		if (selected_row == -1) {
			JOptionPane.showMessageDialog(this, "Please select a tutor from the table to remove.", "No Selection",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int tutorID = (int) tutorTable.getValueAt(selected_row, 0);
		int userID = (int) tutorTable.getValueAt(selected_row, 1);
		String name = (String) tutorTable.getValueAt(selected_row, 2);
		String contact = (String) tutorTable.getValueAt(selected_row, 3);
		String email = (String) tutorTable.getValueAt(selected_row, 4);
		String address = (String) tutorTable.getValueAt(selected_row, 5);

		String message = String.format(
				"Are you sure you want to delete this tutor?\n\nID: %d\nUserID: %d\nName: %s\nContact: %s\nEmail: %s\nAddress: %s",
				tutorID, userID, name, contact, email, address);
		int confirm = JOptionPane.showConfirmDialog(this, message, "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			Tutor selected_tutor = tutorManager.findTutorByUserID(userID);
			tutorManager.removeTutor(selected_tutor);
			tutorManager.saveTutors(Constants.TUTORS_CSV);
			userManager.saveUsers(Constants.USERS_CSV);
			refreshManageTutorsPanel();
			JOptionPane.showMessageDialog(this, "Tutor deleted successfully.", "Deleted",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// =============== Assign subject to tutor ==============
	private void assignSubjectToTutor() {
		int selected_index = tutorTable.getSelectedRow();
		
		if (selected_index == -1) {
			JOptionPane.showMessageDialog(this, "Please select a tutor in the table","No tutor selected", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int tutorID = (int) tutorTable.getValueAt(selected_index, 1);
		Tutor selected_tutor = tutorManager.findTutorByUserID(tutorID);
		
		new AssignTutorSubjectFrame(tutorManager, userManager, selected_tutor, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addTutorBtn) {
			showCreateTutorWindow();
		}
		if (e.getSource() == removeTutorsBtn) {
			deleteTutor();
		}
		if (e.getSource() == assignSubjectBtn) {
			assignSubjectToTutor();
		}
	}
}

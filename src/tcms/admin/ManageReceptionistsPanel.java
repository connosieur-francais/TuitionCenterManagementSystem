package tcms.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.CustomRoundedPanel;

public class ManageReceptionistsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// === Panels ===
	private CustomRoundedPanel totalReceptionistsPanel;
	private CustomRoundedPanel receptionistInfoPanel;

	// === Labels ===
	private JLabel totalRecepLabel;
	private JLabel totalRecepNumberLbl;
	private JLabel receptionistLbl;
	private JLabel manageReceptionistsLabel;

	// === Buttons ===
	private CustomJButton customJButton;

	// === Scroll Pane ===
	private JScrollPane scrollPane;
	
	// === Table ===
	private JTable receptionistTable;

	/**
	 * Create the panel.
	 */
	public ManageReceptionistsPanel() {
		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		// Total Receptionists Panel
		totalReceptionistsPanel = new CustomRoundedPanel();
		totalReceptionistsPanel.setRoundTopRight(10);
		totalReceptionistsPanel.setRoundTopLeft(10);
		totalReceptionistsPanel.setRoundBottomRight(10);
		totalReceptionistsPanel.setRoundBottomLeft(10);
		totalReceptionistsPanel.setBounds(926, 10, 250, 98);
		totalReceptionistsPanel.setLayout(null);
		add(totalReceptionistsPanel);

		totalRecepLabel = new JLabel("Total Receptionists: ");
		totalRecepLabel.setFont(new Font("Arial", Font.BOLD, 16));
		totalRecepLabel.setBounds(10, 10, 200, 20);
		totalReceptionistsPanel.add(totalRecepLabel);

		totalRecepNumberLbl = new JLabel("<dynamic>");
		totalRecepNumberLbl.setFont(new Font("Arial", Font.BOLD, 16));
		totalRecepNumberLbl.setBounds(10, 40, 200, 20);
		totalReceptionistsPanel.add(totalRecepNumberLbl);

		// Register Receptionist Panel
		receptionistInfoPanel = new CustomRoundedPanel();
		receptionistInfoPanel.setBorder(null);
		receptionistInfoPanel.setBackground(new Color(35, 39, 42));
		receptionistInfoPanel.setBounds(10, 118, 1166, 500);
		receptionistInfoPanel.setLayout(null);
		add(receptionistInfoPanel);

		customJButton = new CustomJButton();
		customJButton.setFont(new Font("Arial", Font.BOLD, 16));
		customJButton.setBounds(956, 10, 200, 40);
		customJButton.setRadius(10);
		customJButton.setText("+ Add Receptionist");
		customJButton.setBackground(new Color(96, 76, 195));
		customJButton.setForeground(new Color(220, 221, 222));
		customJButton.setBorder(null);
		customJButton.setColorClick(new Color(60, 69, 165));
		customJButton.setColor(new Color(88, 101, 242));
		customJButton.setColorOver(new Color(79, 82, 196));
		customJButton.setBorderColor(new Color(43, 45, 49));
		customJButton.setFocusPainted(false);
		customJButton.setFocusable(true);
		receptionistInfoPanel.add(customJButton);

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Add action logic here
	}
}

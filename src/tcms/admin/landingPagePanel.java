package tcms.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.custom_gui_components.IncomeChartPanel;

public class landingPagePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField searchTxtfield;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTableHeader header;

	private JMenuItem item;

	private CustomJButton searchButton;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public landingPagePanel(UserManager um, Admin admin, User user) {
		SwingUtilities.invokeLater(() -> {
			requestFocusInWindow(); // focus a neutral component
		});
		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(40, 43, 48), 2));
		scrollPane.getVerticalScrollBar().setUI(new DiscordScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new DiscordScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scrolling
		scrollPane.setBackground(new Color(44, 47, 51));
		scrollPane.setBounds(10, 428, 1166, 190);
		add(scrollPane);

		table = new JTable();
		table.setBorder(null);
		table.setShowGrid(false);
		table.setGridColor(new Color(30, 33, 36));
		table.setForeground(new Color(220, 221, 222));
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBackground(new Color(44, 47, 51));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(new Color(44, 47, 51)); // Match your table background

		// Set custom cell renderer for all columns
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 2415388109071200355L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				// Always apply custom colors
				if (isSelected) {
					setBackground(new Color(88, 101, 242));
					setForeground(Color.WHITE);
				} else {
					setBackground(new Color(54, 57, 63));
					setForeground(Color.LIGHT_GRAY);
				}
				setBorder(null);
				return this;
			}
		});

		// Style the table header
		header = table.getTableHeader();
		header.setOpaque(false); // make background transparent if needed
		header.setReorderingAllowed(false); // Dont allow the users to reorder the columns
		header.setBorder(null); // remove column header borders
		header.setPreferredSize(new Dimension(0, 32));

		header.setDefaultRenderer(new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6697153150821605529L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				JLabel label = new JLabel(value.toString());
				label.setFont(new Font("Arial", Font.BOLD, 12));
				label.setForeground(Color.WHITE);
				label.setBackground(new Color(32, 34, 37));
				label.setOpaque(true);
				label.setBorder(null); // <- this removes the column dividers
				label.setHorizontalAlignment(CENTER);
				return label;
			}
		});

		JLabel label1 = new JLabel("Table Of Users");
		label1.setForeground(new Color(220, 221, 222));
		label1.setFont(new Font("Arial Black", Font.BOLD, 16));
		label1.setBounds(10, 400, 200, 25);
		add(label1);

		JLabel dashboardLabel = new JLabel();
		dashboardLabel.setText("Welcome, " + user.getUsername());
		dashboardLabel.setFont(new Font("Arial", Font.BOLD, 32));
		dashboardLabel.setForeground(new Color(220, 221, 222));
		dashboardLabel.setBounds(10, 10, 700, 40);
		add(dashboardLabel);

		searchTxtfield = new JTextField();
		searchTxtfield.setText("🔍 Search for a specific username");
		searchTxtfield.setCaretColor(Color.white);
		final boolean[] placeholderCleared = { false };

		searchTxtfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!placeholderCleared[0] && searchTxtfield.getText().equals("🔍 Search for a specific username")) {
					searchTxtfield.setText("");
					searchTxtfield.setForeground(Color.WHITE);
					placeholderCleared[0] = true;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTxtfield.getText().trim().isEmpty()) {
					searchTxtfield.setText("🔍 Search for a specific username");
					searchTxtfield.setForeground(Color.GRAY);
					placeholderCleared[0] = false;
				}
			}
		});
		searchTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 33, 36)));
		searchTxtfield.setForeground(new Color(163, 166, 170));
		searchTxtfield.setBackground(new Color(35, 39, 42));
		searchTxtfield.setBounds(290, 400, 730, 25);
		add(searchTxtfield);

		searchButton = new CustomJButton();
		searchButton.addActionListener(this);
		searchButton.setRadius(15);
		searchButton.setText("Search");
		searchButton.setBackground(new Color(96, 76, 195));
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.setBorder(null);
		searchButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		searchButton.setColorClick(new Color(60, 69, 165));
		searchButton.setColor(new Color(88, 101, 242));
		searchButton.setColorOver(new Color(79, 82, 196));
		searchButton.setBorderColor(new Color(54, 57, 62));
		searchButton.setFocusable(true);
		searchButton.setFocusPainted(false);
		searchButton.setBounds(1026, 398, 150, 27);
		add(searchButton);

		CustomJButton filterButton = new CustomJButton();
		filterButton.setText("Filter");
		filterButton.setRadius(15);
		filterButton.setForeground(Color.WHITE);
		filterButton.setBackground(new Color(88, 101, 242));
		filterButton.setColor(new Color(88, 101, 242));
		filterButton.setColorClick(new Color(60, 69, 165));
		filterButton.setColorOver(new Color(79, 82, 196));
		filterButton.setBorder(null);
		filterButton.setFocusPainted(false);
		filterButton.setBounds(163, 403, 117, 25);
		add(filterButton);

		// Create popup menu for role filtering
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBackground(new Color(35, 39, 42));
		popupMenu.setBorder(null);

		String[] roles = { "Admin", "Student", "Tutor", "Receptionist" };
		for (String role : roles) {
			item = new JMenuItem(role);
			item.setBorder(null);
			item.setForeground(new Color(220, 221, 222));
			item.setBackground(new Color(54, 57, 63));
			item.setFont(new Font("Arial", Font.BOLD, 12));

			// Hover effect
			item.addChangeListener(e -> {
				JMenuItem source = (JMenuItem) e.getSource();
				if (source.isArmed()) {
					source.setBackground(new Color(88, 101, 242));
				} else {
					source.setBackground(new Color(54, 57, 63));
				}
			});

			// Apply filter when clicked
			item.addActionListener(e -> {
				filterButton.setText(role);
				sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + role + "$", 3)); // Role is column index 3
			});

			popupMenu.add(item);
		}

		// Add an option to clear the role filter
		JMenuItem clearFilter = new JMenuItem("Clear Filter");
		clearFilter.setForeground(Color.LIGHT_GRAY);
		clearFilter.setBackground(new Color(35, 39, 42));
		clearFilter.setFont(new Font("Arial", Font.ITALIC, 12));
		clearFilter.addActionListener(e -> {
			filterButton.setText("🔽 Filter");
			sorter.setRowFilter(null);
		});
		popupMenu.addSeparator();
		popupMenu.add(clearFilter);

		// Show the popup menu on top when filter button is clicked
		filterButton.addActionListener(e -> {
			popupMenu.setPreferredSize(new Dimension(filterButton.getWidth(), popupMenu.getPreferredSize().height));
			popupMenu.show(filterButton, 0, -popupMenu.getPreferredSize().height);

		});

		// Load users from UserManager
		loadUsersIntoTable(um);
		
		IncomeChartPanel incomeChartPanel = new IncomeChartPanel();
		incomeChartPanel.setBackground(new Color(35, 39, 42));
		incomeChartPanel.setBounds(600, 10, 576, 380);
		add(incomeChartPanel);
	}

	private void loadUsersIntoTable(UserManager um) {
		List<User> userList = um.getAllUsers();

		// Define column headers
		Vector<String> columns = new Vector<>();
		columns.add("User ID");
		columns.add("Username");
		columns.add("Password");
		columns.add("Role");
		columns.add("Login Attempts");
		columns.add("Status");

		// Prepare table data
		Vector<Vector<String>> data = new Vector<>();
		for (User user : userList) {
			Vector<String> row = new Vector<>();
			row.add(String.valueOf(user.getID()));
			row.add(user.getUsername());
			row.add(user.getPassword());
			row.add(user.getRole());
			row.add(String.valueOf(user.getLoginAttempts()));
			row.add(user.getAccountStatus());
			data.add(row);
		}

		// Set table model
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // make table read-only
			}
		};

		table.setModel(model);

		// Set the sorter
		sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
	}

	private static class DiscordScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
			thumbColor = new Color(220, 221, 222); // Discord blurple
			trackColor = new Color(35, 39, 42); // dark track
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Color thumbColor = new Color(220, 221, 222);
			g2.setPaint(thumbColor);
			g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
			g2.dispose();
		}

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(new Color(35, 39, 42));
			g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
			g2.dispose();
		}

		@Override
		protected Dimension getMinimumThumbSize() {
			return new Dimension(8, 40); // Slim thumb
		}
	}

	// ACTION PERFORMED METHOD -> FOR ACTION LISTENERS (BUTTON & TEXTFIELD)
	// -----------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String query = searchTxtfield.getText().trim();
			// If input is empty or placeholder text, remove filter
			if (query.isEmpty() || query.equals("🔍 Search for a specific username")) {
				sorter.setRowFilter(null);
			} else {
				// Case insensitive filter on column index 1 (Username)
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1));
			}

		}

	}
}

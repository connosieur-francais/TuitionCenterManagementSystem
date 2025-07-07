package tcms.admin.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import tcms.admin.backend.Admin;
import tcms.custom_gui_components.CustomJButton;
import tcms.custom_gui_components.IncomeChartPanel;
import tcms.users.User;
import tcms.users.UserManager;
import tcms.utils.Constants;

public class LandingPagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Timer searchTimer;
	private JTextField searchTxtfield;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTableHeader header;
	private String lastSearchQuery = "";

	private JScrollPane scrollPane;

	public LandingPagePanel(UserManager um, Admin admin, User user) {
		SwingUtilities.invokeLater(() -> requestFocusInWindow());

		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(40, 43, 48), 2));
		scrollPane.getVerticalScrollBar().setUI(new DiscordScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new DiscordScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setBackground(new Color(44, 47, 51));
		scrollPane.setBounds(10, 428, 1166, 190);
		add(scrollPane);

		table = new JTable();
		table.setBorder(null);
		table.setShowGrid(false);
		table.setGridColor(Constants.DARK_BUT_NOT_BLACK);
		table.setForeground(Constants.TEXT_COLOR);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBackground(Constants.DARK_BUT_NOT_BLACK);
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Constants.DARK_BUT_NOT_BLACK);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 2415388109071200355L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
														   boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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

		header = table.getTableHeader();
		header.setOpaque(false);
		header.setFocusable(false);
		header.setReorderingAllowed(false);
		header.setBorder(null);
		header.setPreferredSize(new Dimension(0, 32));
		header.setDefaultRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 6697153150821605529L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
														   boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setFont(new Font("Arial", Font.BOLD, 12));
				label.setForeground(Color.WHITE);
				label.setBackground(Constants.CANVAS_COLOR);
				label.setOpaque(true);
				label.setBorder(null);
				label.setHorizontalAlignment(CENTER);
				return label;
			}
		});

		JLabel userTableLabel = new JLabel("Table Of Users");
		userTableLabel.setForeground(new Color(220, 221, 222));
		userTableLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		userTableLabel.setBounds(10, 400, 200, 25);
		add(userTableLabel);

		JLabel dashboardLabel = new JLabel("Welcome, " + user.getUsername());
		dashboardLabel.setFont(new Font("Arial", Font.BOLD, 32));
		dashboardLabel.setForeground(new Color(220, 221, 222));
		dashboardLabel.setBounds(10, 10, 700, 40);
		add(dashboardLabel);

		searchTxtfield = new JTextField("🔍 Search for a specific username");
		searchTxtfield.setCaretColor(Color.white);
		final boolean[] placeholderCleared = {false};
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
		searchTxtfield.setBorder(new MatteBorder(0, 0, 2, 0, new Color(30, 33, 36)));
		searchTxtfield.setForeground(new Color(163, 166, 170));
		searchTxtfield.setBackground(new Color(35, 39, 42));
		searchTxtfield.setBounds(290, 400, 886, 25);
		add(searchTxtfield);

		searchTimer = new Timer(300, e -> {
			String currentQuery = searchTxtfield.getText().trim();
			if (!currentQuery.equals(lastSearchQuery)) {
				lastSearchQuery = currentQuery;
				if (currentQuery.isEmpty() || currentQuery.equals("🔍 Search for a specific username")) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + currentQuery, 1));
				}
			}
		});
		searchTimer.setRepeats(true);
		searchTimer.start();

		CustomJButton filterButton = new CustomJButton();
		filterButton.setText("Filter");
		filterButton.setRadius(15);
		filterButton.setForeground(Color.WHITE);
		filterButton.setBackground(new Color(88, 101, 242));
		filterButton.setColor(new Color(88, 101, 242));
		filterButton.setColorClick(new Color(60, 69, 165));
		filterButton.setColorOver(new Color(79, 82, 196));
		filterButton.setBorder(null);
		filterButton.setFocusable(true);
		filterButton.setFocusPainted(false);
		filterButton.setBounds(163, 403, 117, 25);
		add(filterButton);

		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setFocusable(true);
		popupMenu.setBackground(new Color(35, 39, 42));
		popupMenu.setBorder(null);

		String[] roles = {"Admin", "Student", "Tutor", "Receptionist"};
		for (String role : roles) {
			JMenuItem item = new JMenuItem(role);
			item.setBorder(null);
			item.setForeground(new Color(220, 221, 222));
			item.setBackground(new Color(54, 57, 63));
			item.setFont(new Font("Arial", Font.BOLD, 12));
			item.addChangeListener(e -> {
				if (item.isArmed()) {
					item.setBackground(new Color(88, 101, 242));
				} else {
					item.setBackground(new Color(54, 57, 63));
				}
			});
			item.addActionListener(e -> {
				filterButton.setText(role);
				sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + role + "$", 3));
			});
			popupMenu.add(item);
		}

		JMenuItem clearFilter = new JMenuItem("Clear Filter");
		clearFilter.setForeground(Color.LIGHT_GRAY);
		clearFilter.setBorder(null);
		clearFilter.setBackground(new Color(35, 39, 42));
		clearFilter.setFont(new Font("Arial", Font.ITALIC, 12));
		clearFilter.setFocusable(true);
		clearFilter.addActionListener(e -> {
			filterButton.setText("🔽 Filter");
			sorter.setRowFilter(null);
		});
		popupMenu.addSeparator();
		popupMenu.add(clearFilter);

		filterButton.addActionListener(e -> {
			popupMenu.setPreferredSize(new Dimension(filterButton.getWidth(), popupMenu.getPreferredSize().height));
			popupMenu.show(filterButton, 0, -popupMenu.getPreferredSize().height);
		});
		
		loadUsersIntoTable(um);

		IncomeChartPanel incomeChartPanel = new IncomeChartPanel();
		incomeChartPanel.setBackground(new Color(35, 39, 42));
		incomeChartPanel.setBounds(600, 10, 576, 380);
		add(incomeChartPanel);
	}

	@Override
	public void removeNotify() {
		super.removeNotify();
		if (searchTimer != null) searchTimer.stop();
	}

	public void reloadUserTableData(UserManager um) {
	    loadUsersIntoTable(um); // Make sure 'userManager' is accessible
	}

	private void loadUsersIntoTable(UserManager um) {
		List<User> userList = um.getAllUsers();

		Vector<String> columns = new Vector<>();
		columns.add("User ID");
		columns.add("Username");
		columns.add("Password");
		columns.add("Role");
		columns.add("Login Attempts");
		columns.add("Status");

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

		DefaultTableModel model = new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		sorter = new TableRowSorter<>(model) {
			@Override
			public void toggleSortOrder(int column) {
				// disables clicking header to sort
			}
		};

		table.setModel(model);
		table.setRowSorter(sorter);
	}

	private static class DiscordScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
			thumbColor = new Color(220, 221, 222);
			trackColor = new Color(35, 39, 42);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(thumbColor);
			g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
			g2.dispose();
		}

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(trackColor);
			g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
			g2.dispose();
		}

		@Override
		protected Dimension getMinimumThumbSize() {
			return new Dimension(8, 40);
		}
	}
}

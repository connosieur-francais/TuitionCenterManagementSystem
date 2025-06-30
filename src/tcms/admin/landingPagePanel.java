package tcms.admin;

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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import tcms.custom_gui_components.CustomJButton;
import tcms.users.User;
import tcms.users.UserManager;

public class landingPagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSearchForUsername;

	/**
	 * Create the panel.
	 */
	public landingPagePanel(UserManager um, Admin admin, User user) {
		SwingUtilities.invokeLater(() -> {
			requestFocusInWindow(); // focus a neutral component
		});
		setBorder(new LineBorder(new Color(40, 43, 48), 2));
		setBackground(new Color(44, 47, 51));
		setSize(1186, 628);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
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
		table.setRowHeight(30); // Optional: more vertical spacing
		scrollPane.setViewportView(table);

		// Set custom cell renderer for all columns
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2415388109071200355L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (isSelected) {
					c.setBackground(new Color(88, 101, 242)); // Discord blurple when selected
					c.setForeground(Color.WHITE);
				} else {
					c.setBackground(new Color(54, 57, 63)); // Normal row
					c.setForeground(Color.LIGHT_GRAY);
				}

				return c;
			}
		});

		// Style the table header
		JTableHeader header = table.getTableHeader();
		header.setOpaque(false); // make background transparent if needed
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
		label1.setBounds(10, 400, 270, 25);
		add(label1);

		JLabel dashboardLabel = new JLabel();
		dashboardLabel.setText("Welcome, " + user.getUsername());
		dashboardLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
		dashboardLabel.setForeground(new Color(220, 221, 222));
		dashboardLabel.setBounds(10, 10, 700, 40);
		add(dashboardLabel);

		txtSearchForUsername = new JTextField();
		txtSearchForUsername.setText("🔍 Search for a specific username");
		txtSearchForUsername.setCaretColor(Color.white);
		final boolean[] placeholderCleared = { false };

		txtSearchForUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (!placeholderCleared[0] && txtSearchForUsername.getText().equals("🔍 Search for a specific username")) {
					txtSearchForUsername.setText("");
					txtSearchForUsername.setForeground(Color.WHITE);
					placeholderCleared[0] = true;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearchForUsername.getText().trim().isEmpty()) {
					txtSearchForUsername.setText("🔍 Search for a specific username");
					txtSearchForUsername.setForeground(Color.GRAY);
					placeholderCleared[0] = false;
				}
			}
		});
		txtSearchForUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 33, 36)));
		txtSearchForUsername.setForeground(new Color(163, 166, 170));
		txtSearchForUsername.setBackground(new Color(35, 39, 42));
		txtSearchForUsername.setBounds(290, 400, 730, 30);
		add(txtSearchForUsername);

		CustomJButton searchButton = new CustomJButton();
		searchButton.setRadius(15);
		searchButton.setText("Search");
		searchButton.setBackground(new Color(96, 76, 195));
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.setBorder(null);
		searchButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		searchButton.setColorClick(new Color(60, 69, 165));
		searchButton.setColor(new Color(88, 101, 242));
		searchButton.setColorOver(new Color(79, 82, 196));
		searchButton.setBorderColor(new Color(43, 45, 49));
		searchButton.setFocusable(true);
		searchButton.setFocusPainted(false);
		searchButton.setBounds(1026, 398, 150, 30);
		add(searchButton);

		// Load users from UserManager
		loadUsersIntoTable(um);
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
}

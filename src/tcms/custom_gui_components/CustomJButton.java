package tcms.custom_gui_components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomJButton extends JButton {

	private static final long serialVersionUID = 1L;

	// =======================
	// Fields
	// =======================
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;

	// =======================
	// Constructor
	// =======================
	public CustomJButton() {
		setText("New Button");
		setBorder(null);
		setFocusable(false);
		setContentAreaFilled(false);

		// Default colors
		setColor(new Color(247, 247, 247));
		setBorderColor(new Color(57, 62, 70));
		setColorClick(new Color(146, 154, 171));
		setColorOver(new Color(238, 238, 238));

		// Mouse interaction
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				setBackground(colorOver);
				over = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
				setBackground(color);
				over = false;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(colorClick);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(over ? colorOver : color);
			}
		});
	}

	// =======================
	// Getters
	// =======================
	public boolean isOver() {
		return over;
	}

	public Color getColor() {
		return color;
	}

	public Color getColorOver() {
		return colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public int getRadius() {
		return radius;
	}

	// =======================
	// Setters
	// =======================
	public void setOver(boolean over) {
		this.over = over;
	}

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	// =======================
	// Overridden Paint Method
	// =======================
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		// Draw button background
		g2.setColor(getBackground());
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

		super.paintComponent(g);
	}
}


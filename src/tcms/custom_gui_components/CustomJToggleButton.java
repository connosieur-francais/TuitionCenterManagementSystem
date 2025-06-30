package tcms.custom_gui_components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class CustomJToggleButton extends JToggleButton {

	private static final long serialVersionUID = 1L;

	// =======================
	// Customization Fields
	// =======================
	private Color color = new Color(247, 247, 247);
	private Color hoverColor = new Color(238, 238, 238);
	private Color selectedColor = new Color(146, 154, 171);
	private Color borderColor = new Color(57, 62, 70);
	private Color textColor = Color.BLACK;
	private int cornerRadius = 10;

	private boolean hovering = false;

	// =======================
	// Constructor
	// =======================
	public CustomJToggleButton() {
		super("Toggle");

		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setForeground(textColor);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hovering = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hovering = false;
				repaint();
			}
		});
	}

	// =======================
	// Custom Painting
	// =======================
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

		// Fill based on state
		if (isSelected()) {
			g2.setColor(selectedColor);
		} else if (hovering) {
			g2.setColor(hoverColor);
		} else {
			g2.setColor(color);
		}
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius);

		// Draw text/icon
		super.paintComponent(g2);
		g2.dispose();
	}

	// =======================
	// Setters for Customization
	// =======================
	public void setBackgroundColor(Color backgroundColor) {
		this.color = backgroundColor;
		repaint();
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
		repaint();
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
		repaint();
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		repaint();
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
		setForeground(textColor);
		repaint();
	}

	public void setCornerRadius(int cornerRadius) {
		this.cornerRadius = cornerRadius;
		repaint();
	}

	// Optional: expose them for external usage
	public Color getBackgroundColor() {
		return color;
	}

	public Color getHoverColor() {
		return hoverColor;
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public int getCornerRadius() {
		return cornerRadius;
	}
}

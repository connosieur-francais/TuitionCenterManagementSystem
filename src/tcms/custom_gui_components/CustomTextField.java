package tcms.custom_gui_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class CustomTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public CustomTextField() {
		setUI(new TextUI());
		setOpaque(false);
		setForeground(new Color(80, 80, 80));
		setBorder(null);
		setSelectedTextColor(new Color(255, 255, 255));
		setSelectionColor(new Color(103, 209, 255));
		setBackground(new Color(255, 255, 255));
		setMargin(new Insets(5, 10, 5, 10)); // Add padding for text
	}

	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;

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

	public void setOver(boolean over) {
		this.over = over;
	}

	public void setColor(Color color) {
		setBackground(color);
		this.color = color;
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

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Only draw background with rounded corners (no blocking border)
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		super.paintComponent(g);
	}

	private class TextUI extends BasicTextFieldUI {
		@Override
		protected void paintBackground(Graphics g) {
			// Do nothing — we handle background manually
		}
	}
}

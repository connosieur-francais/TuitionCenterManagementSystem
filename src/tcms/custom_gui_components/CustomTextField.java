package tcms.custom_gui_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.BorderFactory;

public class CustomTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public CustomTextField() {
		setUI(new TextUI());
		setOpaque(false);
		setForeground(new Color(80, 80, 80));
		setSelectedTextColor(new Color(255, 255, 255));
		setSelectionColor(new Color(103, 209, 255));
		setBackground(new Color(255, 255, 255));

		// ✅ Asymmetric internal padding: top, left, bottom, right
		setBorder(BorderFactory.createEmptyBorder(5, 10, 3, 8));
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

		// Background fill with rounded corners
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		super.paintComponent(g);
	}

	private class TextUI extends BasicTextFieldUI {
		@Override
		protected void paintBackground(Graphics g) {
			// Background already handled in paintComponent()
		}
	}
}

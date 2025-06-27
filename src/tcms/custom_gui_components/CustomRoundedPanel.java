package tcms.custom_gui_components;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CustomRoundedPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int roundTopLeft = 0;
	private int roundTopRight = 0;
	private int roundBottomLeft = 0;
	private int roundBottomRight = 0;

	private List<ModelColor> colors;

	public CustomRoundedPanel() {
		initComponents();
		setOpaque(false);
		colors = new ArrayList<>();
	}

	private void initComponents() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 407, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 218, Short.MAX_VALUE));
	}

	// ------------------ Public Accessors -------------------
	public int getRoundTopLeft() {
		return roundTopLeft;
	}

	public void setRoundTopLeft(int roundTopLeft) {
		this.roundTopLeft = roundTopLeft;
		repaint();
	}

	public int getRoundTopRight() {
		return roundTopRight;
	}

	public void setRoundTopRight(int roundTopRight) {
		this.roundTopRight = roundTopRight;
		repaint();
	}

	public int getRoundBottomLeft() {
		return roundBottomLeft;
	}

	public void setRoundBottomLeft(int roundBottomLeft) {
		this.roundBottomLeft = roundBottomLeft;
		repaint();
	}

	public int getRoundBottomRight() {
		return roundBottomRight;
	}

	public void setRoundBottomRight(int roundBottomRight) {
		this.roundBottomRight = roundBottomRight;
		repaint();
	}

	public void addColor(ModelColor... newColors) {
		for (ModelColor c : newColors) {
			this.colors.add(c);
		}
		repaint();
	}

	// ------------------ Paint Method -------------------
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Create rounded area from all corners
		Area area = new Area(createRoundTopLeft());
		if (roundTopRight > 0)
			area.intersect(new Area(createRoundTopRight()));
		if (roundBottomLeft > 0)
			area.intersect(new Area(createRoundBottomLeft()));
		if (roundBottomRight > 0)
			area.intersect(new Area(createRoundBottomRight()));

		// Fill with gradient if available
		if (!colors.isEmpty()) {
			Color[] gradientColors = new Color[colors.size()];
			float[] positions = new float[colors.size()];
			for (int i = 0; i < colors.size(); i++) {
				gradientColors[i] = colors.get(i).getColor();
				positions[i] = colors.get(i).getPosition();
			}

			LinearGradientPaint gradient = new LinearGradientPaint(0, 0, width, height, positions, gradientColors);
			g2.setPaint(gradient);
		} else {
			// fallback: use background color
			g2.setColor(getBackground());
		}

		// Fill the rounded shape
		g2.fill(area);
		g2.dispose();

		// Continue painting child components
		super.paintComponent(g);
	}

	// ------------------ Rounded Shape Builders -------------------
	private Shape createRoundTopLeft() {
		int width = getWidth();
		int height = getHeight();
		int arc = Math.min(width, roundTopLeft);
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, arc, arc));
		area.add(new Area(new Rectangle2D.Double(arc / 2.0, 0, width - arc / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(0, arc / 2.0, width, height - arc / 2.0)));
		return area;
	}

	private Shape createRoundTopRight() {
		int width = getWidth();
		int height = getHeight();
		int arc = Math.min(width, roundTopRight);
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, arc, arc));
		area.add(new Area(new Rectangle2D.Double(0, 0, width - arc / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(0, arc / 2.0, width, height - arc / 2.0)));
		return area;
	}

	private Shape createRoundBottomLeft() {
		int width = getWidth();
		int height = getHeight();
		int arc = Math.min(width, roundBottomLeft);
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, arc, arc));
		area.add(new Area(new Rectangle2D.Double(arc / 2.0, 0, width - arc / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - arc / 2.0)));
		return area;
	}

	private Shape createRoundBottomRight() {
		int width = getWidth();
		int height = getHeight();
		int arc = Math.min(width, roundBottomRight);
		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, arc, arc));
		area.add(new Area(new Rectangle2D.Double(0, 0, width - arc / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - arc / 2.0)));
		return area;
	}
}

package tcms.custom_gui_components;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CustomRoundedPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Corner radii
	private int roundTopLeft = 0;
	private int roundTopRight = 0;
	private int roundBottomLeft = 0;
	private int roundBottomRight = 0;

	// Gradient color stops
	private List<ModelColor> colors = new ArrayList<>();

	public CustomRoundedPanel() {
		initComponents();
		setOpaque(false); // We paint the background manually
	}

	private void initComponents() {
		setLayout(new BorderLayout()); // Important to allow adding child components
	}

	// ------------------ Public Setters / Getters -------------------
	public int getRoundTopLeft() { return roundTopLeft; }
	public void setRoundTopLeft(int val) { roundTopLeft = val; repaint(); }

	public int getRoundTopRight() { return roundTopRight; }
	public void setRoundTopRight(int val) { roundTopRight = val; repaint(); }

	public int getRoundBottomLeft() { return roundBottomLeft; }
	public void setRoundBottomLeft(int val) { roundBottomLeft = val; repaint(); }

	public int getRoundBottomRight() { return roundBottomRight; }
	public void setRoundBottomRight(int val) { roundBottomRight = val; repaint(); }

	public void addColor(ModelColor... newColors) {
		for (ModelColor c : newColors) {
			this.colors.add(c);
		}
		repaint();
	}

	// ------------------ Paint Logic -------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // ✅ This MUST be first to support child components

		int width = getWidth();
		int height = getHeight();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Create a rounded area shape
		Area area = new Area(createRoundedShape());

		// Use gradient if set
		if (!colors.isEmpty()) {
			Color[] colorArr = new Color[colors.size()];
			float[] posArr = new float[colors.size()];
			for (int i = 0; i < colors.size(); i++) {
				colorArr[i] = colors.get(i).getColor();
				posArr[i] = colors.get(i).getPosition();
			}
			LinearGradientPaint gradient = new LinearGradientPaint(0, 0, width, height, posArr, colorArr);
			g2.setPaint(gradient);
		} else {
			g2.setColor(getBackground());
		}

		// Fill the rounded area
		g2.fill(area);
		g2.dispose();
	}

	// ------------------ Rounded Shape -------------------
	private Shape createRoundedShape() {
		int width = getWidth();
		int height = getHeight();

		// Use average arc values (rounded rectangle)
		int arcWidth = Math.max(roundTopLeft, roundTopRight);
		int arcHeight = Math.max(roundBottomLeft, roundBottomRight);

		// Fallback if all are 0
		if (arcWidth == 0 && arcHeight == 0) {
			return new Rectangle2D.Double(0, 0, width, height);
		}

		RoundRectangle2D rounded = new RoundRectangle2D.Double(
			0, 0, width, height,
			Math.max(roundTopLeft, roundTopRight) * 2,
			Math.max(roundBottomLeft, roundBottomRight) * 2
		);
		return rounded;
	}
}

package tcms.custom_gui_components;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Font;

public final class HeaderButton {
	/**DO NOT REMOVE THIS COMMENT !!!
	 * @wbp.factory
	 * @wbp.factory.parameter.source icon new javax.swing.ImageIcon("src\\tcms\\resources\\profile.png")
	 * @wbp.factory.parameter.source text "<html>\r\n<body>\r\n<p>Update</p>\r\n<p>Profile</p>\r\n</body>\r\n</html>"
	 */
	public static CustomJButton createHeaderButton(Icon icon, String text) {
		CustomJButton customJButton = new CustomJButton();
		customJButton.setRadius(25);
		customJButton.setIcon(icon);
		customJButton.setText(text);
		customJButton.setBackground(new Color(96, 76, 195));
		customJButton.setForeground(new Color(220, 221, 222));
		customJButton.setBorder(null);
		customJButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		customJButton.setColorClick(new Color(60, 69, 165));
		customJButton.setColor(new Color(88, 101, 242));
		customJButton.setColorOver(new Color(79, 82, 196));
		customJButton.setBorderColor(new Color(43, 45, 49));
		customJButton.setFocusable(false);
		return customJButton;
	}
}
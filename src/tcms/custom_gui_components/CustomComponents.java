package tcms.custom_gui_components;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public final class CustomComponents {
	/** DO NOT REMOVE THIS COMMENT !!!
	 * @wbp.factory
	 * @wbp.factory.parameter.source text contact
	 */
	public static JTextField createJTextField(String text) {
		JTextField textField = new JTextField();
		textField.setForeground(new Color(220, 221, 222));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setText(text);
		textField.setEnabled(false);
		textField.setDisabledTextColor(new Color(79, 84, 92));
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 31, 34)));
		textField.setBackground(new Color(56, 58, 65));
		return textField;
	}
	/**
	 * @wbp.factory
	 */
	public static CustomJButton createReturnBtn() {
		CustomJButton customJButton = new CustomJButton();
		customJButton.setRadius(15);
		customJButton.setText("<- Return to dashboard");
		customJButton.setBackground(new Color(96, 76, 195));
		customJButton.setForeground(new Color(220, 221, 222));
		customJButton.setBorder(null);
		customJButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		customJButton.setColorClick(new Color(60, 69, 165));
		customJButton.setColor(new Color(88, 101, 242));
		customJButton.setColorOver(new Color(79, 82, 196));
		customJButton.setBorderColor(new Color(43, 45, 49));
		customJButton.setFocusPainted(false);
		return customJButton;
	}
}
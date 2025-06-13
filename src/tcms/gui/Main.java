package tcms.gui;
import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage loginPage = new LoginPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

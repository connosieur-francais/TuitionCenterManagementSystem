package tcms.main;
import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					LoginPage loginPage = new LoginPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

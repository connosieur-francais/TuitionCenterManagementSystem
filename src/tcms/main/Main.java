package tcms.main;
import java.awt.EventQueue;

import tcms.data.SystemInitializer;
import tcms.data.SystemInitializer.ManagerBundle;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerBundle managerBundle = SystemInitializer.initialize();
					new LoginPage(managerBundle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

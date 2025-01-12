package uii;

public class MainUI {

	    public static void main(String[] args) {
	        // Lansează aplicația cu fereastra de login
	        javax.swing.SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
	        System.out.println("Aplicația a pornit!");
	    }
	}



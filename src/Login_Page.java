import java.awt.*;
import Test.*;

public class Login_Page {
	Login_Page(){
		Frame f = new Frame("Attendance Management System");
        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
        new Login_class(f);
	}
    public static void main(String[] args) {
    	new Login_Page();
    }
}


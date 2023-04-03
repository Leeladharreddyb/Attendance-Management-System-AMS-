package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Faculty_after_login {
	public Faculty_after_login(Frame f, String username, String subject){
		f.removeAll();
		f.repaint();
		Label l1 = new Label("Welcome! Mr." + username );
		l1.setFont(new Font("Arial", Font.BOLD, 24));
	    l1.setAlignment(Label.CENTER);  
	    l1.setBounds(100,50,350,40);
	    f.add(l1);
		Font font = new Font("Times new roman", Font.PLAIN, 20);
		Button b1 = new Button("Logout");
		b1.setFont(font);
		b1.setBounds(200,350,200,35);
		f.add(b1);
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login_class(f);
            }
        });
		Button b2 = new Button("Mark Attendance");
		b2.setFont(new Font("Times new roman", Font.PLAIN, 20));
		b2.setBounds(200,250,200,35);
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Select_Class_and_Section(f,username, subject);
            }
        });
		f.add(b2);
	}
}

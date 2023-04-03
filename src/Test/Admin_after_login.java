package Test;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Admin_after_login {
	Admin_after_login(Frame f,String username){
		f.removeAll();
		f.repaint();
		Scrollbar sc = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 101);
		f.add(sc);
		Label l1 = new Label("Welcome! Mr." + username );
		l1.setFont(new Font("Arial", Font.BOLD, 24));
	    l1.setAlignment(Label.CENTER);  
	    l1.setBounds(100,50,350,40);
	    f.add(l1);
	    Font font = new Font("Times new roman", Font.PLAIN, 20);
		Button b1 = new Button("Logout");
		b1.setFont(font);
		b1.setBounds(190,500,150,30);
		f.add(b1);
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login_class(f);
            }
        });
		Button b2 = new Button("Add a new Faculty Member");
		b2.setFont(font);
		b2.setBounds(150,200,250,30);
		f.add(b2);
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Add_Faculty(f,username);
            }
        });
		Button b3 = new Button("Add a new Student");
		b3.setFont(font);
		b3.setBounds(150,250,250,30);
		f.add(b3);
		b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Add_Student_Details(f, username);
            }
        });
		Button b4 = new Button("Faculty Details");
		b4.setFont(font);
		b4.setBounds(150,300,250,30);
		f.add(b4);
		b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Details_Faculty_Students().Details_Faculty();
            }
        });
		Button b5 = new Button("Student Details");
		b5.setFont(font);
		b5.setBounds(150,350,250,30);
		f.add(b5);
		b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Details_Faculty_Students().Details_Students();
            }
        });
		Button b6 = new Button("Remove an existing Faculty");
		b6.setFont(font);
		b6.setBounds(150,400,250,30);
		f.add(b6);
		b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Delete_Faculty(f,username);
            }
        });
		Button b7 = new Button("Remove an existing Student");
		b7.setFont(font);
		b7.setBounds(150,450,250,30);
		f.add(b7);
		b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Delete_Student(f,username);
            }
        });
	}
}

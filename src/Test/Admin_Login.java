package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class Admin_Login {
	private TextField usernameField, passwordField;
	Admin_Login(Frame f){
		f.removeAll();
		f.repaint();
		Button b1 = new Button("Back");
		b1.setBounds(200,400,200,35);
		Font font = new Font("Arial", Font.PLAIN, 20);
        b1.setFont(font);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login_class(f);
			}
		});
		f.add(b1);
		Label titleLabel = new Label("Admin Login");
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	    titleLabel.setAlignment(Label.CENTER);  
	    titleLabel.setBounds(250,50,150,40);
	    f.add(titleLabel);
	    Label usernameLabel = new Label("Username :");
	    usernameLabel.setFont(font);
	    usernameLabel.setBounds(120,140,150,50);
	    f.add(usernameLabel);
	    usernameField = new TextField();
	    usernameField.setFont(font);
	    usernameField.setBounds(270,155,200,30);
	    usernameField.addTextListener(new TextListener() {
	    	public void textValueChanged(TextEvent e) {
	            if(usernameField.getText().length() > 16) {
	            	usernameField.setText(usernameField.getText().substring(0, 16));
	            }
	        }
	    });
	    f.add(usernameField);
	    Label passwordLabel = new Label("Password:");
	    passwordLabel.setFont(font);
	    passwordLabel.setBounds(120,190,150,50);
	    f.add(passwordLabel);
	    passwordField = new TextField();
	    passwordField.setEchoChar('*');  
	    passwordField.setFont(font);
	    passwordField.setBounds(270,205,200,30);
	    passwordField.addTextListener(new TextListener() {
	    	public void textValueChanged(TextEvent e) {
	            if(passwordField.getText().length() > 16) {
	            	passwordField.setText(passwordField.getText().substring(0, 16));
	            }
	        }
	    });
	    f.add(passwordField);
	    Button loginButton = new Button("Login");
	    loginButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	   	 String username = usernameField.getText();
         String password = passwordField.getText();
         try {
 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
 			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM admin_login_details WHERE user_id = ? AND passcode = ?");
 			if(username.length() == 0) {
				JOptionPane.showMessageDialog(f, " Please enter User Id", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(password.length() == 0) {
				JOptionPane.showMessageDialog(f, "Please enter Password", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				if(!new ifNumber().ifnumber(username)) {
					JOptionPane.showMessageDialog(f, "Username must be a number", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					pstmt.setInt(1, Integer.parseInt(username));
		 		    pstmt.setString(2, password);
		 		    ResultSet rs = pstmt.executeQuery();
		 			if(rs.next()) {
		 				pstmt = con.prepareStatement("select admin_name from admin_details where admin_id = ?;");
		 				pstmt.setInt(1,Integer.parseInt(username));
		 				try {
		 					rs = pstmt.executeQuery();
		 			        if (rs.next()) {
		 			            username = rs.getString(1);
		 			            new Admin_after_login(f,username);
		 			        }
		 			    }
		 				catch (SQLException e1) {
		 					e1.printStackTrace();
		 				}
		 			}
		 			else {
		 				JOptionPane.showMessageDialog(f, "Incorrect User Id or Password", "Message", JOptionPane.INFORMATION_MESSAGE);
		 			}
				}
			}
 			con.close();
 		} 
 		catch (SQLException e1) {
 			e1.printStackTrace();
 		}
        }
	    });
	    loginButton.setFont(font);
	    loginButton.setBounds(200,300,200,35);
	    f.add(loginButton);
	}
}

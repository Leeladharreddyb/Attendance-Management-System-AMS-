package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Add_Faculty {
	Add_Faculty(Frame f, String username){
		f.removeAll();
		f.repaint();
		int ID = new ifNumber().searchIdforFaculty();
		Label l1 = new Label("Add a new Faculty Member");
		l1.setFont(new Font("Arial", Font.BOLD, 24));
	    l1.setAlignment(Label.CENTER);  
	    l1.setBounds(100,50,350,40);
	    f.add(l1);
		Label nameLabel = new Label("Name:");
        Label idLabel = new Label("ID:");
        Label ageLabel = new Label("Age:");
        Label subjectLabel = new Label("Subject:");
        Label passcodeLabel = new Label("Passcode:");
        TextField nameField = new TextField();
        TextField idField = new TextField(""+ID);
        idField.setEditable(false);
        TextField ageField = new TextField();
        TextField subjectField = new TextField();
        TextField passcodeField = new TextField();
        Font font = new Font("Times new roman", Font.PLAIN, 20);
        nameLabel.setBounds(100, 100, 100, 30);
        nameLabel.setFont(font);
        idLabel.setBounds(100, 150, 100, 30);
        idLabel.setFont(font);
        ageLabel.setBounds(100, 200, 100, 30);
        ageLabel.setFont(font);
        subjectLabel.setBounds(100, 250, 100, 30);
        subjectLabel.setFont(font);
        passcodeLabel.setBounds(100, 300, 100, 30);
        passcodeLabel.setFont(font);
        nameField.setBounds(250, 100, 150, 30);
        nameField.setFont(font);
        idField.setBounds(250, 150, 150, 30);
        idField.setFont(font);
        ageField.setBounds(250, 200, 150, 30);
        ageField.setFont(font);
        subjectField.setBounds(250, 250, 150, 30);
        subjectField.setFont(font);
        passcodeField.setBounds(250, 300, 150, 30);
        passcodeField.setFont(font);
        f.add(nameLabel);
        f.add(idLabel);
        f.add(ageLabel);
        f.add(subjectLabel);
        f.add(passcodeLabel);
        f.add(nameField);
        f.add(idField);
        f.add(ageField);
        f.add(subjectField);
        f.add(passcodeField);
        Button b1 = new Button("Back");
        b1.setFont(font);
        b1.setBounds(200,400,100,30);
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Admin_after_login(f,username);
            }
        });
        Button b2 = new Button("Submit");
        b2.setFont(font);
        b2.setBounds(200,350,100,30);
        f.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText();
                String id = idField.getText();
                String age = ageField.getText();
                String subject = subjectField.getText();
                String pass = passcodeField.getText();
                if(name.length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(idField.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(ageField.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(subject.length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(pass.length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                	if(!name.matches("^[a-zA-Z]*$")) {
                		JOptionPane.showMessageDialog(f, " Only Alphabets are allowed in Name", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!new ifNumber().ifnumber(id)) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Id", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!new ifNumber().ifnumber(age)) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Age", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!subject.matches("^[a-zA-Z]*$")) {
                		JOptionPane.showMessageDialog(f, " Only Alphabets are allowed in Name", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else {
                		new Add_to_base(f,name,Integer.parseInt(id),Integer.parseInt(age),subject,pass,username);
                	}
                }
            }
        });
	}
}

class Add_to_base{
	Add_to_base(Frame f,String name,int id,int age,String subject,String password,String username){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement pstmt = con.prepareStatement("select user_id from faculty_login_details where user_id = ?;");
			pstmt.setInt(1, id);
			ResultSet r = pstmt.executeQuery();
			if(r.next()) {
				JOptionPane.showMessageDialog(f, " Duplicate Value ", "Message", JOptionPane.INFORMATION_MESSAGE);
				con.close();
			}
			pstmt = con.prepareStatement("insert into faculty_details values (?, ?, ?, ?, ?);");
			PreparedStatement pstmt1 = con.prepareStatement("insert into faculty_login_details values (?, ?);");
		    pstmt.setString(1, name);
		    pstmt.setInt(2, id);
		    pstmt.setInt(3, age);
		    pstmt.setString(4, subject);
		    pstmt.setString(5, password);
		    pstmt1.setInt(1, id);
		    pstmt1.setString(2, password);
		    int rs = pstmt.executeUpdate();
		    int rs1 = pstmt1.executeUpdate();
		    JOptionPane.showMessageDialog(f, " Details Saved ", "Message", JOptionPane.INFORMATION_MESSAGE);
		    new Add_Faculty(f, username);
		    con.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ifNumber{
	boolean ifnumber(String s) {
		char[] a = s.toCharArray();
		for(char t : a) {
			if(t <'0' || t>'9') return false;
		}
		return true;
	}
	int searchIdforFaculty() {
		int cnt = 0;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement p = con.prepareStatement("select * from faculty_login_details");
			ResultSet r = p.executeQuery();
			while(r.next()) {
				cnt++;
			}
			cnt += 100;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	int searchIdforStudent() {
		int cnt = 0;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement p = con.prepareStatement("select * from student_login_details");
			ResultSet r = p.executeQuery();
			while(r.next()) {
				cnt++;
			}
			cnt += 1000;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
}
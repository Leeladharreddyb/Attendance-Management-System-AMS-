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

public class Add_Student_Details {
	Add_Student_Details(Frame f, String username){
		f.removeAll();
		f.repaint();
		int ID = new ifNumber().searchIdforStudent();
		Label l1 = new Label("Add a new Student");
		l1.setFont(new Font("Arial", Font.BOLD, 24));
	    l1.setAlignment(Label.CENTER);  
	    l1.setBounds(100,50,350,40);
	    f.add(l1);
		Label name = new Label("Name:");
        Label reg = new Label("Registration No.:");
        Label Class = new Label("Class:");
        Label section = new Label("Section:");
        Label rollno = new Label("Roll No.:");
        Label phone = new Label("Phone No.:");
        Label address = new Label("Address:");
        Label pass = new Label("Password:");
        TextField namet = new TextField();
        TextField regt = new TextField(""+ID);
        regt.setEditable(false);
        TextField Classt = new TextField();
        TextField sectiont = new TextField();
        TextField rollnot = new TextField();
        TextField phonet = new TextField();
        TextField addresst = new TextField();
        TextField passt = new TextField();
        Font font = new Font("Times new roman", Font.PLAIN, 20);
        name.setBounds(100, 100, 150, 30);
        name.setFont(font);
        reg.setBounds(100, 150, 150, 30);
        reg.setFont(font);
        Class.setBounds(100, 200, 150, 30);
        Class.setFont(font);
        section.setBounds(100, 250, 150, 30);
        section.setFont(font);
        rollno.setBounds(100, 300, 150, 30);
        rollno.setFont(font);
        phone.setBounds(100, 350, 150, 30);
        phone.setFont(font);
        address.setBounds(100, 400, 150, 30);
        address.setFont(font);
        pass.setBounds(100, 450, 150, 30);
        pass.setFont(font);
        namet.setBounds(300, 100, 150, 30);
        namet.setFont(font);
        regt.setBounds(300, 150, 150, 30);
        regt.setFont(font);
        Classt.setBounds(300, 200, 150, 30);
        Classt.setFont(font);
        sectiont.setBounds(300, 250, 150, 30);
        sectiont.setFont(font);
        rollnot.setBounds(300, 300, 150, 30);
        rollnot.setFont(font);
        phonet.setBounds(300, 350, 150, 30);
        phonet.setFont(font);
        addresst.setBounds(300, 400, 150, 30);
        addresst.setFont(font);
        passt.setBounds(300, 450, 150, 30);
        passt.setFont(font);
        f.add(name);
        f.add(reg);
        f.add(Class);
        f.add(section);
        f.add(rollno);
        f.add(phone);
        f.add(address);
        f.add(namet);
        f.add(regt);
        f.add(Classt);
        f.add(sectiont);
        f.add(rollnot);
        f.add(phonet);
        f.add(addresst);
        f.add(pass);
        f.add(passt);
        Button b1 = new Button("Back");
        b1.setFont(font);
        b1.setBounds(200,550,100,30);
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Admin_after_login(f,username);
            }
        });
        Button b2 = new Button("Submit");
        b2.setFont(font);
        b2.setBounds(200,500,100,30);
        f.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(namet.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(regt.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(Classt.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(sectiont.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rollnot.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(phonet.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(addresst.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(passt.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " All Fields are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                	if(!namet.getText().matches("^[a-zA-Z]*$")) {
                		JOptionPane.showMessageDialog(f, " Only Alphabets are allowed in Name", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!new ifNumber().ifnumber(regt.getText())) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Id", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!Classt.getText().matches("^[a-zA-Z]*$")) {
                		JOptionPane.showMessageDialog(f, " Only Alphabets are allowed in Class", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!sectiont.getText().matches("^[a-zA-Z]*$")) {
                		JOptionPane.showMessageDialog(f, " Only Alphabets are allowed in Section", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!new ifNumber().ifnumber(rollnot.getText())) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Roll No", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else if(!new ifNumber().ifnumber(phonet.getText())) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Phone number", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
                	else {
                		new Add_to_bases(f,namet.getText(),Integer.parseInt(regt.getText()),Classt.getText(),
                				sectiont.getText(),Integer.parseInt(rollnot.getText()),phonet.getText(),
                				addresst.getText(),passt.getText(),username);
                	}
                }
            }
        });
	}
}

class Add_to_bases{
	Add_to_bases(Frame f,String name,int reg,String Class,String section,int rollno,String phone,String address,String password, String username){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement pstmt = con.prepareStatement("select user_id from student_login_details where user_id = ?;");
			pstmt.setInt(1, reg);
			ResultSet r = pstmt.executeQuery();
			if(r.next()) {
				JOptionPane.showMessageDialog(f, " Duplicate Value ", "Message", JOptionPane.INFORMATION_MESSAGE);
				con.close();
			}
			pstmt = con.prepareStatement("insert into students values (?, ?, ?, ?, ?, ?, ?, ?);");
			PreparedStatement pstmt1 = con.prepareStatement("insert into student_login_details values (?, ?);");
		    pstmt.setString(1, name);
		    pstmt.setInt(2, reg);
		    pstmt.setString(3, Class);
		    pstmt.setString(4, section);
		    pstmt.setInt(5, rollno);
		    pstmt.setString(6, phone);
		    pstmt.setString(7, address);
		    pstmt.setString(8, password);
		    pstmt1.setInt(1, reg);
		    pstmt1.setString(2, password);
		    int rs = pstmt.executeUpdate();
		    int rs1 = pstmt1.executeUpdate();
		    JOptionPane.showMessageDialog(f, " Details Saved ", "Message", JOptionPane.INFORMATION_MESSAGE);
		    new Add_Student_Details(f,username);
		    con.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class Delete_Student {
	Delete_Student(Frame f, String username){
		f.removeAll();
		f.repaint();
	    Font font = new Font("Times new roman", Font.PLAIN, 20);
		Label l1 = new Label("Remove Student from Database");
		l1.setFont(new Font("Arial", Font.BOLD, 24));
		l1.setAlignment(Label.CENTER);  
	    l1.setBounds(100,50,400,40);
	    f.add(l1);
	    Label idLabel = new Label("Registration No.:");
	    idLabel.setBounds(100, 150, 150, 30);
        idLabel.setFont(font);
        f.add(idLabel);
        TextField idField = new TextField();
        idField.setBounds(300, 150, 150, 30);
        idField.setFont(font);
        f.add(idField);
	    Button b1 = new Button("Back");
        b1.setFont(font);
        b1.setBounds(200,250,100,30);
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Admin_after_login(f,username);
            }
        });
        Button b2 = new Button("Submit");
        b2.setFont(font);
        b2.setBounds(200,200,100,30);
        f.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String id = idField.getText();
            	if(idField.getText().length() == 0) {
                	JOptionPane.showMessageDialog(f, " The Field are compulsory", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            	else {
            		if(!new ifNumber().ifnumber(id)) {
                		JOptionPane.showMessageDialog(f, " Only numbers are allowed in Registration No.", "Message", JOptionPane.INFORMATION_MESSAGE);
                	}
            		else {
            			new Delete_from_bases(f,id);
            		}
            	}
            }
        });
	}
}

class Delete_from_bases{
	Delete_from_bases(Frame f, String id){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement pstmt = con.prepareStatement("select registration_no from students where registration_no = ?;");
			pstmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = pstmt.executeQuery();
			if(!rs.next()) {
				JOptionPane.showMessageDialog(f, " Invalid Registration No.", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				pstmt = con.prepareStatement("delete from students where registration_no = ?;");
				pstmt.setInt(1, Integer.parseInt(id));
				pstmt.executeUpdate();
				pstmt = con.prepareStatement("delete from student_login_details where user_id = ?;");
				pstmt.setInt(1, Integer.parseInt(id));
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(f, " Removed Student with this Registration No. from database", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			con.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

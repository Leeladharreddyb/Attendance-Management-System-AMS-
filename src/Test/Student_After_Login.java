package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class Student_After_Login {
	Student_After_Login(Frame f, String username, int id){
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
		Button b2 = new Button("Attendance");
		b2.setFont(font);
		b2.setBounds(200,200,200,35);
		f.add(b2);
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkAttendance(f,username,id);
            }
        });
		Label l2 = new Label("Total Attendance : ");
		l2.setFont(font);
		l2.setBounds(100,150,200,30);
		f.add(l2);
		double a = (int)calculateAttendance(id);
		Label l3 = new Label("" + a + "%");
		l3.setFont(font);
		l3.setBounds(300,150,100,30);
		f.add(l3);
	}
	double calculateAttendance(int id) {
		int attended = 0, total = 0;
		try {
  			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
  			PreparedStatement pstmt = con.prepareStatement("SELECT attended,total FROM student_attendance_details WHERE registration_no = ?;");
  			pstmt.setInt(1, id);
  			ResultSet rs = pstmt.executeQuery();
  			while(rs.next()) {
  				attended += rs.getInt("attended");
  				total += rs.getInt("total");
  			}
  			con.close();
  		} 
  		catch (SQLException e1) {
  			e1.printStackTrace();
  		}
		return (attended*100)/total;
	}
	void checkAttendance(Frame f, String username, int id) {
		f.removeAll();
		f.repaint();
		Font font = new Font("Times new roman", Font.PLAIN, 20);
		Label l1 = new Label("Select Class and Section to Check Attendance");
		l1.setFont(new Font("Arial", Font.BOLD, 20));
	    l1.setAlignment(Label.CENTER);  
	    l1.setBounds(50,50,500,40);
	    f.add(l1);
		Button b1 = new Button("Back");
        b1.setFont(font);
        b1.setBounds(200,400,100,30);
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Student_After_Login(f, username, id);
            }
        });
        Button b2 = new Button("Check Attendance");
        b2.setFont(font);
        b2.setBounds(200,350,200,30);
        f.add(b2);
        Label l2 = new Label("Select Subject : ");
		l2.setFont(font); 
	    l2.setBounds(100,200,150,40);
	    f.add(l2);
        Choice c1 = new Choice();
        c1.add("--select--");
        addItemsInChoiceOfSubjects(c1);
        c1.setBounds(300,210,100,70);
        f.add(c1);
        c1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedSubject = c1.getSelectedItem();
                if(selectedSubject.equals("--select--")) {
                	JOptionPane.showMessageDialog(f, "Select a subject to check attendance", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        String selectedClass = c1.getSelectedItem();
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedSubject = c1.getSelectedItem();
            	if(selectedSubject.equals("--select--")) {
                	JOptionPane.showMessageDialog(f, "Select a subject to check attendance", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            	if(selectedSubject.equals("Subject-wise")) new Check_Attendance_Jtable().Check_Attendance_SubjectWise(f,id);
            	else new Check_Attendance_Jtable().Check_Attendance_For_Each_Subject(f,selectedSubject, id);
            }
        });
	}
	void addItemsInChoiceOfSubjects(Choice c) {
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			PreparedStatement p = con.prepareStatement("select distinct subject from faculty_details");
			ResultSet r = p.executeQuery();
			while(r.next()) {
				c.add(r.getString("subject"));
			}
			c.add("Subject-wise");
			con.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

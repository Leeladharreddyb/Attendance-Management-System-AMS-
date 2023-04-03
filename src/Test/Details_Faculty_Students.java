package Test;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;


public class Details_Faculty_Students extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton backButton;
	void Details_Faculty(){
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT name, id, age, subject FROM faculty_details");
		    DefaultTableModel model = new DefaultTableModel();
		      model.addColumn("Name");
		      model.addColumn("ID");
		      model.addColumn("Age");
		      model.addColumn("Subject");
		      while(rs.next()) {
		         Object[] row = new Object[4];
		         row[0] = rs.getString("name");
		         row[1] = rs.getInt("id");
		         row[2] = rs.getInt("age");
		         row[3] = rs.getString("subject");
		         model.addRow(row);
		      }
		      table = new JTable(model);
		      add(new JScrollPane(table));
		      setTitle("My Table");
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      setLocationRelativeTo(null);
		      pack();
		      setSize(1920,1080);
		      setVisible(true);
		      backButton = new JButton("Back");
		      backButton.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		        	  dispose();
		          }
		       });
		      add(backButton, BorderLayout.SOUTH);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void Details_Students() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT student_name, registration_no, class, section, roll_no, parentnumber,address FROM students");
		    DefaultTableModel model = new DefaultTableModel();
		      model.addColumn("Name");
		      model.addColumn("Registration No");
		      model.addColumn("Class");
		      model.addColumn("Section");
		      model.addColumn("Roll No.");
		      model.addColumn("Parent Phone Number");
		      model.addColumn("Address");

		      while(rs.next()) {
		         Object[] row = new Object[7];
		         row[0] = rs.getString("student_name");
		         row[1] = rs.getInt("registration_no");
		         row[2] = rs.getString("class");
		         row[3] = rs.getString("section");
		         row[4] = rs.getInt("roll_no");
		         row[5] = rs.getString("parentnumber");
		         row[6] = rs.getString("address");
		         model.addRow(row);
		      }
		      table = new JTable(model);
		      add(new JScrollPane(table));
		      setTitle("My Table");
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      setLocationRelativeTo(null);
		      pack();
		      setSize(1920,1080);
		      setVisible(true);
		      backButton = new JButton("Back");
		      backButton.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
		        	  dispose();
		          }
		       });
		      add(backButton, BorderLayout.SOUTH);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

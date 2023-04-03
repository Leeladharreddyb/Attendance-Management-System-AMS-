package Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class Check_Attendance_Jtable extends JFrame{
	void Check_Attendance_For_Each_Subject(Frame f, String subject, int id){
		setTitle("Attendance history for " + subject);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] columnNames = {"Faculty Name", "Date of Attended", "Attendance detail"};
        Object[][] data = attendanceDetailForEachSubject(subject, id);
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	void Check_Attendance_SubjectWise(Frame f, int id) {
		setTitle("Attendance details Subject-Wise");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] columnNames = {"Subject", "Faculty Name", "Last Attended", "Total Attended", "Total Conducted", "Attendance Percentage"};
        Object[][] data = getAttendanceDetails(id);
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	Object[][] getAttendanceDetails(int id) {
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	    	PreparedStatement p = con.prepareStatement("SELECT distinct subject from student_attendance_details where registration_no = ?;");
	    	p.setInt(1, id);
	    	ResultSet rs = p.executeQuery();
	    	int rowCount = 0;
	    	while(rs.next()) {
	    		rowCount++;
	    	}
	    	rs = p.executeQuery();
	    	rowCount += 2;
	        Object[][] data = new Object[rowCount][6];
	        int i=0;
	        int attended1, total1;
	        while(rs.next()) {
	        	data[i][0] = rs.getString("subject");
	        	p = con.prepareStatement("select name from faculty_details where subject = ?");
	        	p.setString(1, data[i][0].toString());
	        	ResultSet r = p.executeQuery();
	        	if(r.next()) {
	        		data[i][1] = r.getString("name");
	        	}
	        	p = con.prepareStatement("select attendance_date from student_attendance_details where subject = ? and registration_no = ?");
	        	p.setString(1, data[i][0].toString());
	        	p.setInt(2, id);
	        	r = p.executeQuery();
	        	Vector<String> v = new Vector<String>();
	        	while(r.next()) {
	        		v.add(r.getString("attendance_date").toString());
	        	}
	        	Collections.sort(v);
	        	data[i][2] = v.lastElement().substring(0, 10);
	        	p = con.prepareStatement("SELECT attended,total FROM student_attendance_details WHERE registration_no = ? and subject = ?;");
	  			p.setInt(1, id);
	  			p.setString(2, data[i][0].toString());
	  			r = p.executeQuery();
	  			attended1 = 0;
	  			total1 = 0;
	  			while(r.next()) {
	  				attended1 += r.getInt("attended");
	  				total1 += r.getInt("total");
	  			}
	  			data[i][3] = attended1;
	  			data[i][4] = total1;
	  			data[i][5] = (attended1*100)/total1;
	        	i++;
	        }
	        data[i+1][0] = "Total Attendance";
	        data[i+1][1] = " for all the";
	        data[i+1][2] = i+1 + " subjects";
	        p = con.prepareStatement("SELECT attended,total FROM student_attendance_details WHERE registration_no = ?;");
  			p.setInt(1, id);
  			ResultSet r = p.executeQuery();
  			int a = 0,t = 0;
  			while(r.next()) {
  				a += r.getInt("attended");
  				t += r.getInt("total");
  			}
  			data[i+1][3] = a;
  			data[i+1][4] = t;
  			data[i+1][5] = (a*100)/t;
	        con.close();
	        return data;
	    } 
		catch (SQLException ex) {
	        ex.printStackTrace();
	        return new Object[0][0];
	    }
	}
	Object[][] attendanceDetailForEachSubject(String subject, int id) {
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	    	PreparedStatement p = con.prepareStatement("SELECT attendance_date,detail from student_attendance_details where registration_no = ? and subject = ?;");
	    	p.setInt(1, id);
	    	p.setString(2, subject);
	    	ResultSet rs = p.executeQuery();
	    	int rowCount = 0;
	    	while(rs.next()) {
	    		rowCount++;
	    	}
	    	rs = p.executeQuery();
	        Object[][] data = new Object[rowCount][3];
	        int i=0;
	        while(rs.next()) {
	        	data[i][1] = rs.getString("attendance_date").toString();
	        	p = con.prepareStatement("select name from faculty_details where subject = ?");
	        	p.setString(1, subject);
	        	ResultSet r = p.executeQuery();
	        	if(r.next()) {
	        		data[i][0] = r.getString("name");
	        	}
	        	data[i][2] = rs.getString("detail");
	        	i++;
	        }
	        con.close();
	        return data;
	    } 
		catch (SQLException ex) {
	        ex.printStackTrace();
	        return new Object[0][0];
	    }
	}
}

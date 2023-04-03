package Test;
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Marking_Attendance extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Marking_Attendance(String c, String s, String subject, Frame f){
		setTitle("Attendance Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] columnNames = {"Name", "Registration No.", "Class", "Section", "Roll No.", "Attendance"};
        Object[][] data = getStudentData(c,s);
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        String[] attendanceOptions = {"present", "absent"};
        JComboBox<String> attendanceComboBox = new JComboBox<>(attendanceOptions);
        table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(attendanceComboBox));
        JButton markAttendanceButton = new JButton("Mark attendance");
        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from JTable
                Object[][] tableData = new Object[table.getRowCount()][2];
                for (int i = 0; i < table.getRowCount(); i++) {
                    int registrationNo = (int) table.getValueAt(i, 1);
                    String attendance = (String) table.getValueAt(i, 5);
                    Object[] row = {registrationNo, attendance};
                    tableData[i] = row;
                }
                if(saveAttendance(tableData, subject, f)) {
                	JOptionPane.showMessageDialog(f, "Attendance Marked", "Message", JOptionPane.INFORMATION_MESSAGE);
                	dispose();
                }
                else JOptionPane.showMessageDialog(f, "Attendance not Marked", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(markAttendanceButton, BorderLayout.EAST);
        buttonPanel.add(backButton, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	boolean saveAttendance(Object[][] a, String subject, Frame f) {
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	    	PreparedStatement pstmt = con.prepareStatement("Insert into student_attendance_details values(?, ?, ?, ?, ?, ?);");
	    	for (Object[] row : a) {
	    		 int reg = (int) row[0];
	    		 String d = (String) row[1];
                pstmt.setInt(1, reg);
                if(d.equals("present")) {
                	pstmt.setInt(2, 1);
                }
                else {
                	pstmt.setInt(2, 0);
                }
                pstmt.setInt(3, 1);
                pstmt.setObject(4, LocalDateTime.now());
                pstmt.setString(5, d);
                pstmt.setString(6, subject);
                if(pstmt.executeUpdate() == 1) {
                	return true;
                }
            }
	    	 con.close();
	    	 return false;
	    } 
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		return false;
	}
	Object[][] getStudentData(String c, String s) {
	    try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	    	PreparedStatement p = con.prepareStatement("SELECT student_name,registration_no,class,section,roll_no FROM students where class = ? and section = ?;");
	    	p.setString(1, c);
	    	p.setString(2, s);
	        ResultSet rs = p.executeQuery();
	        int rowCount = 0;
	        while (rs.next()) {
	            rowCount++;
	        }
	        p = con.prepareStatement("SELECT student_name,registration_no,class,section,roll_no FROM students where class = ? and section = ?;");
	        p.setString(1, c);
	    	p.setString(2, s);
	        rs = p.executeQuery();
	        Object[][] data = new Object[rowCount][6];
	        int i = 0;
	        while (rs.next()) {
	            String name = rs.getString(1);
	            int id = rs.getInt(2);
	            String Class = rs.getString(3);
	            String section = rs.getString(4);
	            int roll = rs.getInt(5);
	            data[i][0] = name;
	            data[i][1] = id;
	            data[i][2] = Class;
	            data[i][3] = section;
	            data[i][4] = roll;
	            data[i][5] = "present";
	            i++;
	        }
	        con.close();
	        return data;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return new Object[0][0];
	    }
	}
}

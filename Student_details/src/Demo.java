import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Demo {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtnum;
	private JTextField txtcgpa;
	private JTextField txtsid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo window = new Demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo() {
		initialize();
		connect();
		table();
	
	}
	 Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 private JTable table;
	
	public void connect() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/firstproject", "root","");
			
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
		
	}
	void table()
	{
		try
		{
			pst=con.prepareStatement("SELECT * FROM table1");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 5));
		frame.setBounds(100, 100, 1028, 784);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STUDENT  DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(263, 40, 339, 66);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 116, 366, 211);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("* STUDENT NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(32, 48, 138, 13);
		panel.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(180, 42, 162, 28);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("* R.G. NUMBER");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(32, 103, 144, 13);
		panel.add(lblNewLabel_1_1);
		
		txtnum = new JTextField();
		txtnum.setColumns(10);
		txtnum.setBounds(180, 97, 162, 28);
		panel.add(txtnum);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("* CURRENT CGPA");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(32, 160, 144, 13);
		panel.add(lblNewLabel_1_1_1);
		
		txtcgpa = new JTextField();
		txtcgpa.setColumns(10);
		txtcgpa.setBounds(180, 154, 162, 28);
		panel.add(txtcgpa);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,num,cgpa;
				
				name=txtname.getText();
				num=txtnum.getText();
				cgpa=txtcgpa.getText();
				try
				{
					pst=con.prepareStatement("INSERT into table1(name,rgnum,cgpa)values(?,?,?)");
					pst.setString(1, name);
					pst.setString(2, num);
					pst.setString(3, cgpa);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "response Recorded");
					table();
					txtname.setText("");
					txtnum.setText("");
					txtcgpa.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(51, 348, 119, 47);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(180, 348, 119, 47);
		frame.getContentPane().add(btnExit);
		
		JButton btnExit_1 = new JButton("CLEAR");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				txtname.setText("");
				txtnum.setText("");
				txtcgpa.setText("");
				txtname.requestFocus();
			}
			
			
			
		});
		btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit_1.setBounds(309, 348, 119, 47);
		frame.getContentPane().add(btnExit_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(54, 442, 366, 172);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("SEARCH ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 0, 63, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("STUDENT ID");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(21, 39, 113, 41);
		panel_1.add(lblNewLabel_3);
		
		txtsid = new JTextField();
		txtsid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
		       
				try
				{
					String id=txtsid.getText();
					pst=con.prepareStatement("select name,rgnum,cgpa from table1 where id=?");
					pst.setString(1, id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						String name=rs.getString(1);
						String num=rs.getString(2);
						String cgpa=rs.getString(3);
						
						txtname.setText(name);
						txtnum.setText(num);
						txtcgpa.setText(cgpa);
					}
					else
					{
						txtname.setText("");
						txtnum.setText("");
						txtcgpa.setText("");
						
					}
				}
				catch(SQLException ex)
				{
					
				}
				
				
				
			}
		});
		txtsid.setBounds(144, 39, 121, 41);
		panel_1.add(txtsid);
		txtsid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String name,num,cgpa,sid;
				
				name=txtname.getText();
				num=txtnum.getText();
				cgpa=txtcgpa.getText();
				sid=txtsid.getText();
				try
				{
					pst=con.prepareStatement("update table1 set name=?,rgnum=?,cgpa=? where id=?");
					pst.setString(1, name);
					pst.setString(2, num);
					pst.setString(3, cgpa);
					pst.setString(4, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "record updated");
					table();
					txtname.setText("");
					txtnum.setText("");
					txtcgpa.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(21, 111, 133, 41);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                 String sid;
				
				
				sid=txtsid.getText();
				try
				{
					pst=con.prepareStatement("delete from table1  where id=?");
					
					pst.setString(1, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "record deleted");
					table();
					txtname.setText("");
					txtnum.setText("");
					txtcgpa.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
			
				
				
				
				
			}
		});
		btnNewButton_1_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1_1.setBounds(202, 111, 133, 41);
		panel_1.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 152, 356, 462);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}

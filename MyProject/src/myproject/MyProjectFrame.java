/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.*;
/**
 *
 * @author Sagar
 */
public class MyProjectFrame extends JFrame implements ActionListener {

    JButton btnAdmin, btnEmp, btnLoginAdmin, btnLoginEmp, btnExitAdmin, btnExitEmp, btnAbout;
    JLabel lblAdminInfo, lblAdminUsername, lblAdminPassword, lblSystemInfo;
    JLabel lblEmpInfo, lblEmpUsername, lblEmpPassword;
    JPanel jplmaster, jplSelect, jplLogin;
    JTextField txtAdminUsername, txtAdminPassword, txtEmpUsername, txtEmpPassword;
    SingleConnection sco=new SingleConnection();
    String inEmpPassword, dbEmpPassword, dbEmpAdmin;
    int inEmpUsername, dbEmpUsername;
    String inAdminPassword, dbAdminPassword, dbAdminAdmin;
    int inAdminUsername, dbAdminUsername;
        
        public MyProjectFrame()
	{
		btnAdmin = new JButton("Admin Login");
                btnAdmin.setToolTipText("Login panel for admin");
		btnEmp = new JButton("Employee Login");
		btnEmp.setToolTipText("Login panel for employees");

		JPanel jplmaster=new JPanel();
		jplmaster = new JPanel(new BorderLayout());

		jplSelect=new JPanel();
		jplSelect.setLayout(new GridLayout(1,2));
		jplSelect.add(btnEmp);
		jplSelect.add(btnAdmin);
				
		jplLogin=new JPanel();
		JLabel img=new JLabel();
                img.setIcon(new ImageIcon(MyProjectFrame.class.getResource("/pizza.jpg")));
                img.setBounds(0, 0, 500, 500);
                jplLogin.add(img);
                
		add(jplmaster);
		jplmaster.add(jplSelect,BorderLayout.NORTH);
		jplmaster.add(jplLogin,BorderLayout.CENTER);
			
		btnAdmin.addActionListener(this);
                btnEmp.addActionListener(this);
                

	}
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
      
         if(e.getSource()==btnAdmin)
            {
                jplLogin.removeAll();
                
                jplLogin.setLayout(new GridBagLayout());
                jplLogin.setBackground(Color.WHITE);
                GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(10,10,10,10);
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.fill=GridBagConstraints.HORIZONTAL;
                
                lblAdminInfo = new JLabel("Admin Login",SwingConstants.CENTER);
                lblAdminInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 30));
                gbc.ipady = 0;
                gbc.gridwidth = 2;
                gbc.gridx=0;
                gbc.gridy=0;
                jplLogin.add(lblAdminInfo,gbc);
                
                lblAdminUsername=new JLabel("Admin Id :", SwingConstants.RIGHT);
                gbc.gridwidth = 1;
                gbc.gridx=0;
                gbc.gridy=1;
                jplLogin.add(lblAdminUsername,gbc);

                txtAdminUsername = new JTextField(10);
                gbc.gridx=1;
                gbc.gridy=1;
                jplLogin.add(txtAdminUsername,gbc);
	
                lblAdminPassword=new JLabel("Admin Password :", SwingConstants.RIGHT);
                gbc.gridx=0;
                gbc.gridy=2;
                jplLogin.add(lblAdminPassword,gbc);

                txtAdminPassword = new JPasswordField(10);
                gbc.gridx=1;
                gbc.gridy=2;
                jplLogin.add(txtAdminPassword,gbc);
                
                btnExitAdmin=new JButton("Exit");
                gbc.gridx=0;
                gbc.gridy=3;
                jplLogin.add(btnExitAdmin,gbc);
                btnExitAdmin.addActionListener(this);
                
                btnLoginAdmin=new JButton("Login");
                gbc.gridx=1;
                gbc.gridy=3;
                jplLogin.add(btnLoginAdmin,gbc);
                
                repaint();
                revalidate();
                
                btnLoginAdmin.addActionListener(this);
            }
            
            if(e.getSource()==btnEmp)
            {
                jplLogin.removeAll();
                jplLogin.setBackground(Color.WHITE);
                jplLogin.setLayout(new GridBagLayout());
	
                GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(10,10,10,10);
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.fill=GridBagConstraints.HORIZONTAL;
                
                lblEmpInfo = new JLabel("Employee Login",SwingConstants.CENTER);
                lblEmpInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 30));
                gbc.ipady = 0;
                gbc.gridwidth = 2;
                gbc.gridx=0;
                gbc.gridy=0;
                jplLogin.add(lblEmpInfo,gbc);
                
                lblEmpUsername=new JLabel("Employee Id :", SwingConstants.RIGHT);
                gbc.gridwidth = 1;
                gbc.gridx=0;
                gbc.gridy=1;
                jplLogin.add(lblEmpUsername,gbc);

                txtEmpUsername = new JTextField(10);
                gbc.gridx=1;
                gbc.gridy=1;
                jplLogin.add(txtEmpUsername,gbc);
	
                lblEmpPassword=new JLabel("Employee Password :", SwingConstants.RIGHT);
                gbc.gridx=0;
                gbc.gridy=2;
                jplLogin.add(lblEmpPassword,gbc);

                txtEmpPassword = new JPasswordField(10);
                gbc.gridx=1;
                gbc.gridy=2;
                jplLogin.add(txtEmpPassword,gbc);
                
                btnExitEmp=new JButton("Exit");
                gbc.gridx=0;
                gbc.gridy=3;
                jplLogin.add(btnExitEmp,gbc);
                btnExitEmp.addActionListener(this);
                
                btnLoginEmp=new JButton("Login");
                gbc.gridx=1;
                gbc.gridy=3;
                jplLogin.add(btnLoginEmp,gbc);
                btnLoginEmp.addActionListener(this);
                
                repaint();
                revalidate();
            }
            
            
            
            
            
            if(e.getSource()==btnLoginEmp)
            {
                sco.runconn();
                
                      
                try
                {
                    inEmpUsername=Integer.parseInt(txtEmpUsername.getText()); 
                    inEmpPassword=txtEmpPassword.getText();
                    PreparedStatement pstmt = sco.conn.prepareStatement("select id,password,admin from employee where id=?");
                    pstmt.setInt(1,inEmpUsername);
                    ResultSet rs=pstmt.executeQuery();
                    while(rs.next())
                    {
                       dbEmpUsername=rs.getInt(1);
                       dbEmpPassword=rs.getString(2);
                       dbEmpAdmin=rs.getString(3);
                    }
                    sco.conn.close();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
                
               if(txtEmpUsername.getText().equals(""))  
               {
                   JOptionPane.showMessageDialog(null, "Username cannot be empty");
                   txtEmpUsername.grabFocus();
               }
               else if(!(Pattern.matches("[0-9]+", txtEmpUsername.getText()))) 
                {
                    JOptionPane.showMessageDialog(null, "Id field cannot have alphabets or special characters");
                    txtEmpUsername.grabFocus();
                }
               else if (!(Integer.parseInt(txtEmpUsername.getText())==dbEmpUsername))
                {
                    JOptionPane.showMessageDialog(null, "No such employee id");
                    txtEmpUsername.grabFocus();
                }
               else if(txtEmpPassword.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty");
                    txtEmpPassword.grabFocus();
                }
                else if(!(txtEmpPassword.getText().equals(dbEmpPassword)))
                {
                    JOptionPane.showMessageDialog(null, "Wrong password");
                    txtEmpPassword.grabFocus();
                }
               else if(dbEmpAdmin.equals("yes"))
                {
                    JOptionPane.showMessageDialog(null, "This employee id has admin privilages\nUse admin login panel to access system");
                    txtEmpPassword.grabFocus();
                }
               else
               {
                    EmpAccessFrame eaf=new EmpAccessFrame();
                    eaf.setVisible(true);
                    eaf.setSize(500,400);
                    eaf.setLocationRelativeTo(null);
                    eaf.setTitle("Pizza Sales System");
                    eaf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                   this.dispose();
                   
               }
               
                
            }
            
            if(e.getSource()==btnLoginAdmin)
            {
                sco.runconn();
                     
                try
                {
                    inAdminUsername=Integer.parseInt(txtAdminUsername.getText()); 
                    inAdminPassword=txtAdminPassword.getText();
                    PreparedStatement pstmt = sco.conn.prepareStatement("select id,password,admin from employee where id=?");
                    pstmt.setInt(1,inAdminUsername);
                    ResultSet rs=pstmt.executeQuery();
                    while(rs.next())
                    {
                       dbAdminUsername=rs.getInt(1);
                       dbAdminPassword=rs.getString(2);
                       dbAdminAdmin=rs.getString(3);
                    }
                    sco.conn.close();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
                
               if(txtAdminUsername.getText().equals(""))  
               {
                   JOptionPane.showMessageDialog(null, "Username cannot be empty");
                   txtAdminUsername.grabFocus();
               }
               else if(!(Pattern.matches("[0-9]+", txtAdminUsername.getText()))) 
                {
                    JOptionPane.showMessageDialog(null, "Id field cannot have alphabets or special characters");
                    txtAdminUsername.grabFocus();
                }
               else if (!(Integer.parseInt(txtAdminUsername.getText())==dbAdminUsername))
                {
                    JOptionPane.showMessageDialog(null, "No such employee id");
                    txtAdminUsername.grabFocus();
                }
               else if(txtAdminPassword.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty");
                    txtAdminPassword.grabFocus();
                }
                else if(!(txtAdminPassword.getText().equals(dbAdminPassword)))
                {
                    JOptionPane.showMessageDialog(null, "Wrong password");
                    txtAdminPassword.grabFocus();
                }
               else if(dbAdminAdmin.equals("no"))
                {
                    JOptionPane.showMessageDialog(null, "This employee id does not have admin privilages\nUse admin login panel to access system");
                    txtAdminPassword.grabFocus();
                }
               else
               {
                    AdminAccessFrame aaf=new AdminAccessFrame();
                    aaf.setVisible(true);
                    aaf.setSize(500,400);
                    aaf.setLocationRelativeTo(null);
                    aaf.setTitle("Pizza Sales System");
                    aaf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    this.dispose();
                   
               }
            }
            
            if(e.getSource()==btnExitAdmin)
            {
                System.exit(0);
            }
            
            if(e.getSource()==btnExitEmp)
            {
                System.exit(0);
            }
            
            
    }
        
    public static void main(String[] args) {
        MyProjectFrame m = new MyProjectFrame();
		m.setVisible(true);
		m.setSize(500,400);
                m.setLocationRelativeTo(null);
		m.setTitle("Pizza Sales System");
		m.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
    
}

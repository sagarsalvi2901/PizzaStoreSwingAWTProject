/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class UpdateEmpPassword extends JFrame implements ActionListener {
        JLabel lblId,lblPassword1,lblPassword2, lblFrameInfo;
        JTextField txtId,txtPassword1,txtPassword2;
        JButton btnUpdate,btnExit;
        JPanel jp;
        int inId, comId, upId;
        String upPassword;
    
    public UpdateEmpPassword() {
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
	
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Update Employee Password ",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
        
        gbc.gridwidth = 1;
        lblId = new JLabel("Employee Id");
	gbc.gridx=0;
	gbc.gridy=1;
	jp.add(lblId,gbc);

        txtId = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=1;
	jp.add(txtId,gbc);
        
        lblPassword1 = new JLabel("Enter Password");
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblPassword1,gbc);

        txtPassword1 = new JPasswordField(10);
	gbc.gridx=1;
	gbc.gridy=2;
	jp.add(txtPassword1,gbc);
	
        lblPassword2 = new JLabel("Re-Enter Passowrd");
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblPassword2,gbc);

        txtPassword2=new JPasswordField(10);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtPassword2,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=4;
	jp.add(btnExit,gbc);

	
	btnUpdate = new JButton("Update passoword");
        gbc.gridx=1;
	gbc.gridy=4;
	jp.add(btnUpdate,gbc);
        
        add(jp);
        
        btnUpdate.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnUpdate)
        {
            SingleConnection sco = new SingleConnection();
            sco.runconn();
            
            try{
            inId=Integer.parseInt(txtId.getText());
            PreparedStatement pstmt = sco.conn.prepareStatement("select * from employee where id=?");
            pstmt.setInt(1,inId);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                    {
                    comId=rs.getInt(1);
                    
                    }
            //sco.conn.close();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            
            if(txtId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Id field cannot be empty");
                txtId.grabFocus();
            }
            else if(!(Pattern.matches("[0-9]+", txtId.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Id field cannot have alphabets or special characters");
                txtId.grabFocus();
            }
            else if(inId!=comId)
            {
                JOptionPane.showMessageDialog(null, "No such employee id");
                txtId.grabFocus();
            }
            else if(txtPassword1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Password field cannot be empty");
                txtPassword1.grabFocus();
            }
            else if(!(Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", txtPassword1.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Password should be atleast 6 characters long\nPassword must have one lowercase, one uppercase, one numerical & one special character");
                txtPassword1.grabFocus();
            }
            else if(txtPassword2.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Password field cannot be empty");
                txtPassword2.grabFocus();
            }
            else if(!(txtPassword1.getText().equals(txtPassword2.getText())))
            {
                JOptionPane.showMessageDialog(null, "Passwords do not match");
                txtPassword2.grabFocus();
            }
            else
            {
                try
                {
                upPassword=txtPassword2.getText();
                upId=Integer.parseInt(txtId.getText());
                PreparedStatement pstmt=sco.conn.prepareStatement("update employee set password=? where id=?");
                pstmt.setString(1,upPassword);
                pstmt.setInt(2, upId);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Password Updated");
                txtId.setText("");
                txtPassword1.setText("");
                txtPassword2.setText("");
                
                sco.conn.close();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
                
            }
            
        }
        
        if(e.getSource()== btnExit)
        {
            this.dispose();
        }
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]) {
        UpdateEmpPassword uep=new UpdateEmpPassword();
        uep.setVisible(true);
        uep.setSize(400,400);
        uep.setLocationRelativeTo(null);
        uep.setTitle("Pizza Sales System");
        uep.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

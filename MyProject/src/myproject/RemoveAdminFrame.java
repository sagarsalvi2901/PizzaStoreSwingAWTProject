/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RemoveAdminFrame extends JFrame implements ActionListener, ItemListener{

    JLabel lblFrameInfo, lblId, lblName;
    JButton btnMake,btnExit;
    JTextField txtName;
    JPanel jp;
    JComboBox cmbId;
    ResultSet rs;
    Statement stmt;
    int a, comId, nameId;
    String comAdmin, x, cmbName, dbName , displayName;
    SingleConnection sco = new SingleConnection(); 
    
    public RemoveAdminFrame() {
        
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
	
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Revoke Admin Privilages",SwingConstants.CENTER);
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
  
        cmbId=new JComboBox();
        sco.runconn();
        try{
        stmt = sco.conn.createStatement();
        rs=stmt.executeQuery("select id from employee");
        while(rs.next())
            {    
            cmbId.addItem(Integer.toString(rs.getInt(1)));
            }
        }
        catch(Exception ex)
            {
            System.out.println(ex);
            }
	gbc.gridx=1;
	gbc.gridy=1;
	jp.add(cmbId,gbc);
        
        lblName = new JLabel("Employee Name");
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblName,gbc);
        
        txtName = new JTextField(10);
        txtName.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=2;
	jp.add(txtName,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=3;
	jp.add(btnExit,gbc);

	btnMake = new JButton("Revoke Admin");
        gbc.gridx=1;
	gbc.gridy=3;
	jp.add(btnMake,gbc);
        
        add(jp);
        
        btnMake.addActionListener(this);
        btnExit.addActionListener(this);
        cmbId.addItemListener(this);
        
        add(jp);
        
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnMake)
        {
            x = (String) cmbId.getSelectedItem();
            a=Integer.parseInt(x);
            sco.runconn();
            try
            {
            PreparedStatement pstmt = sco.conn.prepareStatement("select admin from employee where id=?");
            pstmt.setInt(1,a);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                    {
                    comAdmin=rs.getString(1);
                    }
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
            
            if(comAdmin.equals("no"))
            {
                JOptionPane.showMessageDialog(null, "Employee id "+x+" does not have admin privilages");
            }
            else if(comAdmin.equals("yes"))
            {
                int response = JOptionPane.showConfirmDialog(null, "Do you want to revoke admin privilages of employee id "+x, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) 
			{
                            int y=Integer.parseInt(x);
                            String upAdmin="no";
                            try
                            {
                            PreparedStatement pstmt=sco.conn.prepareStatement("update employee set admin=? where id=?");
                            pstmt.setString(1,upAdmin);
                            pstmt.setInt(2, y);
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Privilages Updated");
                            sco.conn.close();
                            }
                            catch(Exception ex)
                            {
                            System.out.println(ex);
                            }
			
    			}
                    
            }
            
        }
     if(e.getSource()== btnExit)
                    {
                     this.dispose();
                    } 
        
        
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        
        
             displayName = (String) evt.getItem();
             int nameId = Integer.parseInt(displayName);

        try
            {
            PreparedStatement pstmt = sco.conn.prepareStatement("select name from employee where id=?");
            pstmt.setInt(1,nameId);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                    {
                    dbName=rs.getString(1);
                    }
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
        
        txtName.setText(dbName);
    
        
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
      RemoveAdminFrame rf=new RemoveAdminFrame();
      rf.setVisible(true);
      rf.setSize(400,300);
      rf.setLocationRelativeTo(null);
      rf.setTitle("Pizza Sales System");
      rf.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class NewMenuItemFrame extends JFrame implements ActionListener {

    JLabel lblFrameInfo, lblMenuItem, lblCategory, lblPrice;
    JComboBox cmbCategory;
    JTextField txtMenuItem, txtPrice;
    JButton btnAdd, btnExit;
    JPanel jp;
    String dbMenuItem, dbCategory; 
    int dbPrice;
    SingleConnection sco=new SingleConnection();
    
    
    
    public NewMenuItemFrame() {
        
        sco.runconn();
       
        jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        lblFrameInfo = new JLabel("Add new menu item",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
                
                
        gbc.gridwidth = 1;
        lblMenuItem = new JLabel("Enter new menu item to add",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=1;
	jp.add(lblMenuItem,gbc);

        txtMenuItem = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=1;
	jp.add(txtMenuItem,gbc);
        
        lblCategory = new JLabel("Select category for menu item :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblCategory,gbc);
        
        cmbCategory=new JComboBox();
        sco.runconn();
        try{
        Statement stmt = sco.conn.createStatement();
        ResultSet rs=stmt.executeQuery("select category_name from category");
        while(rs.next())
            {    
            cmbCategory.addItem(rs.getString(1));
            }
        }
        catch(Exception ex)
            {
            System.out.println(ex);
            }
	gbc.gridx=1;
	gbc.gridy=2;
	jp.add(cmbCategory,gbc);
       
        lblPrice = new JLabel("Enter price of menu item :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblPrice,gbc);

        txtPrice = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtPrice,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=4;
	jp.add(btnExit,gbc);

	btnAdd = new JButton("Add new menu");
        gbc.gridx=1;
	gbc.gridy=4;
	jp.add(btnAdd,gbc);
        
        add(jp);
        
        btnAdd.addActionListener(this);
        btnExit.addActionListener(this);
        
    }

  
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==btnAdd)
        {
            
            if(txtMenuItem.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Menu item field cannot be empty");
                txtMenuItem.grabFocus();
            }
            else if(!(Pattern.matches("^[\\p{L} .'-]+$", txtMenuItem.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Menu item field cannot have numbers or special characters");
                txtMenuItem.grabFocus();
            }
            else if(txtPrice.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Price field cannot be empty");
                txtPrice.grabFocus();
            }
            else if(!(Pattern.matches("[0-9]+", txtPrice.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Price field cannot have alphabets or special characters");
                txtPrice.grabFocus();
            }
            else
            {
                dbCategory=(String)cmbCategory.getSelectedItem();
                dbMenuItem=txtMenuItem.getText();
                dbPrice=Integer.parseInt(txtPrice.getText());
                sco.runconn();
                
                try {
                    PreparedStatement pstmt= sco.conn.prepareStatement("insert into items(item_name,price,category_name) values(?,?,?)");
                    pstmt.setString(1,dbMenuItem);
                    pstmt.setInt(2,dbPrice);
                    pstmt.setString(3,dbCategory);
                    pstmt.execute();
                    
                    sco.conn.close();
                
                
                } catch (SQLException ex) {
                    Logger.getLogger(NewCategoryFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                txtMenuItem.setText("");
                txtPrice.setText("");
                JOptionPane.showMessageDialog(null, "New menu item added");
            }
        }
        
         if(e.getSource()== btnExit)
        {
            try {
                sco.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SearchServiceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
    
    
    }
    
    
      public static void main(String args[]) {
        NewMenuItemFrame nmi=new NewMenuItemFrame();
        nmi.setVisible(true);
        nmi.setSize(400,300);
        nmi.setLocationRelativeTo(null);
        nmi.setTitle("Pizza Sales System");
        nmi.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

   

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

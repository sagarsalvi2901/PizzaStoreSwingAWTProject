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


public class NewCategoryFrame extends JFrame implements ActionListener {

    JLabel lblFrameInfo, lblCategory;
    JTextField txtCategory;
    JButton btnAdd, btnExit;
    JPanel jp;
    String dbCategory;
    SingleConnection sco=new SingleConnection();
    
    public NewCategoryFrame() {
        
        sco.runconn();
        
        jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        lblFrameInfo = new JLabel("Add new menu category",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
                
                
        gbc.gridwidth = 1;
        lblCategory = new JLabel("Enter new category to add");
	gbc.gridx=0;
	gbc.gridy=1;
	jp.add(lblCategory,gbc);

        txtCategory = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=1;
	jp.add(txtCategory,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=2;
	jp.add(btnExit,gbc);

	
	btnAdd = new JButton("Add new category");
        gbc.gridx=1;
	gbc.gridy=2;
	jp.add(btnAdd,gbc);
        
        add(jp);
        
        btnAdd.addActionListener(this);  
        btnExit.addActionListener(this);
    }

    
     @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        if(e.getSource()==btnAdd)
        {
            if(txtCategory.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Category field cannot be empty");
                txtCategory.grabFocus();
            }
            else if(!(Pattern.matches("^[\\p{L} .'-]+$", txtCategory.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Category field cannot have alphabets or special characters");
                txtCategory.grabFocus();
            }
            else
            {
                dbCategory=txtCategory.getText();
                
                
                
                try {
                    PreparedStatement pstmt= sco.conn.prepareStatement("insert into category(category_name) values(?)");
                    pstmt.setString(1,dbCategory);
                    pstmt.execute();
                    
                    sco.conn.close();
                
                
                } catch (SQLException ex) {
                    Logger.getLogger(NewCategoryFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                txtCategory.setText("");
                JOptionPane.showMessageDialog(null, "New category added");
            }
        }
    
            if(e.getSource()== btnExit)
        {
          
            try {
                sco.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewCategoryFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.dispose();
        }
     
    }
    
    
    
    public static void main(String args[]) {
  
        NewCategoryFrame ncf=new NewCategoryFrame();
        ncf.setVisible(true);
        ncf.setSize(400,300);
        ncf.setLocationRelativeTo(null);
        ncf.setTitle("Pizza Sales System");
        ncf.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Boolean.FALSE;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class FoodOrderFrame extends JFrame implements ItemListener, ActionListener{
    JPanel jp, js;
    JLabel lblFrameInfo, lblQuantity, lblId, lblName, lblCategory, lblTotal;
    JTextField txtQuantity, txtId, txtName, txtTotal;
    SingleConnection sco = new SingleConnection();
    JComboBox cmbCategories;
    JList lstItems, lstOrders;
    String selectCategory, dbItem, lstSelectedItem, lstQuantity, confirmItem, deleteItem;
    DefaultListModel listModel, orderlistModel;
    JTable table;
    int numRows, getQuantity=0, confirmQuantity, dbId, itemPrice, totalItemPrice, deleteQuantity;
    int totalAmount, deleteAmount, clearAmount;
    JButton btnAdd, btnClear, btnConfirm, btnExit, btnTotal;
    DefaultTableModel tableModel;
    String confirmName; 
    int confirmId, confirmAmount;
    
    
    
    public FoodOrderFrame() {
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
        js = new JPanel();
	js.setLayout(new GridBagLayout());
	
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Pizza Order Panel",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 4;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
        
        gbc.gridwidth = 2;
        lblId = new JLabel("Order Id",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=1;
	jp.add(lblId,gbc);
        
        txtId=new JTextField();
        txtId.setEditable(FALSE);
        sco.runconn();
        try{
        Statement stmt = sco.conn.createStatement();
        ResultSet rs=stmt.executeQuery("select order_id from order_details order by order_id desc limit 1");
        while(rs.next())
            {    
            dbId=rs.getInt(1);
            }
        }
        catch(Exception ex)
            {
            System.out.println(ex);
            }
        int dbsId=dbId+1;
        String str2 = Integer.toString(dbsId);
        txtId.setText(str2);
        gbc.gridx=2;
	gbc.gridy=1;
	jp.add(txtId,gbc);
        
        lblName = new JLabel("Customer Name",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblName,gbc);

        txtName = new JTextField(10);
	gbc.gridx=2;
	gbc.gridy=2;
	jp.add(txtName,gbc);
        
        lblCategory = new JLabel("Select a category", SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblCategory,gbc);
        
        cmbCategories=new JComboBox();
        sco.runconn();
        try{
            Statement stmt = sco.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select category_name from category");
            while(rs.next())
            {    
            cmbCategories.addItem(rs.getString(1));
            }
            }
        catch(Exception ex)
            {
            System.out.println(ex);
            }
        gbc.gridx=2;
	gbc.gridy=3;
	jp.add(cmbCategories,gbc);
        
        listModel = new DefaultListModel();
        lstItems=new JList(listModel);
        lstItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstItems.setLayoutOrientation(JList.VERTICAL);
        lstItems.setVisibleRowCount(8);
        JScrollPane listScrollPane = new JScrollPane(lstItems, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.setPreferredSize(new Dimension(250, 150));
        gbc.gridx=0;
	gbc.gridy=4;
        jp.add(listScrollPane,gbc);
     
        String[] colHeadings = {"Item Name","Quantity"};
        int numRows = 0 ;
        tableModel = new DefaultTableModel(numRows, colHeadings.length) ;
        tableModel.setColumnIdentifiers(colHeadings);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null); 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableScrollPane.setPreferredSize(new Dimension(250, 150));
        gbc.gridx=2;
	gbc.gridy=4;
        jp.add(tableScrollPane,gbc);
        
        lblQuantity = new JLabel("Add required quantity for selected item :", SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=5;
	jp.add(lblQuantity,gbc);

        txtQuantity = new JTextField(10);
	gbc.gridx=2;
	gbc.gridy=5;
	jp.add(txtQuantity,gbc);
        
        btnAdd = new JButton("Add Item");
        gbc.gridx=0;
	gbc.gridy=6;
	jp.add(btnAdd,gbc);
        
        btnClear = new JButton("Clear Item");
        gbc.gridx=2;
	gbc.gridy=6;
	jp.add(btnClear,gbc);
        
        lblTotal = new JLabel("Total amount >>>", SwingConstants.RIGHT);
        gbc.gridx=0;
	gbc.gridy=7;
	jp.add(lblTotal,gbc);
        
        txtTotal = new JTextField(10);
        txtTotal.setEditable(FALSE);
        gbc.gridx=2;
	gbc.gridy=7;
	jp.add(txtTotal,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=8;
	jp.add(btnExit,gbc);
        
        btnConfirm = new JButton("Confirm Order");
        gbc.gridx=2;
	gbc.gridy=8;
	jp.add(btnConfirm,gbc);
        
        add(jp);
        
        cmbCategories.addItemListener(this);
        btnAdd.addActionListener(this); 
        btnClear.addActionListener(this);
        btnConfirm.addActionListener(this);
        btnExit.addActionListener(this);
        //btnTotal.addActionListener(this);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnExit)
        {
            this.dispose();
        }
        
        if(e.getSource()==btnAdd)
        {
            lstSelectedItem = (String)lstItems.getSelectedValue();
            lstQuantity=txtQuantity.getText();
            if(lstItems.isSelectionEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please select food category and food item");
            }
            else if(txtQuantity.getText().isEmpty())
            {
                 JOptionPane.showMessageDialog(null, "Please add required quantity for selected item");
                 txtQuantity.grabFocus();
            }
             else if(!(Pattern.matches("[0-9]+", txtQuantity.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Quantity field cannot have alphabets or special characters");
                txtQuantity.grabFocus();
            }
            else
            {
            getQuantity=Integer.parseInt(lstQuantity);
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.addRow(new Object[]{lstSelectedItem, getQuantity});  
           
            int a = table.getRowCount();
            //DefaultTableModel model=(DefaultTableModel) table.getModel(); 
                       
            try 
            {
            for(int i=0;i<a;i++)
            {
                confirmItem=(String)model.getValueAt(i,0);
                confirmQuantity = (Integer)model.getValueAt(i,1);
                PreparedStatement pstmt = sco.conn.prepareStatement("select price from items where item_name=?");
                pstmt.setString(1,confirmItem);
                ResultSet rs=pstmt.executeQuery();
                while(rs.next())
                    {
                    itemPrice=rs.getInt(1);
                    }
                
                totalItemPrice=itemPrice*confirmQuantity;
                
            } 
                totalAmount+=totalItemPrice;
                String strAmount = Integer.toString(totalAmount);
                txtTotal.setText(strAmount);
                
            }
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
            }
            
            txtQuantity.setText("");
            
            
            
        }
        
        
        if(e.getSource()==btnClear)
        {
            try 
            {
            deleteItem =table.getValueAt(table.getSelectedRow(), 0).toString();
            String getdeleteQuantity=table.getValueAt(table.getSelectedRow(), 1).toString();
            deleteQuantity=Integer.parseInt(getdeleteQuantity);
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
            
            int rowToRemove=table.getSelectedRow();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            if(rowToRemove>=0)
            {
            model.removeRow(rowToRemove);
            
            
            int a = table.getRowCount();
            
                      
            try 
            {
           
                PreparedStatement pstmt = sco.conn.prepareStatement("select price from items where item_name=?");
                pstmt.setString(1,deleteItem);
                ResultSet rs=pstmt.executeQuery();
                while(rs.next())
                    {
                    itemPrice=rs.getInt(1);
                    }
                
                deleteAmount=itemPrice*deleteQuantity;
               
             
                
                
                totalAmount=totalAmount-deleteAmount;
                String strAmount = Integer.toString(totalAmount);
                txtTotal.setText(strAmount);
                
            }
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
            
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Unable to delete");
            } 
            
            
            
            
        }
        
       
        
        if(e.getSource()==btnConfirm)
        {
            
           if(txtName.getText().isEmpty())
           {
                JOptionPane.showMessageDialog(null, "Enter Customer Name");
                txtName.grabFocus();
           }
           else if(!(Pattern.matches("^[\\p{L} .'-]+$", txtName.getText()))) 
           {
                JOptionPane.showMessageDialog(null, "Name field cannot have numbers or special characters");
                txtName.grabFocus();
           }
           else if(table.getRowCount()<=0)
           {
               JOptionPane.showMessageDialog(null, "No menu items selected");
           }
           else
           {
           int a = table.getRowCount();
           DefaultTableModel model=(DefaultTableModel) table.getModel(); 
            
           confirmId=Integer.parseInt(txtId.getText());
           confirmName=txtName.getText();
           confirmAmount=Integer.parseInt(txtTotal.getText());
           java.util.Date dt1=new java.util.Date();
           java.sql.Date dt2=new Date(dt1.getTime());
           
            try 
            {
            for(int i=0;i<a;i++)
            {
                confirmItem=table.getValueAt(i,0).toString();
                confirmQuantity = (Integer)model.getValueAt(i,1);
                PreparedStatement pstmt1 = sco.conn.prepareStatement("insert into service_details(item_name,quantity,order_id) values(?,?,?)");
                pstmt1.setString(1,confirmItem);
                pstmt1.setInt(2,confirmQuantity);
                pstmt1.setInt(3,confirmId);
                pstmt1.execute();
            } 
                  
                PreparedStatement pstmt2 = sco.conn.prepareStatement("insert into order_details(name,amount,date) values(?,?,?)");
                pstmt2.setString(1,confirmName);
                pstmt2.setInt(2,confirmAmount);
                pstmt2.setDate(3,dt2);
                pstmt2.execute();
                
                JOptionPane.showMessageDialog(null, "Order confirmed");
                listModel.clear();
                tableModel.setRowCount(0);
                txtQuantity.setText("");
                txtId.setText("");
                
                this.dispose();
                
                FoodOrderFrame fof=new FoodOrderFrame();
                fof.setVisible(true);
                fof.setSize(700,600);
                fof.setLocationRelativeTo(null);
                fof.setTitle("Pizza Sales System");
                fof.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
            
            
           }
           
        }
       
    }
  
    
    @Override
    public void itemStateChanged(ItemEvent evt) {

        selectCategory = (String) evt.getItem();
        listModel.clear();
        
        try
            {
            PreparedStatement pstmt = sco.conn.prepareStatement("select item_name from items where category_name=?");
            pstmt.setString(1,selectCategory);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                    {
                    
                    dbItem=rs.getString(1);
                    listModel.addElement(dbItem);
                    }
            }
            catch(Exception ex)
            {
            System.out.println(ex);
            }
            
    }



    

   
    public static void main(String args[]) {
      FoodOrderFrame fof=new FoodOrderFrame();
      fof.setVisible(true);
      fof.setSize(700,600);
      fof.setLocationRelativeTo(null);
      fof.setTitle("Pizza Sales System");
      fof.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
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

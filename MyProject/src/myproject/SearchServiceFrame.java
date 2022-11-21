
package myproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class SearchServiceFrame extends JFrame implements ActionListener {

    JLabel lblFrameInfo, lblOrderId, lblName, lblAmount, lblDate, lblId;
    JTextField txtOrderId, txtName, txtAmount, txtDate, txtId;
    JButton btnSearch, btnExit;
    JPanel jp;
    JTable table;
    DefaultTableModel tableModel;
    SingleConnection sco=new SingleConnection();
    String dbItemName, dbName;
    int dbAmount, dbQuantity;
    Date dbDate;
    
    public SearchServiceFrame() {
        
        sco.runconn();
        
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
        
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Order Details",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
        
        gbc.gridwidth = 1;
        lblId = new JLabel("Enter Order Id to Search :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=1;
	jp.add(lblId,gbc);

        txtId = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=1;
	jp.add(txtId,gbc);
        
        btnSearch = new JButton("Show Order Details");
        gbc.gridx=1;
	gbc.gridy=2;
	jp.add(btnSearch,gbc);
                
        gbc.gridwidth = 1;
        lblName = new JLabel("Customer Name :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblName,gbc);

        txtName = new JTextField(10);
        txtName.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtName,gbc);
        
        lblDate = new JLabel("Order Date :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=4;
	jp.add(lblDate,gbc);

        txtDate = new JTextField(10);
        txtDate.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=4;
	jp.add(txtDate,gbc);
        
        lblAmount = new JLabel("Total Amount :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=5;
	jp.add(lblAmount,gbc);

        txtAmount = new JTextField(10);
        txtAmount.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=5;
	jp.add(txtAmount,gbc);
        
        gbc.gridwidth = 2;
        tableModel = new DefaultTableModel(0, 2) ;
        tableModel.setColumnIdentifiers(new Object[]{"Menu Items","Quantity"});
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null); 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setPreferredSize(new Dimension(350, 150));
        gbc.gridx=0;
	gbc.gridy=6;
        jp.add(tableScrollPane,gbc);
        
        btnExit = new JButton("Exit Window");
        gbc.gridx=0;
	gbc.gridy=7;
	jp.add(btnExit,gbc);
        
        add(jp);
        
        btnSearch.addActionListener(this);
        btnExit.addActionListener(this);
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnSearch)
        {
            txtName.setText("");
            txtDate.setText("");
            txtAmount.setText("");
            
            DefaultTableModel model1=(DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            String inputId;
            int searchId=0;
            int compareId = 0;
            try
            {
                inputId=txtId.getText();
                searchId=Integer.parseInt(inputId);
                
                PreparedStatement pstmt1=sco.conn.prepareStatement("select order_id from order_details where order_id=?");
                pstmt1.setInt(1,searchId);
                ResultSet rs1=pstmt1.executeQuery();
                while(rs1.next())
                {
                    compareId=rs1.getInt(1);
                }
                
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            if(txtId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter order id");
                txtId.grabFocus();
            }
            else if(!(Pattern.matches("[0-9]+", txtId.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Order Id field cannot have alphabets or special characters");
                txtId.grabFocus();
            }
            else if(searchId!=compareId || searchId==0)
            {
                JOptionPane.showMessageDialog(null, "No such order id exists");
                txtId.grabFocus();
            }
            else
            {
                try
                {
                  
                PreparedStatement pstmt2=sco.conn.prepareStatement("select name,amount,date from order_details where order_id=?");
                pstmt2.setInt(1,searchId);
                ResultSet rs2=pstmt2.executeQuery();
                while(rs2.next())
                {
                    dbName=rs2.getString(1);
                    dbAmount=rs2.getInt(2);
                    dbDate=rs2.getDate(3);
                }
                
                txtName.setText(dbName);
                String amount_to_string = Integer.toString(dbAmount);
                txtAmount.setText(amount_to_string);
                String date_to_string = df.format(dbDate);
                txtDate.setText(date_to_string);
                
                PreparedStatement pstmt3=sco.conn.prepareStatement("select item_name,quantity from service_details where order_id=?");
                pstmt3.setInt(1,searchId);
                ResultSet rs3=pstmt3.executeQuery();
                while(rs3.next())
                {
                    dbItemName=rs3.getString(1);
                    dbQuantity=rs3.getInt(2);
                    
                    model1.addRow(new Object[]{dbItemName,dbQuantity});
                }
                
                
                
                }
                catch (Exception ex)
                {
                    
                }
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
        SearchServiceFrame ssf=new SearchServiceFrame();
        ssf.setVisible(true);
        ssf.setSize(500,600);
        ssf.setLocationRelativeTo(null);
        ssf.setTitle("Pizza Sales System");
        ssf.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

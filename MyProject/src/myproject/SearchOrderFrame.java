
package myproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class SearchOrderFrame extends JFrame implements ActionListener {

    JLabel lblFrameInfo, lblOrderId, lblOr, lblDate;
    JTextField txtOrderId, txtDate;
    JButton btnSearch, btnSearchAll, btnExit;
    JPanel jp;
    JTable table;
    DefaultTableModel tableModel;
    SingleConnection sco=new SingleConnection();
 
    public SearchOrderFrame() {
        
        sco.runconn();
        
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
        
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Sales Report",SwingConstants.CENTER);
        lblFrameInfo.setFont(new Font("Microsoft Sans Sheriff", Font.BOLD, 20));
        gbc.ipady = 0;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy=0;
        jp.add(lblFrameInfo,gbc);
                
        
        gbc.gridwidth = 1;
        lblDate = new JLabel("Enter Date to Search :",SwingConstants.RIGHT);
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblDate,gbc);

        txtDate = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtDate,gbc);
        
        btnSearchAll = new JButton("Show All Records");
        gbc.gridx=1;
	gbc.gridy=5;
	jp.add(btnSearchAll,gbc);
	
	btnSearch = new JButton("Show Record");
        gbc.gridx=1;
	gbc.gridy=4;
	jp.add(btnSearch,gbc);
        
        gbc.gridwidth = 2;
        tableModel = new DefaultTableModel(0, 4) ;
        tableModel.setColumnIdentifiers(new Object[]{"Order Id","Customer Name","Total Amount","Order Date"});
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null); 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setPreferredSize(new Dimension(450, 300));
        gbc.gridx=0;
	gbc.gridy=6;
        jp.add(tableScrollPane,gbc);
        
        
        btnExit = new JButton("Exit Window");
        gbc.gridx=0;
	gbc.gridy=7;
	jp.add(btnExit,gbc);
        
        add(jp);
        
        btnSearch.addActionListener(this);
        btnSearchAll.addActionListener(this);
        btnExit.addActionListener(this);
       
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSearch)
        {
            Date current = new Date();
            String dt1=txtDate.getText();
            java.util.Date  dt2 = null;
            try
            {
            dt2=new SimpleDateFormat("dd-MM-yyyy").parse(dt1);
            }
            catch(ParseException ex)
            {    
            }
            
            
            if(txtDate.getText().equals(""))
            {
                   JOptionPane.showMessageDialog(null, "Please enter date in dd-mm-yyyy format");
            }
            else if(!(Pattern.matches("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", txtDate.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Invalid date format\nDate should be in dd-mm-yyyy format");
                txtDate.grabFocus();
            }
            else if(dt2.after(current))
            {
                JOptionPane.showMessageDialog(null,"Entered date is future date\nPlease enter current or past date to see sales report");
            }
            else
            {
                try
                {
                DefaultTableModel model1=(DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String inputDate1=txtDate.getText();
                java.util.Date inputDate2=new SimpleDateFormat("dd-MM-yyyy").parse(inputDate1);
                java.sql.Date inputDate3=new java.sql.Date(inputDate2.getTime());
                
                PreparedStatement pstmt1=sco.conn.prepareStatement("select order_id,name,amount,date from order_details where date=? ");
                pstmt1.setDate(1,inputDate3);
                ResultSet rs1=pstmt1.executeQuery();
                
                while(rs1.next())
                {
                    int dbId1=rs1.getInt(1);
                    String dbName1=rs1.getString(2);
                    int dbAmount1=rs1.getInt(3);
                    Date dbDate1=rs1.getDate(4);
                    String date_to_string = df.format(dbDate1);
                    
                    model1.addRow(new Object[]{dbId1,dbName1,dbAmount1,date_to_string});
                    
                }
                
                }
                catch (Exception ex)
                {
                    
                }
            }
        }
        
        if(e.getSource()==btnSearchAll)
        {
            
            try
                {
                DefaultTableModel model2=(DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                
                
                Statement stmt = sco.conn.createStatement();
                ResultSet rs2=stmt.executeQuery("select order_id,name,amount,date from order_details");
                
                
                while(rs2.next())
                {
                    int dbId2=rs2.getInt(1);
                    String dbName2=rs2.getString(2);
                    int dbAmount2=rs2.getInt(3);
                    Date dbDate2=rs2.getDate(4);
                    String date_to_string1 = df.format(dbDate2);
                    
                    model2.addRow(new Object[]{dbId2,dbName2,dbAmount2,date_to_string1});
                    
                }
                
                }
                catch (Exception ex)
                {
                    
                }
            
        }
        
        
        
        
        if(e.getSource()==btnExit)
        {
            try {
                sco.conn.close();
            } catch (SQLException ex) {
           }
            this.dispose();
        }
   }

    
    
    
    public static void main(String args[]) {
     
        SearchOrderFrame sof=new SearchOrderFrame();
        sof.setVisible(true);
        sof.setSize(600,600);
        sof.setLocationRelativeTo(null);
        sof.setTitle("Pizza Sales System");
        sof.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

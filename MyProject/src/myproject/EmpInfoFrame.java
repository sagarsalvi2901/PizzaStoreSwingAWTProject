
package myproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class EmpInfoFrame extends JFrame implements ActionListener {

    JLabel lblAdmin, lblName,lblAddress,lblGender,lblEmail,lblContact,lblFrameInfo, lblId, lblDate;
    JTextField txtAdmin, txtName, txtEmail,txtContact, txtDate, txtId, txtGender;
    JTextArea txtAddress;
    JButton btnShow,btnExit;
    JRadioButton r1,r2;
    JPanel jp;
    JComboBox cmbId;
    JSpinner spin;
    int comId, inId, a;
    String x, dbName, dbAddress, dbGender, dbMail, dbContact, dbAdmin;
    Date dbDate;
    SingleConnection sco = new SingleConnection();
    int dbId;
   
    public EmpInfoFrame() {
        
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
        
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Employee Information",SwingConstants.CENTER);
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
        Statement stmt = sco.conn.createStatement();
        ResultSet rs=stmt.executeQuery("select id from employee");
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
        	
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=2;
	jp.add(btnExit,gbc);

	btnShow = new JButton("Show Information");
        gbc.gridx=1;
	gbc.gridy=2;
	jp.add(btnShow,gbc);
        
        lblName = new JLabel("Full Name");
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblName,gbc);

        txtName = new JTextField(10);
        txtName.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtName,gbc);
	
        lblAddress = new JLabel("Residential Address :");
	gbc.gridx=0;
	gbc.gridy=4;
	jp.add(lblAddress,gbc);

        txtAddress=new JTextArea(5,5);
        txtAddress.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=4;
	jp.add(txtAddress,gbc);
	
        lblGender = new JLabel("Gender :");
	gbc.gridx=0;
	gbc.gridy=5;
        jp.add(lblGender,gbc);
        
        txtGender = new JTextField(8);
        txtGender.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=5;
	jp.add(txtGender,gbc);
        
	lblEmail = new JLabel("E-mail Address :");
        gbc.gridx=0;
	gbc.gridy=6;
	jp.add(lblEmail,gbc);

        txtEmail = new JTextField(8);
        txtEmail.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=6;
	jp.add(txtEmail,gbc);
	
        lblContact = new JLabel("Contact Number :");
	gbc.gridx=0;
	gbc.gridy=7;
	jp.add(lblContact,gbc);

        txtContact = new JTextField(10);
        txtContact.setEditable(false);
	gbc.gridx=1;
	gbc.gridy=7;
	jp.add(txtContact,gbc);
        
        lblDate = new JLabel("Date of Joining :");
	gbc.gridx=0;
	gbc.gridy=8;
	jp.add(lblDate,gbc);

        txtDate = new JTextField();
        txtDate.setEditable(false);
        gbc.gridx=1;
	gbc.gridy=8;
        jp.add(txtDate,gbc);
        
        lblAdmin = new JLabel("Admin Privilages :");
	gbc.gridx=0;
	gbc.gridy=9;
	jp.add(lblAdmin,gbc);

        txtAdmin = new JTextField();
        txtAdmin.setEditable(false);
        gbc.gridx=1;
	gbc.gridy=9;
        jp.add(txtAdmin,gbc);
                
        add(jp);
        
        btnShow.addActionListener(this);
        btnExit.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnShow)
        {
            txtName.setText("");
            txtAddress.setText("");
            txtGender.setText("");
            txtEmail.setText("");
            txtContact.setText("");
            txtDate.setText("");
            txtAdmin.setText("");
            
            x = (String) cmbId.getSelectedItem();
            a=Integer.parseInt(x);
            sco.runconn();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            
            try
                {
                    PreparedStatement pstmt = sco.conn.prepareStatement("select id,name,address,gender,phone,email,date,admin from employee where id=?");
                    pstmt.setInt(1,a);
                    ResultSet rs=pstmt.executeQuery();
                    while(rs.next())
                    {
                       dbId=rs.getInt(1);
                       dbName=rs.getString(2);
                       dbAddress=rs.getString(3);
                       dbGender=rs.getString(4);
                       dbContact=rs.getString(5);
                       dbMail=rs.getString(6);
                       dbDate=rs.getDate(7);
                       dbAdmin=rs.getString(8);
                    }
                    
                    txtName.setText(dbName);
                    txtAddress.setText(dbAddress);
                    txtGender.setText(dbGender);
                    txtEmail.setText(dbMail);
                    txtContact.setText(dbContact);
                    String date_to_string = df.format(dbDate);
                    txtDate.setText(date_to_string);
                    if(dbAdmin.equals("yes"))
                    {
                    txtAdmin.setText("Yes");
                    }
                    else if(dbAdmin.equals("no"))
                    {
                    txtAdmin.setText("No");
                    }
                    sco.conn.close();
                    
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
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
        EmpInfoFrame ei=new EmpInfoFrame();
        ei.setVisible(true);
        ei.setSize(500,500);
        ei.setLocationRelativeTo(null);
        ei.setTitle("Pizza Sales System");
        ei.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

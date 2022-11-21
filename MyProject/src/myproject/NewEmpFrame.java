
package myproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.text.DateFormatter;


public class NewEmpFrame extends JFrame implements ActionListener{

    JLabel lblName,lblAddress,lblGender,lblEmail,lblContact,lblFrameInfo, lblId, lblDate;
    JTextField txtName,txtEmail,txtContact, txtDate, txtId;
    JTextArea txtAddress;
    JButton btnAdd,btnExit;
    JRadioButton r1,r2;
    JPanel jp,jb;
    SpinnerDateModel spinMod;
    JSpinner spin;
    int comId, inId;
    //Connection conn=null;
    
    public NewEmpFrame() {
       
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
        
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("New Employee Registration",SwingConstants.CENTER);
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
        
        lblName = new JLabel("Full Name");
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblName,gbc);

        txtName = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=2;
	jp.add(txtName,gbc);
	
        lblAddress = new JLabel("Residential Address :");
	gbc.gridx=0;
	gbc.gridy=3;
	jp.add(lblAddress,gbc);

        txtAddress=new JTextArea(5,5);
        txtAddress.setLineWrap(true);
	gbc.gridx=1;
	gbc.gridy=3;
	jp.add(txtAddress,gbc);
	
        lblGender = new JLabel("Gender :");
	gbc.gridx=0;
	gbc.gridy=4;
        r1=new JRadioButton("Male");    
        r2=new JRadioButton("Female");
	jp.add(lblGender,gbc);
        ButtonGroup bg=new ButtonGroup();    
        bg.add(r1);bg.add(r2);
        jb =new JPanel();
        jb.setLayout(new BoxLayout(jb, BoxLayout.X_AXIS));
        jb.add(r1);jb.add(r2);
        gbc.gridx=1;
	gbc.gridy=4;
	jp.add(jb,gbc);
        
	lblEmail = new JLabel("E-mail Address :");
        gbc.gridx=0;
	gbc.gridy=5;
	jp.add(lblEmail,gbc);

        txtEmail = new JTextField(8);
	gbc.gridx=1;
	gbc.gridy=5;
	jp.add(txtEmail,gbc);
	
        lblContact = new JLabel("Contact Number :");
	gbc.gridx=0;
	gbc.gridy=6;
	jp.add(lblContact,gbc);

        txtContact = new JTextField(10);
	gbc.gridx=1;
	gbc.gridy=6;
	jp.add(txtContact,gbc);
        
        lblDate = new JLabel("Date of Joining :");
	gbc.gridx=0;
	gbc.gridy=7;
	jp.add(lblDate,gbc);

        txtDate = new JTextField();
       	gbc.gridx=1;
	gbc.gridy=7;
	jp.add(txtDate,gbc);
        	
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=8;
	jp.add(btnExit,gbc);

	
	btnAdd = new JButton("Add new employee");
        gbc.gridx=1;
	gbc.gridy=8;
	jp.add(btnAdd,gbc);
        
        add(jp);
        
        btnAdd.addActionListener(this);
        btnExit.addActionListener(this);
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
    @Override
    public void actionPerformed(ActionEvent e) {
      
        if(e.getSource()== btnAdd)
        {
            
            SingleConnection sco = new SingleConnection();
            sco.runconn();
            
            java.util.Date currentDate = new java.util.Date();
            String strDate=txtDate.getText();
            java.util.Date inputDate = null;
            try
            {
            inputDate=new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
            }
            catch(ParseException ex)
            {    
            }
            
            try{
                inId=Integer.parseInt(txtId.getText());
            PreparedStatement pstmt = sco.conn.prepareStatement("select * from employee where id=?");
            pstmt.setInt(1,inId);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
                    {
                    comId=rs.getInt(1);
                    
                    }
            }
            catch(Exception ex)
            {
                
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
            else if(inId==comId)
            {
                JOptionPane.showMessageDialog(null, "Id already exist\nPlease assign another Id for new employee");
                txtId.grabFocus();
            }
            else if(txtName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Name field cannot be empty");
                txtName.grabFocus();
            } 
            else if(!(Pattern.matches("^[\\p{L} .'-]+$", txtName.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Name field cannot have numbers or special characters");
                txtName.grabFocus();
            }
            else if(txtAddress.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Address field cannot be empty");
                txtAddress.grabFocus();
            }
            else if((r1.isSelected()==false)&&(r2.isSelected()==false))
            {
                JOptionPane.showMessageDialog(null,"Please select employee gender");
            }
            else if(txtEmail.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "E-mail Address field cannot be empty");
                txtEmail.grabFocus();
            }
            else if(!(Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$", txtEmail.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Invalid E-mail address format");
                txtEmail.grabFocus();
            }
            else if(txtContact.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Contact number field cannot be empty");
                txtContact.grabFocus();
            }
            else if(!(Pattern.matches("(9)?[9][0-9]{9}", txtContact.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Invalid mobile number format");
                txtContact.grabFocus();
            }
            else if(txtDate.getText().equals(""))
            {
                   JOptionPane.showMessageDialog(null, "Please enter date in dd-mm-yyyy format");
            }
            else if(!(Pattern.matches("^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", txtDate.getText()))) 
            {
                JOptionPane.showMessageDialog(null, "Invalid date format\nDate should be in dd-mm-yyyy format");
                txtDate.grabFocus();
            }
            else if(inputDate.after(currentDate))
            {
                JOptionPane.showMessageDialog(null,"Invalid date\nFuture date not allowed");
            }
            else
            {
                try
                {
                int dbId=Integer.parseInt(txtId.getText());
                String dbName=txtName.getText();
                String dbAddress=txtAddress.getText();
                String dbGender="";
                if (r1.isSelected()) { 
                      dbGender = "Male"; 
                } 
                else if (r2.isSelected()) { 
                      dbGender = "Female";
                }
                String dbContact=txtContact.getText();
                String dbMail=txtEmail.getText();
                
                String dbDate1=txtDate.getText();
                java.util.Date dbDate2=new SimpleDateFormat("dd-MM-yyyy").parse(dbDate1);
                java.sql.Date dbDate3=new Date(dbDate2.getTime());
                
                
                PreparedStatement pstmt = sco.conn.prepareStatement("insert into employee(id,name,address,gender,phone,email,date) values(?,?,?,?,?,?,?)");
                pstmt.setInt(1,dbId);
                pstmt.setString(2,dbName);
                pstmt.setString(3,dbAddress);
                pstmt.setString(4,dbGender); 
                pstmt.setString(5,dbContact);
                pstmt.setString(6,dbMail);
                pstmt.setDate(7,dbDate3);
                pstmt.execute();
                
                JOptionPane.showMessageDialog(null, "New Employee added");
                txtName.setText("");
                txtEmail.setText("");
                txtContact.setText("");
                txtDate.setText("");
                txtId.setText("");
                txtAddress.setText("");
                
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
    
    public static void main(String args[]) {
        NewEmpFrame ef=new NewEmpFrame();
        ef.setVisible(true);
        ef.setSize(500,500);
        ef.setLocationRelativeTo(null);
        ef.setTitle("Pizza Sales System");
        ef.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

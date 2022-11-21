
package myproject;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class UpdateEmpAddress extends JFrame implements ActionListener {
    
    JButton btnUpdate, btnExit;
    JLabel lblFrameInfo, lblId, lblAddress;
    JTextField txtId; 
    JTextArea txtAddress;
    JPanel jp;
    int inId, comId, upId;
    String upAddress;
    
    public UpdateEmpAddress() {
     
        jp = new JPanel();
	jp.setLayout(new GridBagLayout());
	
	GridBagConstraints gbc=new GridBagConstraints();
	gbc.insets=new Insets(10,10,5,10);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill=GridBagConstraints.HORIZONTAL;
	
	lblFrameInfo = new JLabel("Update Employee Address ",SwingConstants.CENTER);
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
        
        lblAddress = new JLabel("Enter new address");
	gbc.gridx=0;
	gbc.gridy=2;
	jp.add(lblAddress,gbc);

        txtAddress=new JTextArea(5,5);
        txtAddress.setLineWrap(true);
	gbc.gridx=1;
	gbc.gridy=2;
	jp.add(txtAddress,gbc);
        
        btnExit = new JButton("Exit window");
        gbc.gridx=0;
	gbc.gridy=4;
	jp.add(btnExit,gbc);

	
	btnUpdate = new JButton("Update Address");
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
            else if(txtAddress.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Address field cannot be empty");
                txtAddress.grabFocus();
            }
            else
            {
                  try
                {
                upAddress=txtAddress.getText();
                upId=Integer.parseInt(txtId.getText());
                PreparedStatement pstmt=sco.conn.prepareStatement("update employee set address=? where id=?");
                pstmt.setString(1,upAddress);
                pstmt.setInt(2, upId);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Employee address updated");
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
    
        UpdateEmpAddress uea=new UpdateEmpAddress();
        uea.setVisible(true);
        uea.setSize(400,400);
        uea.setLocationRelativeTo(null);
        uea.setTitle("Pizza Sales System");
        uea.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

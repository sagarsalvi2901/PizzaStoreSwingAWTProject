/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminAccessFrame extends JFrame implements ActionListener {
    
    JMenuBar mbar;
    JMenu empMenu, adminMenu, detailsMenu, itemMenu;
    JMenuItem newEmp, passwordEmp, addressEmp, contactEmp, infoEmp, salesDetails, orderDetails;
    JMenuItem assignAdmin, revokeAdmin, addItem, addCategory;
    JPanel panAdmin;
    
    public AdminAccessFrame() {
        
        panAdmin=new JPanel();
	JLabel img=new JLabel();
        img.setIcon(new ImageIcon(MyProjectFrame.class.getResource("/pizza.jpg")));
        img.setBounds(0, 0, 500, 500);
        panAdmin.add(img);
        
        mbar=new JMenuBar();
        
        empMenu=new JMenu("Employee");
        newEmp=new JMenuItem("New Employee Registration");
        infoEmp=new JMenuItem("Employee Information");
        passwordEmp=new JMenuItem("Update Employee Password"); 
        addressEmp=new JMenuItem("Update Employee Address"); 
        contactEmp=new JMenuItem("Update Employee Contact No.");
        empMenu.add(newEmp);
        empMenu.add(infoEmp);
        empMenu.add(passwordEmp);
        empMenu.add(addressEmp);
        empMenu.add(contactEmp);
        mbar.add(empMenu);
        
        adminMenu=new JMenu("Admin");
        assignAdmin=new JMenuItem("Assign Admin Privilages");  
        revokeAdmin=new JMenuItem("Revoke Admin Privilages"); 
        adminMenu.add(assignAdmin);
        adminMenu.add(revokeAdmin);
        mbar.add(adminMenu);
        
        itemMenu = new JMenu("Menu Items");
        addCategory = new JMenuItem("Add new Menu Category");
        addItem = new JMenuItem("Add new Menu Item");
        itemMenu.add(addCategory);
        itemMenu.add(addItem);
        mbar.add(itemMenu);

        detailsMenu=new JMenu("Details");
        salesDetails=new JMenuItem("Sales Details");  
        orderDetails=new JMenuItem("Order Details");
        detailsMenu.add(salesDetails);
        detailsMenu.add(orderDetails);
        mbar.add(detailsMenu);
        
        
        setJMenuBar(mbar);
        add(panAdmin);
        
        newEmp.addActionListener(this);
        infoEmp.addActionListener(this);
        passwordEmp.addActionListener(this);
        addressEmp.addActionListener(this);
        contactEmp.addActionListener(this);
        revokeAdmin.addActionListener(this);
        assignAdmin.addActionListener(this);
        salesDetails.addActionListener(this);
        orderDetails.addActionListener(this);
        addCategory.addActionListener(this);
        addItem.addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==newEmp)
        {
            NewEmpFrame ef=new NewEmpFrame();
            ef.setVisible(true);
            ef.setSize(500,500);
            ef.setLocationRelativeTo(null);
            ef.setTitle("Pizza Sales System");
            ef.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==infoEmp)
        {
            EmpInfoFrame ei=new EmpInfoFrame();
            ei.setVisible(true);
            ei.setSize(500,500);
            ei.setLocationRelativeTo(null);
            ei.setTitle("Pizza Sales System");
            ei.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==passwordEmp)
        {
            UpdateEmpPassword uep=new UpdateEmpPassword();
            uep.setVisible(true);
            uep.setSize(400,400);
            uep.setLocationRelativeTo(null);
            uep.setTitle("Pizza Sales System");
            uep.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==contactEmp)
        {
           UpdateEmpContact uec=new UpdateEmpContact();
            uec.setVisible(true);
            uec.setSize(400,400);
            uec.setLocationRelativeTo(null);
            uec.setTitle("Pizza Sales System");
            uec.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        }
        
        if(e.getSource()==addressEmp)
        {
            UpdateEmpAddress uea=new UpdateEmpAddress();
            uea.setVisible(true);
            uea.setSize(400,400);
            uea.setLocationRelativeTo(null);
            uea.setTitle("Pizza Sales System");
            uea.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==revokeAdmin)
        {
            RemoveAdminFrame rf=new RemoveAdminFrame();
            rf.setVisible(true);
            rf.setSize(400,300);
            rf.setLocationRelativeTo(null);
            rf.setTitle("Pizza Sales System");
            rf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==assignAdmin)
        {
            NewAdminFrame af=new NewAdminFrame();
            af.setVisible(true);
            af.setSize(400,300);
            af.setLocationRelativeTo(null);
            af.setTitle("Pizza Sales System");
            af.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
          
        if(e.getSource()==addCategory)
        {
            NewCategoryFrame ncf=new NewCategoryFrame();
            ncf.setVisible(true);
            ncf.setSize(400,300);
            ncf.setLocationRelativeTo(null);
            ncf.setTitle("Pizza Sales System");
            ncf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==addItem)
        {
            NewMenuItemFrame nmi=new NewMenuItemFrame();
            nmi.setVisible(true);
            nmi.setSize(400,300);
            nmi.setLocationRelativeTo(null);
            nmi.setTitle("Pizza Sales System");
            nmi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==orderDetails)
        {
            SearchServiceFrame ssf=new SearchServiceFrame();
            ssf.setVisible(true);
            ssf.setSize(500,600);
            ssf.setLocationRelativeTo(null);
            ssf.setTitle("Pizza Sales System");
            ssf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        if(e.getSource()==salesDetails)
        {
            SearchOrderFrame sof=new SearchOrderFrame();
            sof.setVisible(true);
            sof.setSize(600,600);
            sof.setLocationRelativeTo(null);
            sof.setTitle("Pizza Sales System");
            sof.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
    
    
    
    public static void main(String args[]) {
        AdminAccessFrame aaf=new AdminAccessFrame();
        aaf.setVisible(true);
        aaf.setSize(500,400);
        aaf.setLocationRelativeTo(null);
        aaf.setTitle("Pizza Sales System");
        aaf.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

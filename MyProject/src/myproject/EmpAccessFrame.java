
package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmpAccessFrame extends JFrame implements ActionListener{

    JMenuBar mbar;
    JMenu empMenu, orderMenu;
    JMenuItem infoEmp, orderPanel;
    JPanel panEmp;
    
    public EmpAccessFrame() {
        
        panEmp = new JPanel();
        JLabel img=new JLabel();
        img.setIcon(new ImageIcon(MyProjectFrame.class.getResource("/pizza.jpg")));
        img.setBounds(0, 0, 500, 500);
        panEmp.add(img);
        
        mbar=new JMenuBar();
        
        empMenu=new JMenu("Employee");
        infoEmp=new JMenuItem("Employee Information");
        empMenu.add(infoEmp);
        mbar.add(empMenu);
         
        orderMenu = new JMenu("Take Orders");
        orderPanel = new JMenuItem("Pizza Order Panel");
        orderMenu.add(orderPanel);
        mbar.add(orderMenu);
        
        setJMenuBar(mbar);
        add(panEmp);
        
        infoEmp.addActionListener(this);
        orderPanel.addActionListener(this);
    }

    
     @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==infoEmp)
        {
            EmpInfoFrame ei=new EmpInfoFrame();
            ei.setVisible(true);
            ei.setSize(500,500);
            ei.setLocationRelativeTo(null);
            ei.setTitle("Pizza Sales System");
            ei.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
                
        if(e.getSource()==orderPanel)
        {
            FoodOrderFrame fof=new FoodOrderFrame();
            fof.setVisible(true);
            fof.setSize(700,600);
            fof.setLocationRelativeTo(null);
            fof.setTitle("Pizza Sales System");
            fof.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
             
        }
  }
    
    
    public static void main(String args[]) {
     
        EmpAccessFrame eaf=new EmpAccessFrame();
        eaf.setVisible(true);
        eaf.setSize(500,400);
        eaf.setLocationRelativeTo(null);
        eaf.setTitle("Pizza Sales System");
        eaf.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

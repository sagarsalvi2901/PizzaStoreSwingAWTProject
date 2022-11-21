/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class SimpleFrame extends JFrame implements ActionListener {
    SpinnerDateModel spinMod1,spinMod2,spinMod3,spinMod4;
    JSpinner spin1,spin2,spin3,spin4; JButton jb;
    java.util.Date dt1,dt2;
    
    public SimpleFrame() {
        
        setLayout(new FlowLayout());
        jb=new JButton("check");
        spinMod1=new SpinnerDateModel();
        spinMod2=new SpinnerDateModel();
        spinMod3=new SpinnerDateModel();
        spinMod4=new SpinnerDateModel();
        spin1=new JSpinner(spinMod1);
        spin1.setEditor(new JSpinner.DateEditor(spin1,"dd-MM-yyyy"));
        spin2=new JSpinner(spinMod2);
        spin2.setEditor(new JSpinner.DateEditor(spin2,"dd-MM-yyyy"));
        spin3=new JSpinner(spinMod3);
        spin3.setEditor(new JSpinner.DateEditor(spin3,"dd-MM-yyyy"));
        spin4=new JSpinner(spinMod4);
        spin4.setEditor(new JSpinner.DateEditor(spin4,"dd-MM-yyyy"));
        //add(spin1);
        //add(spin2);
        add(jb);
        add(spin3);
        add(spin4);
        
        
        
        jb.addActionListener(this);
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
       SimpleFrame a = new SimpleFrame();
       a.setSize(600,600);
       a.setVisible(true);
       a.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jb)
        {
            try {
                //java.util.Date dt1=(java.util.Date) spin1.getValue();
                Date current = new Date();
                //java.util.Date dt2=(java.util.Date) spin2.getValue();
                
                String date1="20-11-2019";
                dt1 = new SimpleDateFormat("dd-MM-yyyy").parse(date1);
                
                String date2="23-11-2019";
                dt2 = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
                
                long diff = dt2.getTime() - dt1.getTime();
                //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                long diffDays = diff / (24 * 60 * 60 * 1000);
                //JOptionPane.showMessageDialog(null,diffDays);
                
                java.util.Date dt3=(java.util.Date) spin3.getValue();
                java.util.Date dt4=(java.util.Date) spin4.getValue();
                
                if(dt4.compareTo(dt3) <= 0)
                {
                    JOptionPane.showMessageDialog(null,"checkout improper date");
                }
                else if(dt3.compareTo(dt1) >= 0 && dt3.compareTo(dt2) <= 0 )
                {
                    JOptionPane.showMessageDialog(null," select future date");
                }
                else if(dt4.compareTo(dt1) >= 0 && dt4.compareTo(dt2) <= 0 )
                {
                    JOptionPane.showMessageDialog(null," select future date");
                }
                else if(dt1.after(dt3) && dt2.before(dt4))
                {
                    JOptionPane.showMessageDialog(null," inbetwwen");
                }
            } catch (ParseException ex) {
                Logger.getLogger(SimpleFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

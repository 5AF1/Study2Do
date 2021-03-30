
import java.awt.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author mueez
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    private DatabaseClass dbObject;
    private ResultSet rsClass;
    private JCheckBox cb = new JCheckBox();
    private JLabel jlab = new JLabel();
    
    private String onThisDate;
    
    
    public NewJFrame() {
        initComponents();
        this.setVisible(true);
        dbObject = new DatabaseClass();
        System.out.println("arrrrrrrrr");
//        setCheckBox();
    }
    
     public NewJFrame(String thisDate) {
        initComponents();
        this.setVisible(true);
        dbObject = new DatabaseClass();
        setCheckBox(thisDate);
    }

     
     public void setCheckBox(String thisDate)
    {
        
        reset();
//        Calendar cal = Calendar.getInstance();
//        int date = cal.get(Calendar.DAY_OF_MONTH);
//        int month = cal.get(Calendar.MONTH);
//        int year = cal.get(Calendar.YEAR);
//        month++;
////        System.out.println("yeehaaw: " + date + " " + month + " " + year);
//        String todaysDate = "";
//        todaysDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);
////        System.out.println("yeehaaw: "+todaysDate);

        int i=0;
        
        rsClass = dbObject.getDbTaskOnThisDate(thisDate);
        
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
//        jPanel1.setBackground(java.awt.Color.WHITE);
        
       try {
           
            while(rsClass.next()){
                
//                System.out.println("well: " + rsClass.getString(3));
                cb = new JCheckBox();
                cb.setText(rsClass.getString("task_name"));
                cb.setFont(new Font("Serif", Font.PLAIN, 14));
                cb.setForeground(Color.BLACK);
                String temp_taskId = rsClass.getString("task_id");
                cb.addActionListener(
                new ActionListener()
                {
//                       public void actionPerformed(ActionEvent event)
//                       {
//                           JOptionPane.showMessageDialog(null, event.getActionCommand());
//                        }
                    public void actionPerformed(ActionEvent event)
                       {
                           try {
                            Thread.sleep(2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TaskFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           System.out.println("dopweoqpwor");
                           dbObject.taskComplete(Integer.parseInt(temp_taskId));
                           setCheckBox(thisDate);
                       }
                }
                );
                
                jPanel1.add(cb);
                
                
                jlab = new JLabel();
                jlab.setText(rsClass.getString("due_date"));
                jlab.setFont(new Font("Serif", Font.BOLD, 14));
                jlab.setForeground(Color.BLACK);
                jPanel1.add(jlab);

                jlab = new JLabel();
//                jlab.setText(rs2.getString("class_name") + " - " + rsClass.getString("class_id"));
                jlab.setText(rsClass.getString("class_id"));
                jlab.setFont(new Font("Serif", Font.PLAIN, 14));
                jlab.setForeground(Color.BLACK);
                jPanel1.add(jlab);
                
                jlab = new JLabel();
                jlab.setText(rsClass.getString("task_type"));
                jlab.setFont(new Font("Serif", Font.PLAIN, 14));
                jlab.setForeground(Color.BLACK);
                jPanel1.add(jlab);
                
                
                jlab = new JLabel();
                jlab.setText(rsClass.getString("description"));
                jlab.setFont(new Font("Serif", Font.PLAIN, 12));
                jlab.setForeground(Color.BLACK);
                jPanel1.add(jlab);
                
//                jPanel1.add(Box.createVerticalGlue());
                jPanel1.add(Box.createVerticalStrut(20));
                
            }
            
            jScrollPane.setViewportView(jPanel1);
        } 
        catch (SQLException ex) {
            Logger.getLogger(TaskFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
//        jPanel1.setBackground(java.awt.Color.WHITE);
//        jScrollPane.setViewportView(jPanel1);
    }
    
    public void reset()
    {
        jPanel1 = new JPanel();
        jScrollPane.setViewportView(jPanel1);
    }
    
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class frm_Login extends javax.swing.JFrame {
    private Object con;
    private ResultSet rss;
    private Statement stt;
    public static Object[] admin = new Object[3];

    /**
     * Creates new form LoginPanel
     */
    public frm_Login() {
        initComponents();
    }
    
    public static Object[] getform(){
        return admin;
    }
        private void ProsesLogin(String username, String password){
         
        try{
            
            Statement statement = (Statement)koneksi.getConnection().createStatement();
            ResultSet result=statement.executeQuery
            ("select * from admin where username='"+username+"'" );
            
            if(result.next()){
                if(password.equals(result.getString("password"))){
                    admin[0] = result.getString("id_admin");
                    admin[1] = result.getString("nama_admin");
                    admin[2] = result.getString("level");
                    String id_admin = admin[0].toString();
                    String nama = admin[1].toString();
                    String level =admin[2].toString();
                    
                    JOptionPane.showMessageDialog(rootPane, "Selamat datang "+nama+", Anda Login Sebagai "+level);
                    
                    frm_Home awal = new frm_Home();
                    frm_Home.lblUsername.setText(admin[1].toString());
                    frm_transaksi transaksi = new frm_transaksi();
                    frm_transaksi.id_admin = Integer.parseInt(admin[0].toString());
                    
                    if(level.equals("Superadmin")){
                        awal.pnlAdmin.setVisible(true);
                    }else{
                        awal.pnlAdmin.setVisible(false);
                    }
                        awal.setVisible(true);
                        dispose();  
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password Salah");
                    tfPassword.setText("");
                    tfPassword.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "User tidak ditemukan");
                tfUsername.setText("");
                tfPassword.setText("");
                tfUsername.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(frm_Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Gagal Login!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new usu.widget.Panel();
        tfUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        panel1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgLogin.png"))); // NOI18N
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfUsername.setBorder(null);
        tfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsernameActionPerformed(evt);
            }
        });
        panel1.add(tfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 237, 227, 23));

        btnLogin.setBackground(new java.awt.Color(0, 202, 102));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        panel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 359, 113, 30));

        btnCancel.setBackground(new java.awt.Color(240, 45, 51));
        btnCancel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        panel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 359, 105, 30));

        tfPassword.setBorder(null);
        panel1.add(tfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 220, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsernameActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
    ProsesLogin(tfUsername.getText(), tfPassword.getText());

    
    
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(frm_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private usu.widget.Panel panel1;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}

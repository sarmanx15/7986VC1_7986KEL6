/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

/**
 *
 * @author acer
 */
public class frm_detail_penghuni extends javax.swing.JFrame {

    /**
     * Creates new form frm_detail_penghuni
     */
    public frm_detail_penghuni() {
        initComponents();
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
        panel2 = new usu.widget.Panel();
        jPanel1 = new javax.swing.JPanel();
        tfDetNama = new javax.swing.JTextField();
        tfDetAlamat = new javax.swing.JTextField();
        tfDetTmptLahir = new javax.swing.JTextField();
        tfDetTglLahir = new javax.swing.JTextField();
        tfDetGender = new javax.swing.JTextField();
        tfDetEmail = new javax.swing.JTextField();
        tfDetNomorKTP = new javax.swing.JTextField();
        tfDetNomorTelp = new javax.swing.JTextField();
        tfDetPekerjaan = new javax.swing.JTextField();
        tfDetAlamatPekerjaan = new javax.swing.JTextField();
        tfDetTelpPekerjaan = new javax.swing.JTextField();
        tfDetTglMasuk = new javax.swing.JTextField();
        pnlDetPasFoto = new usu.widget.Panel();
        pnlDetPasKTP = new usu.widget.Panel();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panel2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormDetailPenghuni.png"))); // NOI18N
        panel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                panel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 2));

        tfDetNama.setText("jTextField1");
        jPanel1.add(tfDetNama);

        tfDetAlamat.setText("jTextField1");
        jPanel1.add(tfDetAlamat);

        tfDetTmptLahir.setText("jTextField1");
        jPanel1.add(tfDetTmptLahir);

        tfDetTglLahir.setText("jTextField1");
        jPanel1.add(tfDetTglLahir);

        tfDetGender.setText("jTextField1");
        jPanel1.add(tfDetGender);

        tfDetEmail.setText("jTextField1");
        jPanel1.add(tfDetEmail);

        tfDetNomorKTP.setText("jTextField1");
        jPanel1.add(tfDetNomorKTP);

        tfDetNomorTelp.setText("jTextField1");
        jPanel1.add(tfDetNomorTelp);

        tfDetPekerjaan.setText("jTextField1");
        jPanel1.add(tfDetPekerjaan);

        tfDetAlamatPekerjaan.setText("jTextField1");
        jPanel1.add(tfDetAlamatPekerjaan);

        tfDetTelpPekerjaan.setText("jTextField1");
        jPanel1.add(tfDetTelpPekerjaan);

        tfDetTglMasuk.setText("jTextField1");
        jPanel1.add(tfDetTglMasuk);

        pnlDetPasFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout pnlDetPasFotoLayout = new javax.swing.GroupLayout(pnlDetPasFoto);
        pnlDetPasFoto.setLayout(pnlDetPasFotoLayout);
        pnlDetPasFotoLayout.setHorizontalGroup(
            pnlDetPasFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        pnlDetPasFotoLayout.setVerticalGroup(
            pnlDetPasFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        pnlDetPasKTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout pnlDetPasKTPLayout = new javax.swing.GroupLayout(pnlDetPasKTP);
        pnlDetPasKTP.setLayout(pnlDetPasKTPLayout);
        pnlDetPasKTPLayout.setHorizontalGroup(
            pnlDetPasKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );
        pnlDetPasKTPLayout.setVerticalGroup(
            pnlDetPasKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Tutup");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(pnlDetPasKTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(pnlDetPasFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1)
                        .addGap(111, 111, 111)
                        .addComponent(pnlDetPasFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)))
                .addComponent(pnlDetPasKTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_panel2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_panel2AncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_detail_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_detail_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_detail_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_detail_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_detail_penghuni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private usu.widget.Panel panel1;
    private usu.widget.Panel panel2;
    public static usu.widget.Panel pnlDetPasFoto;
    public static usu.widget.Panel pnlDetPasKTP;
    public static javax.swing.JTextField tfDetAlamat;
    public static javax.swing.JTextField tfDetAlamatPekerjaan;
    public static javax.swing.JTextField tfDetEmail;
    public static javax.swing.JTextField tfDetGender;
    public static javax.swing.JTextField tfDetNama;
    public static javax.swing.JTextField tfDetNomorKTP;
    public static javax.swing.JTextField tfDetNomorTelp;
    public static javax.swing.JTextField tfDetPekerjaan;
    public static javax.swing.JTextField tfDetTelpPekerjaan;
    public static javax.swing.JTextField tfDetTglLahir;
    public static javax.swing.JTextField tfDetTglMasuk;
    public static javax.swing.JTextField tfDetTmptLahir;
    // End of variables declaration//GEN-END:variables
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class frm_Admin extends javax.swing.JFrame {
private DefaultTableModel model;
private Connection con = koneksi.getConnection();
private Statement stt;
private ResultSet rss;    
String id_admin;
    public void InitTable(){
    model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NAMA");
        model.addColumn("USERNAME");
        model.addColumn("PASSWORD");
        model.addColumn("LEVEL");
        
        tblAdmin.setModel(model);
}

    private void TampilDataAdmin(){
        try{
            String sql = "Select *from admin order by id_admin;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] admin = new Object[5];
                admin[0] = rss.getString("id_admin");
                admin[1] = rss.getString("nama_admin");
                admin[2] = rss.getString("username");
                admin[3] = rss.getString("password");
                admin[4] = rss.getString("level");

                model.addRow(admin);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void TambahDataAdmin(String nama_admin,String username, String password, String level) {
        try{
            String sql = "INSERT INTO admin VALUES(NULL,'"+nama_admin+"','"+username+"','"+password+"','"+level+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nama_admin,username, password, level});
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataAdmin();
    }
    
    public boolean UbahDataFasilitas(String id_admin,String nama_admin,String username, String password, String level ){
        try{
            String sql = "UPDATE admin set nama_admin = '"+nama_admin+"', username = '"+username+"', password = '"+password+"', level = '"+level+"' WHERE id_admin = '"+id_admin+"';";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private void bersihkanfield(){
        tfNamaAdmin.setText("");
        tfPasswordAdmin.setText("");
        tfUsernameAdmin.setText("");        
        cbLevelAdmin.setSelectedItem("Pilih Level");
    }
    
    private void KunciField(Boolean x){
        tfNamaAdmin.setEnabled(x);
        tfPasswordAdmin.setEnabled(x);
        tfUsernameAdmin.setEnabled(x);
        cbLevelAdmin.setEnabled(x);
        
//        btnSimpanFasilitas.setEnabled(x);
    }
    
    public boolean HapusData(String id_admin){
        try{
            String sql = "DELETE FROM admin WHERE id_admin = "+id_admin+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
//    private void PencarianData(String by, String cari){
//        try{
//            String sql = "Select *from admin where "+by+" Like '%"+cari+"%';";
//            stt = con.createStatement();
//            rss = stt.executeQuery(sql);
//            while(rss.next()){
//                Object[] data = new Object[5];
//                data[0] = rss.getString("id_admin");
//                data[1] = rss.getString("nama_admin");
//                data[2] = rss.getString("username");
//                data[3] = rss.getString("password");
//                data[4] = rss.getString("level");
//               
//                model.addRow(data);
//            }
//        }
//            catch(Exception e){
//                    System.out.println(e.getMessage());
//                    }
//        
//    }
public void cekstatus(){
    
         if(tfNamaAdmin.getText().length()==0 ||
            tfPasswordAdmin.getText().length()==0 ||
            tfUsernameAdmin.getText().length()==0
            
                 ){
            btnSimpan.setEnabled(false);
        }
        else{
            btnSimpan.setEnabled(true);
        }
     } 

public boolean validasikode(String id_admin){
    try{
            String sql = "Select *from admin where id_admin = '"+id_admin+"';";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            if(rss.next())
                return true;
            else 
                return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return true;
        }
}

public boolean validasiNama(String nama_admin){
    try{
            String sql = "Select *from admin where nama_admin = '"+nama_admin+"';";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            if(rss.next())
                return true;
            else 
                return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return true;
        }
}
    /**
     * Creates new form frm_Admin
     */
    public frm_Admin() {
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
        panel3 = new usu.widget.Panel();
        jPanel1 = new javax.swing.JPanel();
        tfNamaAdmin = new javax.swing.JTextField();
        tfUsernameAdmin = new javax.swing.JTextField();
        tfPasswordAdmin = new javax.swing.JPasswordField();
        cbLevelAdmin = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
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

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        panel3.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormAdmin.png"))); // NOI18N
        panel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel3MouseClicked(evt);
            }
        });
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        tfNamaAdmin.setText("jTextField1");
        tfNamaAdmin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaAdminCaretUpdate(evt);
            }
        });
        jPanel1.add(tfNamaAdmin);

        tfUsernameAdmin.setText("jTextField2");
        tfUsernameAdmin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfUsernameAdminCaretUpdate(evt);
            }
        });
        jPanel1.add(tfUsernameAdmin);

        tfPasswordAdmin.setText("jPasswordField1");
        tfPasswordAdmin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPasswordAdminCaretUpdate(evt);
            }
        });
        jPanel1.add(tfPasswordAdmin);

        cbLevelAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Level", "Admin", "Superadmin" }));
        jPanel1.add(cbLevelAdmin);

        panel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 310, 130));

        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAdmin);

        panel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 580, 180));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpan);

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel2.add(btnTambah);

        btnBersih.setText("Bersihkan");
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });
        jPanel2.add(btnBersih);

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel2.add(btnEdit);

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapus);

        panel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 480, 40));

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Tutup");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 26, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        KunciField(true);
        try{
            String sql = "Select max(id_admin) as id_admin from admin;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[1];
                o[0] = rss.getString("id_admin");
                
                int id = Integer.parseInt(o[0].toString())+1;                
                id_admin = String.valueOf(id);

                
            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String nama_admin = tfNamaAdmin.getText();
        String username = tfUsernameAdmin.getText();
        String password = tfPasswordAdmin.getText();
        String level = cbLevelAdmin.getSelectedItem().toString();
        if(cbLevelAdmin.getSelectedItem().equals("Pilih Level")){
            JOptionPane.showMessageDialog(null, "Level Masih Kosong, Silahkan Isi Dengan Benar!!");
        }
        else{
            if(validasikode(id_admin)){
                int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Mengubah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (pilihan==0) {
                    if(UbahDataFasilitas(id_admin, nama_admin, username, password, level)){
                        JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
                        InitTable();
                        TampilDataAdmin();
                        bersihkanfield();
                        KunciField(false);
                        btnSimpan.setEnabled(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Gagal Ubah Data");
                    }
                }
            }
            else{
                int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Menambah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (pilihan==0) {
                    if(validasiNama(nama_admin)){
                        JOptionPane.showMessageDialog(null, "Nama Fasilitas Telah Terdaftar...");
                    }
                    else{
                        TambahDataAdmin(nama_admin, username, password, level);
                        bersihkanfield();
                        KunciField(false);
                    }
                }            
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        // TODO add your handling code here:
        bersihkanfield();
    }//GEN-LAST:event_btnBersihActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if((tblAdmin.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin diedit pada Tabel..");            
        }
        else{ 
            int baris=tblAdmin.getSelectedRow();
            id_admin = tblAdmin.getValueAt(baris, 0).toString();
            try{
                String sql = "Select *from admin where id_admin = "+id_admin+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);
                while(rss.next()){
                    Object[] data = new Object[5];
                    data[0] = rss.getString("id_admin");
                    data[1] = rss.getString("nama_admin");
                    data[2] = rss.getString("username");
                    data[3] = rss.getString("password");
                    data[4] = rss.getString("level");

                    id_admin = (data[0].toString());
                    tfNamaAdmin.setText(data[1].toString());                    
                    tfUsernameAdmin.setText(data[2].toString());  
                    tfPasswordAdmin.setText(data[3].toString());                      
                    cbLevelAdmin.setSelectedItem(data[4].toString());  
                    KunciField(true);
                }
                 }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
         if((tblAdmin.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin diedit pada Tabel..");            
        }
        else{ 
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Hapus Data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {

                int baris = tblAdmin.getSelectedRow();
                String id_admin = tblAdmin.getValueAt(baris, 0).toString();

                if(HapusData(id_admin)){
                    JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");

                }
                InitTable();
                TampilDataAdmin();
                bersihkanfield();
                KunciField(false);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataAdmin();
        btnSimpan.setEnabled(false);
        KunciField(false);
        bersihkanfield();
    }//GEN-LAST:event_formComponentShown

    private void tfNamaAdminCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaAdminCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNamaAdminCaretUpdate

    private void tfUsernameAdminCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfUsernameAdminCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfUsernameAdminCaretUpdate

    private void tfPasswordAdminCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPasswordAdminCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfPasswordAdminCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Menutup? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (pilihan==0) {
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void panel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel3MouseClicked
        // TODO add your handling code here:
        tblAdmin.clearSelection();
    }//GEN-LAST:event_panel3MouseClicked

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
            java.util.logging.Logger.getLogger(frm_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbLevelAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private usu.widget.Panel panel1;
    private usu.widget.Panel panel2;
    private usu.widget.Panel panel3;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTextField tfNamaAdmin;
    private javax.swing.JPasswordField tfPasswordAdmin;
    private javax.swing.JTextField tfUsernameAdmin;
    // End of variables declaration//GEN-END:variables
}

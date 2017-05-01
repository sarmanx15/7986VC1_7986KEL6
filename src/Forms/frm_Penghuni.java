/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class frm_Penghuni extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet rss;
    
    public void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NAMA");
        model.addColumn("ALAMAT");
//        model.addColumn("TEMPAT LAHIR");
//        model.addColumn("TGL LAHIR");
        model.addColumn("GENDER");
        model.addColumn("NO. TELP");
        model.addColumn("EMAIL");
//        model.addColumn("NO. KTP");
        
//        model.addColumn("PEKERJAAN");
//        model.addColumn("ALMT PEKERJAAN");
//        model.addColumn("NO TELP PEKERJAAN");
//        model.addColumn("PAS FOTO");
//        model.addColumn("FOTO KTP");
        tblPenghuni.setModel(model);
        
    }
    private void TambahDataPenghuni(String nama, String alamat, String tempat_lahir, String tgl_lahir, String jk, String email, String noktp, String nohp, String pekerjaan, String alamat_kerja, String notelp_kerja, String foto_wajah, String foto_ktp){
        try{
            String sql = "INSERT INTO penyewa VALUES(NULL,'"+nama+"','"+alamat+"','"+tempat_lahir+"','"+tgl_lahir+"','"+jk+"','"+noktp+"','"+email+"','"+nohp+"','"+pekerjaan+"','"+alamat_kerja+"','"+notelp_kerja+"','"+foto_wajah+"','"+foto_ktp+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nama, alamat, tempat_lahir, tgl_lahir, jk, email, noktp, nohp, pekerjaan, alamat_kerja, notelp_kerja, foto_wajah, foto_ktp});
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataPenghuni();
    }
    
    public boolean UbahDataPenghuni(String id_penyewa, String nama, String alamat, String tempat_lahir, String tgl_lahir, String jk, String email, String noktp, String nohp, String pekerjaan, String alamat_kerja, String notelp_kerja, String foto_wajah, String foto_ktp){
        try{
            String sql = "UPDATE penyewa set nama = '"+nama+"', alamat = '"+alamat+"', tempat_lahir = '"+tempat_lahir+"', tgl_lahir = '"+tgl_lahir+"', jk = '"+jk+"', email = '"+email+"', noktp = '"+nohp+"', pekerjaan= '"+pekerjaan+"', alamat_kerja = '"+alamat_kerja+"', notelp_kerja = '"+notelp_kerja+"', foto_wajah= '"+foto_wajah+"', foto_ktp= '"+foto_ktp+"' WHERE id_penyewa = "+id_penyewa+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private void TampilDataPenghuni(){
        try{
            String sql = "Select *from penyewa;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[6];
//                o[0] = rss.getString("nama");
//                o[1] = rss.getString("alamat");
//                o[2] = rss.getString("tempat_lahir");
//                o[3] = rss.getDate("tgl_lahir");
//                o[4] = rss.getString("jk");
//                o[5] = rss.getString("noktp");
//                o[6] = rss.getString("nohp");
//                o[7] = rss.getString("email");
//                o[8] = rss.getString("pekerjaan");
//                o[9] = rss.getString("alamat_kerja");
//                o[10] = rss.getString("notelp_kerja");
//                o[11] = rss.getString("foto_wajah");
//                o[12] = rss.getString("foto_ktp");
                o[0] = rss.getString("id_penyewa");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("alamat");
                o[3] = rss.getString("jk");
                o[4] = rss.getString("nohp");
                o[5] = rss.getString("email");

                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void bersihkanfield(){
        tfNamaPenghuni.setText("");
        tfAlamatPenghuni.setText("");
        tfTempatLahirPenghuni.setText("");
        dcTanggalLahirPenghuni.setDate(null);
        cbGender.setSelectedItem("Pilih Gender");
        tfEmail.setText("");
        tfNomorKTP.setText("");
        tfNomorTelp.setText("");
        tfPekerjaan.setText("");
        tfAlamatPekerjaan.setText("");
        tfNomorTelpPekerjaan.setText("");
        tfPasFoto.setText("");
        tfFotoKTP.setText("");
    }
    
    private void KunciField(Boolean x){
        tfNamaPenghuni.setEnabled(x);
        tfAlamatPenghuni.setEnabled(x);
        tfTempatLahirPenghuni.setEnabled(x);
        dcTanggalLahirPenghuni.setEnabled(x);
        cbGender.setEnabled(x);
        tfEmail.setEnabled(x);
        tfNomorKTP.setEnabled(x);
        tfNomorTelp.setEnabled(x);
        tfPekerjaan.setEnabled(x);
        tfAlamatPekerjaan.setEnabled(x);
        tfNomorTelpPekerjaan.setEnabled(x);
        tfPasFoto.setEnabled(x);
        tfFotoKTP.setEnabled(x);
        
    }
    
    public boolean HapusData(String id){
        try{
            String sql = "DELETE FROM penyewa WHERE id_penyewa = "+id+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void cekstatus(){
         if(tfNamaPenghuni.getText().length()==0 ||
        tfAlamatPenghuni.getText().length()==0 ||
        tfTempatLahirPenghuni.getText().length()==0 ||
        dcTanggalLahirPenghuni.getDate() == null ||
        cbGender.getSelectedItem().equals("Pilih Gender") ||
        tfEmail.getText().length()==0 ||
        tfNomorKTP.getText().length()==0 ||
        tfNomorTelp.getText().length()==0 ||
        tfPekerjaan.getText().length()==0 ||
        tfAlamatPekerjaan.getText().length()==0 ||
        tfNomorTelpPekerjaan.getText().length()==0 ||
        tfPasFoto.getText().length()==0 ||
        tfFotoKTP.getText().length()==0 ){
            btnSimpan.setEnabled(false);
        }
        else{
            btnSimpan.setEnabled(true);
        }
     }
    /**
     * Creates new form frm_Penyewa
     */
    public frm_Penghuni() {
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

        layoutStyle1 = new org.jdesktop.layout.LayoutStyle();
        panel1 = new usu.widget.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPenghuni = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cbCariPenghuni = new javax.swing.JComboBox<>();
        btnCariPenghuni = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        tfNamaPenghuni = new javax.swing.JTextField();
        tfAlamatPenghuni = new javax.swing.JTextField();
        tfTempatLahirPenghuni = new javax.swing.JTextField();
        dcTanggalLahirPenghuni = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        cbGender = new javax.swing.JComboBox<>();
        tfEmail = new javax.swing.JTextField();
        tfNomorKTP = new javax.swing.JTextField();
        tfNomorTelp = new javax.swing.JTextField();
        tfPekerjaan = new javax.swing.JTextField();
        tfAlamatPekerjaan = new javax.swing.JTextField();
        tfNomorTelpPekerjaan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        tfPasFoto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tfFotoKTP = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTextField13 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormPenghuni.png"))); // NOI18N
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPenghuni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NAMA", "ALAMAT", "TMPT. LAHIR", "TGL. LAHIR", "GENDER", "EMAIL", "NO. KTP", "NO. TELP", "PEKERJAAN", "ALMT PEKERJAAN", "NO. TELP PEKRJN", "PAS FOTO", "FOTO KTP"
            }
        ));
        tblPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenghuniMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPenghuni);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 690, -1));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan);

        btnTambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah);

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit);

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus);

        panel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 573, 360, 40));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        cbCariPenghuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Berdasarkan", "Nama", "No. Kamar", "Gender" }));
        jPanel4.add(cbCariPenghuni);

        btnCariPenghuni.setText("Cari");
        btnCariPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPenghuniActionPerformed(evt);
            }
        });
        jPanel4.add(btnCariPenghuni);

        panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 270, 30));

        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        tfNamaPenghuni.setText("jTextField1");
        tfNamaPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfNamaPenghuni);

        tfAlamatPenghuni.setText("jTextField2");
        tfAlamatPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfAlamatPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfAlamatPenghuni);

        tfTempatLahirPenghuni.setText("jTextField3");
        tfTempatLahirPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfTempatLahirPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfTempatLahirPenghuni);

        dcTanggalLahirPenghuni.setDateFormatString("yyyy-MM-dd");
        dcTanggalLahirPenghuni.setMaxSelectableDate(new java.util.Date(253370739686000L));
        dcTanggalLahirPenghuni.setMinSelectableDate(new java.util.Date(-62135794714000L));
        jPanel5.add(dcTanggalLahirPenghuni);

        panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 190, 120));

        jPanel6.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Gender", "Pria", "Wanita", " " }));
        jPanel6.add(cbGender);

        tfEmail.setText("jTextField5");
        tfEmail.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfEmailCaretUpdate(evt);
            }
        });
        jPanel6.add(tfEmail);

        tfNomorKTP.setText("jTextField6");
        tfNomorKTP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorKTPCaretUpdate(evt);
            }
        });
        jPanel6.add(tfNomorKTP);

        tfNomorTelp.setText("jTextField7");
        tfNomorTelp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorTelpCaretUpdate(evt);
            }
        });
        jPanel6.add(tfNomorTelp);

        tfPekerjaan.setText("jTextField8");
        tfPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPekerjaanCaretUpdate(evt);
            }
        });
        jPanel6.add(tfPekerjaan);

        tfAlamatPekerjaan.setText("jTextField9");
        tfAlamatPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfAlamatPekerjaanCaretUpdate(evt);
            }
        });
        jPanel6.add(tfAlamatPekerjaan);

        tfNomorTelpPekerjaan.setText("jTextField10");
        tfNomorTelpPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorTelpPekerjaanCaretUpdate(evt);
            }
        });
        jPanel6.add(tfNomorTelpPekerjaan);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        tfPasFoto.setText("jTextField11");
        tfPasFoto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPasFotoCaretUpdate(evt);
            }
        });
        jPanel2.add(tfPasFoto);

        jButton1.setText("Browse");
        jPanel2.add(jButton1);

        jPanel6.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        tfFotoKTP.setText("jTextField12");
        tfFotoKTP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfFotoKTPCaretUpdate(evt);
            }
        });
        jPanel3.add(tfFotoKTP);

        jButton2.setText("Browse");
        jButton2.setAlignmentY(0.0F);
        jButton2.setAutoscrolls(true);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.add(jButton2);

        jPanel6.add(jPanel3);

        panel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 190, 270));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jTextField13.setText("jTextField13");
        jPanel7.add(jTextField13, java.awt.BorderLayout.CENTER);

        panel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 230, 30));

        jButton8.setBackground(new java.awt.Color(255, 204, 0));
        jButton8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton8.setText("Tutup");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        panel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, 90, 30));

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1180, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPenghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPenghuniActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
//        int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Keluar","Exit",JOptionPane.YES_NO_OPTION);
//        if (pilihan==0) {
//            System.exit(0);
//        }
        dispose();
    }//GEN-LAST:event_jButton8MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataPenghuni();
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_formComponentShown

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        
        String nama = tfNamaPenghuni.getText();
        String alamat = tfAlamatPenghuni.getText();
        String t4_lahir = tfTempatLahirPenghuni.getText();
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        
        String tgl_lahir = dateFormat.format(dcTanggalLahirPenghuni.getDate());
        String gender = cbGender.getSelectedItem().toString();
        String email = tfEmail.getText();
        String no_ktp = tfNomorKTP.getText();
        String no_telp = tfNomorTelp.getText();
        String pekerjaan = tfPekerjaan.getText();
        String alamat_pekerjaan = tfAlamatPekerjaan.getText();
        String nt_pekerjaan = tfNomorTelpPekerjaan.getText();
        String pasfoto = tfPasFoto.getText();
        String foto_ktp = tfFotoKTP.getText();
        
        TambahDataPenghuni(nama, alamat, t4_lahir, tgl_lahir, gender, email, no_ktp, no_telp, pekerjaan, alamat_pekerjaan, nt_pekerjaan, pasfoto, foto_ktp);
        bersihkanfield();
        KunciField(false);
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int baris = tblPenghuni.getSelectedRow();
        String id = tblPenghuni.getValueAt(baris, 0).toString();
        String nama = tfNamaPenghuni.getText();
        String alamat = tfAlamatPenghuni.getText();
        String t4_lahir = tfTempatLahirPenghuni.getText();
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        
        String tgl_lahir = dateFormat.format(dcTanggalLahirPenghuni.getDate());
        String gender = cbGender.getSelectedItem().toString();
        String email = tfEmail.getText();
        String no_ktp = tfNomorKTP.getText();
        String no_telp = tfNomorTelp.getText();
        String pekerjaan = tfPekerjaan.getText();
        String alamat_pekerjaan = tfAlamatPekerjaan.getText();
        String nt_pekerjaan = tfNomorTelpPekerjaan.getText();
        String pasfoto = tfPasFoto.getText();
        String foto_ktp = tfFotoKTP.getText();
        if(UbahDataPenghuni(id, nama, alamat, t4_lahir, tgl_lahir, gender, email, no_ktp, no_telp, pekerjaan, alamat_pekerjaan, nt_pekerjaan, pasfoto, foto_ktp)){
            JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
            InitTable();
            TampilDataPenghuni();
            bersihkanfield();
            KunciField(false);
            btnSimpan.setEnabled(false);
        }
        else{
            JOptionPane.showConfirmDialog(null, "Gagal Ubah Data");
            
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenghuniMouseClicked
        // TODO add your handling code here:
        int baris = tblPenghuni.getSelectedRow();
        String id_penyewa = tblPenghuni.getValueAt(baris, 0).toString();
        
        try{
            String sql = "Select *from penyewa where id_penyewa = "+id_penyewa+";";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[14];
                o[0] = rss.getString("id_penyewa");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("alamat");
                o[3] = rss.getString("tempat_lahir");
                o[4] = rss.getDate("tgl_lahir");
                o[5] = rss.getString("jk");
                o[6] = rss.getString("noktp");
                o[7] = rss.getString("nohp");
                o[8] = rss.getString("email");
                o[9] = rss.getString("pekerjaan");
                o[10] = rss.getString("alamat_kerja");
                o[11] = rss.getString("notelp_kerja");
                o[12] = rss.getString("foto_wajah");
                o[13] = rss.getString("foto_ktp");

                tfNamaPenghuni.setText(o[1].toString());
//                tfAlamatPenghuni.setText(tblPenghuni.getValueAt(baris, 2).toString());
//                tfTempatLahirPenghuni.setText(tblPenghuni.getValueAt(baris, 3).toString());
//                dcTanggalLahirPenghuni.setDateFormatString(tblPenghuni.getValueAt(baris, 4).toString());
//                cbGender.setSelectedItem(tblPenghuni.getValueAt(baris, 5).toString());
//                tfEmail.setText(tblPenghuni.getValueAt(baris, 6).toString());
//                tfNomorKTP.setText(tblPenghuni.getValueAt(baris, 7).toString());
//                tfNomorTelp.setText(tblPenghuni.getValueAt(baris, 8).toString());
//                tfPekerjaan.setText(tblPenghuni.getValueAt(baris, 9).toString());
//                tfAlamatPekerjaan.setText(tblPenghuni.getValueAt(baris, 10).toString());
//                tfNomorTelpPekerjaan.setText(tblPenghuni.getValueAt(baris, 11).toString());
//                tfPasFoto.setText(tblPenghuni.getValueAt(baris, 12).toString());
//                tfFotoKTP.setText(tblPenghuni.getValueAt(baris, 13).toString());
                tfAlamatPenghuni.setText(o[2].toString());
                tfTempatLahirPenghuni.setText(o[3].toString());
                
                DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
                dcTanggalLahirPenghuni.setDate(dateFormat.parse(o[4].toString()));
                cbGender.setSelectedItem(o[5]);
                tfEmail.setText(o[6].toString());
                tfNomorKTP.setText(o[7].toString());
                tfNomorTelp.setText(o[8].toString());
                tfPekerjaan.setText(o[9].toString());
                tfAlamatPekerjaan.setText(o[10].toString());
                tfNomorTelpPekerjaan.setText(o[11].toString());
                tfPasFoto.setText(o[12].toString());
                tfFotoKTP.setText(o[13].toString());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblPenghuniMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int baris = tblPenghuni.getSelectedRow();
        String id = tblPenghuni.getValueAt(baris, 0).toString();
        
        if(HapusData(id)){
            JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
        }
        else{
            JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");
            
        }
        InitTable();
        TampilDataPenghuni();
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        KunciField(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tfNamaPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaPenghuniCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfNamaPenghuniCaretUpdate

    private void tfAlamatPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfAlamatPenghuniCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfAlamatPenghuniCaretUpdate

    private void tfTempatLahirPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfTempatLahirPenghuniCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfTempatLahirPenghuniCaretUpdate

    private void tfEmailCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfEmailCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfEmailCaretUpdate

    private void tfNomorKTPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomorKTPCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfNomorKTPCaretUpdate

    private void tfNomorTelpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomorTelpCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfNomorTelpCaretUpdate

    private void tfPekerjaanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPekerjaanCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfPekerjaanCaretUpdate

    private void tfAlamatPekerjaanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfAlamatPekerjaanCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfAlamatPekerjaanCaretUpdate

    private void tfNomorTelpPekerjaanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomorTelpPekerjaanCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfNomorTelpPekerjaanCaretUpdate

    private void tfPasFotoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPasFotoCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfPasFotoCaretUpdate

    private void tfFotoKTPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfFotoKTPCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfFotoKTPCaretUpdate

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
            java.util.logging.Logger.getLogger(frm_Penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Penghuni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariPenghuni;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbCariPenghuni;
    private javax.swing.JComboBox<String> cbGender;
    private com.toedter.calendar.JDateChooser dcTanggalLahirPenghuni;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField13;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.Panel panel1;
    private javax.swing.JTable tblPenghuni;
    private javax.swing.JTextField tfAlamatPekerjaan;
    private javax.swing.JTextField tfAlamatPenghuni;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFotoKTP;
    private javax.swing.JTextField tfNamaPenghuni;
    private javax.swing.JTextField tfNomorKTP;
    private javax.swing.JTextField tfNomorTelp;
    private javax.swing.JTextField tfNomorTelpPekerjaan;
    private javax.swing.JTextField tfPasFoto;
    private javax.swing.JTextField tfPekerjaan;
    private javax.swing.JTextField tfTempatLahirPenghuni;
    // End of variables declaration//GEN-END:variables
}

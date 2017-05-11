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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;




/**
 *
 * @author acer
 */
public class frm_transaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet rss;
    int harga;
    int total;
    String id_admin;
    
    public void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("NO.PEMBAYARAN");
        model.addColumn("NAMA PENYEWA");
        model.addColumn("NAMA KAMAR");
        model.addColumn("TOTAL PEMBAYARAN");
        
        tblTransaksi.setModel(model);
    }
    
    private void TambahDataTransaksi(int id_admin,int id_penyewa,String tgl_masuk,String tgl_keluar,int id_kamar,int periode, int biaya){
           try{
            String sql = "INSERT INTO transaksi VALUES(NULL,"
                    +id_admin+",'"+id_penyewa+"','"+tgl_masuk+"','"+tgl_keluar+"','"+id_kamar+"','"+periode+"','"+biaya+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);

        TampilDataTransaksi();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataTransaksi();
    }
    
    public boolean UbahDataTransaksi(String id_transaksi,String nama_kamar,String nama, String biaya){
        try{
            String sql = "UPDATE transaksi "
                    + " join penyewa on transaksi.id_penyewa=penyewa.id_penyewa"
                    + " join kamar on transaksi.id_kamar=kamar.id_kamar"
                    + " set nama_kamar='"+nama_kamar+" ,biaya="+biaya+" , nama='"+nama+"' where id_transaksi="+id_transaksi+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }        
            
    private void TampilDataTransaksi(){
        try{
            String sql = "Select transaksi.id_transaksi,penyewa.nama,kamar.nama_kamar,transaksi.biaya from transaksi"
                    + " join penyewa on transaksi.id_penyewa=penyewa.id_penyewa"
                    + " join kamar on transaksi.id_kamar=kamar.id_kamar;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getString("id_transaksi");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("nama_kamar");
                o[3] = rss.getString("biaya");        
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void bersihkanfield(){
        tfNoPembayaran.setText("");        
        tfNamaPenghuni.setText("");
        tfNomorKtpPenghuni.setText("");
        tfAlamatAsalPenghuni.setText("");
        tfNamaKamar.setText("");
        tfHargaKamar.setText("");
        tfPeriode.setText("");
        tfTotal.setText("");
        dcTglMasuk.setDate(null);
        dcTglKeluar.setDate(null);
    }
    
    private void KunciField(Boolean x){
       tfNoPembayaran.setEnabled(x);
        tfNamaPenghuni.setEnabled(x);
        tfNomorKtpPenghuni.setEnabled(x);
        tfAlamatAsalPenghuni.setEnabled(x);

        tfNamaKamar.setEnabled(x);
        tfHargaKamar.setEnabled(x);
        tfPeriode.setEnabled(x);
        tfTotal.setEnabled(x);
        dcTglMasuk.setEnabled(x);
        dcTglKeluar.setEnabled(x);
        cbKodePenghuni.setEnabled(x);
        cbKamar.setEnabled(x);
    }
    
    public boolean HapusData(String id){
        try{
            String sql = "DELETE FROM transaksi WHERE id_transaksi = "+id+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    private void PencarianData(String by, String cari){
        try{
            String sql = "Select transaksi.id_transaksi,penyewa.nama,kamar.nama_kamar,transaksi.biaya from transaksi"
                    + " join penyewa on transaksi.id_penyewa=penyewa.id_penyewa"
                    + " join kamar on transaksi.id_kamar=kamar.id_kamar where "+by+" Like '%"+cari+"%';";
            
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] data = new Object[4];
                data[0] = rss.getString("id_transaksi");
                data[1] = rss.getString("nama_penyewa");
                data[2] = rss.getString("nama_kamar");
                data[3] = rss.getString("biaya");
                model.addRow(data);
            }
        }
            catch(Exception e){
                    System.out.println(e.getMessage());
                    }
}
    
    public void CetakLaporan (String NamaFile, HashMap hash){
        try{
            InputStream report;
            report = getClass().getResourceAsStream(NamaFile);
            JasperPrint jprint = JasperFillManager.fillReport (report, hash, con);
                JasperViewer viewer = new JasperViewer (jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }        
    }
    
    public void combo_kamar(){
        try{
        String sql = "Select id_kamar from kamar";
        stt = con.createStatement();
        rss = stt.executeQuery(sql);
        while(rss.next()){
            cbKamar.addItem(rss.getString("id_kamar"));
        }
        }catch(SQLException e){
            
        }
    }
    
    public void combo_penghuni(){
        try{
        String sql = "Select id_penyewa from penyewa";
        stt = con.createStatement();
        rss = stt.executeQuery(sql);
        while(rss.next()){
            cbKodePenghuni.addItem(rss.getString("id_penyewa"));
        }
        }catch(SQLException e){
            
        }
    }
    /**
     * Creates new form frm_transaksi
     */
    public frm_transaksi() {
        initComponents();
        combo_penghuni();
        combo_kamar();
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
        panelGlass1 = new usu.widget.glass.PanelGlass();
        jPanel4 = new javax.swing.JPanel();
        btnSimpanTransaksi = new javax.swing.JButton();
        btnTambahTransaksi = new javax.swing.JButton();
        btnEditTransaksi = new javax.swing.JButton();
        btnHapusTransaksi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        btnCetakKwitansi = new javax.swing.JButton();
        btnTutupPembayaran = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        tfNoPembayaran = new javax.swing.JTextField();
        cbKodePenghuni = new javax.swing.JComboBox<>();
        tfNamaPenghuni = new javax.swing.JTextField();
        tfNomorKtpPenghuni = new javax.swing.JTextField();
        tfAlamatAsalPenghuni = new javax.swing.JTextField();
        cbKamar = new javax.swing.JComboBox<>();
        tfNamaKamar = new javax.swing.JTextField();
        tfHargaKamar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tfPeriode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        tfCariPembayaran = new javax.swing.JTextField();
        cbFilterPembayaran = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        dcTglMasuk = new com.toedter.calendar.JDateChooser();
        dcTglKeluar = new com.toedter.calendar.JDateChooser();
        btnCariKamar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGlass1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormPembayaran.png"))); // NOI18N
        panelGlass1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        btnSimpanTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpanTransaksi.setText("SIMPAN");
        btnSimpanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTransaksiActionPerformed(evt);
            }
        });
        jPanel4.add(btnSimpanTransaksi);

        btnTambahTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTambahTransaksi.setText("TAMBAH");
        btnTambahTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahTransaksiActionPerformed(evt);
            }
        });
        jPanel4.add(btnTambahTransaksi);

        btnEditTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditTransaksi.setText("EDIT");
        btnEditTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTransaksiActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditTransaksi);

        btnHapusTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusTransaksi.setText("HAPUS");
        btnHapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusTransaksiActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapusTransaksi);

        panelGlass1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 460, 50));

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        panelGlass1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 590, 220));

        btnCetakKwitansi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCetakKwitansi.setText("KWITANSI PEMBAYARAN");
        btnCetakKwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakKwitansiActionPerformed(evt);
            }
        });
        panelGlass1.add(btnCetakKwitansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 140, 170, 70));

        btnTutupPembayaran.setBackground(new java.awt.Color(240, 177, 32));
        btnTutupPembayaran.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTutupPembayaran.setText("Tutup");
        btnTutupPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTutupPembayaranMouseClicked(evt);
            }
        });
        btnTutupPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupPembayaranActionPerformed(evt);
            }
        });
        panelGlass1.add(btnTutupPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1065, 15, 100, 30));

        jPanel5.setLayout(new java.awt.GridLayout(10, 0, 0, 4));

        tfNoPembayaran.setText("jTextField1");
        jPanel5.add(tfNoPembayaran);

        cbKodePenghuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kode Penghuni" }));
        cbKodePenghuni.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKodePenghuniItemStateChanged(evt);
            }
        });
        jPanel5.add(cbKodePenghuni);

        tfNamaPenghuni.setText("jTextField3");
        jPanel5.add(tfNamaPenghuni);

        tfNomorKtpPenghuni.setText("jTextField4");
        jPanel5.add(tfNomorKtpPenghuni);

        tfAlamatAsalPenghuni.setText("jTextField5");
        jPanel5.add(tfAlamatAsalPenghuni);

        cbKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kamar" }));
        cbKamar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKamarItemStateChanged(evt);
            }
        });
        jPanel5.add(cbKamar);

        tfNamaKamar.setText("jTextField7");
        jPanel5.add(tfNamaKamar);

        tfHargaKamar.setText("jTextField8");
        jPanel5.add(tfHargaKamar);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        tfPeriode.setText("jTextField9");
        tfPeriode.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPeriodeCaretUpdate(evt);
            }
        });
        tfPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPeriodeActionPerformed(evt);
            }
        });
        jPanel3.add(tfPeriode);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bulan");
        jPanel3.add(jLabel1);

        jPanel5.add(jPanel3);

        tfTotal.setText("jTextField10");
        jPanel5.add(tfTotal);

        panelGlass1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 290, 310));

        jPanel6.setLayout(new java.awt.GridLayout(2, 0, 0, 4));

        tfCariPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariPembayaranActionPerformed(evt);
            }
        });
        jPanel6.add(tfCariPembayaran);

        cbFilterPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdasarkan", "Kode Penghuni", "No. KTP", "Nama Kamar" }));
        jPanel6.add(cbFilterPembayaran);

        panelGlass1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 140, 240, 60));

        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 0, 4));
        jPanel8.add(dcTglMasuk);
        jPanel8.add(dcTglKeluar);

        panelGlass1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 470, 285, 60));

        btnCariKamar.setText("CARI");
        btnCariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKamarActionPerformed(evt);
            }
        });
        panelGlass1.add(btnCariKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 80, 30));

        getContentPane().add(panelGlass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTransaksiActionPerformed
        // TODO add your handling code here:
        int baris = tblTransaksi.getSelectedRow();
        String id_transaksi = tblTransaksi.getValueAt(baris, 0).toString();
        String nama_kamar = tfNamaKamar.getText();
//        String harga_sewa = tfHargaKamar.getText();
        String nama = tfNamaPenghuni.getText();
        
        if(UbahDataTransaksi(id_transaksi, nama_kamar, nama, nama)){
            JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
            InitTable();
            TampilDataTransaksi();
            bersihkanfield();
            KunciField(false);
            btnSimpanTransaksi.setEnabled(false);
        }
        else{
            JOptionPane.showConfirmDialog(null, "Gagal Ubah Data");
            
        }
    }//GEN-LAST:event_btnEditTransaksiActionPerformed

    private void btnTambahTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahTransaksiActionPerformed
        // TODO add your handling code here:
        KunciField(true);
    }//GEN-LAST:event_btnTambahTransaksiActionPerformed

    private void btnTutupPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupPembayaranActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnTutupPembayaranActionPerformed

    private void btnTutupPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTutupPembayaranMouseClicked
        // TODO add your handling code here:
//         int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Keluar","Exit",JOptionPane.YES_NO_OPTION);
//        if (pilihan==0) {
//            System.exit(0);
//        }
        dispose();
    }//GEN-LAST:event_btnTutupPembayaranMouseClicked

    private void tfCariPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariPembayaranActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        // TODO add your handling code here:
        int simpan=JOptionPane.showConfirmDialog(this, "Apakah Anda Ingin Menyimpan Data","Confirm Simpan",
        JOptionPane.YES_OPTION);
//        if(tfKodePenghuni.getText().length()!=0 && tfNomorKtpPenghuni.getText().length()!=0 
//                && tfHargaKamar.getText().length()!=0 && tfPeriode.getText().length()!=0 && tfTotal.getText().length()!=0){
//        String nama_penghuni = tfNamaPenghuni.getText();
//        String alamat = tfAlamatAsalPenghuni.getText();
//        String nama_kamar = tfNamaKamar.getText();
        int id_admin = 1;
        int id_penyewa = Integer.parseInt(cbKodePenghuni.getSelectedItem().toString());
        int id_kamar = Integer.parseInt(cbKamar.getSelectedItem().toString());
        int periode = Integer.parseInt(tfPeriode.getText());
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        String tgl_masuk = dateFormat.format(dcTglMasuk.getDate());
        String tgl_keluar = dateFormat.format(dcTglKeluar.getDate());
//        int periode = Integer.parseInt(tfPeriode.getText());
//        harga = periode * harga;
//        }else{
//            JOptionPane.showMessageDialog(this, "Isi Data yang Lengkap Jangan Ada yang Kosong");
//        }\

//        try
//            {
//                String sql="select penyewa.id_penyewa, nama, noktp, alamat, kamar.id_kamar, nama_kamar, harga_sewa, tgl_masuk, tgl_keluar from penyewa join transaksi on penyewa.id_penyewa=transaksi.id_penyewa join admin on admin.id_admin=transaksi.id_admin join kamar on kamar.id_kamar=transaksi.id_kamar;";
//                stt = con.createStatement();
//                rss = stt.executeQuery(sql);
//
//                while(rss.next())
//                {
//                    
//                    
//                    tfNamaPenghuni.setText(rss.getString("nama"));
//                    tfAlamatAsalPenghuni.setText(rss.getString("alamat"));
//                    tfNomorKtpPenghuni.setText(rss.getString("noktp"));
//                    
//                }
//            }catch(Exception e)
//            {
//                JOptionPane.showMessageDialog(null,"GAGAL");
//            }




        TambahDataTransaksi(id_admin, id_penyewa, tgl_masuk, tgl_keluar, id_kamar,periode, total);
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataTransaksi();
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_formComponentShown

    private void tfPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPeriodeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfPeriodeActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        int baris=tblTransaksi.getSelectedRow();
        String id_transaksi = tblTransaksi.getValueAt(baris, 0).toString();
        
        try{
//            String sql = "Select transaksi.id_transaksi,penyewa.nama,kamar.nama_kamar,transaksi.biaya from transaksi"
//                    + " join penyewa on transaksi.id_penyewa=penyewa.id_penyewa"
//                    + " join kamar on transaksi.id_kamar=kamar.id_kamar where id_transaksi="+id_transaksi+";";
            String sql = " select transaksi.id_transaksi, penyewa.id_penyewa, penyewa.nama, penyewa.noktp, penyewa.alamat, kamar.id_kamar, kamar.nama_kamar, kamar.harga_sewa,transaksi.periode, transaksi.biaya, transaksi.tgl_masuk, transaksi.tgl_keluar from penyewa join transaksi on penyewa.id_penyewa=transaksi.id_penyewa join admin on admin.id_admin=transaksi.id_admin join kamar on kamar.id_kamar=transaksi.id_kamar where transaksi.id_transaksi = "+id_transaksi+";";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[12];
                o[0] = rss.getString("id_transaksi");
                o[1] = rss.getString("id_penyewa");
                o[2] = rss.getString("nama");
                o[3] = rss.getString("noktp");
                o[4] = rss.getString("alamat");
                o[5] = rss.getString("id_kamar");
                o[6] = rss.getString("nama_kamar");
                o[7] = rss.getString("harga_sewa");
                o[8] = rss.getString("periode");
                o[9] = rss.getString("biaya");
                o[10] = rss.getString("tgl_masuk");
                o[11] = rss.getString("tgl_keluar");
               

//                tfNoPembayaran.setText(o[0].toString());
//                tfNamaPenghuni.setText(o[1].toString());
//                tfNamaKamar.setText(o[2].toString());
//                tfTotal.setText(o[2].toString());
                tfNoPembayaran.setText(o[0].toString());
//                tfKodePenghuni.setText(o[1].toString());
                cbKodePenghuni.setSelectedItem(o[1].toString());
                tfNamaPenghuni.setText(o[2].toString());
                tfNomorKtpPenghuni.setText(o[3].toString());
                tfAlamatAsalPenghuni.setText(o[4].toString());
//                tfPilihKamarPenghuni.setText(o[5].toString());
                cbKamar.setSelectedItem(o[5].toString());
                tfNamaKamar.setText(o[6].toString());
                tfHargaKamar.setText(o[7].toString());
                tfPeriode.setText(o[8].toString());
                tfTotal.setText(o[9].toString());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//                dcTanggalLahirPenghuni.setDate(dateFormat.parse(o[4].toString()));
                dcTglMasuk.setDate(dateFormat.parse(o[10].toString()));
                dcTglKeluar.setDate(dateFormat1.parse(o[11].toString()));
                

            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void tfPeriodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPeriodeCaretUpdate
        // TODO add your handling code here:\
        try{
            
        
        
        if(tfPeriode.getText().equals("")){
            
        }else{
            int jumlah=Integer.parseInt(tfPeriode.getText())*Integer.parseInt(tfHargaKamar.getText());
            tfTotal.setText(Integer.toString(jumlah));
            total=Integer.parseInt(tfTotal.getText());
        }
        }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL");
            }
    }//GEN-LAST:event_tfPeriodeCaretUpdate

    private void btnCetakKwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakKwitansiActionPerformed
        
        int baris = tblTransaksi.getSelectedRow();
        int id_transaksi = Integer.parseInt(tblTransaksi.getValueAt(baris, 0).toString());
        String namaFile;
        HashMap hash = new HashMap();
        if((tblTransaksi.getSelectedRow()==0)){
            namaFile = "/reports/KWITANSI1.jasper";
        }
        else{
            namaFile = "/reports/KWITANSI1.jasper";
            hash.put("id_transaksi",id_transaksi);
        }
        CetakLaporan(namaFile, hash);
     
        
        
    }//GEN-LAST:event_btnCetakKwitansiActionPerformed

    private void btnHapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusTransaksiActionPerformed
        // TODO add your handling code here:
        int baris = tblTransaksi.getSelectedRow();
        String id = tblTransaksi.getValueAt(baris, 0).toString();

        if (HapusData(id)) {
            JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
        } else {
            JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");

        }
        InitTable();
        TampilDataTransaksi();
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_btnHapusTransaksiActionPerformed

    private void btnCariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKamarActionPerformed
        // TODO add your handling code here:
        if (cbFilterPembayaran.getSelectedItem().equals("Berdasarkan")) {//jika pada combobox yang terseleksi masih"cari bardasarkan, maka jalankan perintah berikut
            JOptionPane.showMessageDialog(null, "Pilih Filter Pencarian", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            //sintak diatas untuk menampilkan pesan dialog beruppa kofirmasi
        } else {

            InitTable();
            if (tfCariPembayaran.getText().length() == 0) {
                TampilDataTransaksi();
            } else {

                PencarianData(cbFilterPembayaran.getSelectedItem().toString(), tfCariPembayaran.getText());

            }
        }
    }//GEN-LAST:event_btnCariKamarActionPerformed

    private void cbKodePenghuniItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKodePenghuniItemStateChanged
        // TODO add your handling code here:
        try
            {
                String sql="select * from penyewa where id_penyewa="+cbKodePenghuni.getSelectedItem()+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);

                while(rss.next())
                {
                    tfNamaPenghuni.setText(rss.getString("nama"));
                    tfAlamatAsalPenghuni.setText(rss.getString("alamat"));
                    tfNomorKtpPenghuni.setText(rss.getString("noktp"));
                    
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL");
            }  
    }//GEN-LAST:event_cbKodePenghuniItemStateChanged

    private void cbKamarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKamarItemStateChanged
        // TODO add your handling code here:
        try
            {
                String sql="select * from kamar where id_kamar="+cbKamar.getSelectedItem()+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);

                while(rss.next())
                {
                    tfNamaKamar.setText(rss.getString("nama_kamar"));
                    tfHargaKamar.setText(rss.getString("harga_sewa"));
                    
                }
                harga = Integer.parseInt(tfHargaKamar.getText());
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL");
            } 
    }//GEN-LAST:event_cbKamarItemStateChanged

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
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariKamar;
    private javax.swing.JButton btnCetakKwitansi;
    private javax.swing.JButton btnEditTransaksi;
    private javax.swing.JButton btnHapusTransaksi;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JButton btnTambahTransaksi;
    private javax.swing.JButton btnTutupPembayaran;
    private javax.swing.JComboBox cbFilterPembayaran;
    private javax.swing.JComboBox<String> cbKamar;
    private javax.swing.JComboBox<String> cbKodePenghuni;
    private com.toedter.calendar.JDateChooser dcTglKeluar;
    private com.toedter.calendar.JDateChooser dcTglMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.glass.PanelGlass panelGlass1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField tfAlamatAsalPenghuni;
    private javax.swing.JTextField tfCariPembayaran;
    private javax.swing.JTextField tfHargaKamar;
    private javax.swing.JTextField tfNamaKamar;
    private javax.swing.JTextField tfNamaPenghuni;
    private javax.swing.JTextField tfNoPembayaran;
    private javax.swing.JTextField tfNomorKtpPenghuni;
    private javax.swing.JTextField tfPeriode;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables

}

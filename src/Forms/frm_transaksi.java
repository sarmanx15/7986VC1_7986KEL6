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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class frm_transaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet rss;
    
    public void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("NO.PEMBAYARAN");
        model.addColumn("NAMA PENYEWA");
        model.addColumn("NAMA KAMAR");
        model.addColumn("TOTAL PEMBAYARAN");
        
        tblTransaksi.setModel(model);
    }
    
    private void TambahDataTransaksi(String id_penyewa,String nama,String alamat,
            String noktp,String id_kamar,String nama_kamar,String harga_sewa,String bulan,String biaya,String tgl_masuk,String tgl_keluar){
           try{
            String sql = "INSERT INTO transaksi VALUES(NULL,"
                    +id_penyewa+",'"+nama+"','"+alamat+"','"+noktp+"','"+nama_kamar+"','"+harga_sewa+"',"
                    +bulan+","+biaya+",'"+tgl_masuk+"','"+tgl_keluar+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{alamat,biaya,bulan,id_kamar,id_penyewa,nama,nama_kamar,noktp,tgl_masuk,tgl_keluar,nama_kamar, harga_sewa});
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
        tfKodePenghuni.setText("");
        tfNamaPenghuni.setText("");
        tfNomorKtpPenghuni.setText("");
        tfAlamatAsalPenghuni.setText("");
        tfPilihKamarPenghuni.setText("");
        tfNamaKamar.setText("");
        tfHargaKamar.setText("");
        tfPeriode.setText("");
        tfTotal.setText("");
        tfTerbilang.setText("");
        dcTglMasuk.setDate(null);
        dcTglKeluar.setDate(null);
    }
    
    private void KunciField(Boolean x){
       tfNoPembayaran.setEnabled(x);
        tfKodePenghuni.setEnabled(x);
        tfNamaPenghuni.setEnabled(x);
        tfNomorKtpPenghuni.setEnabled(x);
        tfAlamatAsalPenghuni.setEnabled(x);
        tfPilihKamarPenghuni.setEnabled(x);
        tfNamaKamar.setEnabled(x);
        tfHargaKamar.setEnabled(x);
        tfPeriode.setEnabled(x);
        tfTotal.setEnabled(x);
        tfTerbilang.setEnabled(x);
        dcTglMasuk.setEnabled(x);
        dcTglKeluar.setEnabled(x);
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
//    private void total_transaksi(){
//        String sql="Select sum(biaya) from transaksi where id_transaksi="+tfNoPembayaran+";";
//        try{
//            stt = con.createStatement();
//            rss = stt.executeQuery(sql);
//            
//            while(rss.next()){
//                lbtotal.setText(rss.getString(1));
//            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            System.out.println(""+e.getMessage());
//        }
//    }
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
    /**
     * Creates new form frm_transaksi
     */
    public frm_transaksi() {
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
        jPanel1 = new javax.swing.JPanel();
        tfKodePenghuni = new javax.swing.JTextField();
        btnCariKodePenghuni = new javax.swing.JButton();
        tfNamaPenghuni = new javax.swing.JTextField();
        tfNomorKtpPenghuni = new javax.swing.JTextField();
        tfAlamatAsalPenghuni = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        tfPilihKamarPenghuni = new javax.swing.JTextField();
        btnCariKamarPenghuni = new javax.swing.JButton();
        tfNamaKamar = new javax.swing.JTextField();
        tfHargaKamar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tfPeriode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        tfTerbilang = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        tfCariPembayaran = new javax.swing.JTextField();
        cbFilterPembayaran = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        btnCariKamar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        dcTglMasuk = new com.toedter.calendar.JDateChooser();
        dcTglKeluar = new com.toedter.calendar.JDateChooser();

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

        panelGlass1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 590, 390));

        btnCetakKwitansi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCetakKwitansi.setText("KWITANSI PEMBAYARAN");
        panelGlass1.add(btnCetakKwitansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 170, 70));

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
        panelGlass1.add(btnTutupPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 100, 30));

        jPanel5.setLayout(new java.awt.GridLayout(10, 0, 0, 4));

        tfNoPembayaran.setText("jTextField1");
        jPanel5.add(tfNoPembayaran);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        tfKodePenghuni.setText("jTextField2");
        jPanel1.add(tfKodePenghuni);

        btnCariKodePenghuni.setText("CARI");
        btnCariKodePenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKodePenghuniActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariKodePenghuni);

        jPanel5.add(jPanel1);

        tfNamaPenghuni.setText("jTextField3");
        jPanel5.add(tfNamaPenghuni);

        tfNomorKtpPenghuni.setText("jTextField4");
        jPanel5.add(tfNomorKtpPenghuni);

        tfAlamatAsalPenghuni.setText("jTextField5");
        jPanel5.add(tfAlamatAsalPenghuni);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        tfPilihKamarPenghuni.setText("jTextField6");
        tfPilihKamarPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPilihKamarPenghuniActionPerformed(evt);
            }
        });
        jPanel2.add(tfPilihKamarPenghuni);

        btnCariKamarPenghuni.setText("CARI");
        btnCariKamarPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKamarPenghuniActionPerformed(evt);
            }
        });
        jPanel2.add(btnCariKamarPenghuni);

        jPanel5.add(jPanel2);

        tfNamaKamar.setText("jTextField7");
        jPanel5.add(tfNamaKamar);

        tfHargaKamar.setText("jTextField8");
        jPanel5.add(tfHargaKamar);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        tfPeriode.setText("jTextField9");
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

        panelGlass1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 290, 310));

        tfTerbilang.setText("jTextField11");
        panelGlass1.add(tfTerbilang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 470, 30));

        jPanel6.setLayout(new java.awt.GridLayout(2, 0, 0, 4));

        tfCariPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariPembayaranActionPerformed(evt);
            }
        });
        jPanel6.add(tfCariPembayaran);

        cbFilterPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdasarkan", "Kode Penghuni", "No. KTP", "Nama Kamar" }));
        jPanel6.add(cbFilterPembayaran);

        panelGlass1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 240, 60));

        jPanel7.setLayout(new java.awt.BorderLayout());

        btnCariKamar.setText("CARI");
        jPanel7.add(btnCariKamar, java.awt.BorderLayout.CENTER);

        panelGlass1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 130, 80, 30));

        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 0, 4));
        jPanel8.add(dcTglMasuk);
        jPanel8.add(dcTglKeluar);

        panelGlass1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 463, 285, 54));

        getContentPane().add(panelGlass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 1180, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTransaksiActionPerformed
        // TODO add your handling code here:
        int baris = tblTransaksi.getSelectedRow();
        String id_transaksi = tblTransaksi.getValueAt(baris, 0).toString();
        String nama_kamar = tfNamaKamar.getText();
        String harga_sewa = tfHargaKamar.getText();
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

    private void btnCariKodePenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKodePenghuniActionPerformed
        // TODO add your handling code here:
//        String id_penyewa=null;
        try
            {
                String sql="select * from penyewa where id_penyewa="+tfKodePenghuni.getText()+";";
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
    }//GEN-LAST:event_btnCariKodePenghuniActionPerformed

    private void tfCariPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariPembayaranActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        // TODO add your handling code here:
        int simpan=JOptionPane.showConfirmDialog(this, "Apakah Anda Ingin Menyimpan Data","Confirm Simpan",
        JOptionPane.YES_OPTION);
//        if(tfKodePenghuni.getText().length()!=0 && tfNomorKtpPenghuni.getText().length()!=0 
//                && tfHargaKamar.getText().length()!=0 && tfPeriode.getText().length()!=0 && tfTotal.getText().length()!=0){
        String nama_penghuni = tfNamaPenghuni.getText();
        String alamat = tfAlamatAsalPenghuni.getText();
        String nama_kamar = tfNamaKamar.getText();
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        String tgl_masuk = dateFormat.format(dcTglMasuk.getDate());
        String tgl_keluar = dateFormat.format(dcTglKeluar.getDate());
//        }else{
//            JOptionPane.showMessageDialog(this, "Isi Data yang Lengkap Jangan Ada yang Kosong");
//        }
        TambahDataTransaksi(tgl_keluar, alamat, alamat, alamat, nama_kamar, nama_kamar, nama_kamar, alamat, alamat, tgl_masuk, tgl_keluar);
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened

    private void tfPilihKamarPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPilihKamarPenghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPilihKamarPenghuniActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataTransaksi();
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_formComponentShown

    private void btnCariKamarPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKamarPenghuniActionPerformed
        // TODO add your handling code here:
        try
            {
                String sql="select * from kamar where id_kamar="+tfPilihKamarPenghuni.getText()+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);

                while(rss.next())
                {
                    tfNamaKamar.setText(rss.getString("nama_kamar"));
                    tfHargaKamar.setText(rss.getString("harga_sewa")); 
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL");
            } 
    }//GEN-LAST:event_btnCariKamarPenghuniActionPerformed

    private void tfPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPeriodeActionPerformed
        // TODO add your handling code here:
        try{
            double jumlah=Double.parseDouble(tfPeriode.getText())*Double.parseDouble(tfHargaKamar.getText());
        double total = 0;
        total= total+jumlah;
        if(tfPeriode.getText().equals("")){
            
        }else{
            tfTotal.setText(Double.toString(total));
            total=Double.parseDouble(tfTotal.getText());
        }
        }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"GAGAL");
            }
    }//GEN-LAST:event_tfPeriodeActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        int baris=tblTransaksi.getSelectedRow();
        String id_transaksi = tblTransaksi.getValueAt(baris, 0).toString();
        
        try{
            String sql = "Select transaksi.id_transaksi,penyewa.nama,kamar.nama_kamar,transaksi.biaya from transaksi"
                    + " join penyewa on transaksi.id_penyewa=penyewa.id_penyewa"
                    + " join kamar on transaksi.id_kamar=kamar.id_kamar where id_transaksi="+id_transaksi+";";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[3];
                o[0] = rss.getString("id_transaksi");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("nama_kamar");
                o[3] = rss.getString("biaya");
                tfNoPembayaran.setText(o[0].toString());
                tfNamaPenghuni.setText(o[1].toString());
                tfNamaKamar.setText(o[2].toString());
                tfTotal.setText(o[2].toString());

            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }//GEN-LAST:event_tblTransaksiMouseClicked

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
    private javax.swing.JButton btnCariKamarPenghuni;
    private javax.swing.JButton btnCariKodePenghuni;
    private javax.swing.JButton btnCetakKwitansi;
    private javax.swing.JButton btnEditTransaksi;
    private javax.swing.JButton btnHapusTransaksi;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JButton btnTambahTransaksi;
    private javax.swing.JButton btnTutupPembayaran;
    private javax.swing.JComboBox cbFilterPembayaran;
    private com.toedter.calendar.JDateChooser dcTglKeluar;
    private com.toedter.calendar.JDateChooser dcTglMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.glass.PanelGlass panelGlass1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField tfAlamatAsalPenghuni;
    private javax.swing.JTextField tfCariPembayaran;
    private javax.swing.JTextField tfHargaKamar;
    private javax.swing.JTextField tfKodePenghuni;
    private javax.swing.JTextField tfNamaKamar;
    private javax.swing.JTextField tfNamaPenghuni;
    private javax.swing.JTextField tfNoPembayaran;
    private javax.swing.JTextField tfNomorKtpPenghuni;
    private javax.swing.JTextField tfPeriode;
    private javax.swing.JTextField tfPilihKamarPenghuni;
    private javax.swing.JTextField tfTerbilang;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
}

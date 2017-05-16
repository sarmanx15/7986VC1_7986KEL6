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
import java.util.Date;
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
     int total1;
    static int jumlahfasilitas;
    static int id_admin;
//    String id_admin;
//    String id_transaksi = null;
    
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
    
    public boolean UbahDataTransaksi(String id_transaksi, int id_admin,int id_penyewa,String tgl_masuk,String tgl_keluar,int id_kamar,int periode, int biaya){
        try{
            String sql = "UPDATE transaksi set id_admin = "+id_admin+", id_kamar="+id_kamar+" ,biaya="+biaya+" where id_transaksi="+id_transaksi+";";
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
            String sql = "Select transaksi.id_transaksi,penyewa.nama,kamar.nama_kamar,transaksi.biaya from transaksi join penyewa on transaksi.id_penyewa=penyewa.id_penyewa join kamar on transaksi.id_kamar=kamar.id_kamar;";
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
        cbKamar.setSelectedItem("Pilih Kamar");
        cbKodePenghuni.setSelectedItem("Pilih Kode Penghuni");
        tfJumlahFas.setText("");
        
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
        btnTmbhFas.setEnabled(x);
        tfJumlahFas.setEnabled(x);
    }
    
    public void cekstatus() {
        if (tfNoPembayaran.getText().length() == 0
                || cbKodePenghuni.getSelectedItem().equals("Pilih Kode Penghuni")
                || tfNamaPenghuni.getText().length() == 0
                || tfNomorKtpPenghuni.getText().length() == 0
                || tfAlamatAsalPenghuni.getText().length() == 0
                || cbKamar.getSelectedItem().equals("Pilih Kamar")
                || tfNamaKamar.getText().length() == 0
                || tfHargaKamar.getText().length() == 0
                || tfPeriode.getText().length() == 0
                || tfTotal.getText().length() == 0
                || dcTglMasuk.getDate()==null
                || dcTglKeluar.getDate()==null
                
                ) {
            btnSimpanTransaksi.setEnabled(false);
        } else {
            btnSimpanTransaksi.setEnabled(true);
        }
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
                data[1] = rss.getString("nama");
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
        String sql = "Select id_kamar from kamar where status = 'Kosong'";
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
    
        public boolean validasitanggal(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date sekarang = new Date();
        String bayar= dateFormat.format(dcTglMasuk.getDate());
//        Date tanggal_sekarang = dateFormat.format(sekarang);
        String tempo = dateFormat.format(dcTglKeluar.getDate());
//        Date tgl_lahir = dcTanggalLahirPenghuni.getDate();
        
        
        try {
            Date tanggal_bayar = dateFormat.parse(bayar);
            Date jatuh_tempo = dateFormat.parse(tempo);

            if(jatuh_tempo.equals(tanggal_bayar)){
                JOptionPane.showMessageDialog(null, "Tanggal Sama dengan Hari Ini.. \n Silahkan Isi Dengan Benar!");
                dcTglKeluar.requestFocus();
                return false;
            }
            else if(jatuh_tempo.before(tanggal_bayar)){
                JOptionPane.showMessageDialog(null, "Tanggal Jatuh Tempo Tidak Boleh Sebelum Tanggal Bayar.. \n Silahkan Isi Dengan Benar!");
                dcTglKeluar.requestFocus();                    
                return false;
                }
            else{
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
        
    }

    
    public boolean validasiID(String id_transaksi){
    try{
            String sql = "Select *from transaksi where id_transaksi = '"+id_transaksi+"';";
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
    
    public boolean isiKamar(int id_kamar){
        try{
            String sql = "UPDATE kamar set status = 'Terisi' WHERE id_kamar = "+id_kamar+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean kosongKamar(String nama_kamar){
        try{
            String sql = "UPDATE kamar set status = 'Kosong' WHERE nama_kamar = '"+nama_kamar+"';";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
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
        jButton1 = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        tfJumlahFas = new javax.swing.JTextField();
        btnTmbhFas = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tfCariPembayaran = new javax.swing.JTextField();
        cbFilterPembayaran = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        dcTglMasuk = new com.toedter.calendar.JDateChooser();
        dcTglKeluar = new com.toedter.calendar.JDateChooser();
        btnCariKamar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnEditTransaksi = new javax.swing.JButton();
        btnHapusTransaksi = new javax.swing.JButton();
        tfTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
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
        panelGlass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelGlass1MouseClicked(evt);
            }
        });
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

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BERSIHKAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

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

        panelGlass1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 530, 220));

        btnCetakKwitansi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCetakKwitansi.setText("KWITANSI PEMBAYARAN");
        btnCetakKwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakKwitansiActionPerformed(evt);
            }
        });
        panelGlass1.add(btnCetakKwitansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 140, 190, 60));

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

        jPanel5.setLayout(new java.awt.GridLayout(10, 0, 0, 3));

        tfNoPembayaran.setEditable(false);
        tfNoPembayaran.setText("jTextField1");
        tfNoPembayaran.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNoPembayaranCaretUpdate(evt);
            }
        });
        jPanel5.add(tfNoPembayaran);

        cbKodePenghuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kode Penghuni" }));
        cbKodePenghuni.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKodePenghuniItemStateChanged(evt);
            }
        });
        jPanel5.add(cbKodePenghuni);

        tfNamaPenghuni.setText("jTextField3");
        tfNamaPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfNamaPenghuni);

        tfNomorKtpPenghuni.setText("jTextField4");
        tfNomorKtpPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorKtpPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfNomorKtpPenghuni);

        tfAlamatAsalPenghuni.setText("jTextField5");
        tfAlamatAsalPenghuni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfAlamatAsalPenghuniCaretUpdate(evt);
            }
        });
        jPanel5.add(tfAlamatAsalPenghuni);

        cbKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kamar" }));
        cbKamar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKamarItemStateChanged(evt);
            }
        });
        jPanel5.add(cbKamar);

        tfNamaKamar.setText("jTextField7");
        tfNamaKamar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaKamarCaretUpdate(evt);
            }
        });
        jPanel5.add(tfNamaKamar);

        tfHargaKamar.setText("jTextField8");
        tfHargaKamar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfHargaKamarCaretUpdate(evt);
            }
        });
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

        jPanel2.setLayout(new java.awt.GridLayout());

        tfJumlahFas.setText("jTextField1");
        tfJumlahFas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfJumlahFasCaretUpdate(evt);
            }
        });
        jPanel2.add(tfJumlahFas);

        btnTmbhFas.setText("Tambah");
        btnTmbhFas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTmbhFasActionPerformed(evt);
            }
        });
        jPanel2.add(btnTmbhFas);

        jPanel5.add(jPanel2);

        panelGlass1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 290, 310));

        jPanel6.setLayout(new java.awt.GridLayout(2, 0, 0, 4));

        tfCariPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariPembayaranActionPerformed(evt);
            }
        });
        jPanel6.add(tfCariPembayaran);

        cbFilterPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdasarkan", "Nama Penghuni", "Nama Kamar", " " }));
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

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        btnEditTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditTransaksi.setText("EDIT");
        btnEditTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditTransaksi);

        btnHapusTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusTransaksi.setText("HAPUS");
        btnHapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusTransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapusTransaksi);

        panelGlass1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 230, 90, 70));

        tfTotal.setText("jTextField10");
        tfTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfTotalCaretUpdate(evt);
            }
        });
        panelGlass1.add(tfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 290, 27));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Note :");
        panelGlass1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, -1, -1));

        jLabel3.setText("- Untuk Menambah Data Pembayaran Penghuni yang pernah melakukan transaksi, harap hapus data transaksi");
        panelGlass1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 500, -1, -1));

        jLabel4.setText("terlebih dahulu, Berlaku Juga Untuk Perpanjangan Masa Penyewaan Kamar.");
        panelGlass1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 515, -1, -1));

        jLabel5.setText("- Data Kamar yang Telah Terisi Tidak Akan Ditampilkan pada Combobox");
        panelGlass1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, -1, -1));

        getContentPane().add(panelGlass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTransaksiActionPerformed
        // TODO add your handling code here:
        
//        if((tblTransaksi.getSelectedRow()==-1)){
//            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin diedit pada Tabel..");            
//        }
//        else{     
            KunciField(true);

            int baris=tblTransaksi.getSelectedRow();
            String id_transaksi = tblTransaksi.getValueAt(baris, 0).toString();

            try{
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

                    tfNoPembayaran.setText(o[0].toString());

                    cbKodePenghuni.setSelectedItem(o[1].toString());
                    tfNamaPenghuni.setText(o[2].toString());
                    tfNomorKtpPenghuni.setText(o[3].toString());
                    tfAlamatAsalPenghuni.setText(o[4].toString());
                    cbKamar.setSelectedItem(o[5].toString());
                    tfNamaKamar.setText(o[6].toString());
                    tfHargaKamar.setText(o[7].toString());
                    tfPeriode.setText(o[8].toString());
                    tfTotal.setText(o[9].toString());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    dcTglMasuk.setDate(dateFormat.parse(o[10].toString()));
                    dcTglKeluar.setDate(dateFormat1.parse(o[11].toString()));                
                }
                 }catch(SQLException e){
                System.out.println(e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
            }
//        }
    }//GEN-LAST:event_btnEditTransaksiActionPerformed

    private void btnTambahTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahTransaksiActionPerformed
        // TODO add your handling code here:
        tblTransaksi.clearSelection();
        KunciField(true);
        bersihkanfield();
        try{
            String sql = "Select max(id_transaksi) as id_transaksi from logIDTransaksi;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[1];
                o[0] = rss.getString("id_transaksi");
                
                int id = Integer.parseInt(o[0].toString())+1;
                String id_transaksi = String.valueOf(id);
                tfNoPembayaran.setText(id_transaksi);

                
            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTambahTransaksiActionPerformed

    private void btnTutupPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupPembayaranActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnTutupPembayaranActionPerformed

    private void btnTutupPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTutupPembayaranMouseClicked
        int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Menutup? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (pilihan==0) {
            dispose();
        }
    }//GEN-LAST:event_btnTutupPembayaranMouseClicked

    private void tfCariPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariPembayaranActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        // TODO add your handling code here:
        
       
        String nama_kamar = tfNamaKamar.getText();
        String nama = tfNamaPenghuni.getText();
        
        
        int id_penyewa = Integer.parseInt(cbKodePenghuni.getSelectedItem().toString());
        int id_kamar = Integer.parseInt(cbKamar.getSelectedItem().toString());
        int periode = Integer.parseInt(tfPeriode.getText());
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        String tgl_masuk = dateFormat.format(dcTglMasuk.getDate());
        String tgl_keluar = dateFormat.format(dcTglKeluar.getDate());
        
        if(validasiID(tfNoPembayaran.getText())){
            int baris = tblTransaksi.getSelectedRow();
            String id_trans = tblTransaksi.getValueAt(baris, 0).toString();
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Mengubah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                
                if(UbahDataTransaksi(id_trans, id_admin, id_penyewa, tgl_masuk, tgl_keluar, id_kamar,periode, total)){
                    JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
                    InitTable();
                    TampilDataTransaksi();
                    bersihkanfield();
                    KunciField(false);
                    btnSimpanTransaksi.setEnabled(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Gagal Ubah Data");

                }
            }
        }
        else{
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Menambah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                if(validasitanggal()){
                    if(isiKamar(id_kamar)){
                        TambahDataTransaksi(id_admin, id_penyewa, tgl_masuk, tgl_keluar, id_kamar,periode, total1);
                        bersihkanfield();
                        KunciField(false);
                    }
                }
            }
        }   
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
       
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void tfPeriodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPeriodeCaretUpdate
        // TODO add your handling code here:
        cekstatus();
        try{
        if(tfPeriode.getText().equals("")){
            
        }else{
            int jumlah=Integer.parseInt(tfPeriode.getText()) * Integer.parseInt(tfHargaKamar.getText());
            tfTotal.setText(Integer.toString(jumlah));
            total=Integer.parseInt(tfTotal.getText());
        }
        }catch(Exception e)
            {
//                JOptionPane.showMessageDialog(null,"GAGAL");
            }
    }//GEN-LAST:event_tfPeriodeCaretUpdate

    private void btnCetakKwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakKwitansiActionPerformed
        
        if((tblTransaksi.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih data pada Tabel..");            
        }
        else{  
            int baris = tblTransaksi.getSelectedRow();
            int id_transaksi = Integer.parseInt(tblTransaksi.getValueAt(baris, 0).toString());
            String namaFile;
            HashMap hash = new HashMap();
            if((tblTransaksi.getSelectedRow()<0)){
                namaFile = "/reports/KWITANSI1.jasper";
            }
            else{
                namaFile = "/reports/KWITANSI1.jasper";
                hash.put("id_transaksi",id_transaksi);
            }
            CetakLaporan(namaFile, hash);
        }        
        
    }//GEN-LAST:event_btnCetakKwitansiActionPerformed

    private void btnHapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusTransaksiActionPerformed
        // TODO add your handling code here:
        if((tblTransaksi.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin dihapus pada Tabel..");            
        }
        else{ 
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Menghapus data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                int baris = tblTransaksi.getSelectedRow();
                String id = tblTransaksi.getValueAt(baris, 0).toString();
                String nama_kamar = tblTransaksi.getValueAt(baris, 2).toString();

                if (HapusData(id)) {
                   if(kosongKamar(nama_kamar)){
                       JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
                   }
                    JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
                } else {
                    JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");
                }
                InitTable();
                TampilDataTransaksi();
                bersihkanfield();
                KunciField(false);
            }
        }
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
                String cari = null;
                if (cbFilterPembayaran.getSelectedItem()=="Nama Penghuni"){
                    cari="nama";
                    
                }
                else if (cbFilterPembayaran.getSelectedItem()=="Nama Kamar"){                
                    cari="nama_kamar";
                    
                }

                PencarianData(cari, tfCariPembayaran.getText());

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
//                JOptionPane.showMessageDialog(null,"GAGAL");
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
//                JOptionPane.showMessageDialog(null,"GAGAL");
            } 
    }//GEN-LAST:event_cbKamarItemStateChanged

    private void tfNoPembayaranCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNoPembayaranCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNoPembayaranCaretUpdate

    private void tfNamaPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaPenghuniCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNamaPenghuniCaretUpdate

    private void tfNomorKtpPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomorKtpPenghuniCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNomorKtpPenghuniCaretUpdate

    private void tfAlamatAsalPenghuniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfAlamatAsalPenghuniCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfAlamatAsalPenghuniCaretUpdate

    private void tfNamaKamarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaKamarCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNamaKamarCaretUpdate

    private void tfHargaKamarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfHargaKamarCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfHargaKamarCaretUpdate

    private void tfTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfTotalCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfTotalCaretUpdate

    private void panelGlass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGlass1MouseClicked
        // TODO add your handling code here:
        tblTransaksi.clearSelection();
    }//GEN-LAST:event_panelGlass1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        bersihkanfield();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTmbhFasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTmbhFasActionPerformed
        // TODO add your handling code here:
        new frm_PilihFasilitas().setVisible(true);        
    }//GEN-LAST:event_btnTmbhFasActionPerformed

    private void tfJumlahFasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfJumlahFasCaretUpdate
        // TODO add your handling code here:
        
        try{
        if(tfJumlahFas.getText().equals("")){
            
        }else{
            int jumlahfas= total+jumlahfasilitas;
            tfTotal.setText(Integer.toString(jumlahfas));
            total1=Integer.parseInt(tfTotal.getText());
        }
        }catch(Exception e)
            {
//                JOptionPane.showMessageDialog(null,"GAGAL");
            }
    }//GEN-LAST:event_tfJumlahFasCaretUpdate

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
    private javax.swing.JButton btnTmbhFas;
    private javax.swing.JButton btnTutupPembayaran;
    private javax.swing.JComboBox cbFilterPembayaran;
    private javax.swing.JComboBox<String> cbKamar;
    private javax.swing.JComboBox<String> cbKodePenghuni;
    private com.toedter.calendar.JDateChooser dcTglKeluar;
    private com.toedter.calendar.JDateChooser dcTglMasuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    public static javax.swing.JTextField tfJumlahFas;
    private javax.swing.JTextField tfNamaKamar;
    private javax.swing.JTextField tfNamaPenghuni;
    private javax.swing.JTextField tfNoPembayaran;
    private javax.swing.JTextField tfNomorKtpPenghuni;
    private javax.swing.JTextField tfPeriode;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables

}

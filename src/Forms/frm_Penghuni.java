/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author acer
 */
public class frm_Penghuni extends javax.swing.JFrame {
    File file,file1;
    JFileChooser jfc,jfc1;    
    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet rss;
    private BufferedImage image;
    
    // =============== Double Click Table to Detail View ====================
//    private void initListeners(){
//    tblPenghuni.addMouseListener(new MouseAdapter(){
//        public void mousePressed(MouseEvent me){
//            JTable table = (JTable)me.getSource();
//            Point p = me.getPoint();
//            int row = table.rowAtPoint(p);
//            if(me.getClickCount()==2){
//                new frm_detail_penghuni().setVisible(true);
//            }
//        }
//    });
//    }
// ======================== EOS =============================
    public void InitTable() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NAMA");
        model.addColumn("ALAMAT");
        model.addColumn("GENDER");
        model.addColumn("NO. TELP");
        model.addColumn("EMAIL");
//        model.addColumn("NO. KTP");

//        model.addColumn("PEKERJAAN");
//        model.addColumn("ALMT PEKERJAAN");
//        model.addColumn("NO TELP PEKERJAAN");
        model.addColumn("PAS FOTO");
        model.addColumn("FOTO KTP");
        tblPenghuni.setModel(model);

    }

    private void TambahDataPenghuni(String nama, String alamat, String tempat_lahir, String tgl_lahir, String jk, String email, String noktp, String nohp, String pekerjaan, String alamat_kerja, String notelp_kerja, String foto_wajah, String foto_ktp) {
        try {
            String sql = "INSERT INTO penyewa VALUES(NULL,'" + nama + "','" + alamat + "','" + tempat_lahir + "','" + tgl_lahir + "','" + jk + "','" + noktp + "','" + email + "','" + nohp + "','" + pekerjaan + "','" + alamat_kerja + "','" + notelp_kerja + "','" + foto_wajah + "','" + foto_ktp + "');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nama, alamat, tempat_lahir, tgl_lahir, jk, email, noktp, nohp, pekerjaan, alamat_kerja, notelp_kerja, foto_wajah, foto_ktp});
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataPenghuni();
    }

    public boolean UbahDataPenghuni(String id_penyewa, String nama, String alamat, String tempat_lahir, String tgl_lahir, String jk, String email, String noktp, String nohp, String pekerjaan, String alamat_kerja, String notelp_kerja, String foto_wajah, String foto_ktp) {
        try {
            String sql = "UPDATE penyewa set nama = '" + nama + "', alamat = '" + alamat + "', tempat_lahir = '" + tempat_lahir + "', tgl_lahir = '" + tgl_lahir + "', jk = '" + jk + "', email = '" + email + "', noktp = '" + nohp + "', pekerjaan= '" + pekerjaan + "', alamat_kerja = '" + alamat_kerja + "', notelp_kerja = '" + notelp_kerja + "', foto_wajah= '" + foto_wajah + "', foto_ktp= '" + foto_ktp + "' WHERE id_penyewa = " + id_penyewa + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void TampilDataPenghuni() {
        try {
            String sql = "Select *from penyewa;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while (rss.next()) {
                Object[] o = new Object[8];
                o[0] = rss.getString("id_penyewa");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("alamat");
                o[3] = rss.getString("jk");
                o[4] = rss.getString("nohp");
                o[5] = rss.getString("email");
                o[6] = rss.getString("foto_wajah");
                o[7] = rss.getString("foto_ktp");
                model.addRow(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void bersihkanfield() {
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
        tfPasKTP.setText("");
        tfNomorKTP.setText("");
        tfCariPenghuni.setText("");
    }

    private void KunciField(Boolean x) {
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
        tfPasKTP.setEnabled(x);
        tfNomorKTP.setEnabled(x);

    }

    public boolean HapusData(String id) {
        try {
            String sql = "DELETE FROM penyewa WHERE id_penyewa = " + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void cekstatus() {
        if (tfNamaPenghuni.getText().length() == 0
                || tfAlamatPenghuni.getText().length() == 0
                || tfTempatLahirPenghuni.getText().length() == 0
                || dcTanggalLahirPenghuni.getDate() == null
                || cbGender.getSelectedItem().equals("Pilih Gender")
                || tfEmail.getText().length() == 0
                || tfNomorKTP.getText().length() == 0
                || tfNomorTelp.getText().length() == 0
                || tfPekerjaan.getText().length() == 0
                || tfAlamatPekerjaan.getText().length() == 0
                || tfNomorTelpPekerjaan.getText().length() == 0
                || tfPasFoto.getText().length() == 0
                || tfNomorKTP.getText().length() == 0) {
            btnSimpan.setEnabled(false);
        } else {
            btnSimpan.setEnabled(true);
        }
    }

    private void PencarianData(String by, String cari) {
        try {
            String sql = "Select *from penyewa where " + by + " Like '%" + cari + "%';";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while (rss.next()) {
                Object[] data = new Object[6];
                data[0] = rss.getString("id_penyewa");
                data[1] = rss.getString("nama");
                data[2] = rss.getString("alamat");
                data[3] = rss.getString("jk");
                data[4] = rss.getString("nohp");
                data[5] = rss.getString("email");
                model.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Creates new form frm_Penyewa
     */
    public frm_Penghuni() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //untuk maximize frame
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
        jPanel1 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        tfNamaPenghuni = new javax.swing.JTextField();
        tfAlamatPenghuni = new javax.swing.JTextField();
        tfTempatLahirPenghuni = new javax.swing.JTextField();
        dcTanggalLahirPenghuni = new com.toedter.calendar.JDateChooser();
        cbGender = new javax.swing.JComboBox<>();
        tfEmail = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        cbCariPenghuni = new javax.swing.JComboBox<>();
        btnCariPenghuni = new javax.swing.JButton();
        tfCariPenghuni = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tfNomorKTP = new javax.swing.JTextField();
        tfNomorTelp = new javax.swing.JTextField();
        tfPekerjaan = new javax.swing.JTextField();
        tfAlamatPekerjaan = new javax.swing.JTextField();
        tfNomorTelpPekerjaan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnCariFoto = new javax.swing.JButton();
        tfPasFoto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCariKTP = new javax.swing.JButton();
        tfPasKTP = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPenghuni = new javax.swing.JTable();
        pnlFoto = new usu.widget.Panel();
        pnlKTP = new usu.widget.Panel();
        jPanel7 = new javax.swing.JPanel();
        btnDetail = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1500, 670));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        panel1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormPenghuni.png"))); // NOI18N
        panel1.setMinimumSize(new java.awt.Dimension(1340, 750));
        panel1.setPreferredSize(new java.awt.Dimension(1340, 750));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        panel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 327, 370, 40));

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

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Gender", "Pria", "Wanita", " " }));
        jPanel5.add(cbGender);

        tfEmail.setText("jTextField5");
        tfEmail.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfEmailCaretUpdate(evt);
            }
        });
        jPanel5.add(tfEmail);

        panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 127, 190, 180));

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
        panel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, 110, 30));

        cbCariPenghuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Berdasarkan", "nama", "alamat", "gender" }));
        panel1.add(cbCariPenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 465, 190, 30));

        btnCariPenghuni.setText("Cari");
        btnCariPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPenghuniActionPerformed(evt);
            }
        });
        panel1.add(btnCariPenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 465, 80, 30));

        tfCariPenghuni.setText("jTextField13");
        panel1.add(tfCariPenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 465, 230, 30));

        jPanel4.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        tfNomorKTP.setText("jTextField12");
        tfNomorKTP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorKTPCaretUpdate(evt);
            }
        });
        jPanel4.add(tfNomorKTP);

        tfNomorTelp.setText("jTextField7");
        tfNomorTelp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorTelpCaretUpdate(evt);
            }
        });
        jPanel4.add(tfNomorTelp);

        tfPekerjaan.setText("jTextField8");
        tfPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPekerjaanCaretUpdate(evt);
            }
        });
        jPanel4.add(tfPekerjaan);

        tfAlamatPekerjaan.setText("jTextField9");
        tfAlamatPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfAlamatPekerjaanCaretUpdate(evt);
            }
        });
        jPanel4.add(tfAlamatPekerjaan);

        tfNomorTelpPekerjaan.setText("jTextField10");
        tfNomorTelpPekerjaan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomorTelpPekerjaanCaretUpdate(evt);
            }
        });
        jPanel4.add(tfNomorTelpPekerjaan);

        panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 127, 190, 150));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        btnCariFoto.setText("Cari");
        btnCariFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariFotoActionPerformed(evt);
            }
        });
        jPanel2.add(btnCariFoto);

        tfPasFoto.setText("jTextField1");
        jPanel2.add(tfPasFoto);

        panel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 250, 130, 30));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        btnCariKTP.setText("Cari");
        btnCariKTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKTPActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariKTP);

        tfPasKTP.setText("jTextField2");
        jPanel3.add(tfPasKTP);

        panel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1167, 250, 130, 30));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        tblPenghuni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMA", "ALAMAT", "GENDER", "NO. TELP", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenghuniMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPenghuni);

        jPanel6.add(jScrollPane2);

        panel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 507, 1200, 200));

        org.jdesktop.layout.GroupLayout pnlFotoLayout = new org.jdesktop.layout.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 70, Short.MAX_VALUE)
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 90, Short.MAX_VALUE)
        );

        panel1.add(pnlFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 154, 70, 90));

        org.jdesktop.layout.GroupLayout pnlKTPLayout = new org.jdesktop.layout.GroupLayout(pnlKTP);
        pnlKTP.setLayout(pnlKTPLayout);
        pnlKTPLayout.setHorizontalGroup(
            pnlKTPLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 150, Short.MAX_VALUE)
        );
        pnlKTPLayout.setVerticalGroup(
            pnlKTPLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 90, Short.MAX_VALUE)
        );

        panel1.add(pnlKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1167, 154, 150, 90));

        jPanel7.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        btnDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDetail.setText("Lihat Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jPanel7.add(btnDetail);

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel7.add(btnHapus);

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel7.add(btnEdit);

        panel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 506, 120, 200));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(panel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1365, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(panel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 766, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPenghuniActionPerformed
        // TODO add your handling code here:
        if (cbCariPenghuni.getSelectedItem().equals("Berdasarkan")) {//jika pada combobox yang terseleksi masih"cari bardasarkan, maka jalankan perintah berikut
            JOptionPane.showMessageDialog(null, "Pilih Filter Pencarian", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            //sintak diatas untuk menampilkan pesan dialog beruppa kofirmasi
        } else {

            InitTable();
            if (tfCariPenghuni.getText().length() == 0) {
                TampilDataPenghuni();
            } else {

                PencarianData(cbCariPenghuni.getSelectedItem().toString(), tfCariPenghuni.getText());

            }
        }
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tgl_lahir = dateFormat.format(dcTanggalLahirPenghuni.getDate());
        String gender = cbGender.getSelectedItem().toString();
        String email = tfEmail.getText();
        String no_ktp = tfNomorKTP.getText();
        String no_telp = tfNomorTelp.getText();
        String pekerjaan = tfPekerjaan.getText();
        String alamat_pekerjaan = tfAlamatPekerjaan.getText();
        String nt_pekerjaan = tfNomorTelpPekerjaan.getText();
        String pasfoto = tfPasFoto.getText();
        String foto_ktp = tfPasKTP.getText();
        

        TambahDataPenghuni(nama, alamat, t4_lahir, tgl_lahir, gender, email, no_ktp, no_telp, pekerjaan, alamat_pekerjaan, nt_pekerjaan, pasfoto, foto_ktp);
        
        try {
            String path=new File(".").getCanonicalPath();
            FileUtils.copyFileToDirectory(file, new File(path+"/image"));
            FileUtils.copyFileToDirectory(file1, new File(path+"/image"));
        } catch (IOException ex) {
            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String tgl_lahir = dateFormat.format(dcTanggalLahirPenghuni.getDate());
        String gender = cbGender.getSelectedItem().toString();
        String email = tfEmail.getText();
        String no_ktp = tfNomorKTP.getText();
        String no_telp = tfNomorTelp.getText();
        String pekerjaan = tfPekerjaan.getText();
        String alamat_pekerjaan = tfAlamatPekerjaan.getText();
        String nt_pekerjaan = tfNomorTelpPekerjaan.getText();
        String pasfoto = tfPasFoto.getText();
        String foto_ktp = tfNomorKTP.getText();
        if (UbahDataPenghuni(id, nama, alamat, t4_lahir, tgl_lahir, gender, email, no_ktp, no_telp, pekerjaan, alamat_pekerjaan, nt_pekerjaan, pasfoto, foto_ktp)) {
            JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
            InitTable();
            TampilDataPenghuni();
            bersihkanfield();
            KunciField(false);
            btnSimpan.setEnabled(false);
        } else {
            JOptionPane.showConfirmDialog(null, "Gagal Ubah Data");

        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void tblPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenghuniMouseClicked
        // TODO add your handling code here:
        int baris = tblPenghuni.getSelectedRow();
        String id_penyewa = tblPenghuni.getValueAt(baris, 0).toString();

        try {
            String sql = "Select *from penyewa where id_penyewa = " + id_penyewa + ";";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while (rss.next()) {
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
                tfAlamatPenghuni.setText(o[2].toString());
                tfTempatLahirPenghuni.setText(o[3].toString());

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dcTanggalLahirPenghuni.setDate(dateFormat.parse(o[4].toString()));
                cbGender.setSelectedItem(o[5]);
                tfEmail.setText(o[6].toString());
                tfNomorKTP.setText(o[7].toString());
                tfNomorTelp.setText(o[8].toString());
                tfPekerjaan.setText(o[9].toString());
                tfAlamatPekerjaan.setText(o[10].toString());
                tfNomorTelpPekerjaan.setText(o[11].toString());
                tfPasFoto.setText(o[12].toString());
                tfNomorKTP.setText(o[13].toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblPenghuniMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int baris = tblPenghuni.getSelectedRow();
        String id = tblPenghuni.getValueAt(baris, 0).toString();

        if (HapusData(id)) {
            JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
        } else {
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

    private void tfNomorKTPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomorKTPCaretUpdate
        cekstatus();
    }//GEN-LAST:event_tfNomorKTPCaretUpdate

    private void btnCariFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariFotoActionPerformed
        // TODO add your handling code here:
//        JFileChooser fc = new JFileChooser(); 
//        int returnVal = fc.showOpenDialog(frm_Penghuni.this); 
//        if (returnVal == fc.APPROVE_OPTION ) { 
//            File file = fc.getSelectedFile(); //hanya nama file saja 
//            tfPasFoto.setText(file.getPath()); 
//            // ImageIcon pic = new ImageIcon("C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\Sample Pictures\\Winter.jpg"); 
//            // jButton2.setIcon(new javax.swing.ImageIcon("E:\\photo.GIF")); 
//            //ImageIcon pic = new ImageIcon(tfPasFoto.getText());
//            ImageIcon pic = new ImageIcon(file.getAbsolutePath());
//            
//            // Get width and height of picLabel
//            Rectangle rect = pnlFoto.getBounds();
//            // Scaling the Image to fit in the picLabel
//            Image scaledimage = pic.getImage().getScaledInstance(rect.width,rect.height,Image.SCALE_DEFAULT);
//            // Converting the image back to ImageIcon to make it acceptable by picLabel
//            pic = new ImageIcon(scaledimage);
//            pnlFoto.setBackgroundImage(pic); 
//        }
            jfc1=new JFileChooser();
        if(jfc1.showOpenDialog(pnlFoto)==JFileChooser.APPROVE_OPTION){
            
            Rectangle rect = pnlFoto.getBounds();
            
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image1=toolkit.getImage(jfc1.getSelectedFile().getAbsolutePath());
            Image imagedResized=image1.getScaledInstance(rect.width,rect.height,Image.SCALE_DEFAULT);
            ImageIcon imageIcon1=new ImageIcon(imagedResized);
            
            pnlFoto.setBackgroundImage(imageIcon1);
            tfPasFoto.setText(jfc1.getSelectedFile().getName());
            
            file1=new File(jfc1.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_btnCariFotoActionPerformed

    private void btnCariKTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKTPActionPerformed
        // TODO add your handling code here:
//        JFileChooser fc1 = new JFileChooser();
//        int returnVal = fc1.showOpenDialog(frm_Penghuni.this); 
//        if (returnVal == fc1.APPROVE_OPTION ) { 
//            File file = fc1.getSelectedFile(); //hanya nama file saja 
//            tfPasKTP.setText(file.getPath()); 
//            // ImageIcon pic = new ImageIcon("C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\Sample Pictures\\Winter.jpg"); 
//            // jButton2.setIcon(new javax.swing.ImageIcon("E:\\photo.GIF")); 
//            //ImageIcon pic = new ImageIcon(tfPasFoto.getText());
//            ImageIcon pic1 = new ImageIcon(file.getAbsolutePath());
//
//            // Get width and height of picLabel
//            Rectangle rect1 = pnlKTP.getBounds();
//            // Scaling the Image to fit in the picLabel
//            Image scaledimage = pic1.getImage().getScaledInstance(rect1.width,rect1.height,Image.SCALE_DEFAULT);
//            // Converting the image back to ImageIcon to make it acceptable by picLabel
//            pic1 = new ImageIcon(scaledimage);
//            pnlKTP.setBackgroundImage(pic1);
//        }
        jfc=new JFileChooser();
        if(jfc.showOpenDialog(pnlKTP)==JFileChooser.APPROVE_OPTION){
            Rectangle rect1 = pnlKTP.getBounds();
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized=image.getScaledInstance(rect1.width,rect1.height,Image.SCALE_DEFAULT);
            ImageIcon imageIcon=new ImageIcon(imagedResized);
            
            pnlKTP.setBackgroundImage(imageIcon);
            tfPasKTP.setText(jfc.getSelectedFile().getName());
            
            file=new File(jfc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_btnCariKTPActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        if(tblPenghuni.getSelectedRowCount()==1){
        int baris = tblPenghuni.getSelectedRow();
        String id_penyewa = tblPenghuni.getValueAt(baris, 0).toString();
        try {
            String sql = "Select *from penyewa where id_penyewa = " + id_penyewa + ";";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while (rss.next()) {
                Object[] o = new Object[14];
                o[0] = rss.getString("id_penyewa");
                o[1] = rss.getString("nama");
                o[2] = rss.getString("alamat");
                o[3] = rss.getString("tempat_lahir");
                o[4] = rss.getDate("tgl_lahir");
                o[5] = rss.getString("jk");
                o[6] = rss.getString("email");
                o[7] = rss.getString("noktp");
                o[8] = rss.getString("nohp");
                o[9] = rss.getString("pekerjaan");
                o[10] = rss.getString("alamat_kerja");
                o[11] = rss.getString("notelp_kerja");
                o[12] = rss.getString("foto_wajah");
                o[13] = rss.getString("foto_ktp");             
                
                frm_detail_penghuni detail = new frm_detail_penghuni();
                String nama = o[1].toString();
                String alamat = o[2].toString();
                String tmptLahir = o[3].toString();
                String tglLahir = o[4].toString();
                String Gender = o[5].toString();
                String email = o[6].toString();
                String noKTP = o[7].toString();
                String noTelp = o[8].toString();
                String pekerjaan = o[9].toString();
                String almtPekerjaan = o[10].toString();
                String telpPek = o[11].toString();
//                String tglMasuk = o[12].toString();
                String fotowajah = o[12].toString();
                String fotoktp = o[13].toString();
//                frm_detail_penghuni.pnlDetPasFoto.setText(this.o[1].toString());
//                frm_detail_penghuni.pnlDetPasKTP.setText(this.o[1].toString());
                
                frm_detail_penghuni.tfDetNama.setText(nama);
                frm_detail_penghuni.tfDetAlamat.setText(alamat);
                frm_detail_penghuni.tfDetTmptLahir.setText(tmptLahir);
                frm_detail_penghuni.tfDetTglLahir.setText(tglLahir);
                frm_detail_penghuni.tfDetGender.setText(Gender);
                frm_detail_penghuni.tfDetEmail.setText(email);
                frm_detail_penghuni.tfDetNomorKTP.setText(noKTP);
                frm_detail_penghuni.tfDetNomorTelp.setText(noTelp);
                frm_detail_penghuni.tfDetPekerjaan.setText(pekerjaan);
                frm_detail_penghuni.tfDetAlamatPekerjaan.setText(almtPekerjaan);
                frm_detail_penghuni.tfDetTelpPekerjaan.setText(telpPek);
                
//                frm_detail_penghuni.tfDetTglMasuk.setText(tglMasuk);
//                frm_detail_penghuni.pnlDetPasFoto.setText(this.o[1].toString());
//                frm_detail_penghuni.pnlDetPasKTP.setText(this.o[1].toString());

                try {
//                    int row=jTableBook.getSelectedRow();
//                    Book book=bookTableModel.findOne(row);
//                    
                    Toolkit toolkit=Toolkit.getDefaultToolkit();
                    
                    String path=new File(".").getCanonicalPath();
                    
                    Rectangle rect2 = frm_detail_penghuni.pnlDetPasFoto.getBounds();
                    Image image=toolkit.getImage(path+"/image/"+fotowajah);
                    Image imagedResized=image.getScaledInstance(rect2.width,rect2.height,Image.SCALE_DEFAULT);
                    ImageIcon icon=new ImageIcon(imagedResized);
                    frm_detail_penghuni.pnlDetPasFoto.setBackgroundImage(icon);
                    
                    Rectangle rect3 = frm_detail_penghuni.pnlDetPasKTP.getBounds();
                    Image image1=toolkit.getImage(path+"/image/"+fotoktp);
                    Image imagedResized1=image1.getScaledInstance(rect3.width,rect3.height,Image.SCALE_DEFAULT);
                    ImageIcon icon1=new ImageIcon(imagedResized1);
                    frm_detail_penghuni.pnlDetPasKTP.setBackgroundImage(icon1);
                    
                } catch (IOException ex) {
                    Logger.getLogger(frm_detail_penghuni.class.getName()).log(Level.SEVERE, null, ex);
                }
                  detail.setVisible(true);
                
//                tfNamaPenghuni.setText(o[1].toString());
////                tfAlamatPenghuni.setText(tblPenghuni.getValueAt(baris, 2).toString());
////                tfTempatLahirPenghuni.setText(tblPenghuni.getValueAt(baris, 3).toString());
////                dcTanggalLahirPenghuni.setDateFormatString(tblPenghuni.getValueAt(baris, 4).toString());
////                cbGender.setSelectedItem(tblPenghuni.getValueAt(baris, 5).toString());
////                tfEmail.setText(tblPenghuni.getValueAt(baris, 6).toString());
////                tfNomorKTP.setText(tblPenghuni.getValueAt(baris, 7).toString());
////                tfNomorTelp.setText(tblPenghuni.getValueAt(baris, 8).toString());
////                tfPekerjaan.setText(tblPenghuni.getValueAt(baris, 9).toString());
////                tfAlamatPekerjaan.setText(tblPenghuni.getValueAt(baris, 10).toString());
////                tfNomorTelpPekerjaan.setText(tblPenghuni.getValueAt(baris, 11).toString());
////                tfPasFoto.setText(tblPenghuni.getValueAt(baris, 12).toString());
////                tfFotoKTP.setText(tblPenghuni.getValueAt(baris, 13).toString());
//                tfAlamatPenghuni.setText(o[2].toString());
//                tfTempatLahirPenghuni.setText(o[3].toString());
//
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                dcTanggalLahirPenghuni.setDate(dateFormat.parse(o[4].toString()));
//                cbGender.setSelectedItem(o[5]);
//                tfEmail.setText(o[6].toString());
//                tfNomorKTP.setText(o[7].toString());
//                tfNomorTelp.setText(o[8].toString());
//                tfPekerjaan.setText(o[9].toString());
//                tfAlamatPekerjaan.setText(o[10].toString());
//                tfNomorTelpPekerjaan.setText(o[11].toString());
//                tfPasFoto.setText(o[12].toString());
//                tfNomorKTP.setText(o[13].toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
//        catch (ParseException ex) {
//            Logger.getLogger(frm_Penghuni.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

        }
        else{
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Pengguna Terlebih Dahulu!..");
        }
    }//GEN-LAST:event_btnDetailActionPerformed

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
    private javax.swing.JButton btnCariFoto;
    private javax.swing.JButton btnCariKTP;
    private javax.swing.JButton btnCariPenghuni;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbCariPenghuni;
    private javax.swing.JComboBox<String> cbGender;
    private com.toedter.calendar.JDateChooser dcTanggalLahirPenghuni;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.Panel panel1;
    private usu.widget.Panel pnlFoto;
    private usu.widget.Panel pnlKTP;
    private javax.swing.JTable tblPenghuni;
    private javax.swing.JTextField tfAlamatPekerjaan;
    private javax.swing.JTextField tfAlamatPenghuni;
    private javax.swing.JTextField tfCariPenghuni;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNamaPenghuni;
    private javax.swing.JTextField tfNomorKTP;
    private javax.swing.JTextField tfNomorTelp;
    private javax.swing.JTextField tfNomorTelpPekerjaan;
    private javax.swing.JTextField tfPasFoto;
    private javax.swing.JTextField tfPasKTP;
    private javax.swing.JTextField tfPekerjaan;
    private javax.swing.JTextField tfTempatLahirPenghuni;
    // End of variables declaration//GEN-END:variables

}
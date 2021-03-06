/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class frm_Kamar extends javax.swing.JFrame {
private DefaultTableModel model;
private Connection con = koneksi.getConnection();
private Statement stt;
private ResultSet rss;
    
public void InitTable(){
    model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NAMA KAMAR");
        model.addColumn("HARGA SEWA");
        model.addColumn("STATUS KAMAR");
        tblKamar.setModel(model);
}

private void TampilDataKamar(){
        try{
            String sql = "Select *from kamar order by id_kamar asc;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getString("id_kamar");
                o[1] = rss.getString("nama_kamar");
                o[2] = rss.getString("harga_sewa");
                o[3] = rss.getString("status");
                

                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

private void TambahDataKamar(String nama_kamar,int harga_sewa, String status) {
        try{
            String sql = "INSERT INTO kamar VALUES(NULL,'"+nama_kamar+"',"+harga_sewa+",'"+status+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nama_kamar, harga_sewa,status});
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataKamar();
    }

public boolean UbahDataKamar(String id_kamar,String nama_kamar,int harga_sewa ){
        try{
            String sql = "UPDATE kamar set nama_kamar = '"+nama_kamar+"', harga_sewa = '"+harga_sewa+"'WHERE id_kamar = "+id_kamar+";";
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
        tfNomorKamar.setText("");
        tfNamaKamar.setText("");
        tfHargaKamar.setText("");
        cbTipeKamar.setSelectedItem("Pilih Tipe Kamar");
        tfCariKamar.setText("");
    }

private void KunciField(Boolean x){
        tfNomorKamar.setEnabled(x);
        tfNamaKamar.setEnabled(x);
        tfHargaKamar.setEnabled(x);
        cbTipeKamar.setEnabled(x);
        
        
//        btnSimpanKamar.setEnabled(x);
    }

public boolean HapusData(String id_kamar){
        try{
            String sql = "DELETE FROM kamar WHERE id_kamar = "+id_kamar+";";
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
            String sql = "Select *from kamar where "+by+" Like '%"+cari+"%';";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] data = new Object[4];
                data[0] = rss.getString("id_kamar");
                data[1] = rss.getString("nama_kamar");
                data[2] = rss.getString("harga_sewa");
                data[3] = rss.getString("status");
               
                model.addRow(data);
            }
        }
            catch(Exception e){
                    System.out.println(e.getMessage());
                    }
}
public void cekstatus(){
    
         if(tfNamaKamar.getText().length()==0 ||
            tfHargaKamar.getText().length()==0 ||
            tfStatusKamar.getText().length()==0){
            btnSimpanKamar.setEnabled(false);
        }
        else{
            btnSimpanKamar.setEnabled(true);
        }
     }        
public boolean validasikode(int id_kamar){
    try{
            String sql = "Select *from kamar where id_kamar = '"+id_kamar+"';";
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

public boolean validasiNamaKamar(String nama_kamar){
    try{
            String sql = "Select *from kamar where nama_kamar = '"+nama_kamar+"';";
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
    
    public frm_Kamar() {
        initComponents();
        tfStatusKamar.setEnabled(false);
        tfStatusKamar.setText("Kosong");
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
        jPanel1 = new javax.swing.JPanel();
        btnSimpanKamar = new javax.swing.JButton();
        btnTambahKamar = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKamar = new javax.swing.JTable();
        btnKeluarKamar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfNomorKamar = new javax.swing.JTextField();
        cbTipeKamar = new javax.swing.JComboBox<>();
        tfNamaKamar = new javax.swing.JTextField();
        tfHargaKamar = new javax.swing.JTextField();
        tfStatusKamar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cbFilterKamar = new javax.swing.JComboBox();
        btnCariKamar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tfCariKamar = new javax.swing.JTextField();
        btnHapusKamar = new javax.swing.JButton();
        btnEditKamar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGlass1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormKamar.png"))); // NOI18N
        panelGlass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelGlass1MouseClicked(evt);
            }
        });
        panelGlass1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        btnSimpanKamar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpanKamar.setText("SIMPAN");
        btnSimpanKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanKamarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanKamar);

        btnTambahKamar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTambahKamar.setText("TAMBAH");
        btnTambahKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKamarActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambahKamar);

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset.setText("BERSIHKAN");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset);

        panelGlass1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 360, 40));

        tblKamar.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKamar);

        panelGlass1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 590, 330));

        btnKeluarKamar.setBackground(new java.awt.Color(255, 204, 0));
        btnKeluarKamar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnKeluarKamar.setText("Tutup");
        btnKeluarKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKeluarKamarMouseClicked(evt);
            }
        });
        btnKeluarKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarKamarActionPerformed(evt);
            }
        });
        panelGlass1.add(btnKeluarKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 15, 70, 30));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        tfNomorKamar.setEditable(false);
        tfNomorKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomorKamarKeyTyped(evt);
            }
        });
        jPanel2.add(tfNomorKamar);

        cbTipeKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Tipe Kamar", "Kelas A", "Kelas B", "Kelas C" }));
        cbTipeKamar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipeKamarItemStateChanged(evt);
            }
        });
        jPanel2.add(cbTipeKamar);

        tfNamaKamar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaKamarCaretUpdate(evt);
            }
        });
        jPanel2.add(tfNamaKamar);

        tfHargaKamar.setEditable(false);
        tfHargaKamar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfHargaKamarCaretUpdate(evt);
            }
        });
        tfHargaKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaKamarActionPerformed(evt);
            }
        });
        jPanel2.add(tfHargaKamar);

        tfStatusKamar.setEditable(false);
        tfStatusKamar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfStatusKamarCaretUpdate(evt);
            }
        });
        jPanel2.add(tfStatusKamar);

        panelGlass1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 135, 190, 140));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setToolTipText("");
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        cbFilterKamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdasarkan", "Harga", "Status" }));
        jPanel3.add(cbFilterKamar);

        btnCariKamar.setText("CARI");
        btnCariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKamarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariKamar);

        panelGlass1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 135, 280, 30));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));
        jPanel4.add(tfCariKamar);

        panelGlass1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 135, 230, 30));

        btnHapusKamar.setBackground(new java.awt.Color(255, 255, 255));
        btnHapusKamar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusKamar.setText("HAPUS");
        btnHapusKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKamarActionPerformed(evt);
            }
        });
        panelGlass1.add(btnHapusKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1073, 230, 87, 40));

        btnEditKamar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditKamar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditKamar.setText("EDIT");
        btnEditKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditKamarActionPerformed(evt);
            }
        });
        panelGlass1.add(btnEditKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1073, 180, 87, 40));

        getContentPane().add(panelGlass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeluarKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKeluarKamarActionPerformed

    private void btnKeluarKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarKamarMouseClicked
//         TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Menutup? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (pilihan==0) {
            dispose();
        }
        
    }//GEN-LAST:event_btnKeluarKamarMouseClicked

    private void btnHapusKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKamarActionPerformed
        // TODO add your handling code here:
        if((tblKamar.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin dihapus pada Tabel..");            
        }
        else{ 
            int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda ingin menghapus? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                int baris = tblKamar.getSelectedRow();
                String id_kamar = tblKamar.getValueAt(baris, 0).toString();

                if(HapusData(id_kamar)){
                    JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");

                }
                InitTable();
                TampilDataKamar();
                bersihkanfield();
                KunciField(false);
            }
        }
    }//GEN-LAST:event_btnHapusKamarActionPerformed

    private void tblKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKamarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblKamarMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataKamar();
        bersihkanfield();
        btnSimpanKamar.setEnabled(false);
        KunciField(false);
    }//GEN-LAST:event_formComponentShown

    private void tfNomorKamarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomorKamarKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_tfNomorKamarKeyTyped

    private void btnCariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKamarActionPerformed
        // TODO add your handling code here:
        if(cbFilterKamar.getSelectedItem().equals("Berdasarkan")){//jika pada combobox yang terseleksi masih"cari bardasarkan, maka jalankan perintah berikut
             JOptionPane.showMessageDialog(null, "Pilih Filter Pencarian","Konfirmasi",JOptionPane.INFORMATION_MESSAGE);
             //sintak diatas untuk menampilkan pesan dialog beruppa kofirmasi
        }
        else{
            
            InitTable();
            if(tfCariKamar.getText().length()==0){
                TampilDataKamar();
            }
            else{
                String b = null;
                if (cbFilterKamar.getSelectedItem()=="Harga"){
                    b="harga_sewa";
                    
                }
                else if (cbFilterKamar.getSelectedItem()=="Status"){                
                    b="status";
                    
                }
                PencarianData(b, tfCariKamar.getText());
            }
        }
    }//GEN-LAST:event_btnCariKamarActionPerformed

    private void btnSimpanKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanKamarActionPerformed
        // TODO add your handling code here:
//        int baris = tblKamar.getSelectedRow();
//        String id = tblKamar.getValueAt(baris, 0).toString();
        
        String nama_kamar = tfNamaKamar.getText();
        String harga = tfHargaKamar.getText();
        String id = tfNomorKamar.getText();
        
        int harga_sewa = Integer.parseInt(harga);
        int id_kam = Integer.parseInt(id);
        
        if(validasikode(id_kam)){
            if(UbahDataKamar(id, nama_kamar, harga_sewa)){
                JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
                InitTable();
                TampilDataKamar();
                bersihkanfield();
                KunciField(false);
                btnSimpanKamar.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Gagal Ubah Data");

            }
        }
        else{   
            if(validasiNamaKamar(nama_kamar)){
                JOptionPane.showMessageDialog(null, "Data Kamar Sudah Terdaftar...\nNama Tidak Boleh Sama!!");
            }
            else{
                String status_kamar = "Kosong";
                TambahDataKamar(nama_kamar, harga_sewa, status_kamar);
            }
            
        }
        bersihkanfield();
        KunciField(false);
    }//GEN-LAST:event_btnSimpanKamarActionPerformed

    private void btnEditKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditKamarActionPerformed
        // TODO add your handling code here:
         if((tblKamar.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin diedit pada Tabel..");            
        }
        else{            
            int baris=tblKamar.getSelectedRow();
            String id_kamar = tblKamar.getValueAt(baris, 0).toString();

            try{
                String sql = "Select *from kamar where id_kamar = "+id_kamar+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);
                while(rss.next()){
                    Object[] o = new Object[4];
                    o[0] = rss.getString("id_kamar");
                    o[1] = rss.getString("nama_kamar");
                    o[2] = rss.getString("harga_sewa");
                    o[3] = rss.getString("status");

                    tfNomorKamar.setText(o[0].toString());
                    tfNamaKamar.setText(o[1].toString());
                    tfHargaKamar.setText(o[2].toString());
                    tfStatusKamar.setText(o[3].toString());
                    KunciField(true);
    //                btnSimpanKamar.setEnabled(false);
                }
                 }catch(SQLException e){
                System.out.println(e.getMessage());
            } 
        }
    }//GEN-LAST:event_btnEditKamarActionPerformed

    private void btnTambahKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKamarActionPerformed
        // TODO add your handling code here:
        tblKamar.clearSelection();
        KunciField(true);
        try{
            String sql = "Select max(id_kamar) as id_kamar from logidkamar;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[1];
                o[0] = rss.getString("id_kamar");
                
                int id = Integer.parseInt(o[0].toString())+1;
                String id_fasilitas = String.valueOf(id);
                tfNomorKamar.setText(id_fasilitas);

                
            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTambahKamarActionPerformed

    private void tfNamaKamarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaKamarCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNamaKamarCaretUpdate

    private void tfHargaKamarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfHargaKamarCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfHargaKamarCaretUpdate

    private void tfStatusKamarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfStatusKamarCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfStatusKamarCaretUpdate

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        bersihkanfield();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tfHargaKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaKamarActionPerformed

    private void cbTipeKamarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipeKamarItemStateChanged
        // TODO add your handling code here:
        if(cbTipeKamar.getSelectedItem()=="Kelas A"){
            tfHargaKamar.setText("900000");
        }
        else if(cbTipeKamar.getSelectedItem()=="Kelas B"){
            tfHargaKamar.setText("750000");
        }
        else if(cbTipeKamar.getSelectedItem()=="Kelas C"){
            tfHargaKamar.setText("450000");
        }
        else{
            tfHargaKamar.setText("");
        }
    }//GEN-LAST:event_cbTipeKamarItemStateChanged

    private void panelGlass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGlass1MouseClicked
        // TODO add your handling code here:
        tblKamar.clearSelection();
    }//GEN-LAST:event_panelGlass1MouseClicked

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
            java.util.logging.Logger.getLogger(frm_Kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Kamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Kamar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariKamar;
    private javax.swing.JButton btnEditKamar;
    private javax.swing.JButton btnHapusKamar;
    private javax.swing.JButton btnKeluarKamar;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpanKamar;
    private javax.swing.JButton btnTambahKamar;
    private javax.swing.JComboBox cbFilterKamar;
    private javax.swing.JComboBox<String> cbTipeKamar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.glass.PanelGlass panelGlass1;
    private javax.swing.JTable tblKamar;
    private javax.swing.JTextField tfCariKamar;
    private javax.swing.JTextField tfHargaKamar;
    private javax.swing.JTextField tfNamaKamar;
    private javax.swing.JTextField tfNomorKamar;
    private javax.swing.JTextField tfStatusKamar;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Class.koneksi;
import java.awt.Color;
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
public class frm_Fasilitas extends javax.swing.JFrame {
private DefaultTableModel model;
private Connection con = koneksi.getConnection();
private Statement stt;
private ResultSet rss;
    /**
     * Creates new form frm_Fasilitas
     */
public void InitTable(){
    model = new DefaultTableModel();
        model.addColumn("NO.");
        model.addColumn("NAMA FASILITAS");
        model.addColumn("HARGA FASILITAS");
//        model.addColumn("STATUS FASILITAS");
        tblFasilitas.setModel(model);
}

private void TampilDataFasilitas(){
        try{
            String sql = "Select *from fasilitas order by id_fasilitas;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[3];
                o[0] = rss.getString("id_fasilitas");
                o[1] = rss.getString("nama_fasilitas");
                o[2] = rss.getString("harga_fasilitas");

                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

private void TambahDataFasilitas(String nama_fasilitas,String harga_fasilitas) {
        try{
            String sql = "INSERT INTO fasilitas VALUES(NULL,'"+nama_fasilitas+"','"+harga_fasilitas+"');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nama_fasilitas,harga_fasilitas});
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        InitTable();
        TampilDataFasilitas();
    }

public boolean UbahDataFasilitas(String id_fasilitas,String nama_fasilitas,String harga_fasilitas ){
        try{
            String sql = "UPDATE fasilitas set nama_fasilitas = '"+nama_fasilitas+"', harga_fasilitas = '"+harga_fasilitas+"'WHERE id_fasilitas = "+id_fasilitas+";";
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
        tfNomorFasilitas.setText("");
        tfNamaFasilitas.setText("");
        tfHargaFasilitas.setText("");
        
        tfCariFasilitas.setText("");
    }

private void KunciField(Boolean x){
        tfNomorFasilitas.setEnabled(x);
        tfNamaFasilitas.setEnabled(x);
        tfHargaFasilitas.setEnabled(x);
        
//        btnSimpanFasilitas.setEnabled(x);
    }

public boolean HapusData(String id_fasilitas){
        try{
            String sql = "DELETE FROM fasilitas WHERE id_fasilitas = "+id_fasilitas+";";
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
            String sql = "Select *from fasilitas where "+by+" Like '%"+cari+"%';";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] data = new Object[3];
                data[0] = rss.getString("id_fasilitas");
                data[1] = rss.getString("nama_fasilitas");
                data[2] = rss.getString("harga_fasilitas");
               
                model.addRow(data);
            }
        }
            catch(Exception e){
                    System.out.println(e.getMessage());
                    }
        
    }
public void cekstatus(){
    
         if(tfNamaFasilitas.getText().length()==0 ||
            tfHargaFasilitas.getText().length()==0){
            btnSimpanFasilitas.setEnabled(false);
        }
        else{
            btnSimpanFasilitas.setEnabled(true);
        }
     } 

public boolean validasikode(int id_fasilitas){
    try{
            String sql = "Select *from fasilitas where id_fasilitas = '"+id_fasilitas+"';";
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

public boolean validasiNama(String nama_fasilitas){
    try{
            String sql = "Select *from fasilitas where nama_fasilitas = '"+nama_fasilitas+"';";
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
    public frm_Fasilitas() {
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
        jPanel1 = new javax.swing.JPanel();
        btnSimpanFasilitas = new javax.swing.JButton();
        btnTambahFasilitas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFasilitas = new javax.swing.JTable();
        btnTutupFasilitas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfNomorFasilitas = new javax.swing.JTextField();
        tfNamaFasilitas = new javax.swing.JTextField();
        tfHargaFasilitas = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cbFilterFasilitas = new javax.swing.JComboBox();
        btnCariFasilitas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tfCariFasilitas = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnEditFasilitas = new javax.swing.JButton();
        btnHapusFasilitas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGlass1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/bgFormFasilitas.png"))); // NOI18N
        panelGlass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelGlass1MouseClicked(evt);
            }
        });
        panelGlass1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        btnSimpanFasilitas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSimpanFasilitas.setText("SIMPAN");
        btnSimpanFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanFasilitasActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanFasilitas);

        btnTambahFasilitas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTambahFasilitas.setText("TAMBAH");
        btnTambahFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahFasilitasActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambahFasilitas);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("BERSIHKAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        panelGlass1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 370, 40));

        tblFasilitas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFasilitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFasilitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFasilitas);

        panelGlass1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 620, 360));

        btnTutupFasilitas.setBackground(new java.awt.Color(240, 217, 39));
        btnTutupFasilitas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTutupFasilitas.setText("Tutup");
        btnTutupFasilitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTutupFasilitasMouseClicked(evt);
            }
        });
        btnTutupFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupFasilitasActionPerformed(evt);
            }
        });
        panelGlass1.add(btnTutupFasilitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 15, 90, 30));

        jPanel2.setLayout(new java.awt.GridLayout(4, 0, 0, 4));

        tfNomorFasilitas.setEditable(false);
        tfNomorFasilitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomorFasilitasKeyTyped(evt);
            }
        });
        jPanel2.add(tfNomorFasilitas);

        tfNamaFasilitas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNamaFasilitasCaretUpdate(evt);
            }
        });
        jPanel2.add(tfNamaFasilitas);

        tfHargaFasilitas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfHargaFasilitasCaretUpdate(evt);
            }
        });
        tfHargaFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaFasilitasActionPerformed(evt);
            }
        });
        tfHargaFasilitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHargaFasilitasKeyTyped(evt);
            }
        });
        jPanel2.add(tfHargaFasilitas);

        panelGlass1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 190, 120));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        cbFilterFasilitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdasarkan", "Harga", "Nama" }));
        jPanel3.add(cbFilterFasilitas);

        btnCariFasilitas.setText("CARI");
        btnCariFasilitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCariFasilitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCariFasilitasMouseExited(evt);
            }
        });
        btnCariFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariFasilitasActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariFasilitas);

        panelGlass1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 290, 30));

        jPanel4.setLayout(new java.awt.BorderLayout());

        tfCariFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariFasilitasActionPerformed(evt);
            }
        });
        jPanel4.add(tfCariFasilitas, java.awt.BorderLayout.CENTER);

        panelGlass1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 230, 30));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        btnEditFasilitas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditFasilitas.setText("EDIT");
        btnEditFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditFasilitasActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditFasilitas);

        btnHapusFasilitas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapusFasilitas.setText("HAPUS");
        btnHapusFasilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusFasilitasActionPerformed(evt);
            }
        });
        jPanel5.add(btnHapusFasilitas);

        panelGlass1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 180, 80, 80));

        getContentPane().add(panelGlass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanFasilitasActionPerformed
        // TODO add your handling code here:
        
        String id = tfNomorFasilitas.getText();
        String nama_fasilitas = tfNamaFasilitas.getText();
        String harga_fasilitas = tfHargaFasilitas.getText();
        int id_fas = Integer.parseInt(id);
        
        if(validasikode(id_fas)){
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Mengubah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                if(UbahDataFasilitas(id, nama_fasilitas, harga_fasilitas)){
                    JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
                    InitTable();
                    TampilDataFasilitas();
                    bersihkanfield();
                    KunciField(false);
                    btnSimpanFasilitas.setEnabled(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Gagal Ubah Data");

                }
            }
        }
        else{
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Menambah data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {
                if(validasiNama(nama_fasilitas)){
                    JOptionPane.showMessageDialog(null, "Nama Fasilitas Telah Terdaftar...");
                }
                else{
                    TambahDataFasilitas(nama_fasilitas,harga_fasilitas);
                    bersihkanfield();
                    KunciField(false);
                }
            }
        }
        
        
        
//        int baris = tblFasilitas.getSelectedRow();
//        String id = tblFasilitas.getValueAt(baris, 0).toString();
//        String nama_fasilitas = tfNamaFasilitas.getText();
//        String harga_fasilitas = tfHargaFasilitas.getText();
//                
        
    }//GEN-LAST:event_btnSimpanFasilitasActionPerformed

    private void tfHargaFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaFasilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaFasilitasActionPerformed

    private void btnTutupFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupFasilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTutupFasilitasActionPerformed

    private void btnTutupFasilitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTutupFasilitasMouseClicked
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(this,"Apa anda yakin ingin Menutup? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (pilihan==0) {
            dispose();
        }
    }//GEN-LAST:event_btnTutupFasilitasMouseClicked

    private void tfCariFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariFasilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariFasilitasActionPerformed

    private void btnHapusFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusFasilitasActionPerformed
        // TODO add your handling code here:
         if((tblFasilitas.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin dihapus pada Tabel..");            
        }
        else{ 
            int pilihan = JOptionPane.showConfirmDialog(this,"Apakah Anda Ingin Hapus Data? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
            if (pilihan==0) {

                int baris = tblFasilitas.getSelectedRow();
                String id_kamar = tblFasilitas.getValueAt(baris, 0).toString();

                if(HapusData(id_kamar)){
                    JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");

                }
                InitTable();
                TampilDataFasilitas();
                bersihkanfield();
                KunciField(false);
            }
         }
    }//GEN-LAST:event_btnHapusFasilitasActionPerformed

    private void tblFasilitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFasilitasMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblFasilitasMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilDataFasilitas();
        btnSimpanFasilitas.setEnabled(false);
        KunciField(false);
        bersihkanfield();
    }//GEN-LAST:event_formComponentShown

    private void tfNomorFasilitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomorFasilitasKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_tfNomorFasilitasKeyTyped

    private void btnCariFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariFasilitasActionPerformed
        // TODO add your handling code here:
        
        if(cbFilterFasilitas.getSelectedItem().equals("Berdasarkan")){//jika pada combobox yang terseleksi masih"cari bardasarkan, maka jalankan perintah berikut
             JOptionPane.showMessageDialog(null, "Pilih Filter Pencarian","Konfirmasi",JOptionPane.INFORMATION_MESSAGE);
             //sintak diatas untuk menampilkan pesan dialog beruppa kofirmasi
        }
        else{
            
            InitTable();
            if(tfCariFasilitas.getText().length()==0){
                TampilDataFasilitas();
            }
            else{
                String a = null;
                if(cbFilterFasilitas.getSelectedItem()=="Nama"){
                 a="nama_fasilitas";
            }else if (cbFilterFasilitas.getSelectedItem()=="Harga"){
                 a="harga_fasilitas";
            }
            else if (cbFilterFasilitas.getSelectedItem()=="Status"){
                 a="Status";
            }
                
                PencarianData(a, tfCariFasilitas.getText());

            }
        }
    }//GEN-LAST:event_btnCariFasilitasActionPerformed

    private void btnEditFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditFasilitasActionPerformed
        // TODO add your handling code here:    
        if((tblFasilitas.getSelectedRow()==-1)){
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin diedit pada Tabel..");            
        }
        else{ 
            int baris=tblFasilitas.getSelectedRow();
            String id_fasilitas = tblFasilitas.getValueAt(baris, 0).toString();
            try{
                String sql = "Select *from fasilitas where id_fasilitas = "+id_fasilitas+";";
                stt = con.createStatement();
                rss = stt.executeQuery(sql);
                while(rss.next()){
                    Object[] o = new Object[3];
                    o[0] = rss.getString("id_fasilitas");
                    o[1] = rss.getString("nama_fasilitas");
                    o[2] = rss.getString("harga_fasilitas");

                    tfNomorFasilitas.setText(o[0].toString());
                    tfNamaFasilitas.setText(o[1].toString());
                    tfHargaFasilitas.setText(o[2].toString());  
                    KunciField(true);
                }
                 }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditFasilitasActionPerformed

    private void btnTambahFasilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahFasilitasActionPerformed
        // TODO add your handling code here:
        KunciField(true);
        try{
            String sql = "Select max(id_fasilitas) as id_fasilitas from logidfasillitas;";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[1];
                o[0] = rss.getString("id_fasilitas");
                
                int id = Integer.parseInt(o[0].toString())+1;
                String id_fasilitas = String.valueOf(id);
                tfNomorFasilitas.setText(id_fasilitas);

                
            }
             }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTambahFasilitasActionPerformed

    private void tfNamaFasilitasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNamaFasilitasCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfNamaFasilitasCaretUpdate

    private void tfHargaFasilitasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfHargaFasilitasCaretUpdate
        // TODO add your handling code here:
        cekstatus();
    }//GEN-LAST:event_tfHargaFasilitasCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        bersihkanfield();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCariFasilitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariFasilitasMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCariFasilitasMouseEntered

    private void btnCariFasilitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariFasilitasMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCariFasilitasMouseExited

    private void tfHargaFasilitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHargaFasilitasKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_tfHargaFasilitasKeyTyped

    private void panelGlass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGlass1MouseClicked
        // TODO add your handling code here:
        tblFasilitas.clearSelection();
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
            java.util.logging.Logger.getLogger(frm_Fasilitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Fasilitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Fasilitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Fasilitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Fasilitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariFasilitas;
    private javax.swing.JButton btnEditFasilitas;
    private javax.swing.JButton btnHapusFasilitas;
    private javax.swing.JButton btnSimpanFasilitas;
    private javax.swing.JButton btnTambahFasilitas;
    private javax.swing.JButton btnTutupFasilitas;
    private javax.swing.JComboBox cbFilterFasilitas;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.layout.LayoutStyle layoutStyle1;
    private usu.widget.glass.PanelGlass panelGlass1;
    private javax.swing.JTable tblFasilitas;
    private javax.swing.JTextField tfCariFasilitas;
    private javax.swing.JTextField tfHargaFasilitas;
    private javax.swing.JTextField tfNamaFasilitas;
    private javax.swing.JTextField tfNomorFasilitas;
    // End of variables declaration//GEN-END:variables
}

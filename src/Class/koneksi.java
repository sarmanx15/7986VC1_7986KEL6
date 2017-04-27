/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class koneksi {
    private static Connection con;
    public static Connection getConnection(){
        try{
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_sak","root","");
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal"+e.getMessage());
        }
        return con;
    }
}

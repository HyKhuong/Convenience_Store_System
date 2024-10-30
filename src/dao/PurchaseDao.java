
package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author hvsom
 */
public class PurchaseDao {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public int getMaxRow(){
    int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(id) from purchase");
            while(rs.next()){
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    public String[] getUserValue(String email){
        String[] value = new String[9];
        try {
            ps = con.prepareStatement("Select uid,uname,uphone,uaddress1,uadress2 from user where uemail = ?"+email+"");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    public void insert(int id, int uid, String uname, String uPhone,int pid, String pname,
            int qyt,double price, double total,String pDate,String address, String rDate
            ,String supplier, String status ){
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,uid);
            ps.setString(3,uname);
            ps.setString(4,uPhone);
            ps.setInt(5,pid);
            ps.setString(6,pname);
            ps.setInt(7,qyt);
            ps.setDouble(8,price);
            ps.setDouble(9,total);
            ps.setString(10,pDate);
            ps.setString(11,address);
            ps.setString(12,rDate);
            ps.setString(13,supplier);
            ps.setString(14,status);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"User added successfull ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getQyt(int id){
        int qyt = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select pqyt from product where pid ="+ id +"");
            while(rs.next()){
                qyt = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qyt;
    }
    
    public void qytUpdate(int pid,int qyt){
        String sql = "update product set pqyt = ? where pid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,qyt);
            ps.setString(2,pid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

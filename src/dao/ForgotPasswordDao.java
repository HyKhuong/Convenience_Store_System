
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
import user.ForgotPassword;

/**
 *
 * @author hvsom
 */
public class ForgotPasswordDao {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    /*Kiem tra email co ton tai chua*/
    public boolean isEmailExit(String email){
        try {
            ps = con.prepareStatement("Select * from user where uemail = ?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){                
                ForgotPassword.jTextField2.setText(rs.getString(6));
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"Email address doesn't exit");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean getAns(String email, String newAns){
        try {
            ps = con.prepareStatement("Select * from user where uemail = ?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){                
                String oldAns = rs.getString(7);
                if(newAns.equals(oldAns)){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null,"Security answer didn't match");
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
    /*Dat lai mat khau*/
    public void setPassword(String email, String pass){
        String  sql = "update user set password = ? where uemail = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2,email);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Password successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

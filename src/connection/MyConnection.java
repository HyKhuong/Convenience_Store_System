package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author huynh
 */
public class MyConnection {
    public static final String username = "";
    public static final String passWord = "";
    public static final String url = " jdbc:mysql://locasthost:3306/convenience_system";
    public static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.myspl.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, passWord);
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ""+ex, "", JOptionPane.WARNING_MESSAGE);
        }
        return con;
    }
}

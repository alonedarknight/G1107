
package hotel.management.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;
    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //kết nối đến localhost (máy mình)/ database truy cập, user, password của database;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "qazedc321");
            s = c.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

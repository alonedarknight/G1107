package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    private JTextField username;
    private JPasswordField password;
    private JButton login, cancel;
    private JLabel signUpLabel;
    private Dashboard db;
    
    Login(Dashboard db) {
        this.db = db;
        // Thiết lập giao diện
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setUndecorated(true);
        
        // Tiêu đề "Login"
        JLabel title = new JLabel("Login to your account");
        title.setBounds(200, 20, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(50, 50, 50));
        add(title);

        // Nhãn và trường nhập liệu cho username
        JLabel user = new JLabel("Username");
        user.setBounds(40, 80, 100, 30);
        user.setFont(new Font("Arial", Font.PLAIN, 16));
        add(user);
        
        // Trường nhập liệu cho username
        username = new JTextField();
        username.setBounds(150, 80, 250, 30);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBackground(new Color(255, 255, 255));
        username.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(username);
        
        // Nhãn và trường nhập liệu cho password
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 130, 100, 30);
        pass.setFont(new Font("Arial", Font.PLAIN, 16));
        add(pass);
        
        // Trường nhập liệu cho password
        password = new JPasswordField();
        password.setBounds(150, 130, 250, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBackground(new Color(255, 255, 255));
        password.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(password);
        
        // Nút Login
        login = new JButton("Login");
        login.setBounds(40, 200, 150, 35);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(new Color(0, 123, 255));
        login.setForeground(Color.WHITE);
        login.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)));
        login.addActionListener(this);
        add(login);
        
        // Nút Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(180, 200, 150, 35);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setBackground(new Color(220, 53, 69));
        cancel.setForeground(Color.WHITE);
        cancel.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69)));
        cancel.addActionListener(this);
        add(cancel);
        
        // Dòng "Sign Up if you don't have an account"
        signUpLabel = new JLabel("Sign Up if you don't have an account");
        signUpLabel.setBounds(40, 250, 300, 30);
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setForeground(new Color(0, 123, 255));
        signUpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                new Register(); // Chuyển sang trang Register
                setVisible(false); // Đóng trang Login
            }
        });
        add(signUpLabel);
        
        // Thêm hình ảnh minh họa
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/register.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 80, 200, 200);
        add(image);
        
        // Thiết lập kích thước cửa sổ và hiển thị
        setBounds(500, 200, 650, 350);
        setTitle("Hotel Management System - Login");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Hàm xử lý sự kiện khi người dùng nhấn nút Login hoặc Cancel
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login) {
            String user = username.getText();
            String pass = new String(password.getPassword());
            
            try {
                Conn c = new Conn();
                
                // Kiểm tra tên người dùng và mật khẩu từ cơ sở dữ liệu
                String query = "SELECT * FROM users WHERE username = '" + user + "' AND pass = '" + pass + "'";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()) {
                    setVisible(false); // Đóng trang Login
                    int lv = rs.getInt(5);
                    db.setVisible_reservation(true);
                    if(lv > 0){
                        db.setVisible_hotel(true);
                    }
                    if(lv > 1){
                        db.setVisible_admin(true);
                    }
                    db.setVisible1(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false); // Ẩn trang Login
        }
    }
    
}

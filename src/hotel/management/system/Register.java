package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    private JTextField username, email, CCCD;
    private JPasswordField password, confirmPassword;
    private JButton register, cancel;
    private JLabel image;
    private Dashboard db;

    Register() {
        // Thiết lập giao diện
        getContentPane().setBackground(new Color(240, 240, 240)); // Màu nền nhẹ
        setLayout(null);
        setUndecorated(true);

        // Tiêu đề
        JLabel title = new JLabel("Register Account");
        title.setBounds(250, 20, 200, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));
        add(title);

        // Nhãn và trường nhập liệu cho username
        JLabel user = new JLabel("Username");
        user.setBounds(40, 80, 100, 30);
        user.setFont(new Font("Arial", Font.PLAIN, 16));
        add(user);
        username = new JTextField();
        username.setBounds(200, 80, 250, 30);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBackground(new Color(255, 255, 255));
        username.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(username);

        // Nhãn và trường nhập liệu cho email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(40, 120, 100, 30);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(emailLabel);
        email = new JTextField();
        email.setBounds(200, 120, 250, 30);
        email.setFont(new Font("Arial", Font.PLAIN, 14));
        email.setBackground(new Color(255, 255, 255));
        email.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(email);
        
        JLabel CCCD_label = new JLabel("ID Number");
        CCCD_label.setBounds(40, 160, 100, 30);
        CCCD_label.setFont(new Font("Arial", Font.PLAIN, 16));
        add(CCCD_label);
        CCCD = new JTextField();
        CCCD.setBounds(200, 160, 250, 30);
        CCCD.setFont(new Font("Arial", Font.PLAIN, 14));
        CCCD.setBackground(new Color(255, 255, 255));
        CCCD.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(CCCD);

        // Nhãn và trường nhập liệu cho mật khẩu
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 200, 100, 30);
        pass.setFont(new Font("Arial", Font.PLAIN, 16));
        add(pass);
        password = new JPasswordField();
        password.setBounds(200, 200, 250, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBackground(new Color(255, 255, 255));
        password.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(password);

        // Nhãn và trường nhập liệu cho xác nhận mật khẩu
        JLabel confirmPass = new JLabel("Confirm Password");
        confirmPass.setBounds(40, 240, 150, 30);
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 16));
        add(confirmPass);
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(200, 240, 250, 30);
        confirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPassword.setBackground(new Color(255, 255, 255));
        confirmPassword.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(confirmPassword);

        // Nút đăng ký
        register = new JButton("Register");
        register.setBounds(90, 290, 150, 35);
        register.setFont(new Font("Arial", Font.BOLD, 16));
        register.setBackground(new Color(0, 123, 255));
        register.setForeground(Color.WHITE);
        register.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)));
        register.addActionListener(this);
        add(register);

        // Nút hủy
        cancel = new JButton("Cancel");
        cancel.setBounds(230, 290, 150, 35);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setBackground(new Color(220, 53, 69));
        cancel.setForeground(Color.WHITE);
        cancel.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69)));
        cancel.addActionListener(this);
        add(cancel);

        // Dòng Sign In
        JLabel signInLabel = new JLabel("Sign In, If you already have an account");
        signInLabel.setBounds(90, 330, 300, 30);
        signInLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signInLabel.setForeground(Color.BLUE);
        signInLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new Login(db); // Chuyển sang màn hình đăng nhập
            }
        });
        add(signInLabel);

        // Thêm hình ảnh minh họa
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/register.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT); // Tăng kích thước ảnh
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(500, 80, 250, 250);
        add(image);

        // Thiết lập cửa sổ
        setBounds(500, 200, 800, 500); // Kích thước cửa sổ hợp lý
        setTitle("Hotel Management System - Register");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == register) {
            String user = username.getText();
            String mail = email.getText();
            String id = CCCD.getText();
            String pass = new String(password.getPassword());
            String confirmPass = new String(confirmPassword.getPassword());

            // Kiểm tra xem các trường có trống không
            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                return;
            }

            // Kiểm tra mật khẩu xác nhận có khớp không
            if (!pass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                return;
            }

            try {
                Conn c = new Conn();

                // Kiểm tra nếu username đã tồn tại trong cơ sở dữ liệu
                String query = "SELECT * FROM users WHERE username = '" + user + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                }
                else {
                    // Nếu không, thực hiện đăng ký
                    String insertQuery = "INSERT INTO users (username, email, CCCD, pass, lv) VALUES ('" + user + "', '" + mail + "', '" + id + "', '" + pass + "', 0)";
                    c.s.executeUpdate(insertQuery);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    setVisible(false); // Đóng cửa sổ đăng ký
                    new Login(db); // Mở cửa sổ đăng nhập
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Register(); // Khởi tạo lớp đăng ký
    }
}

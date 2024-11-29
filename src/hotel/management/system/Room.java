package hotel.management.system;

// add them libarary tren may de chay rs2xml.jar
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*; 
import javax.swing.border.Border;

// Lớp Room mở rộng từ JFrame, đại diện cho giao diện quản lý phòng trong hệ thống quản lý khách sạn
public class Room extends JFrame implements ActionListener {
    JTable table;  // Bảng để hiển thị danh sách phòng
    JButton back;  // Nút để quay lại màn hình chính hoặc màn hình trước đó
    
    // Constructor của lớp Room, dùng để thiết lập giao diện và các thành phần của lớp
    Room() {
        getContentPane().setBackground(Color.WHITE);  // Thiết lập màu nền của giao diện là trắng
        setLayout(null);  // Đặt layout thành null để có thể tự xác định vị trí của các thành phần
        setResizable(false);
        // Tải và thiết lập hình ảnh đại diện cho phòng
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);  // Đặt vị trí và kích thước của ảnh
        add(image);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        // Thiết lập các tiêu đề cột cho bảng
        JLabel l1 = new JLabel("Room Number");  // Tiêu đề cột số phòng
        l1.setBounds(10, 10, 100, 20);
        add(l1);
        
        
        JLabel l2 = new JLabel("Availability");  // Tiêu đề cột trạng thái sẵn có của phòng
        l2.setBounds(120, 10, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Status");  // Tiêu đề cột trạng thái (sạch hay cần dọn dẹp)
        l3.setBounds(230, 10, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");  // Tiêu đề cột giá phòng
        l4.setBounds(330, 10, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Bed Type");  // Tiêu đề cột loại giường
        l5.setBounds(410, 10, 100, 20);
        add(l5);
        
        // Tạo bảng để hiển thị danh sách các phòng
        table = new JTable();
        table.setBorder(border);
        table.setBounds(0, 30, 500, 450);  // Đặt vị trí và kích thước của bảng
        add(table);
        
        // Kết nối với cơ sở dữ liệu và đổ dữ liệu vào bảng
        try {
            Conn c = new Conn();  // Khởi tạo đối tượng kết nối
            ResultSet rs = c.s.executeQuery("select * from room");  // Thực thi câu truy vấn lấy dữ liệu từ bảng phòng
            table.setModel(DbUtils.resultSetToTableModel(rs));  // Đổ dữ liệu từ ResultSet vào bảng
        } catch (Exception e) {
            e.printStackTrace();  // Xử lý lỗi nếu có
        }
        
        // Tạo nút quay lại và thiết lập các thuộc tính
        back = new JButton("Back");
        back.setBackground(Color.BLACK);  
        back.setForeground(Color.WHITE);  
        back.addActionListener(this);  
        back.setBounds(200, 500, 120, 30);  
        add(back);
        
        // Thiết lập các thuộc tính của cửa sổ JFrame
        setBounds(300, 200, 1050, 600);  
        setVisible(true);  
    }
    
    // Phương thức xử lý sự kiện khi nút được bấm
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);  
        new Reception();  
    }
    
    // Phương thức main để chạy chương trình và tạo đối tượng Room
    public static void main(String[] args) {
        new Room();
    }
}

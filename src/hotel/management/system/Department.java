package hotel.management.system;

// Import các thư viện cần thiết cho giao diện, xử lý sự kiện, và kết nối cơ sở dữ liệu
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;  // Thư viện tiện ích để đổ dữ liệu từ ResultSet vào JTable
import javax.swing.border.Border;
// Lớp Department mở rộng từ JFrame, đại diện cho giao diện quản lý các phòng ban và ngân sách của chúng trong hệ thống quản lý khách sạn
public class Department extends JFrame implements ActionListener {
    JTable table;  // Bảng hiển thị dữ liệu phòng ban và ngân sách
    JButton back;  // Nút quay lại màn hình trước đó
    
    // Constructor của lớp Department, thiết lập giao diện và các thành phần trong giao diện
    Department() {
        getContentPane().setBackground(Color.WHITE);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        JLabel l1 = new JLabel("Department"); 
        l1.setBounds(180, 10, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Budget");  
        l2.setBounds(420, 10, 100, 20);
        add(l2);
        
        // Tạo bảng để hiển thị danh sách các phòng ban và ngân sách
        table = new JTable();
        table.setBorder(border);
        table.setBounds(0, 50, 700, 330);  
        add(table);
        
        // Kết nối với cơ sở dữ liệu và đổ dữ liệu vào bảng
        try {
            Conn c = new Conn();  // Khởi tạo đối tượng kết nối cơ sở dữ liệu
            ResultSet rs = c.s.executeQuery("select * from department");  // Truy vấn lấy dữ liệu từ bảng "department"
            table.setModel(DbUtils.resultSetToTableModel(rs));  // Đổ dữ liệu từ ResultSet vào bảng
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có vấn đề trong quá trình truy vấn
        }
        
        // Tạo nút "Back" để quay lại và thiết lập các thuộc tính
        back = new JButton("Back");
        back.setBackground(Color.BLACK);  
        back.setForeground(Color.WHITE);  
        back.addActionListener(this);  
        back.setBounds(280, 400, 120, 30);  
        add(back);
        
        // Thiết lập các thuộc tính của cửa sổ JFrame
        setBounds(400, 200, 700, 480);  
        setVisible(true);  
    }
    
    // Phương thức xử lý sự kiện khi nút "Back" được bấm
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);  // Ẩn cửa sổ hiện tại
        new Reception();  // Mở màn hình tiếp tân (Reception)
    }
    
    // Phương thức main để khởi chạy chương trình và tạo một đối tượng Department
    public static void main(String[] args) {
        new Department();
    }
}


// thêm trong sql
//create table department(department varchar(30), budget varchar(30));
//
//insert into department values('Front Office' , '500000');
//insert into department values('Housekeeping' ,'40000');
//insert into department values('Food and Beverage' , '23000');
//insert into department values('Kitchen or Food Production' , '540000');
//insert into department values('Security','320000');
//
//select * from department;
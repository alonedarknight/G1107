package hotel.management.system;

// Import các thư viện cần thiết cho giao diện, xử lý sự kiện và kết nối cơ sở dữ liệu
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;  // Dùng thư viện để đổ dữ liệu từ ResultSet vào JTable dễ dàng

// Lớp Room mở rộng từ JFrame, đại diện cho giao diện quản lý phòng trong hệ thống quản lý khách sạn
public class SearchRoom extends JFrame implements ActionListener {
    JTable table;  // Bảng để hiển thị danh sách phòng
    JButton back, Submit; 
    JComboBox bedType;// Nút để quay lại màn hình chính hoặc màn hình trước đó
    JCheckBox available;
    // Constructor của lớp Room, dùng để thiết lập giao diện và các thành phần của lớp
    SearchRoom() {
        getContentPane().setBackground(Color.WHITE);  // Thiết lập màu nền của giao diện là trắng
        setLayout(null);  // Đặt layout thành null để có thể tự xác định vị trí của các thành phần
        
        JLabel text =new JLabel("Search for Room");
        text.setFont(new Font ("Tahoma",Font.PLAIN,20));
        text.setBounds(400,30,200,30);
        add(text);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setBounds(50,100,100,20);
        add(lblbed);
        
        bedType = new JComboBox (new String[]{"Single Bed","Double Bed"});
        bedType.setBounds(150,100,150,25);
        bedType.setBackground(Color.WHITE);
        add(bedType);
        
        available =new JCheckBox("Only display Available");
        available.setBounds(650,100,150,25);
        available.setBackground(Color.WHITE);
        add(available);
        
        // Thiết lập các tiêu đề cột cho bảng
        JLabel l1 = new JLabel("Room Number");  // Tiêu đề cột số phòng
        l1.setBounds(50, 160, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Availability");  // Tiêu đề cột trạng thái sẵn có của phòng
        l2.setBounds(270, 160, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Cleaning Status");  // Tiêu đề cột trạng thái (sạch hay cần dọn dẹp)
        l3.setBounds(450, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");  // Tiêu đề cột giá phòng
        l4.setBounds(670, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Bed Type");  // Tiêu đề cột loại giường
        l5.setBounds(870, 160, 100, 20);
        add(l5);
        
        // Tạo bảng để hiển thị danh sách các phòng
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);  // Đặt vị trí và kích thước của bảng
        add(table);
        
        // Kết nối với cơ sở dữ liệu và đổ dữ liệu vào bảng
        try {
            Conn c = new Conn();  // Khởi tạo đối tượng kết nối
ResultSet rs = c.s.executeQuery("select * from room");  // Thực thi câu truy vấn lấy dữ liệu từ bảng phòng
            table.setModel(DbUtils.resultSetToTableModel(rs));  // Đổ dữ liệu từ ResultSet vào bảng
        } catch (Exception e) {
            e.printStackTrace();  // Xử lý lỗi nếu có
        }
        
        Submit = new JButton("Submit");
        Submit .setBackground(Color.BLACK);  
        Submit .setForeground(Color.WHITE);  
        Submit .addActionListener(this);  
        Submit .setBounds(300, 520, 120, 30);  
        add( Submit );
        
        // Tạo nút quay lại và thiết lập các thuộc tính
        back = new JButton("Back");
        back.setBackground(Color.BLACK);  
        back.setForeground(Color.WHITE);  
        back.addActionListener(this);  
        back.setBounds(500, 520, 120, 30);  
        add(back);
        
        // Thiết lập các thuộc tính của cửa sổ JFrame
        setBounds(300, 200, 1000, 600);  
        setVisible(true);  
    }
    
    // Phương thức xử lý sự kiện khi nút được bấm
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()== Submit){
            try{
                String query1="select * from room where bed_type = '"+bedType.getSelectedItem()+"'";
                String query2="select * from room where availability = 'Available' AND bed_type='"+bedType.getSelectedItem()+"'";
                
                Conn conn= new Conn();
                ResultSet rs;
                if (available.isSelected()){
                    rs=conn.s.executeQuery(query2);
                }else{
                    rs=conn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
                
            } catch(Exception e){
                e.printStackTrace();
            }
        } else{
        setVisible(false);  
        new Reception();  
    }
    }
    
    // Phương thức main để chạy chương trình và tạo đối tượng Room
    public static void main(String[] args) {
        new SearchRoom();
    }
}

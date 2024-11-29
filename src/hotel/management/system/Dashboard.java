package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    private JLabel imageLabel;
    private JLabel text;
    private JLabel login, logout, register, reservation;
    private JPanel panel;
    private JMenuBar leftPanel, rightPanel;
    private JMenu hotel, admin;
    private JMenuItem reception, addemployee, addrooms, adddrivers, updateemployee, modifyroom;

    Dashboard() {
        setSize(1550, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load ảnh và thiết lập nhãn để chứa ảnh
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/muongthanh2.jpg"));
        imageLabel = new JLabel(i1);
        imageLabel.setBounds(0, 0, getWidth(), getHeight());
        add(imageLabel);

        // Thiết lập ComponentListener để cập nhật kích thước hình ảnh khi kích thước cửa sổ thay đổi
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateImageSize();
                updateTextSize();
            }
        });

        // Chèn chữ
        text = new JLabel("THE MUONG THANH WELCOMES YOU");
        text.setForeground(Color.WHITE);
        imageLabel.add(text);
        // Cập nhật kích thước và vị trí chữ ban đầu
        updateTextSize();

        panel = new JPanel();
        panel.setBounds(20, 0, 1510, 60);
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(new Color(0,0,0,0));
        imageLabel.add(panel);
        
        leftPanel = new JMenuBar();
        leftPanel.setOpaque(false);
        leftPanel.setBorderPainted(false);
        
        // Thêm các mục menu
        hotel = new JMenu("   Management  ");
        hotel.setForeground(Color.WHITE);
        hotel.setFont(new Font("Arial", Font.PLAIN, 20));
        leftPanel.add(hotel);

        admin = new JMenu("   Admin   ");
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Arial", Font.PLAIN, 20));
        leftPanel.add(admin);
        
        reservation = new JLabel("    Reservation    ");
        reservation.setFont(new Font("Arial", Font.PLAIN, 20));
        reservation.setForeground(Color.WHITE);
        reservation.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reservation.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "COMING SOON!!! :D");
            }
        });
        leftPanel.add(reservation);
        
        rightPanel = new JMenuBar();
        rightPanel.setOpaque(false);
        leftPanel.setBorderPainted(false);

        login = new JLabel("    Log in    ");
        login.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setForeground(Color.WHITE);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Login(Dashboard.this);
            }
        });
        rightPanel.add(login);
        
        register = new JLabel("    Register    ");
        register.setFont(new Font("Arial", Font.PLAIN, 20));
        register.setForeground(Color.WHITE);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Register(); 
            }
        });
        rightPanel.add(register);
        
        logout = new JLabel("    Log out    ");
        logout.setFont(new Font("Arial", Font.PLAIN, 20));
        logout.setForeground(Color.WHITE);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible1(false);
                reservation.setVisible(false);
                admin.setVisible(false);
                hotel.setVisible(false);
                repaint();
            }
        });
        rightPanel.add(logout);
        logout.setVisible(false);
        
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);
        
        // Thêm mục con vào menu
        reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        hotel.add(reception);

        addemployee = new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        admin.add(addemployee);

        addrooms = new JMenuItem("ADD ROOMS");
        addrooms.addActionListener(this);
        admin.add(addrooms);

        adddrivers = new JMenuItem("ADD DRIVERS");
        adddrivers.addActionListener(this);
        admin.add(adddrivers);
        
        updateemployee = new JMenuItem("UPDATE EMPLOYEE");
        updateemployee.addActionListener(this);
        admin.add(updateemployee);
        
        modifyroom = new JMenuItem("MODIFY ROOM");
        modifyroom.addActionListener(this);
        admin.add(modifyroom);
        
        reservation.setVisible(false);
        admin.setVisible(false);
        hotel.setVisible(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("ADD EMPLOYEE")) {
            new AddEmployee();
        } else if (ae.getActionCommand().equals("ADD ROOMS")) {
            new AddRooms();
        } else if(ae.getActionCommand().equals("ADD DRIVERS")) {
            new AddDrivers();
        } else if(ae.getActionCommand().equals("RECEPTION")) {
            new Reception();
        } else if(ae.getActionCommand().equals("UPDATE EMPLOYEE")){
            new UpdateEmployee();
        } else if(ae.getActionCommand().equals("MODIFY ROOM")){
            new ModifyRoom();
        }
        
        
    }

    private void updateImageSize() {
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/muongthanh2.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setBounds(0, 0, getWidth(), getHeight());
    }

    private void updateTextSize() {
        // Sử dụng tỉ lệ font mới để đảm bảo chữ hiển thị đẹp
        int fontSize = getWidth() / 25; // Điều chỉnh hệ số chia để font chữ có tỉ lệ tốt hơn
        text.setFont(new Font("Tahoma", Font.BOLD, fontSize)); // Đổi font thành đậm để dễ đọc hơn

        // Cập nhật vị trí của dòng chữ để căn giữa
        text.setBounds((getWidth() - text.getPreferredSize().width) / 2, getHeight() / 8, text.getPreferredSize().width, text.getPreferredSize().height);
    }
    public void setVisible1(boolean t){
        this.login.setVisible(!t);
        this.register.setVisible(!t);
        this.logout.setVisible(t);
        this.repaint();
    }
    public void setVisible_hotel(boolean t){
        this.hotel.setVisible(t);
        this.repaint();
    }
    public void setVisible_admin(boolean t){
        this.admin.setVisible(t);
        this.repaint();
    }
    public void setVisible_reservation(boolean t){
        this.reservation.setVisible(t);
        this.repaint();
    }
    
    public static void main(String[] args) {
        Dashboard db = new Dashboard();
    }
}

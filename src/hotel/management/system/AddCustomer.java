
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class AddCustomer extends JFrame implements ActionListener{
    
    private JComboBox comboid;
    private JTextField tfnumber, tfname, tfcountry, tfdeposit;
    private JRadioButton rmale, rfemale;
    private Choice croom;
    private JLabel checkintime;
    private JButton add, back;
    
    
    AddCustomer(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        
        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(text);
        
        JLabel lblid = new JLabel("Type of Cards");
        lblid.setBounds(35, 80, 150, 30);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblid);
        
        String options[] = {"Identification Card", "Passport", "Driving License"};
        comboid = new JComboBox(options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel("ID Number");
        lblnumber.setBounds(35, 120, 100, 20);
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 160, 100, 20);
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 200, 100, 20);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 200, 60, 25);
        add(rmale);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270, 200, 100, 25);
        add(rfemale);
        
        ButtonGroup bg = new ButtonGroup();
            bg.add(rmale);
            bg.add(rfemale);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35, 240, 100, 25);
        lblcountry.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);
        
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(35, 280, 150, 20);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblroom);
        
        croom = new Choice();
        
        try{
            Conn conn = new Conn();
            String query = "select * from room";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        croom.setBounds(200, 280, 150, 25);
        add(croom);
        
        JLabel lbltime = new JLabel("Checkin time");
        lbltime.setBounds(35, 320, 150, 20);
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbltime);
        
       
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        
        checkintime = new JLabel("" + dateString);
        checkintime.setBounds(200, 320, 120, 25);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 360, 100, 20);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbldeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);
        
        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(50, 410, 120, 30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200, 410, 120, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);
        
        setBounds(350, 200, 800, 550);
        setVisible(true);
    }
    // phần cần thêm ở trong database máy ae
    
    //create table customer(document varchar(20), number varchar(30), name varchar(30), gender varchar(15), country varchar(20), room varchar(10), checkintime varchar(80), deposit varchar(20));
    
    
    
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String id = (String)comboid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
            
            if(rmale.isSelected()){
                gender = "Male";
            }
            else{
                gender = "Female";
            }
            
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            String deposit = tfdeposit.getText();
           
            try{
                String query = "insert into customer values('"+id+"', '"+number+"', '"+name+"', '"+gender+"', '"+country+"', '"+room+"', '"+time+"', '"+deposit+"', 'NULL')";
                String query2 = "update room set availability = 'Occupied' where roomnumber = '"+room+"'";
               
                Conn conn = new Conn();
                
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "New Customer Is Added Succesfully");
                setVisible(false);
                new Reception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
    
    
    
    public static void main(String[] args){
        new AddCustomer();
    }
}

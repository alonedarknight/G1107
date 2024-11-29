package hotel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.*;


public class ModifyRoom extends JFrame implements ActionListener {
    
    Choice croom;
    JTextField tfprice, tfbed, tfnumber;
    JButton check, update, back, remove;
    
    ModifyRoom(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel text = new JLabel("Modify Room");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 20, 250, 30);
        add(text);
        
        JLabel lblroomnumber = new JLabel("Room Number");
        lblroomnumber.setBounds(30, 80, 100, 20);
        add(lblroomnumber);
        
        croom = new Choice();
        croom.setBounds(200, 80, 150, 25);
        add(croom);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        JLabel lblnumber = new JLabel("Modify Roomnumber");
        lblnumber.setBounds(30, 150, 150, 20);
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200, 150, 150, 25);
        add(tfnumber);
        
        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(30, 200, 100, 20);
        add(lblprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200, 200, 150, 25);
        add(tfprice);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setBounds(30, 280, 100, 20);
        add(lblbed);
         
        tfbed = new JTextField();
        tfbed.setBounds(200, 280, 150, 25);
        add(tfbed);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 370, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 370, 100, 30);
        update.addActionListener(this);
        add(update);
        
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 370, 100, 30);
        back.addActionListener(this);
        add(back);
        
        remove = new JButton("Remove");
        remove.setBackground(Color.BLACK);
        remove.setForeground(Color.WHITE);
        remove.setBounds(150, 420, 100, 30);
        remove.addActionListener(this);
        add(remove);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/room3.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 40, 500, 370);
        add(image);
        
        
        
        setBounds(300, 200, 980, 500);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String room = croom.getSelectedItem();
            String query = "select * from room where roomnumber = '"+room+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfprice.setText(rs.getString("price"));
                    tfbed.setText(rs.getString("bed_type"));                      
                     
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(ae.getSource() == update){
            String room = croom.getSelectedItem();
            String number = tfnumber.getText();
            String price = tfprice.getText();
            String bed = tfbed.getText();
           
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update room set roomnumber = '"+ number +"', price = '"+price+"', bed_type = '"+bed+"' where roomnumber = '"+room+"'");
                
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == remove){
            String query = "delete from room where roomnumber = '"+croom.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully remove room");
                setVisible(false);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
        
        
        else{
            setVisible(false);
            
        }
    }
    
    
    public static void main(String[] args){
        new ModifyRoom();
    }
}

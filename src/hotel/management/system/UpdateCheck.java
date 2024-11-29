
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateCheck extends JFrame implements ActionListener {
    
    private Choice ccustomer;
    private JTextField tfroom, tfname, tfcheckin , tfpaid, tftotal, tfpending, tfdeposit;
    private JButton check, update, back;
    private String dateCheckoutString, dateCheckinString, paid;
    
    UpdateCheck(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 20, 200, 30);
        add(text);
        
        
        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(200, 80, 150, 25);
        add(ccustomer);
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 120, 100, 20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 120, 150, 25);
        add(tfroom);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
        JLabel lblcheckin = new JLabel("Checkout Time");
        lblcheckin.setBounds(30, 200, 100, 20);
        add(lblcheckin);
        
        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 150, 25);
        add(tfcheckin);
        
        JLabel lblpaid = new JLabel("Deposit");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);
        
        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);
        
        JLabel lbltotal = new JLabel("Total");
        lbltotal.setBounds(30, 280, 100, 20);
        add(lbltotal);
        
        
        
        tftotal = new JTextField();
        tftotal.setBounds(200, 280, 150, 25);
        add(tftotal);
        
        JLabel lblpending = new JLabel("Pending");
        lblpending.setBounds(30, 320, 100, 20);
        add(lblpending);
        
        tfpending = new JTextField();
        tfpending.setBounds(200, 320, 150, 25);
        add(tfpending);
        
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
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        
        setBounds(300, 200, 980, 500);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+id+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dateCheckoutString = sdf.format(date);
                    dateCheckinString = rs.getString("checkintime");
                    
                    tfcheckin.setText(dateCheckoutString);
                    paid = rs.getString("deposit");
                    tfpaid.setText(paid);
                     
                }
                
                ResultSet rs2 = c.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
                while(rs2.next()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime1= LocalDateTime.parse(dateCheckinString, formatter);
                    LocalDateTime dateTime2= LocalDateTime.parse(dateCheckoutString, formatter);
                    long duration = java.time.Duration.between(dateTime2, dateTime1).toMinutes();
                    String price = rs2.getString("price");
                    long total; 
                    if(duration < 60){
                        total = Long.parseLong(price) * 30 / 100;
                 
                    }
                    else if(duration < 120){
                        total = Long.parseLong(price) * 40 / 100;
                    }
                    else if(duration < 1440){
                        total = Long.parseLong(price);
                    }
                    else{
                        total = Long.parseLong(price)* (duration / 1440); 
                    }
                    tftotal.setText("" + total);
                    Long pending = total - Long.parseLong(paid);
                    tfpending.setText("" + pending);
                    
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(ae.getSource() == update){
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkout = tfcheckin.getText();
            String deposit = tfpaid.getText();
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set room = '"+room+"', name = '"+name+"', checkouttime = '"+checkout+"', deposit = '"+deposit+"' where number = '"+number+"'");
                
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);
                new Reception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String[] args){
        new UpdateCheck();
    }
    
}

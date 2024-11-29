package hotel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.*;

public class UpdateEmployee extends JFrame implements ActionListener {
    
    private Choice cemployee;
    private JTextField tfage, tfgender, tfjob, tfpaid, tfphone, tfnumber;
    private JButton check, update, back, remove;
    
    UpdateEmployee(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setResizable(false);
        
        JLabel text = new JLabel("Update Employee Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 20, 250, 30);
        add(text);
        
        JLabel lblname = new JLabel("Employee's name");
        lblname.setBounds(30, 80, 100, 20);
        add(lblname);
        
        cemployee = new Choice();
        cemployee.setBounds(200, 80, 150, 25);
        add(cemployee);
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()){
                cemployee.add(rs.getString("name"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        JLabel lblage = new JLabel("Age");
        lblage.setBounds(30, 120, 100, 20);
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200, 120, 150, 25);
        add(tfage);
        
        
        
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 160, 100, 20);
        add(lblgender);
        
        tfgender = new JTextField();
        tfgender.setBounds(200, 160, 150, 25);
        add(tfgender);
        
        JLabel lbljob = new JLabel("Position");
        lbljob.setBounds(30, 200, 100, 20);
        add(lbljob);
        
        tfjob = new JTextField();
        tfjob.setBounds(200, 200, 150, 25);
        add(tfjob);
        
        JLabel lblpaid = new JLabel("Salary");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);
        
        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 280, 100, 20);
        add(lblphone);
         
        tfphone = new JTextField();
        tfphone.setBounds(200, 280, 150, 25);
        add(tfphone);
        
        JLabel lblnumber = new JLabel("ID Number");
        lblnumber.setBounds(30, 320, 100, 20);
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200, 320, 150, 25);
        add(tfnumber);
        
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
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/remove.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 40, 500, 370);
        add(image);
        
        
        
        setBounds(300, 200, 980, 500);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String name = cemployee.getSelectedItem();
            String query = "select * from employee where name = '"+name+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfage.setText(rs.getString("age"));
                    tfgender.setText(rs.getString("gender"));
                    tfjob.setText(rs.getString("job"));
                    tfpaid.setText(rs.getString("salary"));
                    tfphone.setText(rs.getString("phone"));
                    tfnumber.setText(rs.getString("aadhar"));                       
                     
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(ae.getSource() == update){
            String name = cemployee.getSelectedItem();
            String age = tfage.getText();
            String gender = tfgender.getText();
            String job = tfjob.getText();
            String salary = tfpaid.getText();
            String phone = tfphone.getText();
            String number = tfnumber.getText();
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update employee set age = '"+ age +"', gender = '"+gender+"', job = '"+job+"', salary = '"+salary+"', phone = '"+phone+"', aadhar = '"+number+"' where name = '"+name+"'");
                
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);
                new Reception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == remove){
            String query = "delete from employee where name = '"+cemployee.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully remove employee");
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
        new UpdateEmployee();
    }
}

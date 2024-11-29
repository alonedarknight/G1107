package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;
import net.proteanit.sql.*;

public class ManagerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back;
    
    ManagerInfo(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(40,10,100,20);
        add(l1);
        
        JLabel l2 = new JLabel("Age");
        l2.setBounds(170,10,100,20);
        add(l2);
        
        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290,10,100,20);
        add(l3);
        
        JLabel l4 = new JLabel("Job");
        l4.setBounds(400,10,100,20);
        add(l4);
        
        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540,10,100,20);
        add(l5);
        
        JLabel l6 = new JLabel("Phone");
        l6.setBounds(670,10,100,20);
        add(l6);
        
        
        JLabel l7 = new JLabel("Email");
        l7.setBounds(790,10,100,20);
        add(l7);
        
        JLabel l8 = new JLabel("ID Number");
        l8.setBounds(910,10,100,20);
        add(l8);
        
        table = new JTable();
        table.setBorder(border);
        table.setBounds(0,40,1000,400);
        add(table);
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee where job = 'Manager'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(440,500,120,30);
        add(back);
        
        setBounds(420,200,1000,600);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    public static void main(String[] args){
        new ManagerInfo();
    }
    
}
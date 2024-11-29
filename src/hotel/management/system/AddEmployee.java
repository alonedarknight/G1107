package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener {
        
        
        JTextField tfname, tfemail, tfphone, tfage, tfsalary, tfaadhar, user, pass;
        JRadioButton rbmale, rbfemale;
        JButton submit;
        JComboBox cbjob;
        
        
	AddEmployee(){
		setLayout(null);
		
		JLabel lblname = new JLabel("NAME");
		lblname.setBounds(60, 30, 120, 30);
		lblname.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(200, 30, 150 , 30);
		add(tfname);
		
		
		JLabel lblage = new JLabel("AGE");
		lblage.setBounds(60, 80, 120, 30);
		lblage.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblage);
		
		tfage = new JTextField();
		tfage.setBounds(200, 80, 150 , 30);
		add(tfage);
		
		
		JLabel lblgender = new JLabel("GENDER");
		lblgender.setBounds(60, 130, 120, 30);
		lblgender.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblgender);
		
		
		rbmale = new JRadioButton("Male");
		rbmale.setBounds(200, 130, 70, 30);
		rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbmale.setBackground(Color.WHITE);
		add(rbmale);
		
		rbfemale = new JRadioButton("Female");
		rbfemale.setBounds(280, 130, 70, 30);
		rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbfemale.setBackground(Color.WHITE);
		add(rbfemale);
		
                
                // Chon 1 trong 2 nut male or female
		ButtonGroup bg = new ButtonGroup();
                bg.add(rbmale);
                bg.add(rbfemale);
                
                
		JLabel lbljob = new JLabel("JOB");
		lbljob.setBounds(60, 180, 120, 30);
		lbljob.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lbljob);
		
		
		String str[] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Accountant", "Receptionist"};
		cbjob = new JComboBox(str);
		cbjob.setBounds(200, 180, 150, 30);
		cbjob.setBackground(Color.WHITE);
		add(cbjob);
		
		
		JLabel lblsalary = new JLabel("SALARY");
		lblsalary.setBounds(60, 230, 120, 30);
		lblsalary.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblsalary);
		
		tfsalary = new JTextField();
		tfsalary.setBounds(200, 230, 150 , 30);
		add(tfsalary);
		
		
		JLabel lblphone = new JLabel("PHONE");
		lblphone.setBounds(60, 280, 120, 30);
		lblphone.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(200, 280, 150 , 30);
		add(tfphone);
		
		JLabel lblemail = new JLabel("EMAIL");
		lblemail.setBounds(60, 330, 120, 30);
		lblemail.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(200, 330, 150 , 30);
		add(tfemail);
		
                
                JLabel lblaadhar = new JLabel("ID NUMBER");
		lblaadhar.setBounds(60, 380, 120, 30);
		lblaadhar.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(lblaadhar);
		
		tfaadhar = new JTextField();
		tfaadhar.setBounds(200, 380, 150 , 30);
		add(tfaadhar);
                
                JLabel username = new JLabel("USERNAME");
		username.setBounds(60, 430, 120, 30);
		username.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(username);
		
		user = new JTextField();
		user.setBounds(200, 430, 150 , 30);
		add(user);
                
                JLabel password = new JLabel("PASSWORD");
		password.setBounds(60, 480, 120, 30);
		password.setFont(new Font("Tahoma" , Font.PLAIN , 17));
		add(password);
		
		pass = new JTextField();
		pass.setBounds(200, 480, 150 , 30);
		add(pass);
		
		submit = new JButton("SUBMIT");
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);
		submit.setBounds(200, 530, 150, 30);
                submit.addActionListener(this);
		add(submit);
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
		Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(380, 60, 450, 370);
		add(image);
		
		getContentPane().setBackground(Color.WHITE);
		setBounds(250, 100, 850, 640);
		setVisible(true);
	}
	
        
        public void actionPerformed(ActionEvent ae){
            String name = tfname.getText();
            String age = tfage.getText();
            String salary = tfsalary.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String aadhar = tfaadhar.getText();
            String user_t = user.getText();
            String pass_t = pass.getText();
            
            String gender = null;
            
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name should not be empty");
                return;    
            } 
            if(rbmale.isSelected()){
                gender = "Male";
            }
            else if(rbfemale.isSelected()){
                gender = "Female";
            }
            
            String job = (String)cbjob.getSelectedItem();
            
            try{
                Conn conn = new Conn();
                /* để truy vấn được phải tạo bảng
                chọn bôi đen từng câu lệnh rồi chạy 1 câu lệnh 1 trong database trên máy mình
                    create database hotelmanagementsystem;
                    use hotelmanagementsystem;
                    create table login(username varchar(25), password varchar(25));
                    
                    insert into login values ('admin','12345');
                    
                    create table employee(name varchar(25), age varchar(5), gender varchar(15), job varchar(30), salary varchar(15), phone varchar(15), email varchar(40), addhar varchar(20));
                
                */
                String query1 = "SELECT * FROM users WHERE username = '" + user_t + "'";
                ResultSet rs = conn.s.executeQuery(query1);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                }
                else {
                    // Nếu không, thực hiện đăng ký
                    String insertQuery = "";
                    if(job.equals("Manager") || job.equals("Receptionist")){
                        insertQuery = "INSERT INTO users (username, email, CCCD, pass, lv) VALUES ('" + user_t + "', '" + email + "', '" + aadhar + "', '" + pass_t + "', 1)";
                    }
                    else{
                        insertQuery = "INSERT INTO users (username, email, CCCD, pass, lv) VALUES ('" + user_t + "', '" + email + "', '" + aadhar + "', '" + pass_t + "', 0)";
                    }
                    conn.s.executeUpdate(insertQuery);
                    String query = "insert into employee values('"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"', '"+aadhar+"')";
                
                    conn.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Employee added succesfully");

                    setVisible(false);
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
	public static void main(String[] args) {
		new AddEmployee();
	}
}

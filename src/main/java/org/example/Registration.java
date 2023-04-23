package org.example;
import com.mysql.cj.Query;
import org.example.Login1;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField teacher_name;
    private JTextField teacher_lastname;
    private JTextField teacher_email;

    private JTextField teacher_telefon;
    private JPasswordField teacher_password;
    private JButton btnNewButton;
    private JButton ButtonLog;

    private JButton resetButton;
    public Registration() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);

        JLabel lblNewUserRegister = new JLabel("New User Register");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("name_and_last");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);



        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 250, 124, 36);
        contentPane.add(lblEmailAddress);

        teacher_name = new JTextField();
        teacher_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        teacher_name.setBounds(214, 151, 228, 50);
        contentPane.add(teacher_name);
        teacher_name.setColumns(10);



        teacher_email = new JTextField();

        teacher_email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        teacher_email.setBounds(214, 250, 228, 50);
        contentPane.add(teacher_email);
        teacher_email.setColumns(10);




        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 200, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobileNumber.setBounds(542, 270, 139, 26);
        contentPane.add(lblMobileNumber);

        teacher_telefon = new JTextField();
        teacher_telefon.setFont(new Font("Tahoma", Font.PLAIN, 32));
        teacher_telefon.setBounds(707, 270, 228, 50);
        contentPane.add(teacher_telefon);
        teacher_telefon.setColumns(10);

        teacher_password = new JPasswordField();
        teacher_password.setFont(new Font("Tahoma", Font.PLAIN, 32));
        teacher_password.setBounds(707, 200, 228, 50);
        contentPane.add(teacher_password);




        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        resetButton.setBounds(700, 470, 100, 50);
        contentPane.add(resetButton);


        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {
                String def = "";
                teacher_name.setText(def);

                teacher_email.setText(def);
                teacher_telefon.setText(def);
                teacher_password.setText(def);


            }

        });


        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = teacher_name.getText();

                String emailId = teacher_email.getText();

                String mobileNumber = teacher_telefon.getText();
                int len = mobileNumber.length();
                String password = teacher_password.getText();

                String msg = "" + firstName;
                msg += " \n";
                if(len!=9){
                    JOptionPane.showMessageDialog(btnNewButton, "enter a 9 digit number" );
                }
                else
                    try {

                        Connection con = getConnect();
                        String searchQuery = "SELECT email FROM nauczyciele WHERE email = ?";
                        PreparedStatement searchPstmt = con.prepareStatement(searchQuery);
                        searchPstmt.setString(1, emailId);
                        ResultSet searchResult = searchPstmt.executeQuery();

                        if (searchResult.next()) {
                            JOptionPane.showMessageDialog(btnNewButton, "teacher with email '" + emailId + "' already exists");
                        } else{

                            try {


                                String query = "INSERT INTO nauczyciele (imie, email, haslo, telefon) VALUES (?, ?, ?, ?)";
                                PreparedStatement insertPstmt = con.prepareStatement(query);
                                insertPstmt.setString(1, firstName);
                                insertPstmt.setString(2, emailId);
                                insertPstmt.setString(3, password);
                                insertPstmt.setString(4, mobileNumber);


                                insertPstmt.executeUpdate();

                                JOptionPane.showMessageDialog(btnNewButton, "Great, you registered");

                                con.close();
                            } catch (Exception exception) {
                                exception.printStackTrace();

                            }
                        }
                    } catch(Exception exception){
                        exception.printStackTrace();
                    }
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(399, 447, 259, 50);
        contentPane.add(btnNewButton);



    }


    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
}
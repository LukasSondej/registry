package org.example;

import com.mysql.cj.Query;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Login1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    public Login1() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);


        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Email");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = passwordField.getText();


                try {
                    Connection con = getConnect();
                    String searchQuery = "Select email, haslo from admin where email=? and haslo=?";
                    PreparedStatement searchPstmt = con.prepareStatement(searchQuery);
                    searchPstmt.setString(1, email);
                    searchPstmt.setString(2, password);
                    ResultSet searchResult = searchPstmt.executeQuery();

                    if (searchResult.next()) {
                        System.out.println("jkdsghskadg");
                    } else {
                        int len = password.length();
                        if (len == 0) {
                            JOptionPane.showMessageDialog(btnNewButton, "Enter password or email");
                        } else {

                            try {

                                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3",
                                        "root", "Rafalek");

                                PreparedStatement st = (PreparedStatement) connection
                                        .prepareStatement("Select email, haslo from nauczyciele where email=? and haslo=?");

                                st.setString(1, email);
                                st.setString(2, password);
                                ResultSet rs = st.executeQuery();
                                if (rs.next()) {

                                    String thismail = textField.getText();
                                    TeacherClass teacherClass = new TeacherClass(thismail);


                                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                                } else {
                                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                                }
                            } catch (SQLException sqlException) {
                                sqlException.printStackTrace();
                            }
                        }
                    }
                    }catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }

            });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }




}







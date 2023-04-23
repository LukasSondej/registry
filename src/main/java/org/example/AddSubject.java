package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddSubject extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    JFrame frame = new JFrame();

    JTextField subjesctte = new JTextField();
    JLabel subna = new JLabel();
    JButton agree = new JButton();

    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    AddSubject() {

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setBounds(200, 50, 1014, 597);
        frame.setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        frame.setVisible(true);
        contentPane.setVisible(true);
        frame.setSize(600, 600);

        frame.add(contentPane);

        JLabel lblNewUserRegister = new JLabel("Add subject");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(200, 52, 325, 50);
        contentPane.add(lblNewUserRegister);




        JLabel subna = new JLabel("type");
        subna.setFont(new Font("Tahoma", Font.PLAIN, 35));
        subna.setBounds(85, 200, 139, 40);
        contentPane.add(subna);

        subjesctte = new JTextField();
        subjesctte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        subjesctte.setBounds(170, 200, 270, 50);
        contentPane.add(subjesctte);
        subjesctte.setColumns(10);

        agree = new JButton("Agree");
        agree.setFont(new Font("Tahoma", Font.PLAIN, 22));
        agree.setBounds(250, 300, 100, 50);
        contentPane.add(agree);

        agree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){

                subject();




            }
        });

    }


    public void subject(){



        String ma = subjesctte.getText().toString();



        try {
            Connection con = getConnect();

            String querry = "INSERT INTO przedmioty (przedmiot) VALUES (?)";
            PreparedStatement stmt = con.prepareStatement(querry);
            stmt.setString(1, ma);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(agree, "Great, you added subject");
            con.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }






}


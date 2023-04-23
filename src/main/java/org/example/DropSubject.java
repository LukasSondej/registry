package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropSubject extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    JFrame frame = new JFrame();

    JTextField subjesctte = new JTextField();
    JLabel subna = new JLabel();
    JButton drop = new JButton();
    JButton reload = new JButton();
    JComboBox<String> box = new JComboBox<>();
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    DropSubject(){
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

        JLabel lblNewUserRegister = new JLabel("Drop subject");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(200, 52, 325, 50);
        contentPane.add(lblNewUserRegister);


        reload = new JButton("Reload");
        reload.setFont(new Font("Tahoma", Font.PLAIN, 22));
        reload.setBounds(100, 200, 150, 50);
        contentPane.add(reload);

        selecting();
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){

                selecting();




            }
        });




    }
    public void selecting() {

        drop = new JButton("Drop");
        drop.setFont(new Font("Tahoma", Font.PLAIN, 22));
        drop.setBounds(350, 310, 100, 50);
        contentPane.add(drop);


        try {

            Connection con = getConnect();

            String querry = "select przedmiot from przedmioty";
            PreparedStatement stmt = con.prepareStatement(querry);
            ResultSet rs = stmt.executeQuery();

            box.removeAllItems();
            while (rs.next()){
                box.addItem(rs.getString("przedmiot"));
            }
            box.setBounds(100, 300, 150, 60);
            contentPane.add(box);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        drop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){

                droping();




            }
        });




    }
    public void droping(){

        String w = box.getSelectedItem().toString();

        try {
            Connection con = getConnect();

            String querry = " DELETE FROM przedmioty WHERE przedmiot = ? ";
            PreparedStatement stmt = con.prepareStatement(querry);
            stmt.setString(1, w);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(drop, "Great, you drop subject");

            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}

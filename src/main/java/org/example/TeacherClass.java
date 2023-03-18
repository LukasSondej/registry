package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherClass extends JFrame {

    JFrame frame = new JFrame();
    JButton clas1 = new JButton();
    JButton clas2 = new JButton();
    JButton clas3 = new JButton();

    JButton setting = new JButton();
    JButton button = new JButton();
    JPanel panel = new JPanel();

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

 public TeacherClass(String thisemail) {

        ArrayList<String> lista = new ArrayList<>();



        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select klasa from klasy, nauczyciele where klasy.idklasy = nauczyciele.idklasy and nauczyciele.email = '" + thisemail + "'";

            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()) {
                lista.add(rs.getString("klasa"));
            }


        } catch (Exception e) {

        }

        for (int i = 0; i < lista.size(); i++) {
            String s = lista.get(i);
            System.out.println(s);
        }
        int i = 0;
        while (i < lista.size()) {

            button = new JButton(lista.get(i));
            button.setBounds(200, i * 100, 100, 100);
            panel.add(button);
            i++;

        }


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        panel.setLayout(null);
        panel.add(clas1);
        panel.add(clas2);
        panel.add(clas3);
        panel.add(setting);
        frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);


        clas1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w) {


            }
        });


    }






}

package org.example;
import com.mysql.cj.Query;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TeacherClass extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
public String przedmiot;
public String email;
public JButton changes;
Scrollbar scrollbar;
    JPanel panel;
    ArrayList<String> klasy;
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    public TeacherClass(String thisemail) {
        panel = new JPanel();
        panel.setLayout(null);

        klasy = new ArrayList<String>();


        try {

            Connection con = getConnect();
            String searchQuery2 = "Select email from admin where email=?";
            PreparedStatement searchPstmt2 = con.prepareStatement(searchQuery2);
            searchPstmt2.setString(1, thisemail);
            ResultSet searchResult2 = searchPstmt2.executeQuery();

            if (searchResult2.next()) {
                try {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select klasa from klasy");
                    while (rs.next()) {
                        klasy.add(rs.getString(1));
                    }
                    con.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
                changes = new JButton("changes");
                changes.setBounds(20, 20, 150, 30);
                panel.add(changes);
                changes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ew3) {
               Changes changes1 = new Changes(email);


                    }

                });

            } else {


                try {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select klasa from klasy, nauczyciele, nauczyciel_klasa where klasy.idklasy = nauczyciel_klasa.id_klasy and nauczyciele.idnauczyciela = nauczyciel_klasa.id_nauczyciela and nauczyciele.email = '" + thisemail + "'");
                    while (rs.next()) {
                        klasy.add(rs.getString(1));
                    }
                    con.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
                changes = new JButton("data_student");
                changes.setBounds(20, 20, 150, 30);
                panel.add(changes);
                changes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ew3) {
                        DataStudent student = new DataStudent(email);

                    }

                });


            }
        } catch (Exception e) {
            System.out.println(e);
        }


        for (int i = 0; i < klasy.size(); i++) {
            JButton button = new JButton(klasy.get(i));
            button.addActionListener(this);
            button.setBounds(350, i * 100, 100, 100);
            panel.setPreferredSize(new Dimension(600,i*100));
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
setContentPane(scrollPane);



        setTitle("Klasy");
        setSize(700, 600);

        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        String klasa = button.getText();

        MainClas clas = new MainClas(klasa,email,przedmiot);

    }
    public void setprzedmiot(String przedmiot){
        this.przedmiot = przedmiot;


    }
    public void setemail(String email){
        this.email = email;


    }

}
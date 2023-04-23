package org.example;
import com.mysql.cj.Query;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ChoseClass extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
public String email;
JButton changes;
    public String przedmiot;
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
    public ChoseClass(String email) {




        klasy = new ArrayList<String>();




        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select klasa from klasy");
            while (rs.next()) {
                klasy.add(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        panel = new JPanel();
        
        for(int i = 0;i<klasy.size();i++){
            JButton button = new JButton(klasy.get(i));
            button.addActionListener(this);
            button.setBounds(350,i*100,100,100);
            panel.add(button);
        }

        
        panel.setLayout(null);
        add(panel);
        setTitle("Klasy");
        setSize(700, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        changes = new JButton("changes");
        changes.setBounds(20, 20, 150, 30);
        panel.add(changes);
        changes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew3) {
                System.out.println("jsldgjls");

            }

        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        String klasa = button.getText();
setEmail(email);
        MainClas clas = new MainClas(klasa, email, przedmiot);

    }
    public void setEmail(String email){
        this.email = email;


    }

}
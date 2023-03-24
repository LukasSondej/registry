package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.awt.BorderLayout.*;

public class TabTEa extends JFrame {
JPanel panel2 = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    JComboBox<String> box2 = new JComboBox<>();
    JButton buttonadd = new JButton();
    JPanel panel = new JPanel();
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    public TabTEa() {
        super("Teachers Table");

        String[] columnNames = {"Name", "Email","Klasa"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
allclass();
       buttonadd = new JButton("add clas");
        buttonadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){
                if(w.getSource()==buttonadd){

                    Login1 login1 = new Login1();

                }

            }
        });
        table.setPreferredScrollableViewportSize(new Dimension(200,100));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        add(scrollPane);

        scrollPane.setBounds(50,50,100,100);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTeachers();
    }

    private void loadTeachers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imie, email, klasa FROM nauczyciele, klasy where nauczyciele.idklasy = klasy.idklasy");
            while (rs.next()) {

                String name = rs.getString("imie");
                String email = rs.getString("email");
                String kl = rs.getString("klasa");

                Object[] row = {name, email,kl};
                model.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void changeclas(){





    }



    public static void main(String[] args) {
        new TabTEa();
    }
    public void allclass(){



        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select klasa from klasy";
            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()){
                box2.addItem(rs.getString("klasa"));
            }
            box2.setBounds(500, 50, 50, 20);
            panel2.add(box2);

            setVisible(true);
        }
        catch (Exception e){

        }



    }
};;;;;;;;;;;;;;

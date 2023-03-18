package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddingClassTeacher extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> box2 = new JComboBox<>();
    private JButton buttonadd = new JButton("Add class");

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public AddingClassTeacher() {
        String[] columnNames = {"Name", "Email", "Klasa"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setPreferredScrollableViewportSize(new Dimension(400,300));
        JPanel mainPanel = new JPanel(null);

        // setBounds(x, y, width, height)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 380, 540);
        mainPanel.add(scrollPane);

        box2.setBounds(450, 100, 100, 30);
        mainPanel.add(box2);

        buttonadd.setBounds(450, 300, 100, 30);
        mainPanel.add(buttonadd);



        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTeachers();
        loadClasses();
    }

    public void loadTeachers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imie, email, klasa FROM nauczyciele, klasy where nauczyciele.idklasy = klasy.idklasy");
            while (rs.next()) {
                String name = rs.getString("imie");
                String email = rs.getString("email");
                String kl = rs.getString("klasa");
                Object[] row = {name, email, kl};
                model.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void loadClasses() {
        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select klasa from klasy";
            ResultSet rs = stmt.executeQuery(querry);
            while (rs.next()) {
                box2.addItem(rs.getString("klasa"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
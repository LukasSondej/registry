package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TabTEa extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public TabTEa() {
        super("Teachers Table");

        String[] columnNames = {"Name", "Email","Clas"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTeachers();
    }

    private void loadTeachers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imie, email FROM nauczyciele");
            while (rs.next()) {

                String name = rs.getString("name");
                String email = rs.getString("email");
                Object[] row = {name, email};
                model.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void removeSelectedRows() {
        int[] rows = table.getSelectedRows();
        for (int i = rows.length - 1; i >= 0; i--) {
            String email = (String) table.getValueAt(rows[i], 0);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM naczyciele WHERE email = ?");
                stmt.setString(1, email);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            model.removeRow(rows[i]);
        }
    }

    public static void main(String[] args) {
        new TabTEa();
    }
};;;;;;;;;;;;;;

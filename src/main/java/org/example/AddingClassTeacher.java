package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        buttonadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){

addclastoteacher();

            }
        });


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
    public void addclastoteacher() {
        int[] selectedrows = table.getSelectedRows();
        if(selectedrows.length == 0) {
            JOptionPane.showMessageDialog(buttonadd, "Select teacher");
        }
            else {
                try{
                    String klasawybrana = box2.getSelectedItem().toString();
                   int rekordwybrany = table.getSelectedRow();
                  String clas = table.getValueAt(rekordwybrany, 2).toString();
                   if(rekordwybrany != -1){

                       for(int i = 0;i<table.getRowCount();i++){
                           if(i == rekordwybrany){
                               continue;
                           }
                           Object klasaznaleziona = table.getValueAt(i,2);
                            if(klasaznaleziona.toString().equals(clas)){
                                System.out.println("rekord"+i+"ta sama");
                            }

                       }
                   }




                try {
                    Connection con = getConnect();
                    int selectedRow = table.getSelectedRow();
                    String imieNauczyciela = table.getValueAt(selectedRow, 0).toString();
                    String emailNauczyciela = table.getValueAt(selectedRow, 1).toString();
                    String klasa = table.getValueAt(selectedRow, 2).toString();

                    String query = "SELECT idklasy FROM nauczyciele WHERE email = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, emailNauczyciela);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        try {
                            String nazwaklasy = box2.getSelectedItem().toString();
                            String idKlasy = resultSet.getString("idklasy");
                            String updateQuery = "UPDATE klasy SET klasa = ? WHERE idklasy = ?";
                            PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                            updateStatement.setString(1, nazwaklasy);
                            updateStatement.setString(2, idKlasy);
                            updateStatement.executeUpdate();


                        } catch (Exception ex1) {
                            JOptionPane.showMessageDialog(null, ex1.getMessage());
                        }


                    }
                }catch (Exception ex2) {
                    JOptionPane.showMessageDialog(null, ex2.getMessage());
                }


                }catch (Exception ex3) {
                    JOptionPane.showMessageDialog(null, ex3.getMessage());
                }


        }
        }
    }


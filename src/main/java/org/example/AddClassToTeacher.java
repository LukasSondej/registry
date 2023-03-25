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
public class AddClassToTeacher extends JFrame {
    JButton agreebutton,dropclasofteacher, addbutton;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> box2 = new JComboBox<>();
    private JButton buttonadd = new JButton("Add class");
    JComboBox<String> box = new JComboBox<>();
    JPanel mainPanel = new JPanel(null);
    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public AddClassToTeacher() {



        String[] columnNames = {"Name", "Email"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.setPreferredScrollableViewportSize(new Dimension(400, 300));


        // setBounds(x, y, width, height)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 380, 540);
        mainPanel.add(scrollPane);


        agreebutton = new JButton("submit");
        agreebutton.setBounds(500,20,150,20);
        mainPanel.add(agreebutton);



        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        classesad();
actionading();

        agreebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){

classes();


            }
        });

        loadTeachers();

    }
    public void loadTeachers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imie, email FROM nauczyciele");
            while (rs.next()) {
                String name = rs.getString("imie");
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


    public void classes() {
        try {
            int rekordwybrany = table.getSelectedRow();
            String teacher = table.getValueAt(rekordwybrany, 1).toString();
            Connection con = getConnect();

            String querry = "SELECT klasa FROM klasy, nauczyciele, nauczyciel_klasa WHERE klasy.idklasy = nauczyciel_klasa.id_klasy AND nauczyciele.idnauczyciela = nauczyciel_klasa.id_nauczyciela AND nauczyciele.email = ?";

            PreparedStatement pstmt = con.prepareStatement(querry);
            pstmt.setString(1, teacher);
            ResultSet rs = pstmt.executeQuery();

            box.removeAllItems();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Nie znaleziono żadnej klasy dla wybranego nauczyciela. Aby dodać klasę ponizej morzesz doadac do nauczyciela klase.");
            } else {
                do {
                    box.addItem(rs.getString("klasa"));
                } while (rs.next());

                box.setBounds(530, 50, 100, 30);
                mainPanel.add(box);
                setVisible(true);
            }
            dropclas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropclas() {

        dropclasofteacher = new JButton("drop_clas");
        dropclasofteacher.setBounds(640,50,100,30);
        mainPanel.add(dropclasofteacher);
        dropclasofteacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w){





        String na = box.getSelectedItem().toString();
        int rekordwybrany = table.getSelectedRow();
        String teacher = table.getValueAt(rekordwybrany, 1).toString();


        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "DELETE FROM nauczyciel_klasa WHERE id_nauczyciela IN (SELECT idnauczyciela FROM nauczyciele WHERE email = '" +teacher + "') AND id_klasy IN ( SELECT idklasy FROM klasy WHERE klasa = '" + na + "')";
            stmt.executeUpdate(querry);
            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
            }
        });
    }



    public void classesad() {
        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select klasa from klasy";
            ResultSet rs = stmt.executeQuery(querry);
            box2.removeAllItems();
            while (rs.next()) {
                box2.addItem(rs.getString("klasa"));
            }
            box2.setBounds(450, 300, 100, 30);
            mainPanel.add(box2);
            setVisible(true);
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void actionading() {
        addbutton = new JButton("submit");
        addbutton.setBounds(570, 300, 100, 30);
        mainPanel.add(addbutton);
        boolean warunek = false;
        addbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {





        int rekordwybrany = table.getSelectedRow();
        String teacher = table.getValueAt(rekordwybrany, 1).toString();

            String na6 = box2.getSelectedItem().toString();
                for(int i =0; i<box.getItemCount();i++)
                {
                    if(box.getItemAt(i).toLowerCase().contains(na6)){
                        JOptionPane.showMessageDialog(addbutton, "The teacher have this clas" );
                           return;
                    }
                }

            try {




                Connection con = getConnect();
                Statement stmt = con.createStatement();
                String querry = "INSERT INTO nauczyciel_klasa (id_klasy, id_nauczyciela) SELECT klasy.idklasy, nauczyciele.idnauczyciela FROM klasy,nauczyciele WHERE klasy.klasa = '" + na6 + "' AND nauczyciele.email = '" + teacher + "' ";
                stmt.executeUpdate(querry);

                con.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            }

        });
        }
        public void Sprwdzklase(){




        }




}

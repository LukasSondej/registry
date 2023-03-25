package org.example;
import com.mysql.cj.Query;

import java.sql.*;
import java.sql.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainClas extends JFrame  {
    private JPanel contentPane;
    JFrame frame1;
    JLabel labelname;
    JComboBox lista,lista2,lista3,lista4;
    JButton agreebutton, addmarks, dropmarks,updatemark;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;
    String sql;
    int ids2;


    JLabel subjects;

    JLabel grad;

    String na;
    String na2;
    private JTextField marksad;
    JComboBox<String> box = new JComboBox<>();
    JComboBox<String> box2 = new JComboBox<>();
    JComboBox<String> box3 = new JComboBox<>();
    JTextField newgrade = new JTextField();
    JTextField updatemarktext = new JTextField();
    private JComboBox comboBoxname;
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary2", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }

    MainClas(){

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        labelname = new JLabel("Select name");
        labelname.setBounds(125, 20, 75, 20);
        subjects= new JLabel("subject");
        subjects.setBounds(500, 20, 120, 20);
        agreebutton = new JButton("submit");
        agreebutton.setBounds(315,50,150,20);
        setTitle("CLASS 1");
        setLayout(null);
        setVisible(true);
        setSize(950,700);

        add(labelname);
        add(subjects);
        add(agreebutton);

        name();
        subjectchose();
        agreebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                grades();





            }

        });



    }
    public void name() {




        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select imie from uczniowie, nauczyciele, nauczyciel_klasa where uczniowie.id_klasy = nauczyciel_klasa.id_klasy and nauczyciele.idnauczyciela = nauczyciel_klasa.id_nauczyciela and nauczyciele.email ";
            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()){
                box.addItem(rs.getString("student_name"));
            }
            box.setBounds(125, 50, 150, 20);
            add(box);
            setVisible(true);
        }
        catch (Exception e){

        }


    }

    public void subjectchose(){

        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select type from school_subject";
            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()){
                box2.addItem(rs.getString("type"));
            }
            box2.setBounds(500, 50, 150, 20);
            add(box2);
            setVisible(true);
        }
        catch (Exception e){

        }


    }


    public void grades() {



        String ma = newgrade.getText();
        addmarks = new JButton("add mark");
        addmarks.setBounds(660, 100, 150, 30);

        dropmarks = new JButton("drop mark");
        dropmarks.setBounds(660, 135, 150, 30);

        updatemark = new JButton("update mark");
        updatemark.setBounds(660, 170, 150, 30);

        updatemarktext.setFont(new Font("Tahoma", Font.PLAIN, 25));
        updatemarktext.setBounds(820, 170, 30, 30);


        newgrade.setFont(new Font("Tahoma", Font.PLAIN, 25));
        newgrade.setBounds(820, 100, 30, 30);

        grad = new JLabel("MARKS");
        grad.setBounds(360, 100, 120, 20);
        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        try {

            Connection con = getConnect();
            Statement stmt = con.createStatement();

            String querry = "select mark from marks,class_1,school_subject where student_name = '" + na5 + "' and type ='"+na6+"' AND marks.idstudent = class_1.idstudent AND marks.idsubject = school_subject.idsubject";
            ResultSet rs = stmt.executeQuery(querry);
            box3.removeAllItems();
            while (rs.next()) {
                box3.addItem(rs.getString("mark"));

            }
            box3.setBounds(315, 120, 150, 20);
            add(box3);
            add(addmarks);
            add(newgrade);
            add(dropmarks);
            add(updatemark);
            add(updatemarktext);
            setVisible(true);


        } catch (Exception e) {
        }


        addmarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew) {


                addmark();






            }

        });

        dropmarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {


                dropmark();






            }

        });
        updatemark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew3) {


                updatepmark();






            }

        });



    }


    public void addmark(){


        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        String ma = newgrade.getText();

        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "INSERT INTO marks (idstudent, idsubject, mark)SELECT class_1.idstudent, school_subject.idsubject, '" + ma + "' FROM school_subject,class_1 WHERE class_1.student_name = '" + na5 + "' AND school_subject.type = '" + na6 + "' ";
            stmt.executeUpdate(querry);

            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void dropmark(){


        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        String na8 = box3.getSelectedItem().toString();

        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "DELETE FROM marks WHERE idstudent IN (SELECT idstudent FROM class_1 WHERE student_name = '" + na5 + "') AND idsubject IN (  SELECT idsubject FROM school_subject WHERE type = '" + na6 + "') AND marks.mark = '" + na8 + "' ";
            stmt.executeUpdate(querry);
            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    public void updatepmark(){


        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        String na8 = box3.getSelectedItem().toString();
        String ma2 = updatemarktext.getText();

        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "UPDATE marks SET mark = '" + ma2 + "' WHERE idstudent IN (SELECT idstudent FROM class_1 WHERE student_name = '" + na5 + "') AND idsubject IN (  SELECT idsubject FROM school_subject WHERE type = '" + na6 + "') AND marks.mark = '" + na8 + "' ";
            stmt.executeUpdate(querry);
            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}


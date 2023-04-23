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
public int idoceny;
    String na;
    String na2;
    private JTextField marksad;
    JComboBox<String> box = new JComboBox<>();
    JComboBox<String> box2 = new JComboBox<>();
    JComboBox<String> box3 = new JComboBox<>();
    JTextField newgrade = new JTextField();
    JTextField updatemarktext = new JTextField();
    private JComboBox comboBoxname;
    public String przedmiot;
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }

   MainClas(String klasa, String email, String przedmiot){

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        labelname = new JLabel("Select name");
        labelname.setBounds(125, 20, 75, 20);
        subjects= new JLabel("subject");
        subjects.setBounds(500, 20, 120, 20);
        agreebutton = new JButton("submit");
        agreebutton.setBounds(315,50,150,20);
        setTitle("Diary");
        setLayout(null);
        setVisible(true);
        setSize(950,700);

        add(labelname);
        add(subjects);
        add(agreebutton);

        name(klasa);
        subjectchose(przedmiot, email);
        agreebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                grades();





            }

        });



    }
    public void name(String klasa) {




        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select imie from uczniowie, uczen_klasa, klasy where uczniowie.iducznia = uczen_klasa.id_ucznia and klasy.idklasy = uczen_klasa.id_klasy and klasy.klasa = '" + klasa + "'";
            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()){
                box.addItem(rs.getString("imie"));
            }
            box.setBounds(125, 50, 150, 20);
            add(box);
            setVisible(true);
        }
        catch (Exception e){

        }


    }

    public void subjectchose(String przedmiot, String email){

        try {
            Connection con = getConnect();
            String searchQuery = "Select email from admin where email = '" + email + "'";
            PreparedStatement searchPstmt = con.prepareStatement(searchQuery);
            ResultSet searchResult = searchPstmt.executeQuery();
if(searchResult.next()){
    String querry = "select przedmiot from przedmioty";
    Statement stmt = con.createStatement();
    ResultSet rs2 = stmt.executeQuery(querry);
            while (rs2.next()){
                box2.addItem(rs2.getString("przedmiot"));
            }
            box2.setBounds(500, 50, 150, 20);
            add(box2);
            setVisible(true);

            } else
        try {

            Statement stmt = con.createStatement();
            String querry = "select przedmiot from przedmioty where przedmiot = '" + przedmiot+ "'";
            ResultSet rs = stmt.executeQuery(querry);

            while (rs.next()){
                box2.addItem(rs.getString("przedmiot"));
            }
            box2.setBounds(500, 50, 150, 20);
            add(box2);
            setVisible(true);
        }
        catch (Exception e){

        }

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

            String querry = "select mark from marks,uczniowie,przedmioty where uczniowie.imie = '" + na5 + "' and przedmioty.przedmiot ='"+na6+"' AND marks.id_student = uczniowie.iducznia AND marks.id_subject = przedmioty.idprzedmiotu";
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
            String querry = "INSERT INTO marks (id_student, id_subject, mark)SELECT uczniowie.iducznia, przedmioty.idprzedmiotu, '" + ma + "' FROM przedmioty, uczniowie WHERE uczniowie.imie = '" + na5 + "' AND przedmioty.przedmiot = '" + na6 + "' ";
            stmt.executeUpdate(querry);

            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        grades();

    }

    public void dropmark(){


        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        String na8 = box3.getSelectedItem().toString();

        try {
            Connection con = getConnect();

            String querry = "Select idmarks FROM marks WHERE id_student IN (SELECT iducznia FROM uczniowie WHERE imie = ?) AND id_subject IN (  SELECT idprzedmiotu FROM przedmioty WHERE przedmiot = ?) AND marks.mark = ?";
            PreparedStatement searchPstmt8 = con.prepareStatement(querry);
            searchPstmt8.setString(1, na5);
            searchPstmt8.setString(2, na6);
            searchPstmt8.setString(3, na8);
            ResultSet searchResult8 = searchPstmt8.executeQuery();
            if (searchResult8.next()) {
                idoceny = searchResult8.getInt("idmarks");

            }

            try{



            Statement stmt2 = con.createStatement();
            String querry2 = "DELETE FROM marks WHERE idmarks = '" + idoceny + "' ";
            stmt2.executeUpdate(querry2);
            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        grades();
    }


    public void updatepmark(){


        String na5 = box.getSelectedItem().toString();
        String na6 = box2.getSelectedItem().toString();
        String na8 = box3.getSelectedItem().toString();
        String ma2 = updatemarktext.getText();

        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "UPDATE marks SET mark = '" + ma2 + "' WHERE id_student IN (SELECT iducznia FROM uczniowie WHERE imie = '" + na5 + "') AND id_subject IN (  SELECT idprzedmiotu FROM przedmioty WHERE przedmiot = '" + na6 + "') AND marks.mark = '" + na8 + "' ";
            stmt.executeUpdate(querry);
            con.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
grades();
    }

}


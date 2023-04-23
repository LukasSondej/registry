package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropStudent extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    JFrame frame = new JFrame();
    JButton submit = new JButton();
    JButton del = new JButton();
public int idrodzic1;
public int idrodzic2;
    JLabel title = new JLabel();
    JComboBox<String> box2 = new JComboBox<>();
    JComboBox<String> box = new JComboBox<>();
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }

    DropStudent() {
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setBounds(200, 50, 1014, 597);
        frame.setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        frame.setVisible(true);
        contentPane.setVisible(true);
        frame.setSize(600, 600);

        frame.add(contentPane);
        JLabel title = new JLabel("Delete student");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        title.setBounds(170, 50, 325, 50);
        contentPane.add(title);

classes();

        submit = new JButton("submit");
        submit.setBounds(315,150,150,60);
        contentPane.add(submit);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {



                drop();


            }

        });



    }

    public void drop(){


        del = new JButton("Delete selected");
        del.setBounds(300,300,150,60);
        contentPane.add(del);




        String classes3 = box.getSelectedItem().toString();


        try {
            Connection con = getConnect();

            String querry = "select imie from uczniowie, uczen_klasa, klasy where uczniowie.iducznia = uczen_klasa.id_ucznia and klasy.idklasy = uczen_klasa.id_klasy and klasy.klasa = '" + classes3 + "'";
            PreparedStatement stmt = con.prepareStatement(querry);
            ResultSet rs = stmt.executeQuery();

            box2.removeAllItems();
            while (rs.next()){
                box2.addItem(rs.getString("imie"));
            }
            box2.setBounds(100, 300, 150, 60);
            contentPane.add(box2);
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

                droping();


            }

        });



    }

    public void droping(){

        String one = box.getSelectedItem().toString();
        String two = box2.getSelectedItem().toString();




        try{
            Connection con = getConnect();
            String searchQuery2 = "SELECT id_rodzic FROM uczen_rodzic WHERE id_uczen IN (SELECT iducznia FROM uczniowie WHERE imie = ?)";
            PreparedStatement searchPstmt2 = con.prepareStatement(searchQuery2);
            searchPstmt2.setString(1, two);
            ResultSet searchResult2 = searchPstmt2.executeQuery();
            if (searchResult2.next()){
                idrodzic1 = searchResult2.getInt("id_rodzic");
            }
            idrodzic2 = idrodzic1 +1;


            try{


                String querry9 = " DELETE FROM rodzic WHERE idrodzic  = ? ";
                PreparedStatement stmt9 = con.prepareStatement(querry9);
                stmt9.setInt(1,idrodzic1);

                stmt9.executeUpdate();
try{



    String querry10 = " DELETE FROM rodzic WHERE idrodzic  = ? ";
    PreparedStatement stmt10 = con.prepareStatement(querry10);
    stmt10.setInt(1,idrodzic2);

    stmt10.executeUpdate();


        try{


            String querry3 = " DELETE FROM uczen_rodzic WHERE id_uczen IN (SELECT iducznia FROM uczniowie WHERE imie = ?) ";
            PreparedStatement stmt3 = con.prepareStatement(querry3);
            stmt3.setString(1,two);

            stmt3.executeUpdate();

            try{


                String querry2 = " DELETE FROM uczen_klasa WHERE id_ucznia IN (SELECT iducznia FROM uczniowie WHERE imie = ?) AND id_klasy IN (  SELECT idklasy FROM klasy WHERE klasa = ?)";
                PreparedStatement stmt2 = con.prepareStatement(querry2);
                stmt2.setString(1,two);
                stmt2.setString(2,one);
                stmt2.executeUpdate();


                try {


                    String querry = " DELETE FROM uczniowie WHERE imie = ? ";
                    PreparedStatement stmt = con.prepareStatement(querry);
                    stmt.setString(1,two);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(del, "Great, you delete student");

        } catch (Exception exception) {
            exception.printStackTrace();
        }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    public void classes() {
        try {
            Connection con = getConnect();
            Statement stmt = con.createStatement();
            String querry = "select klasa from klasy";
            ResultSet rs = stmt.executeQuery(querry);
            box.removeAllItems();
            while (rs.next()) {
                box.addItem(rs.getString("klasa"));
            }
            box.setBounds(100, 150, 150, 60);
            contentPane.add(box);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

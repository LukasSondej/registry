package org.example;
import com.mysql.cj.Query;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataStudent extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField student_name_and_lastname;
    private JTextField student_email;

    private JTextField student_pesel;
    private JTextField number;
    private JButton btnNewButton;

    private JButton submit, savechanges;
    private JTextField par1;
    public String email;
    public int id;
    public int idrodzica;
    public int idrodzica2;
    private JTextField par2;
    private JTextField nameTextField, peselTextField, numberTextField, emailTextField, parent1TextField, parent2TextField, parent2numbertext, parent1numbertext;
    JComboBox<String> box = new JComboBox<>();
    JComboBox<String> box2 = new JComboBox<>();

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public DataStudent(String email) {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 50, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);
        contentPane.setVisible(true);
        setSize(1000, 700);


        JLabel lblNewUserRegister = new JLabel("Data student");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewUserRegister.setBounds(630, 40, 350, 30);
        contentPane.add(lblNewUserRegister);

        JLabel nameLabel = new JLabel("Name and lastName:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(58, 150, 180, 43);
        contentPane.add(nameLabel);

        nameTextField = new JTextField(20);
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        nameTextField.setBounds(270, 150, 270, 50);
        contentPane.add(nameTextField);


        JLabel peselLabel = new JLabel("pesel:");
        peselLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        peselLabel.setBounds(110, 220, 124, 36);
        contentPane.add(peselLabel);

        peselTextField = new JTextField(20);
        peselTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        peselTextField.setBounds(270, 220, 270, 50);
        contentPane.add(peselTextField);


        JLabel emailLabel = new JLabel("email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emailLabel.setBounds(110, 290, 139, 26);
        contentPane.add(emailLabel);

        emailTextField = new JTextField(20);
        emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        emailTextField.setBounds(270, 290, 270, 50);
        contentPane.add(emailTextField);

        JLabel numberLabel = new JLabel("number:");
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberLabel.setBounds(580, 150, 140, 26);
        contentPane.add(numberLabel);

        numberTextField = new JTextField(20);
        numberTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        numberTextField.setBounds(700, 150, 270, 50);
        contentPane.add(numberTextField);

        JLabel parent1Label = new JLabel("parent1");
        parent1Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent1Label.setBounds(600, 220, 124, 36);
        contentPane.add(parent1Label);

        parent1TextField = new JTextField(20);
        parent1TextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        parent1TextField.setBounds(700, 220, 270, 50);
        contentPane.add(parent1TextField);


        JLabel parent2Label = new JLabel("parent2");
        parent2Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent2Label.setBounds(600, 290, 124, 36);
        contentPane.add(parent2Label);

        parent2TextField = new JTextField(20);
        parent2TextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        parent2TextField.setBounds(700, 290, 270, 50);
        contentPane.add(parent2TextField);


        JLabel parent1numberlabel = new JLabel("par_number1");
        parent1numberlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent1numberlabel.setBounds(100, 360, 124, 36);
        contentPane.add(parent1numberlabel);

        parent1numbertext = new JTextField(20);
        parent1numbertext.setFont(new Font("Tahoma", Font.PLAIN, 32));
        parent1numbertext.setBounds(270, 360, 270, 50);
        contentPane.add(parent1numbertext);


        JLabel parent2numberlabel = new JLabel("par_number2");
        parent2numberlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent2numberlabel.setBounds(590, 360, 124, 36);
        contentPane.add(parent2numberlabel);

        parent2numbertext = new JTextField(20);
        parent2numbertext.setFont(new Font("Tahoma", Font.PLAIN, 32));
        parent2numbertext.setBounds(700, 360, 270, 50);
        contentPane.add(parent2numbertext);


        submit = new JButton("submit");
        submit.setBounds(400, 20, 150, 60);
        contentPane.add(submit);

        savechanges = new JButton("savechanges");
        savechanges.setBounds(400, 500, 150, 60);
        contentPane.add(savechanges);
        classes(email);

        savechanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

                save(email);
            }

        });

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

                maistud();
            }

        });


        box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent w) {

                students();


            }
        });

    }

    public void maistud() {


        String one = box.getSelectedItem().toString();
        String two = box2.getSelectedItem().toString();


        try {
            Connection con = getConnect();
            String querry2 = "select iducznia from uczniowie where imie = '" + two + "'";
            PreparedStatement stmt3 = con.prepareStatement(querry2);
            ResultSet rs3 = stmt3.executeQuery();
            if (rs3.next()) {
                id = rs3.getInt("iducznia");
            }


            try {


                String querry = " Select * FROM uczniowie WHERE iducznia = ? ";
                PreparedStatement stmt2 = con.prepareStatement(querry);
                stmt2.setInt(1, id);
                ResultSet rs = stmt2.executeQuery();
                String def = "";
                nameTextField.setText(def);
                peselTextField.setText(def);
                emailTextField.setText(def);
                numberTextField.setText(def);
                while (rs.next()) {
                    nameTextField.setText(rs.getString("imie"));
                    peselTextField.setText(rs.getString("pesel"));
                    emailTextField.setText(rs.getString("email"));
                    numberTextField.setText(rs.getString("telefon"));

                }


                try {


                    String querry3 = "select id_rodzic from uczen_rodzic where id_uczen = '" + id + "'";
                    PreparedStatement stmt4 = con.prepareStatement(querry3);
                    ResultSet rs4 = stmt4.executeQuery();
                    if (rs4.next()) {
                        idrodzica = rs4.getInt("id_rodzic");

                    }
                    idrodzica2 = idrodzica + 1;

                    try {


                        String querry5 = " Select * FROM rodzic WHERE idrodzic = '" + idrodzica + "' ";
                        PreparedStatement stmt5 = con.prepareStatement(querry5);
                        ResultSet rs5 = stmt5.executeQuery();
                        String def2 = "";
                        parent1TextField.setText(def2);
                        parent1numbertext.setText(def2);

                        while (rs5.next()) {
                            parent1TextField.setText(rs5.getString("imie"));
                            parent1numbertext.setText(rs5.getString("telefon"));

                        }

                        try {

                            String querry6 = " Select * FROM rodzic WHERE idrodzic = '" + idrodzica2 + "' ";
                            PreparedStatement stmt6 = con.prepareStatement(querry6);
                            ResultSet rs6 = stmt6.executeQuery();
                            String def3 = "";
                            parent2TextField.setText(def3);
                            parent2numbertext.setText(def3);

                            while (rs6.next()) {
                                parent2TextField.setText(rs6.getString("imie"));
                                parent2numbertext.setText(rs6.getString("telefon"));

                            }

                            con.close();


                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
        } catch (SQLException ex2) {
            ex2.printStackTrace();
        }
    }


    public void students() {


        String classes3 = box.getSelectedItem().toString();


        try {


            Connection con = getConnect();


            String querry = "Select idklasy FROM klasy WHERE klasa = '" + classes3 + "'";
            PreparedStatement stmt = con.prepareStatement(querry);
            ResultSet rs = stmt.executeQuery();
            int id = 0;
            box2.removeAllItems();
            while (rs.next()) {
                id = rs.getInt("idklasy");
            }
try{
    String querry3 = "select imie from uczniowie, uczen_klasa, klasy where uczniowie.iducznia = uczen_klasa.id_ucznia and klasy.idklasy = uczen_klasa.id_klasy and klasy.idklasy = '" + id + "'";
    PreparedStatement stmt3 = con.prepareStatement(querry3);
    ResultSet rs3 = stmt3.executeQuery();


   if (rs3.next()) {

       try {


           String querry2 = "select imie from uczniowie, uczen_klasa, klasy where uczniowie.iducznia = uczen_klasa.id_ucznia and klasy.idklasy = uczen_klasa.id_klasy and klasy.idklasy = '" + id + "'";
           PreparedStatement stmt2 = con.prepareStatement(querry2);
           ResultSet rs2 = stmt2.executeQuery();

           box2.removeAllItems();
           while (rs2.next()) {
               box2.addItem(rs2.getString("imie"));
           }
           box2.setBounds(200, 20, 150, 60);
           contentPane.add(box2);
           con.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

    }
    else {
       JOptionPane.showMessageDialog(box, "this clas dont have students" );
   }

} catch (Exception e) {
    e.printStackTrace();
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void save(String email) {


        try {
            Connection con = getConnect();
            String searchQuery7 = "Select email from nauczyciele where email=? ";
            PreparedStatement searchPstmt7 = con.prepareStatement(searchQuery7);
            searchPstmt7.setString(1, email);

            ResultSet searchResult7 = searchPstmt7.executeQuery();

            if (searchResult7.next()) {
                JOptionPane.showMessageDialog(btnNewButton, "you are not authorized");

            } else {


                String two = box2.getSelectedItem().toString();

                String firstName = nameTextField.getText();
                String emailId = emailTextField.getText();

                String peselID = peselTextField.getText();

                String mobileNumber = numberTextField.getText();


                String pare1 = parent1TextField.getText();

                String pare2 = parent2TextField.getText();


                String num1 = parent1numbertext.getText();


                String num2 = parent2numbertext.getText();


                int pe = peselID.length();
                int len = mobileNumber.length();

                int name = firstName.length();
                int ema = emailId.length();
                int p1 = pare1.length();
                int p2 = pare2.length();
                int nu1 = num1.length();
                int nu2 = num2.length();
                if (name == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter name");
                } else if (ema == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter email");
                } else if (p1 == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter parent1");
                } else if (p2 == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter parent2");
                } else if (nu1 == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter number_parent1");
                } else if (nu2 == 0) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter number_parent2");
                } else if (len != 9) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter a 9 digit number");
                } else if (pe != 5) {
                    JOptionPane.showMessageDialog(btnNewButton, "enter a 5 digit pesel and you won't change it");
                } else


                    try {


                        String insertQuery = " UPDATE uczniowie set imie = ?, pesel = ?, email = ?, telefon = ? WHERE iducznia = '" + id + "' ";

                        PreparedStatement insertPstmt = con.prepareStatement(insertQuery);
                        insertPstmt.setString(1, firstName);
                        insertPstmt.setString(2, peselID);
                        insertPstmt.setString(3, emailId);
                        insertPstmt.setString(4, mobileNumber);

                        insertPstmt.executeUpdate();

                        try {


                            String insertQuery2 = " UPDATE rodzic set imie = ?, telefon = ? where idrodzic = '" + idrodzica + "' ";

                            PreparedStatement insertPstmt2 = con.prepareStatement(insertQuery2);
                            insertPstmt2.setString(1, pare1);
                            insertPstmt2.setString(2, num1);

                            insertPstmt2.executeUpdate();

                            try {


                                String insertQuery3 = " UPDATE rodzic set imie = ?, telefon = ? where idrodzic = '" + idrodzica2 + "' ";

                                PreparedStatement insertPstmt3 = con.prepareStatement(insertQuery3);
                                insertPstmt3.setString(1, pare2);
                                insertPstmt3.setString(2, num2);

                                insertPstmt3.executeUpdate();


                                JOptionPane.showMessageDialog(btnNewButton, "Great, you changed data student");

                                con.close();

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
            } catch(Exception exception){
                exception.printStackTrace();
            }
        }





    public void setemail(String email){
        this.email = email;


    }
public void classes(String email) {

    try {
        Connection con = getConnect();
        String searchQuery2 = "Select email from nauczyciele where email=? ";
        PreparedStatement searchPstmt2 = con.prepareStatement(searchQuery2);
        searchPstmt2.setString(1, email);

        ResultSet searchResult2 = searchPstmt2.executeQuery();

        if (searchResult2.next()) {

            try {


                Statement stmt = con.createStatement();


                String querry = "SELECT klasa FROM klasy, nauczyciele, nauczyciel_klasa WHERE klasy.idklasy = nauczyciel_klasa.id_klasy AND nauczyciele.idnauczyciela = nauczyciel_klasa.id_nauczyciela AND nauczyciele.email = '" + email + "'";
                ResultSet rs = stmt.executeQuery(querry);
                box.removeAllItems();
                while (rs.next()) {
                    box.addItem(rs.getString("klasa"));
                }
                box.setBounds(20, 20, 150, 60);
                contentPane.add(box);
                setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }


        } else {


            try {


                Statement stmt = con.createStatement();


                String querry = "SELECT klasa FROM klasy";
                ResultSet rs = stmt.executeQuery(querry);
                box.removeAllItems();
                while (rs.next()) {
                    box.addItem(rs.getString("klasa"));
                }
                box.setBounds(20, 20, 150, 60);
                contentPane.add(box);
                setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }


        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }
}
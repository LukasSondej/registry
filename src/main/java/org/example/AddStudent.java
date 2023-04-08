package org.example;
import com.mysql.cj.Query;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddStudent extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField student_name_and_lastname;
    private JTextField student_email;

    private JTextField student_pesel;
    private JTextField number;
    private JButton btnNewButton;

    private JButton resetButton;
    private JTextField par1;
    private JTextField par2;

    private JTextField numberparent1;
    private JTextField numberparent2;

    JComboBox<String> box = new JComboBox<>();

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    private JTextField student_name;
    public int idpar1;
    public int idpar2;
    public Connection getConnect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary3", "root", "Rafalek");
        }catch(SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE,null,ex);
        }
        return  con;
    }
    AddStudent() {


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 50, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);
        contentPane.setVisible(true);
        setSize(1000,700);


        JLabel lblNewUserRegister = new JLabel("Add student");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewUserRegister.setBounds(460, 10, 350, 30);
        contentPane.add(lblNewUserRegister);


        JLabel lblName = new JLabel("name and lastname");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 150, 180, 43);
        contentPane.add(lblName);

        student_name_and_lastname = new JTextField();
        student_name_and_lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        student_name_and_lastname.setBounds(270, 150, 270, 50);
        contentPane.add(student_name_and_lastname);
        student_name_and_lastname.setColumns(10);



        JLabel lblEmailAddress = new JLabel("Email");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(110, 220, 124, 36);
        contentPane.add(lblEmailAddress);

        student_email = new JTextField();
        student_email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        student_email.setBounds(270, 220, 270, 50);
        contentPane.add(student_email);
        student_email.setColumns(10);






        JLabel pesel = new JLabel("pesel");
        pesel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pesel.setBounds(110, 290, 139, 26);
        contentPane.add(pesel);

        student_pesel = new JTextField();
        student_pesel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        student_pesel.setBounds(270, 290, 270, 50);
        contentPane.add(student_pesel);
        student_pesel.setColumns(10);




        JLabel numberstudent = new JLabel("tel_number");
        numberstudent.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberstudent.setBounds(580, 150, 140, 26);
        contentPane.add(numberstudent);

        number = new JTextField();
        number.setFont(new Font("Tahoma", Font.PLAIN, 32));
        number.setBounds(700, 150, 270, 50);
        contentPane.add(number);


        JLabel parent1te = new JLabel("parent_1");
        parent1te.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent1te.setBounds(600, 220, 124, 36);
        contentPane.add(parent1te);

        par1 = new JTextField();
        par1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        par1.setBounds(700, 220, 270, 50);
        contentPane.add(par1);
        par1.setColumns(10);


        JLabel parent2te = new JLabel("parent_2");
        parent2te.setFont(new Font("Tahoma", Font.PLAIN, 20));
        parent2te.setBounds(600, 290, 124, 36);
        contentPane.add(parent2te);

        par2 = new JTextField();
        par2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        par2.setBounds(700, 290, 270, 50);
        contentPane.add(par2);
        par2.setColumns(10);



        JLabel chclases = new JLabel("chose_class");
        chclases.setFont(new Font("Tahoma", Font.PLAIN, 20));
        chclases.setBounds(430, 60, 124, 36);
        contentPane.add(chclases);


        JLabel numberpar1 = new JLabel("tel_number1");
        numberpar1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberpar1.setBounds(110, 360, 139, 26);
        contentPane.add(numberpar1);

        numberparent1 = new JTextField();
        numberparent1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        numberparent1.setBounds(270, 360, 270, 50);
        contentPane.add(numberparent1);


        JLabel numberpar2 = new JLabel("tel_number2");
        numberpar2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberpar2.setBounds(580, 360, 124, 36);
        contentPane.add(numberpar2);

        numberparent2 = new JTextField();
        numberparent2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        numberparent2.setBounds(700, 360, 270, 50);
        contentPane.add(numberparent2);


      classes();














        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        resetButton.setBounds(200, 550, 259, 50);
        contentPane.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {
                String def = "";
                student_name_and_lastname.setText(def);
                student_email.setText(def);
                student_pesel.setText(def);
                number.setText(def);
                par1.setText(def);
                par2.setText(def);





            }

        });


        btnNewButton = new JButton("Agree");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = student_name_and_lastname.getText();

                String emailId = student_email.getText();

                String peselID = student_pesel.getText();

                String mobileNumber = number.getText();


                String pare1 = par1.getText();

                String pare2 = par2.getText();

                String msg = "" + box.getSelectedItem().toString();;

                 String mobpar1 = numberparent1.getText();
                 String mobpar2 = numberparent2.getText();
                 int iduczen = 0;



                int pe = peselID.length();
                int len = mobileNumber.length();

                int name = firstName.length();
                int ema = emailId.length();
                int p1 = pare1.length();
                int p2 = pare2.length();


                String claschosing = box.getSelectedItem().toString();
                msg += " \n";
                if(name==0){
                    JOptionPane.showMessageDialog(btnNewButton, "enter name" );
                }
                else
                if(ema==0){
                    JOptionPane.showMessageDialog(btnNewButton, "enter email" );
                }
                else
                if(p1==0){
                    JOptionPane.showMessageDialog(btnNewButton, "enter parent1" );
                }
                else
                if(p2==0){
                    JOptionPane.showMessageDialog(btnNewButton, "enter parent2" );
                }
                else


                if(len!=9){
                    JOptionPane.showMessageDialog(btnNewButton, "enter a 9 digit number" );
                }
                else
                if(pe!=5){
                    JOptionPane.showMessageDialog(btnNewButton, "enter a 5 digit pesel and you won't change it" );
                }
                else
                    try {
                        Connection con = getConnect();
                        String searchQuery = "SELECT pesel FROM uczniowie WHERE pesel = ?";
                        PreparedStatement searchPstmt = con.prepareStatement(searchQuery);
                        searchPstmt.setString(1, peselID);
                        ResultSet searchResult = searchPstmt.executeQuery();

                        if (searchResult.next()) {
                            JOptionPane.showMessageDialog(btnNewButton, "Student with pesel '" + peselID + "' already exists in this class");
                        } else {



                            try {
                                String idklas = "";
String klasaWybrana = box.getSelectedItem().toString();
                                String searchQuery2 = "SELECT idklasy FROM klasy WHERE klasa = ?";
                                PreparedStatement searchPstmt2 = con.prepareStatement(searchQuery2);
                                searchPstmt2.setString(1, klasaWybrana);
                                ResultSet searchResult2 = searchPstmt2.executeQuery();
if (searchResult2.next()){
    idklas = searchResult2.getString("idklasy");
}


try {

    String insertQuery4 = "INSERT INTO rodzic (imie, telefon) values (?, ?)";
    PreparedStatement insertPstmt4 = con.prepareStatement(insertQuery4);
    insertPstmt4.setString(1, pare1);
    insertPstmt4.setString(2, mobpar1);


    insertPstmt4.executeUpdate();
    try {

        String insertQuery5 = "INSERT INTO rodzic (imie, telefon) values (?, ?)";
        PreparedStatement insertPstmt5 = con.prepareStatement(insertQuery5);
        insertPstmt5.setString(1, pare2);
        insertPstmt5.setString(2, mobpar2);


        insertPstmt5.executeUpdate();









                                try {


                                String insertQuery = "INSERT INTO uczniowie (imie, pesel, email, telefon, id_klasy) values (?, ?, ?, ?, ?)";
                                PreparedStatement insertPstmt = con.prepareStatement(insertQuery);
                                insertPstmt.setString(1, firstName);
                                insertPstmt.setString(2, peselID);
                                insertPstmt.setString(3, emailId);
                                insertPstmt.setString(4, mobileNumber);
                                insertPstmt.setString(5, idklas);
                                insertPstmt.executeUpdate();
                                    con.close();
                                    try{
                                        int iducz = 0;
                                        Connection con1 = getConnect();

                                        String searchQuery3 = "SELECT iducznia FROM uczniowie WHERE pesel = ?";
                                        PreparedStatement searchPstmt3 = con1.prepareStatement(searchQuery3);
                                        searchPstmt3.setString(1, peselID);
                                        ResultSet searchResult3 = searchPstmt3.executeQuery();
                                        if (searchResult3.next()) {
                                            iducz = searchResult3.getInt("iducznia");

                                        }

                                        try{


    String searchQuery7 = "SELECT idrodzic FROM rodzic WHERE imie = ?";
    PreparedStatement searchPstmt7 = con1.prepareStatement(searchQuery7);
    searchPstmt7.setString(1, pare1);
    ResultSet searchResult7 = searchPstmt7.executeQuery();
                                            if (searchResult7.next()) {
                                                idpar1 = searchResult7.getInt("idrodzic");

                                            }
    try{


        String searchQuery8 = "SELECT idrodzic FROM rodzic WHERE imie = ?";
        PreparedStatement searchPstmt8 = con1.prepareStatement(searchQuery8);
        searchPstmt8.setString(1, pare2);
        ResultSet searchResult8 = searchPstmt8.executeQuery();
        if (searchResult8.next()) {
            idpar2 = searchResult8.getInt("idrodzic");

        }

        try{

       
    String insertQuery6 = "INSERT INTO uczen_rodzic (id_uczen, id_rodzic) values (?, ?)";
    PreparedStatement insertPstmt6 = con1.prepareStatement(insertQuery6);
    insertPstmt6.setInt(1, iducz);
    insertPstmt6.setInt(2, idpar1);


    insertPstmt6.executeUpdate();

    try {
        String insertQuery9 = "INSERT INTO uczen_rodzic (id_uczen, id_rodzic) values (?, ?)";
        PreparedStatement insertPstmt9 = con1.prepareStatement(insertQuery9);
        insertPstmt9.setInt(1, iducz);
        insertPstmt9.setInt(2, idpar2);


        insertPstmt9.executeUpdate();




                                JOptionPane.showMessageDialog(btnNewButton, "Great, you added student to '" + msg + "'");

                                con1.close();
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
                    }

                    catch(Exception exception){
                        exception.printStackTrace();
                    }


            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(500, 550, 259, 50);
        contentPane.add(btnNewButton);





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
            box.setBounds(550, 50, 150, 60);
            contentPane.add(box);
            setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }



}


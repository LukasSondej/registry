package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Changes extends JFrame{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public String email;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton addstudent;

    private JButton dropstudent;

    private JButton datastudent;

    private JButton dropsubject;
    private JButton addsubject;
    private JButton dataTeacher;
    private JButton teacher;
   public Changes(String email){


        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setBounds(200, 50, 1014, 597);
        frame.setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        frame.setVisible(true);
        contentPane.setVisible(true);
        frame.setSize(500,800);

        frame.add(contentPane);


        JLabel title = new JLabel("Changes");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        title.setBounds(180, 5, 350, 50);
        contentPane.add(title);

        addstudent = new JButton("add student");
        addstudent.setFont(new Font("Tahoma", Font.PLAIN, 22));
        addstudent.setBounds(120, 100, 250, 40);
        contentPane.add(addstudent);


        addstudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {
                AddStudent student = new AddStudent();





            }

        });
        dropstudent = new JButton("drop student");
        dropstudent.setFont(new Font("Tahoma", Font.PLAIN, 22));
        dropstudent.setBounds(120, 180, 250, 40);
        contentPane.add(dropstudent);
        dropstudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

DropStudent student = new DropStudent();




            }

        });
        datastudent = new JButton("change data student");
        datastudent.setFont(new Font("Tahoma", Font.PLAIN, 22));
        datastudent.setBounds(120, 260, 250, 40);
        contentPane.add(datastudent);
        datastudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

DataStudent student = new DataStudent(email);





            }

        });


        addsubject = new JButton("add subject");
        addsubject.setFont(new Font("Tahoma", Font.PLAIN, 22));
        addsubject.setBounds(120, 340, 250, 40);
        contentPane.add(addsubject);
        addsubject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

AddSubject subject = new AddSubject();



            }

        });


        dropsubject = new JButton("drop subject");
        dropsubject.setFont(new Font("Tahoma", Font.PLAIN, 22));
        dropsubject.setBounds(120, 420, 250, 40);
        contentPane.add(dropsubject);
        dropsubject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ew2) {

DropSubject subject = new DropSubject();



            }

        });

       teacher = new JButton("add teacher");
       teacher.setFont(new Font("Tahoma", Font.PLAIN, 22));
       teacher.setBounds(120, 520, 250, 40);
       contentPane.add(teacher);
       teacher.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ew2) {

Registration registration = new Registration();



           }

       });


       dataTeacher = new JButton("dataTeacher");
       dataTeacher.setFont(new Font("Tahoma", Font.PLAIN, 22));
       dataTeacher.setBounds(120, 620, 250, 40);
       contentPane.add(dataTeacher);
       dataTeacher.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ew2) {


AddClassToTeacher teacher1 = new AddClassToTeacher();


           }

       });

    }

    public void setemail(String email){
        this.email = email;


    }


}

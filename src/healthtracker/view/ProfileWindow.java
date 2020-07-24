//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ProfileWindow extends JFrame {

    //CONSTRUCTOR: USER'S PROFILE
    public ProfileWindow(StorageController s, User user) {
        JFrame profileFrame = new JFrame(UserController.getFirstName(user)
                + " " + UserController.getLastName(user));
        profileFrame.setSize(600, 200);
        JPanel panel = new JPanel();
        //Email
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(20, 20, 20, 10);
        panel.add(emailLabel);
        JTextField emailText = 
                new JTextField(UserController.getEmail(user), 10);
        emailText.setEditable(false);
        panel.add(emailText);

        //Username
        JLabel usernameLabel = new JLabel("Username: ");
        panel.add(usernameLabel);
        JTextField usernameText = 
                new JTextField(UserController.getUsername(user), 10);
        usernameText.setEditable(false);
        panel.add(usernameText);

        //First name
        JLabel firstNameLabel = new JLabel("First Name: ");
        panel.add(firstNameLabel);
        JTextField firstNameText = 
                new JTextField(UserController.getFirstName(user), 10);
        firstNameText.setEditable(false);
        panel.add(firstNameText);

        //Surname
        JLabel surnameLabel = new JLabel("Surname: ");
        panel.add(surnameLabel);
        JTextField surnameText = 
                new JTextField(UserController.getLastName(user), 10);
        surnameText.setEditable(false);
        panel.add(surnameText);

        //Weight
        JLabel weightLabel = new JLabel("Weight/KG: ");
        panel.add(weightLabel);
        JTextField weightText = 
                new JTextField(Integer.toString((int) UserController.
                        getWeight(user)), 5);
        weightText.setEditable(false);
        panel.add(weightText);

        //Height
        JLabel heightLabel = new JLabel("Height: ");
        panel.add(heightLabel);
        JTextField heightText = 
                new JTextField(Double.toString(UserController.
                        getHeight(user)), 5);
        heightText.setEditable(false);
        panel.add(heightText);

        //Current BMI
        JLabel bmiLabel = new JLabel("Current BMI: ");
        panel.add(bmiLabel);
        JTextField bmiText = 
                new JTextField((int) UserController.getBMI(user) + "");
        bmiText.setEditable(false);
        panel.add(bmiText);

        //Back button to go to the main page
        JButton backButton = new JButton("Home");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileFrame.dispose();
                new MainWindow(s, user);
            }
        });
        panel.add(backButton);

        profileFrame.add(panel);
        profileFrame.setPreferredSize(new Dimension(170, 340));
        profileFrame.pack();
        profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profileFrame.setVisible(true);
    }

    //------------------------------------------
    //CONSTRUCTOR: visiting other user's profile
    public ProfileWindow(StorageController s, User visiting, User user) {
        JFrame profileFrame = new JFrame(UserController.getFirstName(user) + 
                " " + UserController.getLastName(user));
        profileFrame.setSize(600, 200);
        JPanel panel = new JPanel();

        //Email
        JLabel emailLabel = new JLabel("Email: ");
        panel.add(emailLabel);
        JTextField emailText = 
                new JTextField(UserController.getEmail(user), 10);
        emailText.setEditable(false);
        panel.add(emailText);

        //Username
        JLabel usernameLabel = new JLabel("Username: ");
        panel.add(usernameLabel);
        JTextField usernameText = 
                new JTextField(UserController.getUsername(user), 10);
        usernameText.setEditable(false);
        panel.add(usernameText);

        //First name
        JLabel firstNameLabel = new JLabel("First Name: ");
        panel.add(firstNameLabel);
        JTextField firstNameText = 
                new JTextField(UserController.getFirstName(user), 10);
        firstNameText.setEditable(false);
        panel.add(firstNameText);

        //Surname
        JLabel surnameLabel = new JLabel("Surname: ");
        panel.add(surnameLabel);
        JTextField surnameText = 
                new JTextField(UserController.getLastName(user), 10);
        surnameText.setEditable(false);
        panel.add(surnameText);

        //Weight
        JLabel weightLabel = new JLabel("Weight/KG: ");
        panel.add(weightLabel);
        JTextField weightText = 
                new JTextField(Double.toString(UserController.
                        getWeight(user)), 4);
        weightText.setEditable(false);
        panel.add(weightText);

        //Height
        JLabel heightLabel = new JLabel("Height: ");
        panel.add(heightLabel);
        JTextField heightText = 
                new JTextField(Double.toString(UserController.
                        getHeight(user)), 5);
        heightText.setEditable(false);
        panel.add(heightText);

        //Current BMI
        JLabel bmiLabel = new JLabel("Current BMI: ");
        panel.add(bmiLabel);
        JTextField bmiText = 
                new JTextField((int) UserController.getBMI(user) + "", 5);
        bmiText.setEditable(false);
        panel.add(bmiText);

        //Back button to go to the main page
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileFrame.dispose();
                new MainWindow(s, visiting, user);
            }
        });
        panel.add(backButton);

        profileFrame.add(panel);
        profileFrame.pack();
        profileFrame.setPreferredSize(new Dimension(170, 340));
        profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profileFrame.setVisible(true);
    }

}

//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegistrationWindow extends JFrame {

    public RegistrationWindow(StorageController s) {
        //Start of user input
        final JFrame registrationFrame = new JFrame("Register");
        JPanel panel = new JPanel();
        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 0, 80, 20);
        panel.add(emailLabel);
        //Email text field
        final JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 0, 100, 25);
        panel.add(emailText);

        //Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, -100, 80, 25);
        panel.add(usernameLabel);
        //Username text field
        final JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100, -100, 165, 25);
        panel.add(usernameText);

        //First name
        JLabel firstNameLabel = new JLabel("First name:");
        firstNameLabel.setBounds(100, -200, 80, 25);
        panel.add(firstNameLabel);
        //First name text field
        final JTextField firstNameText = new JTextField(20);
        firstNameText.setBounds(100, -200, 165, 25);
        panel.add(firstNameText);

        //Surname
        JLabel surnameLabel = new JLabel("surname:");
        surnameLabel.setBounds(10, -300, 80, 25);
        panel.add(surnameLabel);
        //Surname text field
        final JTextField surnameText = new JTextField(20);
        surnameText.setBounds(100, -300, 165, 25);
        panel.add(surnameText);

        //Weight
        JLabel weightLabel = new JLabel("Weight/kg:");
        weightLabel.setBounds(10, -400, 80, 25);
        panel.add(weightLabel);
        //Weight text field
        final JTextField weightText = new JTextField(20);
        weightText.setBounds(100, -400, 165, 25);
        panel.add(weightText);

        //Height
        JLabel heightLabel = new JLabel("Height/m:");
        heightLabel.setBounds(10, -500, 80, 20);
        panel.add(heightLabel);
        //Height text field
        final JTextField heightText = new JTextField(20);
        heightText.setBounds(100, -500, 165, 20);
        panel.add(heightText);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, -600, 80, 25);
        submitButton.addActionListener(new actionListener() {
            //When button is pressed, execute actionPerformed function
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get value from userText
                String input_username = usernameText.getText();
                while (input_username.charAt(0)==' '){
                    input_username = input_username.substring(1);
                }
                    
                String input_email = emailText.getText();
                while (input_email.charAt(0)==' '){
                    input_email = input_email.substring(1);
                }
                String input_firstName = firstNameText.getText();
                while (input_firstName.charAt(0)==' '){
                    input_firstName = input_firstName.substring(1);
                }
                String input_surname = surnameText.getText();
                while (input_surname.charAt(0)==' '){
                    input_surname = input_surname.substring(1);
                }
                double input_weight = Double.parseDouble(weightText.getText());
                double input_height = Double.parseDouble(heightText.getText());

                //Controller creates and adds user to list of users
                if (input_username.equals("")) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Please enter a username");
                } else if (input_email.equals("")) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Please enter your email");
                } else if (input_firstName.equals("")) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Please enter your first name");
                } else if (input_surname.equals("")) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Please enter your surname");
                } else if (input_firstName.length() < 2
                        || input_surname.length() < 2) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "First/Surname must be minimum 2 characters. "
                            + "Please try again.");
                } else if (!input_firstName.chars()
                        .allMatch(Character::isLetter) 
                        || !input_surname.chars()
                        .allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "First/Surname must contain only alphabet "
                            + "characters");
                } else if (!UserController.isValidHeight(input_height)) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Height cannot be a negative value, less than 0.1 "
                            + "metres or greater than 5 metres");
                } else if (!UserController.isValidWeight(input_weight)) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Weight cannot be a negative value "
                            + "or less than 1kg");
                } else if (!(UserController.isValidEmail(input_email))) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Email is invalid");
                } else if (!UserController.createUser(s, input_username,
                        input_email,
                        input_firstName,
                        input_surname,
                        input_weight,
                        input_height)) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Failed to create user");
                } else {
                    User thisUser = UserController.getUserByUsername(s, 
                            input_username);
                    //Close current frame:
                    registrationFrame.dispose();
                    //Go to main window
                    new MainWindow(s, thisUser);
                }
            }
        });
        panel.add(submitButton);

        //Button to return to main page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationFrame.dispose();
                new LoginWindow(s);
            }
        });
        
        panel.add(backButton);
        registrationFrame.add(panel);
        registrationFrame.setResizable(false);
        registrationFrame.pack();
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(250, 600);
        registrationFrame.setVisible(true);
    }
}

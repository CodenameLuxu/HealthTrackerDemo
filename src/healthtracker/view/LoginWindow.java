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

//This is the first page the user encounters
public class LoginWindow {

    public LoginWindow(StorageController s) {

        final JFrame loginFrame = new JFrame("Login");
        JPanel panel = new JPanel();

        loginFrame.add(panel);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        //User input text field
        final JTextField userText = new JTextField(24);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get value from userText
                String userinput = userText.getText();
                //Get user
                User thisUser = UserController.getUserByUsername(s, userinput);

                //If user is found, open main window of user
                if (thisUser != null) {
                    loginFrame.dispose();
                    new MainWindow(s, thisUser);
                } else {
                    //Else stay in the same window and display an error message
                    JOptionPane.showMessageDialog(loginFrame, 
                            "User not found");
                }
            }
        });
        panel.add(loginButton);

        //Register button takes user to registration page
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get value from userText
                new RegistrationWindow(s);
                loginFrame.dispose();
            }
        });
        panel.add(registerButton);

        loginFrame.pack();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }
}

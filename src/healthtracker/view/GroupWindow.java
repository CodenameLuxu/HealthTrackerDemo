//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.model.*;
import healthtracker.controller.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class GroupWindow extends JFrame {

    public GroupWindow(StorageController s, Group group, User user) {
        final JFrame groupFrame = new JFrame("Group, " + group.getGroupName());
        JPanel panel = new JPanel();
        setLayout(null);
        //SECTION : GROUP'S ACHEIEVEMENT BOARD
        //Achievement Board Label
        JLabel boardLabel = new JLabel("Achievement Board");
        panel.add(boardLabel);
        //Achievement Board
        JTextArea achievementBoard = new JTextArea("History: ");
        achievementBoard.setColumns(25);
        for (String line : GroupController.getArchievementBoard(group)) {
            if (line != null) {
                achievementBoard.append("\n");
                achievementBoard.append(line);
            }
        }
        achievementBoard.setEditable(false);
        JScrollPane scroll_ArchievementBoard = 
                new JScrollPane(achievementBoard,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll_ArchievementBoard.setPreferredSize(new Dimension(300, 150));
        panel.add(scroll_ArchievementBoard);

        //SECTION: SELECT USER TO VIEW THEIR WINDOW
        String[] empty = {""};
        String[] options_names = 
                GroupController.getMembersNamesExceptUser(group, user);
        if (options_names.length <= 0) {
            options_names = empty;
        }
        //getAllUsernames returns the names of every user in a group 
        JLabel memberNameLabel = new JLabel("Name: ");
        panel.add(memberNameLabel);

        //Drop down box to select a username
        final JComboBox dropDownBox = new JComboBox(options_names);
        dropDownBox.setSelectedIndex(0);
        panel.add(dropDownBox);

        //View user button
        JButton viewUserButton = new JButton("View user");
        viewUserButton.setBounds(10, 80, 80, 25);
        viewUserButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get username from drop down box
                String username = (String) dropDownBox.getSelectedItem();
                User goToUser = s.getByUsername(username);
                if (goToUser != user) {
                    new MainWindow(s, user, goToUser);
                } else {
                    new MainWindow(s, user);
                }
                //Close current frame
                groupFrame.dispose();
            }
        });

        panel.add(viewUserButton);
        //SECTION: ADD NEW MEMBER TO GROUP
        JLabel addmemberButton = new JLabel("Add member(Enter a username): ");
        panel.add(addmemberButton);

        //Text field to enter username
        JTextField addUserText = new JTextField("", 10);
        addUserText.setBounds(20, 5, 20, 5);
        panel.add(addUserText);

        //Add user button
        JButton addUserButton = new JButton("Add user");
        addUserButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get username of a user to be added to a group
                String username = (String) addUserText.getText();

                if (GroupController.addMember(s, group, username)) {
                    JOptionPane.showMessageDialog(groupFrame, "Success");
                    groupFrame.dispose();
                    //Refresh window
                    new GroupWindow(s, group, user);
                } else {
                    JOptionPane.showMessageDialog(groupFrame,
                            "ERROR: invalid username");
                }
            }
        });
        panel.add(addUserButton);

        //SECTION: REMOVE USER :: CHANGE TO LEAVE GROUP BUTTON
        JButton removeUserButton = new JButton("Leave group");
        removeUserButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get username of a user to be added to a group
                String username = user.getUsername();
                if (GroupController.removeMember(s, group, username)) {
                    JOptionPane.showMessageDialog(groupFrame, "Success");
                    groupFrame.dispose();
                    //refresh window
                    new AllGroupsWindow(s, user);
                } else {
                    JOptionPane.showMessageDialog(groupFrame, "ERROR");
                }
            }
        });
        panel.add(removeUserButton);

        //Button to return to group selection page
        JButton goToAllGroupButton = new JButton("View All Group");
        goToAllGroupButton.setBounds(10, 100, 80, 25);
        goToAllGroupButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupFrame.dispose();
                new AllGroupsWindow(s, user);
            }
        });
        panel.add(goToAllGroupButton);
        JButton backButton = new JButton("Home");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(s, user);
                groupFrame.dispose();
            }
        });
        panel.add(backButton);

        groupFrame.add(panel);
        groupFrame.setPreferredSize(new Dimension(340, 370));
        groupFrame.setResizable(false);
        groupFrame.pack();
        groupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        groupFrame.setVisible(true);
    }
}

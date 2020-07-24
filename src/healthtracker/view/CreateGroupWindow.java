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

public class CreateGroupWindow {

    public CreateGroupWindow(StorageController s, User user) {
        JFrame createGroupFrame = new JFrame("Create a Group");
        JPanel panel = new JPanel();

        JLabel groupNameLabel = new JLabel("Group Name:");
        panel.add(groupNameLabel);

        JTextField groupNameText = new JTextField("",15);
        groupNameText.setBounds(20, 2, 20, 3);
        panel.add(groupNameText);

        JButton createGroupButton = new JButton("Create Group");
        createGroupButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupname = (String) groupNameText.getText();
                if (GroupController.groupNameCheck(s, groupname)) {
                    GroupController.addGroup(s, groupname, user);
                    JOptionPane.showMessageDialog(createGroupFrame,
                            "Group : " + groupname + " Added");
                    createGroupFrame.dispose();
                    new AllGroupsWindow(s, user);
                } else {
                    JOptionPane.showMessageDialog(createGroupFrame,
                            "ERROR: Name already Taken");
                }
            }
        });
        panel.add(createGroupButton);

        //Button to return to main page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGroupFrame.dispose();
                new AllGroupsWindow(s, user);
            }
        });
        panel.add(backButton);

        createGroupFrame.add(panel);
        createGroupFrame.pack();
        createGroupFrame.setResizable(false);
        createGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGroupFrame.setVisible(true);
    }
}

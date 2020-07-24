//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class AllGroupsWindow {
    public AllGroupsWindow(StorageController s, User user){
        JFrame groupsFrame = new JFrame("All groups");
        JPanel panel = new JPanel();
        
        JLabel groupName = new JLabel("Group Name:");
        panel.add(groupName);
        String [] empty = {""};
        String [] options_allgroup = GroupController.getAllGroupNames(s,user);
        if (options_allgroup == null){
            options_allgroup = empty;   
        }
        //Drop down box for group names
        final JComboBox dropDownBox_groups = new JComboBox(options_allgroup);
        dropDownBox_groups.setSelectedIndex(0);
        panel.add(dropDownBox_groups);
        
        //View group button
        JButton accessGroupButton = new JButton("View Group");
        accessGroupButton.setBounds(10, 80, 80, 25);
        accessGroupButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = (String)dropDownBox_groups.getSelectedItem();
                Group thisGroup = GroupController.getGroup(s,groupName, user);
                new GroupWindow(s,thisGroup,user);
                groupsFrame.dispose();
            }
        });
        panel.add(accessGroupButton);
        
        //Create new group button
        JButton gotoCreateGroup = new JButton("Create New Group");
        gotoCreateGroup.addActionListener(new actionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               new CreateGroupWindow(s,user);
               groupsFrame.dispose();
            }
        });
        panel.add(gotoCreateGroup);
        
        //Button to return to main page
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(new actionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                groupsFrame.dispose();
                new MainWindow(s,user);
            }
        });
        panel.add(backButton);
        
        groupsFrame.add(panel);
        groupsFrame.pack();
        groupsFrame.setResizable(false);
        groupsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        groupsFrame.setVisible(true);
    }
}

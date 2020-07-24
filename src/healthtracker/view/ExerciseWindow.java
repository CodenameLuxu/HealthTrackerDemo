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
import java.util.ArrayList;

public class ExerciseWindow extends JFrame {

    public ExerciseWindow(StorageController s, User user) {
        final JFrame doExerciseFrame = new JFrame("Add workout");
        JPanel panel = new JPanel();

        JLabel excercisesLabel = new JLabel("Excercise:");
        panel.add(excercisesLabel);

        //Get all exercise names
        ArrayList<String> allExerciseNames = s.getAllExerciseNames();
        String[] options;

        String[] empty = {""};
        if (allExerciseNames.size() < 1) {
            options = empty;
        } else {
            options = allExerciseNames.
                    toArray(new String[allExerciseNames.size()]);
        }

        //Creates a drop down box for allExerciseNames
        final JComboBox dropDownBox = new JComboBox(options);
        dropDownBox.setSelectedIndex(0);
        panel.add(dropDownBox);

        //User input text boxes
        JLabel timeLabel = new JLabel("Time(minute(s)) : ");
        panel.add(timeLabel);
        final JTextField timeText = new JTextField("60",7);
        timeText.setBounds(10, 10, 10, 2);
        panel.add(timeText);

        //Add workout button
        JButton addWorkoutButton = new JButton("Add Workout");
        addWorkoutButton.setBounds(10, 80, 80, 25);
        addWorkoutButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String excerciseName = 
                            (String) dropDownBox.getSelectedItem();
                    //Get ExerciseItem object by name
                    ExerciseItem thisActivity = ExerciseController.
                            getExerciseByName(s, excerciseName);
                    int excerciseTime = 
                            Integer.parseInt(timeText.getText());
                    UserController.addExercise(user, thisActivity, 
                            excerciseTime);
                    JOptionPane.showMessageDialog(doExerciseFrame,
                            "Workout Added");    
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(doExerciseFrame, exception);
                }
            }
        });
        panel.add(addWorkoutButton);

        //Button to return to main page
        JButton backButton = new JButton("Done");
        backButton.setBounds(10, 80, 80, 25);
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doExerciseFrame.dispose();
                new MainWindow(s, user);
            }
        });
        
        panel.add(backButton);
        doExerciseFrame.add(panel);
        doExerciseFrame.pack();
        doExerciseFrame.setResizable(false);
        doExerciseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doExerciseFrame.setVisible(true);
    }
}

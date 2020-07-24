//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.model.*;
import healthtracker.controller.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class MainWindow extends JFrame {

    public MainWindow(StorageController s, User user) {
        UserController.processGoal(s, user);
        final JFrame mainFrame = new JFrame("Welcome, " + user.getUsername());
        JPanel panel = new JPanel();

        //History Lists
        List<FoodRecord> dietHistory = user.getDietHistory();
        List<ExerciseRecord> exerciseHistory = user.getWorkoutHistory();
        List<Goal> goals = user.getGoals();

        //Iterates throught exerciseHistory list and displays it in text area
        JLabel exerciseHistoryLabel = new JLabel("Excercise History: ");
        exerciseHistoryLabel.setBounds(50, 50, 100, 50);
        panel.add(exerciseHistoryLabel);
        JTextArea exerciseTextArea = new JTextArea("");
        exerciseTextArea.setColumns(35);
        for (ExerciseRecord sessions : exerciseHistory) {
            exerciseTextArea.append(sessions.toString());
            exerciseTextArea.append("\n");
        }
        exerciseTextArea.setEditable(false);
        JScrollPane exerciseScroll = new JScrollPane(exerciseTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        exerciseScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(exerciseScroll);

        //Iterates throught dietHistory list and displays it in text area
        JLabel dietHistoryLabel = new JLabel("Diet History: ");
        panel.add(dietHistoryLabel);
        JTextArea dietTextArea = new JTextArea("");
        dietTextArea.setColumns(35);
        for (FoodRecord a : dietHistory) {
            dietTextArea.append(a.toString());
            dietTextArea.append("\n");
        }
        JScrollPane dietScroll = new JScrollPane(dietTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dietScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(dietScroll);

        //Iterates throught goals list and displays it in text area
        JLabel goalLabel = new JLabel("All current goals: ");
        goalLabel.setBounds(50, 500, 100, 50);
        panel.add(goalLabel);
        JTextArea goalTextArea = new JTextArea("");
        goalTextArea.setColumns(35);
        for (Goal g : goals) {
            if (g != null) {
                goalTextArea.append(g.toString());
                goalTextArea.append("\n");
            }
        }
        goalTextArea.setBounds(200, 500, 100, 200);
        goalTextArea.setEditable(false);
        JScrollPane goalScroll = new JScrollPane(goalTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        goalScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(goalScroll);

        //Calorie intake today
        JLabel calorieIntakeLabel = new JLabel("Today's intake: ");
        calorieIntakeLabel.setBounds(50, 800, 100, 50);
        panel.add(calorieIntakeLabel);
        JTextField intake = new JTextField(Integer.toString(UserController.
                getCalorieIntake(user, LocalDate.now())), 6);
        intake.setBounds(200, 800, 100, 50);
        intake.setEditable(false);
        panel.add(intake);

        //Calories burned today
        JLabel caloriesBurnedLabel = new JLabel("Today's calories burned: ");
        JTextField caloriesBurnedToday = new JTextField(Integer.
                toString(UserController.getCaloriesBurned(user, 
                        LocalDate.now())), 6);
        caloriesBurnedLabel.setBounds(50, 900, 100, 50);
        caloriesBurnedToday.setBounds(200, 900, 100, 50);
        caloriesBurnedToday.setEditable(false);
        panel.add(caloriesBurnedLabel);
        panel.add(caloriesBurnedToday);

        //Add workout button
        JButton addWorkoutButton = new JButton("Add workout");
        addWorkoutButton.setBounds(400, 50, 100, 50);
        addWorkoutButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExerciseWindow(s, user);
                mainFrame.dispose();
            }
        }
        );
        panel.add(addWorkoutButton);

        //Add meal button
        JButton addMealButton = new JButton("Add Meal");
        addMealButton.setBounds(400, 150, 100, 50);
        addMealButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FoodWindow(s, user);
                mainFrame.dispose();
            }
        }
        );
        panel.add(addMealButton);

        //Add goal button
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.setBounds(400, 250, 100, 50);
        addGoalButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddGoalWindow_S1(s, user);
                mainFrame.dispose();
            }
        }
        );
        panel.add(addGoalButton);
        //Update weight button
        JButton updateWeightButton = new JButton("Update weight");
        updateWeightButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateWeightWindow(s, user);
                mainFrame.dispose();
            }
        }
        );
        panel.add(updateWeightButton);

        //Profile button
        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(400, 250, 100, 50);

        profileButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ProfileWindow(s, user);
            }

        });
        panel.add(profileButton);

        //View all groups button
        JButton goToAllGroupButton = new JButton("View All Groups");
        goToAllGroupButton.setBounds(400, 350, 100, 50);
        goToAllGroupButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new AllGroupsWindow(s, user);
            }
        });
        panel.add(goToAllGroupButton);

        //Log off button
        JButton logoffButton = new JButton("Log off");
        logoffButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoginWindow(s);
            }
        });
        logoffButton.setBounds(400, 450, 100, 50);
        panel.add(logoffButton);

        //Pack and display everything on frame
        panel.setPreferredSize(new Dimension(370, 500));
        JScrollPane scrollPanel = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainFrame.add(scrollPanel);
        mainFrame.setPreferredSize(new Dimension(500, 530));
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    //--------------------------------------------
    //Constructor for user viewing other's profile
    public MainWindow(StorageController s, User visitor, User user) {
        final JFrame mainFrame = new JFrame("Profile: " + user.getUsername());
        JPanel panel = new JPanel();

        //CurrentBMI:
        JLabel bmiLabel = new JLabel("Current BMI: ");
        panel.add(bmiLabel);
        JTextField bmiText = new JTextField(Integer.
                toString((int) UserController.getBMI(user)));
        bmiText.setEditable(false);
        panel.add(bmiText);

        //Output History List
        List<FoodRecord> dietHistory = user.getDietHistory();
        List<ExerciseRecord> exerciseHistory = user.getWorkoutHistory();
        List<Goal> goals = user.getGoals();

        JLabel exerciseHistoryLabel = new JLabel("Excercise History: ");
        exerciseHistoryLabel.setBounds(50, 50, 100, 50);
        panel.add(exerciseHistoryLabel);
        JTextArea exerciseTextArea = new JTextArea("");
        exerciseTextArea.setColumns(35);
        for (ExerciseRecord sessions : exerciseHistory) {
            exerciseTextArea.append(sessions.toString());
            exerciseTextArea.append("\n");
        }
        exerciseTextArea.setEditable(false);
        JScrollPane exerciseScroll = new JScrollPane(exerciseTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        exerciseScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(exerciseScroll);

        //Iterates throught dietHistory list and displays it in text area
        JLabel dietHistoryLabel = new JLabel("Diet History: ");
        panel.add(dietHistoryLabel);
        JTextArea dietTextArea = new JTextArea("");
        dietTextArea.setColumns(35);
        for (FoodRecord a : dietHistory) {
            dietTextArea.append(a.toString());
            dietTextArea.append("\n");
        }
        dietTextArea.setEditable(false);
        JScrollPane dietScroll = new JScrollPane(dietTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dietScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(dietScroll);

        //Iterates throught goals list and displays it in text area
        JLabel goalLabel = new JLabel("All current goals: ");
        goalLabel.setBounds(50, 500, 100, 50);
        panel.add(goalLabel);
        JTextArea goalTextArea = new JTextArea("");
        goalTextArea.setColumns(35);
        for (Goal g : goals) {
            if (g != null) {
                goalTextArea.append(g.toString());
                goalTextArea.append("\n");
            }
        }
        goalTextArea.setBounds(200, 500, 100, 200);
        goalTextArea.setEditable(false);
        JScrollPane goalScroll = new JScrollPane(goalTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        goalScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(goalScroll);

        //Calorie intake today
        JLabel calorieIntakeLabel = new JLabel("Today's intake: ");
        calorieIntakeLabel.setBounds(50, 800, 100, 50);
        panel.add(calorieIntakeLabel);
        JTextField intake = new JTextField(Integer.
                toString(UserController.getCalorieIntake(user, 
                        LocalDate.now())), 6);
        intake.setBounds(200, 800, 100, 50);
        intake.setEditable(false);
        panel.add(intake);

        //Calories burned today
        JLabel caloriesBurnedLabel = new JLabel("Today's calories burned: ");
        JTextField caloriesBurnedToday = new JTextField(Integer.
                toString(UserController.getCaloriesBurned(user, 
                        LocalDate.now())), 6);
        caloriesBurnedLabel.setBounds(50, 900, 100, 50);
        caloriesBurnedToday.setBounds(200, 900, 100, 50);
        caloriesBurnedToday.setEditable(false);
        panel.add(caloriesBurnedLabel);
        panel.add(caloriesBurnedToday);

        //See this user's profile
        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(10, 100, 80, 25);
        profileButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ProfileWindow(s, visitor, user);
            }

        });
        panel.add(profileButton);

        JButton myProfileButton = new JButton("My Profile");
        myProfileButton.setBounds(10, 100, 80, 25);
        myProfileButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new MainWindow(s, visitor);
            }
        });
        panel.add(myProfileButton);

        panel.setPreferredSize(new Dimension(370, 550));
        //Pack and display everything on frame
        JScrollPane scrollPanel = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainFrame.add(scrollPanel);
        mainFrame.setPreferredSize(new Dimension(500, 550));
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}

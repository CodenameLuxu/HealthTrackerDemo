//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    static final long serialVersionUID = 107;

    private static DateTimeFormatter dtf = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //FIELDS
    private String registrationDate;
    private String username;
    private String email;
    private PersonalInfo personalInfo;
    private List<ExerciseRecord> workOutHistory = new ArrayList();
    private List<FoodRecord> dietHistory = new ArrayList();
    private List<Group> groups = new ArrayList();
    private List<Goal> goals = new ArrayList();

    //CONSTRUCTOR
    public User(String newUsername, String newEmail, String newFirstName,
            String newSurname, double newWeight, double newHeight, 
            double newBmi) {
        registrationDate = dtf.format(LocalDate.now());
        username = newUsername;
        email = newEmail;
        personalInfo = new PersonalInfo(newFirstName, newSurname,
                newWeight, newHeight, newBmi);
    }

    //Mutators
    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public List<ExerciseRecord> getWorkoutHistory() {
        return workOutHistory;
    }

    public List<FoodRecord> getDietHistory() {
        return dietHistory;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    //Accessors
    public void setRegistrationDate(String newRegistrationDate) {
        registrationDate = newRegistrationDate;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setPersonalInfo(PersonalInfo newPersonalInfo) {
        personalInfo = newPersonalInfo;
    }

    public void setworkoutHistory(List<ExerciseRecord> newHistory) {
        workOutHistory = newHistory;
    }

    public void setDietHistory(List<FoodRecord> newHistory) {
        dietHistory = newHistory;
    }

    public void setGroups(List<Group> newGroups) {
        groups = newGroups;
    }

    public void setGoals(List<Goal> newGoals) {
        goals = newGoals;
    }

}

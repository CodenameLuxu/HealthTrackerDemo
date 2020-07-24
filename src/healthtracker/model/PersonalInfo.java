//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;

public class PersonalInfo implements Serializable {

    static final long serialVersionUID = 108;

    private String firstName;
    private String surname;
    private double weight;
    private double height;
    private double bmi;

    public PersonalInfo(String newFirstName, String newSurname,
            double newWeight, double newHeight, double newBmi) {
        firstName = newFirstName;
        surname = newSurname;
        weight = newWeight;
        height = newHeight;
        bmi = newBmi;
    }

    //Accessors
    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBMI() {
        return bmi;
    }

    //Mutators
    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public void setSurname(String newSurname) {
        surname = newSurname;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public void setHeight(double newHeight) {
        height = newHeight;
    }

    public void setBMI(double newBMI) {
        bmi = newBMI;
    }
}

//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;

public class ExerciseItem implements Serializable {

    static final long serialVersionUID = 105;

    private String exerciseName;
    private int caloriesPerMinute;

    public String getExerciseName() {
        return exerciseName;
    }

    public int getCaloriesPerMinute() {
        return caloriesPerMinute;
    }

    public void setExerciseName(String thisName) {
        exerciseName = thisName;
    }

    public void setCaloriesPerMinute(int thisRate) {
        caloriesPerMinute = thisRate;
    }
}

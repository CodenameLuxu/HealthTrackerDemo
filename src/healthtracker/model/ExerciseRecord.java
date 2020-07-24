//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;
import healthtracker.controller.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExerciseRecord implements Serializable{
    
    static final long serialVersionUID = 109;

    transient DateTimeFormatter dtf = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate date;
    private ExerciseItem exercise;
    private int duration;    //Stored in minutes, user input must be converted.
    private int totalCaloriesBurned;
    
    public ExerciseRecord(ExerciseItem newExercise, int newDuration) {
        date = LocalDate.now();
        exercise = newExercise;
        duration = newDuration;
        totalCaloriesBurned = 
                ExerciseController.calculateTotalCalories(exercise, duration);
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public ExerciseItem getExercise(){
        return exercise;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getTotalCaloriesBurned(){
        return totalCaloriesBurned;
    }
    
    public void setDate(LocalDate thisDate){
        date = thisDate;
    }
    
    public void setExercise(ExerciseItem item){
        exercise = item;
    }
    
    public void setDuration(int newDuration){
        duration = newDuration;
    }
    
    public void setTotalCaloriesBurned(int caloriesBurned){
        totalCaloriesBurned = caloriesBurned;
    }
    
    //Prints out the date, exercise name and duration of an exercise record
    @Override
    public String toString(){
        return (date + " | " + exercise.getExerciseName() + " | " + duration);
    }
}

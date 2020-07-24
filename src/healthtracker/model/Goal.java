//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Goal implements Serializable {

    static final long serialVersionUID = 111;

    private long goalID;
    private String goalName;
    private String goalType;
    private LocalDate goalStart;
    private LocalDate goalDeadline;

    private int goalTarget;
    private LocalDate goalDurationTarget;
    private int dayCount;

    public Goal(long ID, String name, String type, LocalDate deadline,
            int target, LocalDate durationtarget) {
        goalID = ID;
        goalName = name;
        goalType = type;
        goalStart = LocalDate.now();
        goalDeadline = deadline;
        goalTarget = target;
        goalDurationTarget = durationtarget;
        dayCount = 0;
    }

    //Accessors
    public long getID() {
        return goalID;
    }

    public String getGoalName() {
        return goalName;
    }

    public String getGoalType() {
        return goalType;
    }

    public LocalDate getGoalStart() {
        return goalStart;
    }

    public LocalDate getGoalDeadline() {
        return goalDeadline;
    }

    public int getGoalTarget() {
        return goalTarget;
    }

    public LocalDate getGoalDurationTarget() {
        return goalDurationTarget;
    }

    public int getDayCount() {
        return dayCount;
    }

    //Mutators
    public void setID(int newID) {
        goalID = newID;
    }

    public void setGoalName(String newName) {
        goalName = newName;
    }

    public void setGoalType(String newType) {
        goalType = newType;
    }

    public void setGoalStart(LocalDate newStart) {
        goalStart = newStart;
    }

    public void setGoalDeadline(LocalDate newDeadline) {
        goalDeadline = newDeadline;
    }

    public void setGoalTarget(int newTarget) {
        goalTarget = newTarget;
    }

    public void setGoalDurationTarget(LocalDate newDurationTarget) {
        goalDurationTarget = newDurationTarget;
    }

    public void setDayCount(int newDayCount) {
        dayCount = newDayCount;
    }

    //Prints out the contents of a goal object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(goalType).append(" | ");
        sb.append(goalName).append(" | ");
        sb.append(goalTarget).append(" | ");
        sb.append(goalStart).append(" | ");
        sb.append(goalDeadline).append(" | ");
        sb.append(dayCount);

        return sb.toString();
    }
}

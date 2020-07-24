//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import java.time.Period;
import healthtracker.model.*;
import java.time.LocalDate;

public class GoalController {

    static public void setGoalDeadlineDays(Goal thisGoal, int numDays) {
        thisGoal.setGoalDeadline(LocalDate.now().plusDays(numDays));
    }

    static public void setGoalDeadlineWeeks(Goal thisGoal, int numWeeks) {
        thisGoal.setGoalDeadline(LocalDate.now().plusWeeks(numWeeks));
    }

    static public void updateDayCount(Goal thisGoal) {
        int full = Period.between(thisGoal.getGoalStart(),
                thisGoal.getGoalDeadline()).getDays();
        int between = Period.between(LocalDate.now(),
                thisGoal.getGoalDeadline()).getDays();
        int left = full - between;
        thisGoal.setDayCount(left);
    }

    static public boolean createGoal(User thisUser, long newGoalID,
            String newGoalName, String newGoalType, LocalDate newGoalStart,
            LocalDate newGoalDeadline, int newGoalTarget,
            LocalDate newGoalDurationTarget, int newDayCount) {
        Goal newGoal = new Goal(newGoalID, newGoalName, newGoalType,
                newGoalDeadline, newGoalTarget,
                newGoalDurationTarget);
        return thisUser.getGoals().add(newGoal);
    }

    //Workout goals check
    static public boolean goalIsMet(Goal thisGoal, User aUser, LocalDate now,
            ExerciseRecord thisSession) {
        now = LocalDate.now();
        if (thisGoal.getGoalType().equals("weight")) {
            if (aUser.getPersonalInfo().getWeight() <= thisGoal.getGoalTarget()
                    && now.isBefore(thisGoal.getGoalDeadline())) {
                return true;
            }
        } else if (thisGoal.getGoalType().equals("bmi")) {
            if (aUser.getPersonalInfo().getBMI() <= thisGoal.getGoalTarget()
                    && now.isBefore(thisGoal.getGoalDeadline())) {
                return true;
            }
        } else if (thisGoal.getGoalType().equals("duration")) {
            if (ExerciseController.getDuration(thisSession)
                    >= thisGoal.getGoalTarget()) {
                return true;
            }
        } else if (thisGoal.getGoalType().equals("calories")) {
            Period timeElapsed = Period.between(thisGoal.getGoalStart(),
                    thisGoal.getGoalDeadline());
            if (UserController.getCalorieIntake(aUser, thisGoal.getGoalStart(),
                    thisGoal.getGoalDeadline()) < thisGoal.getGoalTarget()
                    && timeElapsed.getDays() >= 1) {
                return true;
            }
        }
        return false;
    }

    static public boolean goalIsMet(Goal thisGoal, User user) {
        if (thisGoal.getGoalName().equals("weight")) {
            //Return if weight is in boundary of 1 kg of target weight
            System.out.println("Weight Goal");
            return ((user.getPersonalInfo().getWeight()
                    <= thisGoal.getGoalTarget() + 1)
                    && (user.getPersonalInfo().getWeight()
                    >= thisGoal.getGoalTarget() - 1));
        }
        if (thisGoal.getGoalName().equals("bmi")) {
            return (int) UserController.calculateBMI(user.getPersonalInfo().
                    getHeight(), user.getPersonalInfo().getWeight())
                    == (int) thisGoal.getGoalTarget();
        }
        if (thisGoal.getGoalName().equals("exercise")) {
            String excerciseType = thisGoal.getGoalType();
            for (ExerciseRecord ex : user.getWorkoutHistory()) {
                if (ex.getExercise().getExerciseName().
                        equals(excerciseType)) {
                    if (ex.getDate().isAfter(thisGoal.getGoalStart())
                            || ex.getDate().isEqual(thisGoal.getGoalStart())) {
                        if (ex.getDuration() >= thisGoal.getGoalTarget()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        if (thisGoal.getGoalName().equals("diet")) {
            System.out.println("Diet Goal");
            System.out.println("Intake : " + UserController.
                    getCalorieIntake(user, thisGoal.getGoalStart(),
                            thisGoal.getGoalDeadline()));
            System.out.println("Target: " + thisGoal.getGoalTarget());
            return (UserController.getCalorieIntake(user,
                    thisGoal.getGoalStart(), thisGoal.getGoalDeadline())
                    >= thisGoal.getGoalTarget());

        }
        return false;
    }

    //Check if choosen Goal have expired
    static public boolean isExpire(Goal thisGoal) {
        LocalDate today = LocalDate.now();
        //If today is the deadline or toda is after 
        return (today.isAfter(thisGoal.getGoalDeadline())
                || thisGoal.getGoalDeadline().isEqual(today));

    }
}

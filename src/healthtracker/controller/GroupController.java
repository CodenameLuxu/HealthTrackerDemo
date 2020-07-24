//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import healthtracker.model.*;

import healthtracker.model.User;
import java.util.ArrayList;
import java.util.List;

public class GroupController {

    public static String getGroupName(Group thisGroup) {
        return thisGroup.getGroupName();
    }

    //Changes the group name
    public static boolean renameGroup(StorageController s, String newName,
            Group thisGroup) {
        for (int i = 0; i < s.getGroups().size(); i++) {
            if (s.getGroups().get(i).getGroupName().equals(newName)) {
                return false;
            }
        }
        thisGroup.setGroupName(newName);
        return true;
    }

    //Returns the list of all members of a group
    public static List<User> getMembersList(Group thisGroup) {
        return thisGroup.getMembers();
    }

    //Returns the list of all members' names of a group
    public static String[] getMembersNames(Group thisGroup) {
        List<User> group = getMembersList(thisGroup);
        String[] returnArray = new String[group.size()];
        for (int i = 0; i < group.size(); i++) {
            returnArray[i] = group.get(i).getUsername();
        }
        return returnArray;
    }

    public static String[] getMembersNamesExceptUser(Group thisGroup,
            User user) {
        List<User> group = getMembersList(thisGroup);
        List<String> names = new ArrayList();
        for (User u : group) {
            if (!u.getUsername().equals(user.getUsername())) {
                names.add(u.getUsername());
            }
        }
        String[] returnArray = names.toArray(new String[names.size()]);
        return returnArray;
    }

    public static Group getGroup(StorageController s,
            String groupName, User user) {
        for (Group thisGroup : s.getGroups()) {
            if (thisGroup.getGroupName().equals(groupName)) {
                return thisGroup;
            }
        }
        return null;
    }

    public static List<String> getArchievementBoard(Group group) {
        return group.getBoard();
    }

    public static void addToBoard(User user, Goal goal, Group group, 
            String status) {
        String s = user.getUsername() + " have " + status + " | " + 
                goal.toString() + " | ";
        group.appendToBoard(s);
        System.out.println("Acheievement added");
    }

    public static boolean addGroup(StorageController s, String groupName,
            User adminUser) {
        for (int i = 0; i < s.getGroups().size(); i++) {
            if (s.getGroups().get(i).getGroupName().equals(groupName)) {
                return false;
            }
        }
        Group newGroup = new Group(groupName, adminUser);
        return s.getGroups().add(newGroup);
    }

    public static boolean addMember(StorageController s, Group thisGroup,
            String memberUsername) {
        User thisUser = UserController.getUserByUsername(s, memberUsername);
        if (thisUser == null) {
            return false;
        }
        if (contains(thisGroup, memberUsername)) {
            return false;
        }
        for (int j = 0; j < s.getGroups().size(); j++) {
            if (s.getGroups().get(j).equals(thisGroup)) {
                return s.getGroups().get(j).getMembers().add(thisUser);
            }
        }
        return false;
    }

    //Returns true if a user is a part of a group, false otherwise
    public static boolean contains(Group group, User user) {
        return group.getMembers().contains(user);
    }

    public static boolean removeMember(StorageController s, Group thisGroup,
            String memberUsername) {
        User thisUser = null;
        for (int i = 0; i < s.getUsers().size(); i++) {
            if (s.getUsers().get(i).getUsername().equals(memberUsername)) {
                thisUser = s.getUsers().get(i);
            }
        }
        if (thisUser == null) {
            return false;
        }
        for (int j = 0; j < s.getGroups().size(); j++) {
            if (s.getGroups().get(j).equals(thisGroup)) {
                return s.getGroups().get(j).getMembers().remove(thisUser);
            }
        }
        return false;
    }

    //Checks if the user is a part of a group by username
    public static boolean contains(Group thisGroup,
            String memberUsername) {
        for (User u : thisGroup.getMembers()) {
            if (u.getUsername().equals(memberUsername)) {
                return true;
            }
        }
        return false;
    }

    //Removes the group as a whole
    public static boolean removeGroup(StorageController s, String groupName) {
        Group thisGroup = null;
        for (int i = 0; i < s.getGroups().size(); i++) {
            if (s.getGroups().get(i).getGroupName().equals(groupName)) {
                thisGroup = s.getGroups().get(i);
            }
        }
        if (thisGroup == null) {
            return false;
        }
        return s.getGroups().remove(thisGroup);
    }

    //Returns a list of usernames of all members of a group
    public static List<String> getAllUsernames(Group thisGroup) {
        List<String> result = new ArrayList();
        for (int i = 0; i < thisGroup.getMembers().size(); i++) {
            result.add(thisGroup.getMembers().get(i).getUsername());
        }
        return result;
    }

    //Returns a list of all group names 
    public static String[] getAllGroupNames(StorageController s,
            User thisUser) {
        List<Group> groups = s.getGroups();
        int size = 0;
        String[] returnArray = new String[groups.size()];
        if (groups.size() > 0) {
            for (int i = 0; i < groups.size(); i++) {
                Group thisgroup = groups.get(i);
                if (GroupController.contains(thisgroup, thisUser)) {
                    returnArray[size] = thisgroup.getGroupName();
                    size++;
                }
            }
            return returnArray;
        } else {
            return null;
        }
    }

    //Checks if provided group name exists in the database
    public static boolean groupNameCheck(StorageController s, String name) {
        for (User user : s.getUsers()) {
            for (Group group : user.getGroups()) {
                if (group.getGroupName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }
}

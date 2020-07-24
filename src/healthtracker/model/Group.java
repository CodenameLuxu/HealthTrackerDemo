//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Group implements Serializable {

    static final long serialVersionUID = 104;

    private String groupName;
    private List<User> members;
    private List<String> board;

    public Group(String newGroupName, User adminUser) {
        this.groupName = newGroupName;
        this.members = new ArrayList();
        this.members.add(adminUser);
        this.board = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setGroupName(String newName) {
        groupName = newName;
    }

    public void setMembers(List<User> newMembers) {
        members = newMembers;
    }
    public List<String> getBoard(){
        return board;
    }
    public boolean appendToBoard(String s){
        return board.add(s);
    }
    public void setBoard(List<String> newBoard){
        board = newBoard;
    }
}

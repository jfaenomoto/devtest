import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvitationList {

    private List<String> invitedAttendants;
    
    private List<String> invitedNonAttendants;
    
    private List<String> uninvitedAttendants;

    public InvitationList(List<Invitee> invitees, List<String> attendants) {
        this.invitedAttendants = new ArrayList<>();
        this.invitedNonAttendants = new ArrayList<>();
        this.uninvitedAttendants = new ArrayList<>(attendants);
        
        for (Invitee invitee : invitees) {
            boolean hasAttended = false;
            for (String attendant : attendants) {
                if (invitee.is(attendant)) {
                    uninvitedAttendants.remove(attendant);
                    invitedAttendants.add(invitee.name);
                    hasAttended = true;
                    break;
                }
            }
            if (!hasAttended) {
                invitedNonAttendants.add(invitee.name);
            }
        }
    }

    List<String> invitedAttendants() {
        return this.invitedAttendants;
    }

    List<String> invitedNonAttendants() {
        return this.invitedNonAttendants;
    }

    List<String> uninvitedAttendants() {
        return this.uninvitedAttendants;
    }
}

class Invitee {

    String name;

    List<String> nicknames;

    public Invitee(String name, String... nicknames) {
        this.name = name;
        this.nicknames = Arrays.asList(nicknames);
    }

    boolean is(String nameOrNickname) {
        return name == nameOrNickname || nicknames.contains(nameOrNickname);
    }

}
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Managing invitation lists to parties and ceremonies are a hard thing, even
 * harder when they are located in Hollywood. There are people who say they are
 * going but don't, there are people who are not invited but insist attempting
 * getting in.
 * 
 * A very well known celebrity is paying a high amount of money for a system
 * that given a list of invitees and another list of people which attended to
 * the party may answer these questions.
 * 
 * Problem is every invitee has a name and some nicknames (represented as
 * strings) which they might use to enter the party. To help keep things under
 * control, lists for invited attendants and non attendants must return
 * his/her name. Uninvited guests must be listed with whatever name they
 * presented upon getting into the event.
 * 
 * There is no such pair of invitees that has exactly the same name, nor share
 * the same nickname. Not all invitees have a nickname, and there is no limit
 * to the number of nicknames one is known. If an invitee comes to the party,
 * you are sure that no one else will attempt to impersonate him/her using
 * his/her name or an unused nickname.
 */
public class InvitationListTest {
    
    @Test
    public void checkInvitedAttendantsByName() {
        Invitee invitee1 = new Invitee("Alex");
        Invitee invitee2 = new Invitee("Bruno", "Ortiz", "Dragon");
        Invitee invitee3 = new Invitee("Rafael", "Bolachinha");
        List<Invitee> invitees = asList(invitee1, invitee2, invitee3);
        List<String> attendants = asList("Alex", "Bruno");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedAttendants = list.invitedAttendants();
        assertTrue(invitedAttendants.contains("Alex"));
        assertTrue(invitedAttendants.contains("Bruno"));
    }
    
    @Test
    public void checkInvitedAttendantsByNickname() {
        Invitee invitee1 = new Invitee("Alex");
        Invitee invitee2 = new Invitee("Bruno", "Ortiz", "Dragon");
        Invitee invitee3 = new Invitee("Rafael", "Bolachinha");
        List<Invitee> invitees = asList(invitee1, invitee2, invitee3);
        List<String> attendants = asList("Bolachinha");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedAttendants = list.invitedAttendants();
        assertTrue(invitedAttendants.contains("Rafael"));
    }
    
    @Test
    public void checkInvitedAttendantsWhenNoOneIsInvited() {
        List<Invitee> invitees = emptyList();
        List<String> attendants = asList("José");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedAttendants = list.invitedAttendants();
        assertTrue(invitedAttendants.isEmpty());
    }
    
    @Test
    public void checkInvitedAttendantsWhenNoOneAttended() {
        List<Invitee> invitees = asList(
                new Invitee("Alexandre", "Bo"),
                new Invitee("Marcelo", "Moita"));
        List<String> attendants = emptyList();
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedAttendants = list.invitedAttendants();
        assertTrue(invitedAttendants.isEmpty());
    }
    
    @Test
    public void checkInvitedNonAttendants() {
        Invitee invitee1 = new Invitee("Alex");
        Invitee invitee2 = new Invitee("Bruno", "Ortiz", "Dragon");
        Invitee invitee3 = new Invitee("Rafael", "Bolachinha");
        List<Invitee> invitees = asList(invitee1, invitee2, invitee3);
        List<String> attendants = asList("Rafael", "Marcos");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedNonAttendants = list.invitedNonAttendants();
        assertTrue(invitedNonAttendants.contains("Alex"));
        assertTrue(invitedNonAttendants.contains("Bruno"));
    }
    
    @Test
    public void checkInvitedNonAttendantsByNickname() {
        Invitee invitee1 = new Invitee("Alex");
        Invitee invitee2 = new Invitee("Bruno", "Ortiz", "Dragon");
        Invitee invitee3 = new Invitee("Rafael", "Bolachinha");
        List<Invitee> invitees = asList(invitee1, invitee2, invitee3);
        List<String> attendants = asList("Bolachinha", "Bruno", "Dimitri");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedNonAttendants = list.invitedNonAttendants();
        assertTrue(invitedNonAttendants.contains("Alex"));
    }
    
    @Test
    public void checkInvitedNonAttendantsWhenNoOneIsInvited() {
        List<Invitee> invitees = emptyList();
        List<String> attendants = asList("José");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedNonAttendants = list.invitedNonAttendants();
        assertTrue(invitedNonAttendants.isEmpty());
    }
    
    @Test
    public void checkInvitedNonAttendantsWhenNoOneAttended() {
        Invitee invitee1 = new Invitee("Alexandre", "Bo");
        Invitee invitee2 = new Invitee("Marcelo", "Moita");
        List<Invitee> invitees = asList(invitee1, invitee2);
        List<String> attendants = emptyList();
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> invitedNonAttendants = list.invitedNonAttendants();
        assertTrue(invitedNonAttendants.contains("Alexandre"));
        assertTrue(invitedNonAttendants.contains("Marcelo"));
    }
    
    @Test
    public void checkUninvitedAttendants() {
        List<Invitee> invitees = asList(
                new Invitee("Alex"),
                new Invitee("Bruno", "Ortiz", "Dragon"),
                new Invitee("Rafael", "Bolachinha"));
        List<String> attendants = asList("Gabriel", "Alex", "Fabio");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> uninvitedAttendants = list.uninvitedAttendants();
        assertTrue(uninvitedAttendants.contains("Gabriel"));
        assertTrue(uninvitedAttendants.contains("Fabio"));
    }
    
    @Test
    public void checkUninvitedAttendantsByNickname() {
        Invitee invitee1 = new Invitee("Alex");
        Invitee invitee2 = new Invitee("Bruno", "Ortiz", "Dragon");
        Invitee invitee3 = new Invitee("Rafael", "Bolachinha");
        List<Invitee> invitees = asList(invitee1, invitee2, invitee3);
        List<String> attendants = asList("Alex", "André", "Bolachinha");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> uninvitedAttendants = list.uninvitedAttendants();
        assertTrue(uninvitedAttendants.contains("André"));
    }
    
    @Test
    public void checkUninvitedAttendantsWhenNoOneIsInvited() {
        List<Invitee> invitees = emptyList();
        List<String> attendants = asList("José", "Carolina");
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> uninvitedAttendants = list.uninvitedAttendants();
        assertTrue(uninvitedAttendants.contains("José"));
        assertTrue(uninvitedAttendants.contains("Carolina"));
    }
    
    @Test
    public void checkUninvitedAttendantsWhenNoOneAttended() {
        Invitee invitee1 = new Invitee("Alexandre", "Bo");
        Invitee invitee2 = new Invitee("Marcelo", "Moita");
        List<Invitee> invitees = asList(invitee1, invitee2);
        List<String> attendants = emptyList();
        InvitationList list = new InvitationList(invitees, attendants);

        List<String> uninvitedAttendants = list.uninvitedAttendants();
        assertTrue(uninvitedAttendants.isEmpty());
    }
    
}

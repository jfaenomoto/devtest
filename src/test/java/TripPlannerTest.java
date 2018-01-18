import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * Getting on track with finances while travelling might be tricky. You may plan
 * ahead the amount of money you will spend with food, transport, shopping, but
 * sometimes you need to spend a little more due to problems or unexpected
 * changes of plan.
 * 
 * You decided to make your own finance trip planner in order to not mess up with
 * the money you are taking with you on your next backpack trip. First, you will
 * fill the amount of money you plan to spend with each category. Then you start
 * filling your system with your expenses. You can check how much you can spend
 * for a given category and if you are on track with your plan.
 * 
 * Your financial plan might have 3 outcomes:
 * 
 *      - "on track": no category have more money spent than what was planned
 *      - "replan": you spent more money than planned on at least one category,
 *      but the total amount spent in all categories is equal or less than the
 *      sum of planned categories
 *      - "overspent": the sum of your spents is strictly greater than the sum of
 *      all plannings
 * 
 * Both planning and spents have a category (represented as a lower case string)
 * and an amount (a strictly integer number, you don't want to count pennies for
 * your trip). It's possible to spend in a category in which you did't plan:
 * virtually it's like you have planned to spend zero on that category. You may
 * spend on a given category zero to n times: everytime you spend an amount, the
 * category planning should be affected.
 */
public class TripPlannerTest {

    @Test
    public void checkExpenses() {
        List<Planning> plannings = asList(
                new Planning("hostel", 500),
                new Planning("food", 100));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("hostel", 100);
        
        assertEquals(400, planner.availableFor("hostel"));
    }
    
    @Test
    public void checkOverExpenses() {
        List<Planning> plannings = asList(
                new Planning("hostel", 500),
                new Planning("food", 100));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("food", 50);
        planner.spend("food", 150);
        
        assertEquals(-100, planner.availableFor("food"));
    }
    
    @Test
    public void checkUnexpectedExpenses() {
        List<Planning> plannings = asList(
                new Planning("hostel", 500),
                new Planning("food", 100));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("hospital", 300);
        
        assertEquals(-300, planner.availableFor("hospital"));
    }
    
    @Test
    public void checkPlanningOnTrack() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("food", 250);
        planner.spend("food", 150);
        
        assertEquals("on track", planner.checkPlanning());
    }
    
    @Test
    public void checkPlanningReplan() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("food", 450);
        planner.spend("plane tickets", 300);
        
        assertEquals("replan", planner.checkPlanning());
    }
    
    @Test
    public void checkPlanningReplanDueToNoPlanning() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("hotel", 250);
        planner.spend("taxes", 50);
        
        assertEquals("replan", planner.checkPlanning());
    }
    
    @Test
    public void checkPlanningOverspent() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("hotel", 350);
        planner.spend("food", 500);
        planner.spend("hotel", 1000);
        
        assertEquals("overspent", planner.checkPlanning());
    }
    
    @Test
    public void checkPlanningOverspentInOneThing() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("food", 3200);
        
        assertEquals("overspent", planner.checkPlanning());
    }

    
    @Test
    public void checkPlanningOverspentInNonPlanned() {
        List<Planning> plannings = asList(
                new Planning("hotel", 300),
                new Planning("food", 400),
                new Planning("plane tickets", 800));
        TripPlanner planner = new TripPlanner(plannings);
        planner.spend("hotel", 250);
        planner.spend("food", 300);
        planner.spend("hotel", 700);
        planner.spend("free shop", 500);
        
        assertEquals("overspent", planner.checkPlanning());
    }
}

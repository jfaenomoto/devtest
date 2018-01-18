import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripPlanner {
    
    private Map<String, Integer> planningPerCategory;
    
    private String planningStatus;
    
    private int plannedSpent;

    public TripPlanner(List<Planning> plannings) {
        this.planningPerCategory = new HashMap<>();
        this.plannedSpent = 0;
        for (Planning planning : plannings) {
            this.planningPerCategory.put(planning.category, planning.amount);
            this.plannedSpent += planning.amount;
        }
        this.planningStatus = "on track";
    }

    public void spend(String category, int amount) {
        int newTotal = -amount;
        if (this.planningPerCategory.containsKey(category)) {
            newTotal += this.planningPerCategory.get(category);
        }
        this.planningPerCategory.put(category, newTotal);
        if (newTotal < 0) {
            this.planningStatus = "replan";
        }
        this.plannedSpent -= amount;
        if (this.plannedSpent < 0) {
            this.planningStatus = "overspent";
        }
    }

    public int availableFor(String category) {
        if (this.planningPerCategory.containsKey(category)) {
            return this.planningPerCategory.get(category);
        }
        return 0;
    }

    public String checkPlanning() {
        return this.planningStatus;
    }

}

class Planning {
    
    String category;
    
    int amount;
    
    public Planning(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }
    
}
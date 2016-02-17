
/**
 * Class for CookingStation that describes a kitchen station.
 * 
 * @author Anshul
 *
 */

public class CookingStation extends CLList<CookingItem> implements CookingStationInterface {
    
    /** String of station name. **/
    public String stationname;
    
    /** 
     * Totals the penalty for this station.
     */
    private int penalty = 0;

    /**
     * Construct a station by providing a name as a string.
     * 
     * @param name Name of station (eg. Grill).
     */
    public CookingStation(String name) {
        this.stationname = name;
    }

    /**
     * Put a new dish at the end of the station.
     * 
     * @param it The dish to add
     */
    public void addItem(CookingItem it) {
        this.append(it);
    };

    /**
     * Simulate one minute time passing for this station.
     */
    public void tick() {
        int curr = this.currPos();
        this.moveToStart();
        for (int i = 0; i < this.length(); i++) {
            this.getValue().tick();
            this.next();
        }

        this.moveToPos(curr);
    };
    
    /**
     * Get the penalty from this kitchen. 
     *
     * @return Penalty from all dishes in this kitchen.
     * 
     */
    public int getPen() {
        return this.penalty;
    }

    /**
     * Tend the current item.
     * 
     * @param removeThreshold
     *            the number of minutes that may be used to determine if an item
     *            should be removed from the station.
     * @param penaltyThreshold
     *            the limit on the penalty value that may be used to determine
     *            if item should be removed from the station.
     * @return the item if you decide to remove it, or null otherwise
     */

    public CookingItem tend(int removeThreshold, int penaltyThreshold) {
        int time = this.getCurrValue().timeleft;

        if (time <= removeThreshold) {
            this.penalty += this.getCurrValue().penalty();
            
            System.out.println(this.getCurrValue().penalty());
            return this.removeThis();
            
        } else if (this.getCurrValue().penalty() <= penaltyThreshold) {
            this.penalty += this.getCurrValue().penalty();
            System.out.println(this.getCurrValue().penalty());
            return this.removeThis();
            
        } else {
            this.next();
            return null;
        }
        
        
    };

    @Override
    public String toString() {
        int pos = this.currPos();

        this.moveToStart();

        int stationlen = this.length();

        StringBuilder station = new StringBuilder();
        station.append(this.stationname + " [ ");
        for (int i = 0; i < stationlen; i++) {
            CookingItem curr = this.getCurrValue();
            station.append("(" + curr.getName() + " " + curr.timeleft + ") ");
            this.next();
        }
        station.append("]");

        this.moveToPos(pos);

        // System.out.println(station.toString());
        return station.toString();
    }

    /**
     * Main Method used to test class.
     * 
     * @param args Nothing used here.
     */

    public static void main(String[] args) {

//        CookingStation a = new CookingStation("Grill");
//        CookingStation b = new CookingStation("Stove");
//        CookingStation c = new CookingStation("Oven");
//        
//        a.append(new CookingItem("Steak", 4, 1, 2));
//        a.append(new CookingItem("Salmon", 5, 2, 1));
//        a.append(new CookingItem("Chicken", 30, 3, 1));
//        
//        b.append(new CookingItem("Pasta", 10, 1, 2));
//        b.append(new CookingItem("Sauce", 5, 2, 1));
//        b.append(new CookingItem("Ratatoulle", 30, 3, 1));
//        
//        c.append(new CookingItem("Bread", 30, 1, 2));
//        c.append(new CookingItem("Stuffing", 5, 2, 1));
//        c.append(new CookingItem("Yams", 30, 3, 1));
//       
        
//        a.tick();
//        a.tend(2,1);
//        a.toString();
//        
//        a.tick();
//        a.tend(2,1);
//        a.toString();
//        
//        a.tick();
//        a.tend(2,1);
//        a.toString();
//        
//        a.tick();
//        a.tend(2,1);
//        a.toString();
        

        

    }
}

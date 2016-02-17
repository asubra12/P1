
/**
 * Create a Cooking Item class that describes a particular meal on a cooking
 * station.
 * 
 * @author Anshul
 *
 */
public class CookingItem implements CookingItemInterface {
    
    /** Total time needed to cook. **/
    public int cookingtime;
    
    /** Penalty for underdone meals. **/
    public int underdonepenalty;
    
    /** penalty for overdone meals. **/
    public int overdonepenalty;
    
    /** Time left to cook completely. **/
    public int timeleft;
    
    /** String name of item. **/
    private String itemname; 
    
    
    
    /**
     * Constructs a Cooking Item.
     *
     * @param it Item name.
     * @param ct Total Cooking Time needed.
     * @param udp Underdone penalty. 
     * @param odp Overdone penalty.
     */
    public CookingItem(String it, int ct, int udp, int odp) {
        this.itemname = it;
        this.cookingtime = ct;
        this.underdonepenalty = udp;
        this.overdonepenalty = odp;
        this.timeleft = ct;
    }
    
    /** Decrements cooking time by one minute.
     */
    public void tick() {
        this.timeleft -= 1;

    }
    
    /** Get the time remaining for cooking this dish.
     *  @return the time in minutes
     */
    public int timeRemaining() {
        return this.timeleft;
    }
    
    /** Calculate the penalty if this dish were removed now.
     *  @return the penalty
     */
    public int penalty() {
        if (this.timeleft < 0) {
            int pen = this.timeleft * this.overdonepenalty * -1;
            return pen;
        } else if (this.timeleft > 0) {
            int pen = this.timeleft * this.underdonepenalty;
            return pen;
        } else {
            return 0;
        }
    }
    
    /**
     * Get the name of the cooking item.
     * @return String representation of the name of the CookingItem.
     */
    public String getName() {
        return this.itemname;
    }

}
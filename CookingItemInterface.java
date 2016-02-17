/**
 * Interface for CookingItem.
 * @author Anshul
 *
 */

interface CookingItemInterface {
    /** Decrements cooking time by one minute.
     */
    void tick(); 

    /** Get the time remaining for cooking this dish.
     *  @return the time in minutes
     */
    int timeRemaining();

    /** Calculate the penalty if this dish were removed now.
     *  @return the penalty
     */
    int penalty(); 
}

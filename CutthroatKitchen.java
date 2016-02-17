import java.util.Scanner;

/**
 * CutthroatKitchen class that manages CookingStations, CookingItems,
 * total penalties, etc.
 * 
 * @author Anshul
 *
 */

public class CutthroatKitchen extends CLList<CookingStation> {
    
    /**
     * Totals the penalty from each station.
     * 
     */
    private static int totpen = 0;
 
    /**
     * Add CookingStation to the CutthroatKitchen.
     * Largely unnecessary because .append() from the CLL class does the same 
     * thing
     * 
     * @param cs The Station to add
     */
    public void addStation(CookingStation cs) {
        this.append(cs);
    }
    
    /**
     * Go through all CookingItems at all CookingStations and decrease the
     * time left to cook by 1.
     */
    public void tickAll() {
        for (int i = 0; i < this.length(); i++) {
            this.getCurrValue().tick();
            this.next();
        }
    }
    
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int a = this.currPos();
    
        this.moveToStart();
        
        for (int i = 0; i < this.length(); i++) {
//            if (i == a) {
//                str.append("->" + (this.getCurrValue().currPos() + 1) + " ");
//                
//            }
            str.append(this.getCurrValue().toString() + "  ");
            this.next();
        }
        
        this.moveToPos(a);
        
        System.out.println(str.toString());// + "\n");
        return str.toString();
    }
    
    public static void runKitchen(CutthroatKitchen fuckthis, int removeThreshold, int penaltyThreshold) {
        while (fuckthis.length() > 0) {
            int removeFlag = 0;
            
            fuckthis.toString();
            
            fuckthis.tickAll();
            
            fuckthis.getCurrValue().tend(removeThreshold, penaltyThreshold);
            if (fuckthis.getCurrValue().length() == 0) {
                totpen += fuckthis.getCurrValue().getPen();
                fuckthis.removeThis();
                removeFlag = 1;
                
            }
            
            if (fuckthis.length() > 0 && removeFlag == 0) {
                fuckthis.next();
            }
        }
        
        System.out.println("Final penalty: " + totpen);
    }
    /**
     * Main function to test Cutthroat Kitchen class. 
     * 
     * Reads input file, creates cutthroat kitchen object
     * that has the desired stations and cookingitems, and iterates
     * through such that the 'chef' checks and rotates among dishes
     * until nothing is left.
     * 
     * @param args Inputted stations, dishes, in specific format.
     */
    public static void main(String[] args) {
        Scanner infile = ReadFile.catchException(args[0]);
        CutthroatKitchen fuckthis = ReadFile.makeKitchen(infile);
       
        for (i=1:4)
        int removeThreshold = 1; //Integer.parseInt(args[1]);
        int penaltyThreshold = 1; //Integer.parseInt(args[2]);
       
        runKitchen(fuckthis, removeThreshold, penaltyThreshold);
        
//      CutthroatKitchen fuckthis = new CutthroatKitchen();
//      
//      CookingStation a = new CookingStation("Grill");
//      CookingStation b = new CookingStation("Stove");
//      CookingStation c = new CookingStation("Oven");
//      
//      a.append(new CookingItem("Steak", 10, 1, 2));
//      a.append(new CookingItem("Salmon", 5, 2, 1));
//      a.append(new CookingItem("Chicken", 30, 3, 1));
//      
//      b.append(new CookingItem("Pasta", 10, 1, 2));
//      b.append(new CookingItem("Sauce", 5, 2, 1));
//      b.append(new CookingItem("Ratatoulle", 30, 3, 1));
//      
//      c.append(new CookingItem("Bread", 30, 1, 2));
//      c.append(new CookingItem("Stuffing", 5, 2, 1));
//      c.append(new CookingItem("Yams", 30, 3, 1));
//      
//      fuckthis.append(a);
//      fuckthis.append(b);
//      fuckthis.append(c);
        
//        while (fuckthis.length() > 0) {
//            int removeFlag = 0;
//            
//            fuckthis.toString();
//            
//            fuckthis.tickAll();
//            
//            fuckthis.getCurrValue().tend(removeThreshold, penaltyThreshold);
//            if (fuckthis.getCurrValue().length() == 0) {
//                totpen += fuckthis.getCurrValue().getPen();
//                fuckthis.removeThis();
//                removeFlag = 1;
//                
//            }
//            
//            if (fuckthis.length() > 0 && removeFlag == 0) {
//                fuckthis.next();
//            }
//        }
//        
//        System.out.println("Final penalty: " + totpen);
    }
}


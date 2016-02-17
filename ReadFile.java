import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
// import java.util.ArrayIndexOutOfBoundsException;
/**
 * Class to read in a file.
 * @author AurinC
 *
 */
public final class ReadFile {
    /**
     * ReadFile constructor.
     */
    private ReadFile() {
        
    }
    /**
     * Main class for reading in files.
     * @param args Arguments to pass into command line.
     * @throws IOException Throws exception based on input/output.
     */
    
   
    public static Scanner catchException(String file) {
        Scanner infile = null;
        boolean inerror = false;

        try {
            System.out.println("0 " + file + " should be input filename");
            infile = new Scanner(new FileReader(file));
        } catch (ArrayIndexOutOfBoundsException a) {
            System.err.println("must give input filename at command line");
            inerror = true;
        } catch (IOException f) {
            System.err.println("can't open that file, try again");
            inerror = true;
        }
        if (inerror) {
            System.err.println("exiting...");
            System.exit(1);
        }
        return infile;
    }
    
    public static CutthroatKitchen makeKitchen(Scanner infile) {
        CutthroatKitchen CK = new CutthroatKitchen();
        
        Scanner inline;
        String line;
        String name, item;
        int time, under, over;
        //int stationCount = 0;
        
        while (infile.hasNext()) {
            name = infile.nextLine();
            CookingStation nextStation = new CookingStation(name);
            //stationCount++;
            line = infile.nextLine();
            
            while (!line.equals("")) {
                inline = new Scanner(line);
                item = inline.next();
                time = inline.nextInt();
                under = inline.nextInt();
                over = inline.nextInt();
                
                nextStation.append(new CookingItem(item, time, under, over));
                
                // name += " " + item + " " + time + " " + under + " " + over;
                line = infile.nextLine();
            }
            
            CK.addStation(nextStation);
            
        }
        //System.out.println("Start Station: " + CK.toString());
        return CK;
    }
    
    public static void main(String[] args) throws IOException {
        
        Scanner infile = catchException(args[0]);

        CutthroatKitchen testKitchen = makeKitchen(infile);

        
        
        
        
        
        
        
        
        
//      Scanner infile = null;
//      boolean inerror = false;
//
//      try {
//          System.out.println("0 " + args[0] + " should be input filename");
//          infile = new Scanner(new FileReader(args[0]));
//      } catch (ArrayIndexOutOfBoundsException a) {
//          System.err.println("must give input filename at command line");
//          inerror = true;
//      } catch (IOException f) {
//          System.err.println("can't open that file, try again");
//          inerror = true;
//      }
//      if (inerror) {
//          System.err.println("exiting...");
//          System.exit(1);
//      }
//        Scanner inline;
//        String line;
//        String name, item;
//        int time, under, over;
//        //int stationCount = 0;
//        while (infile.hasNext()) {
//            name = infile.nextLine();
//            //stationCount++;
//            line = infile.nextLine();
//            while (!line.equals("")) {
//                inline = new Scanner(line);
//                item = inline.next();
//                time = inline.nextInt();
//                under = inline.nextInt();
//                over = inline.nextInt();
//                name += " " + item + " " + time + " " + under + " " + over;
//                line = infile.nextLine();
//            }
//            System.out.println(name);
//        }
       // CookingStation[] stations = new CookingStation [stationCount];
        
        

    }
}
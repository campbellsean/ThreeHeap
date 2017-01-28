import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyClient {

    public static void main(String[] args) throws InterruptedException  {
        System.out.println("Welcome to the CSE 373 ThreeHeap Test Program!");
        System.out.println();
        
        PriorityQueue s = new ThreeHeap();
        
        // s.deleteMin(); Checked for EmptyHeapException
     
        // Testing buildQueue method by adding 1000000 doubles
        // and then removing most and checking the end of the 
        
        
        List<Double> list = new ArrayList<Double>();
        for (int i = 1000; i > 0; i--) {
            double j = i;
            list.add(j);
        }        
        s.buildQueue(list);
        System.out.println("Size: " + s.size());
        /*
        for (int i = 0; i < 100; i++) {
            System.out.println(s.deleteMin());
            // Checking the first 10 deleteMin's
        }
        
        System.out.println("BREAK: Now checking end!");
        
        /*
        for (int i = 9800; i > 0; i--) {
            s.deleteMin();
            // Needed for this to run without crashing.
        }
        */
                
        while(!s.isEmpty()) {
            System.out.println(s.deleteMin());
            //Thread.sleep(1000);
        }       
        
        /*
        
        System.out.println("BREAK: Testing random number insertion and deletion :: ");
        System.out.println();
        // Testing insert method and deleteMin method
        Random randy = new Random();
        for (int i = 0; i < 1000; i++) {
            int val = randy.nextInt(1000);
            System.out.println(val);
            s.insert(val);
        }
        System.out.println("MIDDLE");
        while(!s.isEmpty()) {
            System.out.println(s.deleteMin());
        }       
        */  

        
    }
}

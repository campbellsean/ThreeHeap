import java.util.Arrays;
import java.util.List;

// Sean Campbell
// Assignment #2 : Three Heap
// January 27, 2017
// CSE 373

public class ThreeHeap implements PriorityQueue{
    
    private int size;
    private double[] elements;
    private static final int INITIAL_ARRAY_SIZE = 10;

    @Override
    /**
     * Returns true if the ThreeHeap is empty, otherwise false.
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * Returns the size of the ThreeHeap
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * If the ThreeHeap is empty then an EmptyHeapException will be thrown.
     * Returns the minimum value in the ThreeHeap
     */
    public double findMin() {
        if (this.isEmpty()) {
            throw new EmptyHeapException();
        }
        return this.elements[1];
    }

    @Override
    /**
     * @param double x is the value that will be inserted into the ThreeHeap
     */
    public void insert(double x) {
        // add the new value at the next valid place in the structure
        // fixes the heap order property by percolating value up to the correct
        // spot
        if (elements == null) {
            this.elements = new double[INITIAL_ARRAY_SIZE];
        } else if (this.elements.length - 1 == this.size) {
            // array is full, so we copy to an array that is twice the size
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
        this.size++;
        int index = this.percolateUp(x, this.size);
        this.elements[index] = x;
    }
    
    /**
     * @param val is the int that must be rearranged to fix the heap ordering
     * property
     */
    private int percolateUp(double val, int hole) {
        while (hole > 1 && val < this.elements[(hole + 1) / 3]) {
            this.elements[hole] = this.elements[(hole + 1) / 3];
            hole = (hole + 1) / 3;
        }
        return hole;        
    }
 
    @Override
    /**
     * Pre: If the ThreeHeap is empty then an EmptyHeapException
     * will be thrown.
     * 
     * Post: The minimum value from the heap will be returned and the
     * heap will change accordingly.
     */
    public double deleteMin() {
        if (this.isEmpty()) {
            throw new EmptyHeapException();
        }
        // remove the smallest value from the root
        double ans = this.elements[1];
        // fix the ordering property by percolating the value down to the 
        // right position.
        int hole = this.percolateDown(this.elements[this.size], 1);     
        this.elements[hole] = this.elements[this.size];
        // adjust the size accordingly
        this.size--;
        return ans;
    }
    
    /**
     * @param val is the double that needs to be rearranged to fix the heap
     * property.
     */
    private int percolateDown(double val, int hole) {
        while (this.leftIndex(hole) <= this.size) {
            int left = this.leftIndex(hole);
            int middle = this.middleIndex(hole);
            int right = this.rightIndex(hole);
            int target;
            
            if (middle > this.size) {
                // left is only possible choice
                target = left;
            } else if (middle < this.size && right > this.size) {
                // middle or right are the possible choices
                if (this.elements[middle] <= this.elements[left]) {
                    target = middle;
                } else {
                    target = left;
                }
            } else {
                // left, middle, and right are possible choices for trade
                if (this.elements[left] <= this.elements[middle] && 
                        this.elements[left] <= this.elements[right]) {
                    target = left;
                } else if (this.elements[middle] <= this.elements[left] &&
                        this.elements[middle] <= this.elements[right]) {
                    target = middle;
                } else {
                    target = right;
                }
            }
            // The target index value is set, now we compare to the double
            // val that was passed in and trade.
            if (this.elements[target] <= val) {
                this.elements[hole] = this.elements[target];
                hole = target;
            } else {
                break;
            }
        }
        return hole;
    }
    
    /**
     * the following methods:
     * @param hole gives the method the int value to compute a function
     * @returns an int value after computing the correct index
     */
    private int leftIndex(int hole) {
        return hole * 3 - 1;
    }
    
    private int middleIndex(int hole) {
        return hole * 3;
    }
    
    private int rightIndex(int hole) {
        return hole * 3 + 1;
    }

    @Override
    /**
     * Builds a ThreeHeap with heap properties given the passed List<Double>
     * list.
     */
    public void buildQueue(List<Double> list) {
        this.makeEmpty();
        // Make a new array the size of the list
        this.elements = new double[list.size() + 1];
        this.size = this.elements.length - 1; // is this right? TEST IT!
        // Randomly place all elements into the array

        for (int i = 1; i < this.elements.length; i++) {
            this.elements[i] = list.get(i - 1);
        }
        // Floyds Method:
        for (int i = (this.size + 1) / 3; i > 0; i--) {
            double val = this.elements[i];
            int hole = this.percolateDown(val, i);
            this.elements[hole] = val; // why is this needed?
        }      
    }

    @Override
    /**
     * Clears the ThreeHeap of all values.
     */
    public void makeEmpty() {
        this.size = 0;
        this.elements = null;
    }
}
/**
 * Circular Linked List implementation of the List interface.
 * Uses sentinel nodes at the head & tail, and an inner Node class.
 * This is only a partial implementation, loosely based on OpenDSA version.
 * @author Joanne Selinski
 * @param <T> the type of the List
 */


public class CLList<T> implements List<T> {

    /**
     * Inner doubly linked Node class for convenience.
     * Note that the generic type is implied since we are within DLList<T>.
     */
    public class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;
        

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
        }
    }

    /** Head sentinel node. */
    private Node head;
    /** Tail sentinel node. */
    private Node tail;
    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;

    /**
     * Create an empty list with sentinels.
     */
    public CLList() {
        this.clear();  // code reuse!
    }
    
    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.curr = null; 
        this.head = null;
        this.tail = null;
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        if (this.size == 0) {
            Node n = new Node(t, null, null);
            n.next = n;
            n.prev = n;
            this.curr = n;
            this.head = n;
            this.tail = n.prev;
            
        } else if (this.size == 1) {
            Node n = new Node(t, this.curr, this.curr);
            n.prev.next = n;
            n.next.prev = n;
            this.tail = n;
//            n.next = this.curr
        
        } else {
            Node n = new Node(t, this.curr, this.curr.next);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            
            if (n.next == this.head) {
                this.tail = n;
            }
        }
        
       
        this.size++;
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor). 
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.size == 0) {
            return null;
        } else if (this.size == 1) {
            T val = this.curr.data;
            this.clear();
            return val;
        } else {
            if (this.curr.next == this.tail) {
                this.tail = this.curr;
            }
            if (this.curr.next == this.head) {
                this.head = this.curr.next.next;
            }
            T val = this.curr.next.data;
            this.curr.next = this.curr.next.next; // bypass node being deleted
            this.curr.next.prev = this.curr;  // bypass it in other direction
            this.size--;

            return val; 
        }
    }
    
    /**
     * Removes the node that the cursor is currently pointing to.
     *
     * @return The removed node.
     */
    public T removeThis() {
        if (this.size == 0) {
            return null;
        } else if (this.size == 1) {
            T val = this.curr.data;
            this.clear();
            return val;
        } else {
            if (this.curr == this.tail) {
                this.tail = this.curr.prev;
            } 
            if (this.curr == this.head) {
                this.head = this.curr.next;
            }
            
            T val = this.curr.data;
            this.curr.next.prev = this.curr.prev;
            this.curr.prev.next = this.curr.next;
            this.size--;
            
            this.curr = this.curr.next;
            
            return val;
        }
    }
    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        return this.curr.next.data;
    }
    
    /**
     * Get the current value of the node the cursor is pointing to.
     * @return Data of current node.
     */
    public T getCurrValue() {
        return this.curr.data;
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }
    
    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        if (this.length() == 0) {
            return this.insert(t);
        } else {
            Node temp = this.curr;        // hold onto original position
            this.curr = this.tail;   // move to before the tail sentinel
            this.insert(t);               // code reuse!
            this.curr = temp;             // restore cursor to original position
            return true;
        }
    }
    
    @Override
    public String toString() {
        int len = this.length();
        
        if (len == 0) {
            return "This is an empty string!";
        }
        
        StringBuilder content = new StringBuilder();
        content.append("Contents of CList including Head at end are: (");
        
        Node temp = this.curr; // Keep current node saved
        this.curr = this.head;
        for (int i = 0; i <= len; i++) { //change to < instead of <= 
            
            Object nodeData = this.curr.data;
            
            content.append(nodeData);
            content.append(", ");
            this.curr = this.curr.next;
            
        }
        
        content.append(")");
        this.curr = temp;
        return content.toString();
    }

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
        this.curr = this.head;
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
        this.curr = this.tail;
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
        this.curr = this.curr.prev;
    }

    /**
     * Move the current position one step right, no change if already at end.
     */
    public void next() {
        this.curr = this.curr.next;
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        // dummy implementation
        Node temp = this.curr;
        this.curr = this.head;
        int loc = 0;
        
        for (int i = 0; i < this.length(); i++) {
            if (this.curr == temp) {
                loc = i;
                break;
            }
            this.curr = this.curr.next;
        }
        return loc;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        // dummy implementation
        if (pos >= this.length()) {
            System.out.println("Position out of range!");
            return false;
        }
        
        this.curr = this.head;
        for (int i = 1; i <= pos; i++) {
            this.curr = this.curr.next;
        }
        return true;
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        return this.curr == this.tail;
    }
    
    
    /**
     * Main Class for CLList. Used for debugging and testing.
     * 
     * @param args Nothing inputted.
     */
    public static void main(String[] args) {
        CLList<String> cList = new CLList<String>();
        cList.insert("one");
        cList.append("end");
        cList.append("end2");
        cList.append("end3");
        System.out.println(cList);
        cList.remove();
        System.out.println(cList.toString());
        
        
        
        
    }

}

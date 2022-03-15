import java.util.EmptyStackException;

/**
 * An implementation of an Integer Stack ADT with a O(1) time complexity for
 * functions of adding and removing integer elements from stack, the retrieval of the topmost element
 * of the stack, and the retrieval of the smallest value in the stack.
 * Getting the minimum of the stack, for best and worst case, is O(1)
 * @author Ivan Sy
 */
public class MinStack {

    /*
     A node implementation containing a prev link and a next link
     */
    class Node {
        private int val;
        private Node prev;
        private Node next;

        /**
         * initializes a link node that will hold the specified value
         * @param val data the node will hold
         */
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    /*
      There are three nodes utilized in this stack:
      1.) head Node - stores the values of the elements in the order where they are added, with the
                      first pointer pointing to the element recently added
      2.) frontMinValue - front pointer of a data structure used to retrieve the min value
      3.) rearMinValue -  rear pointer of a data structure used to retrieve the min value

      - head node will contain the elements of the stack in the Last-In-First-Out principle
      - frontMinValue is the head node of the auxiliary structure that will contain the smallest integer value in either the front of the stack
        or in the rear of the stack, which is represented by rearMinValue node
     */
    private Node head, frontMinValue, rearMinValue;
    private int count;

    /**
     * returns true whether the stack is empty, false otherwise
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * returns the stack data elements in string format, with the most recently added element at the
     * first all the way to the first element added
     * @return the stack elements in the order of which elements was most recently added
     */
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            String result = "";
            Node currentNode = head;
            while (currentNode != null) {
                result += ", "  + currentNode.val;
                currentNode = currentNode.next;
            }
            /*
            IF you want to test and check the elements of the second data structure in the stack
            below
             */
//            String test = "";
//            Node testNode = frontMinValue;
//            while (testNode != null) {
//                test += ", " + testNode.val;
//                testNode = testNode.next;
//            }
//            System.out.println(test.substring(2));
            return result.substring(2);
        }
    }

    /**
     * adds integer element to the top of the stack
     * @param elem the element to be added to the top of the stack
     */
    public void push(int elem) {
        Node temp = new Node(elem);
        if (isEmpty()) {
            head = temp;
            Node minTest = new Node(elem);
            frontMinValue = minTest;
            rearMinValue = minTest;
        } else {
            temp.next = head;
            head = temp;
            verifyMinValue(elem);
        }
        count++;
    }

    //a value added to the stack will be stored in the auxiliary data structure
    private void verifyMinValue(int elem) {
        Node temp = new Node(elem);
        /*
           IF the specified value is less than the value to the value stored at frontMinValue,
           add it to the beginning of the auxiliary structure
         */
        if (frontMinValue.val > temp.val) {
            frontMinValue.prev = temp;
            temp.next = frontMinValue;
            frontMinValue = temp;
        } else {
            /*
              otherwise, add it to the end of the auxiliary structure
             */
            temp.prev = rearMinValue;
            rearMinValue.next = temp;
            rearMinValue = rearMinValue.next;
        }
    }

    /**
     * removes and returns the most recently added element of the stack
     * @return the most recently added element of the stack
     */
    public int pop() {
        if (isEmpty())
            throw new EmptyStackException();

        int result = head.val;
        if (count == 1) {
            head = null;
        } else {
            head = head.next;
            /*
             if the integer value that was popped was the minimum value of the stack,
             the stack must still have the next minimum value available for access
             */
            if (result == frontMinValue.val) {
                frontMinValue = frontMinValue.next;
                frontMinValue.prev = null;
            } else if (result == rearMinValue.val) {
                rearMinValue = rearMinValue.prev;
                rearMinValue.next = null;
            }
        }
        count--;
        return result;
    }

    /**
     * returns the most recently added element of the stack
     * Note: not remove
     * @return the most recently added element of the stack
     */
    public int peek() {
        if (isEmpty()) throw new EmptyStackException();
        else
            return head.val;
    }

    /**
     * returns the smallest value between the elements in the stack
     * @return the smallest value between the elements in the stack
     */
    public int getMin() {
        if (isEmpty()) throw new EmptyStackException();
        if (frontMinValue.val > rearMinValue.val) return rearMinValue.val;
        else                                      return frontMinValue.val;
        /*
         the smallest values of the stack will always be in either the front or the rear of the auxiliary data structure
         */
    }
}